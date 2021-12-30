package com.tencent.sms;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class SendSms {
    private static final String SECRET_ID = "AKIDkHYZKp3A5nGTR9cpoZ5NQDm59xVoreDX";
    private static final String SECRET_KEY = "HL9j48vXswjT8lDPM3VhfOr47vUmpeDX";
    private static final String ENDPOINT = "sms.tencentcloudapi.com";
    public static final String REGION = "ap-nanjing";
    public static final String SMS_SDK_APP_ID = "1400617781";
    public static final String TEMPLATE_ID = "1260653";
    public static final String SignMethod = "HmacSHA256";
    public static final String ReqMethod = "POST";
    private static final Charset UTF8 = StandardCharsets.UTF_8;
    private static final String protocol = "https://";


    public static void main(String[] args) {
        sendSms("18662407410", "666666");
//        sendSms("13914084606", "888888");
    }

    private static void sendSms(String phoneNumber, String templateParam) {
        try {
            SendSmsRequest req = new SendSmsRequest();
            String[] phoneNumberSet1 = {phoneNumber};
            req.setPhoneNumberSet(phoneNumberSet1);

            req.setSmsSdkAppId(SMS_SDK_APP_ID);
            req.setTemplateId(TEMPLATE_ID);

            String[] templateParamSet1 = {templateParam};
            req.setTemplateParamSet(templateParamSet1);

            HttpResponse response = new SendSms().doRequest(req);
            String string = EntityUtils.toString(response.getEntity());
            System.out.println(string);
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            System.out.println(e.toString());
        }
    }

    private HttpResponse doRequest(SendSmsRequest request) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        HashMap<String, String> param = new HashMap<>();
        request.toMap(param);
        String strParam = this.formatRequestData(param);
        String url = protocol + SendSms.ENDPOINT + "/";

        // 1、创建 client 实例
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 2、创建 post 实例
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        post.setEntity(new StringEntity(strParam));
        return httpclient.execute(post);
    }

    private String formatRequestData(Map<String, String> param) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        param.put("Action", "SendSms");
        param.put("Nonce", String.valueOf(Math.abs((new SecureRandom()).nextInt())));
        param.put("Timestamp", String.valueOf(System.currentTimeMillis() / 1000L));
        param.put("Version", "2021-01-11");
        param.put("SecretId", SECRET_ID);
        param.put("Region", REGION);
        param.put("SignatureMethod", SignMethod);
        param.put("Language", "en-US");


        String sigInParam = makeSignPlainText(new TreeMap<>(param), ReqMethod, ENDPOINT);
        String sigOutParam = sign(SECRET_KEY, sigInParam, SignMethod);
        StringBuilder strParam = new StringBuilder();


        for (Map.Entry<String, String> entry : param.entrySet()) {
            strParam.append(URLEncoder.encode(entry.getKey(), "utf-8"))
                    .append("=")
                    .append(URLEncoder.encode(entry.getValue(), "utf-8"))
                    .append("&");
        }
        strParam.append("Signature=")
                .append(URLEncoder.encode(sigOutParam, "utf-8"));
        return strParam.toString();

    }

    private static String sign(String secretKey, String sigStr, String sigMethod) throws InvalidKeyException, NoSuchAlgorithmException {
        String sig;
        Mac mac = Mac.getInstance(sigMethod);
        SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(UTF8), mac.getAlgorithm());
        mac.init(secretKeySpec);
        byte[] hash = mac.doFinal(sigStr.getBytes(UTF8));
        sig = DatatypeConverter.printBase64Binary(hash);
        return sig;

    }

    private static String makeSignPlainText(TreeMap<String, String> requestParams, String reqMethod, String host) {
        String retStr = "";
        retStr = retStr + reqMethod;
        retStr = retStr + host;
        retStr = retStr + "/";
        retStr = retStr + buildParamStr(requestParams);
        return retStr;
    }

    private static String buildParamStr(TreeMap<String, String> requestParams) {
        StringBuilder retStr = new StringBuilder();
        String key;
        String value;
        for (Iterator<String> var3 = requestParams.keySet().iterator(); var3.hasNext(); retStr.append(key.replace("_", ".")).append('=').append(value)) {
            key = var3.next();
            value = requestParams.get(key);
            if (retStr.length() == 0) {
                retStr.append('?');
            } else {
                retStr.append('&');
            }
        }
        return retStr.toString();
    }
}