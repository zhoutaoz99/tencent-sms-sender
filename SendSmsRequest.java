//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.tencent.sms;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.HashMap;

public class SendSmsRequest {
    @SerializedName("PhoneNumberSet")
    @Expose
    private String[] PhoneNumberSet;
    @SerializedName("SmsSdkAppId")
    @Expose
    private String SmsSdkAppId;
    @SerializedName("TemplateId")
    @Expose
    private String TemplateId;
    @SerializedName("SignName")
    @Expose
    private String SignName;
    @SerializedName("TemplateParamSet")
    @Expose
    private String[] TemplateParamSet;
    @SerializedName("ExtendCode")
    @Expose
    private String ExtendCode;
    @SerializedName("SessionContext")
    @Expose
    private String SessionContext;
    @SerializedName("SenderId")
    @Expose
    private String SenderId;

    public String[] getPhoneNumberSet() {
        return this.PhoneNumberSet;
    }

    public void setPhoneNumberSet(String[] PhoneNumberSet) {
        this.PhoneNumberSet = PhoneNumberSet;
    }

    public String getSmsSdkAppId() {
        return this.SmsSdkAppId;
    }

    public void setSmsSdkAppId(String SmsSdkAppId) {
        this.SmsSdkAppId = SmsSdkAppId;
    }

    public String getTemplateId() {
        return this.TemplateId;
    }

    public void setTemplateId(String TemplateId) {
        this.TemplateId = TemplateId;
    }

    public String getSignName() {
        return this.SignName;
    }

    public void setSignName(String SignName) {
        this.SignName = SignName;
    }

    public String[] getTemplateParamSet() {
        return this.TemplateParamSet;
    }

    public void setTemplateParamSet(String[] TemplateParamSet) {
        this.TemplateParamSet = TemplateParamSet;
    }

    public String getExtendCode() {
        return this.ExtendCode;
    }

    public void setExtendCode(String ExtendCode) {
        this.ExtendCode = ExtendCode;
    }

    public String getSessionContext() {
        return this.SessionContext;
    }

    public void setSessionContext(String SessionContext) {
        this.SessionContext = SessionContext;
    }

    public String getSenderId() {
        return this.SenderId;
    }

    public void setSenderId(String SenderId) {
        this.SenderId = SenderId;
    }

    public SendSmsRequest() {
    }

    public void toMap(HashMap<String, String> map) {
        this.setParamArraySimple(map, "PhoneNumberSet.", this.PhoneNumberSet);
        this.setParamSimple(map, "SmsSdkAppId", this.SmsSdkAppId);
        this.setParamSimple(map, "TemplateId", this.TemplateId);
        this.setParamSimple(map, "SignName", this.SignName);
        this.setParamArraySimple(map, "TemplateParamSet.", this.TemplateParamSet);
        this.setParamSimple(map, "ExtendCode", this.ExtendCode);
        this.setParamSimple(map, "SessionContext", this.SessionContext);
        this.setParamSimple(map, "SenderId", this.SenderId);
    }

    private <V> void setParamArraySimple(HashMap<String, String> map, String prefix, V[] array) {
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                this.setParamSimple(map, prefix + i, array[i]);
            }
        }
    }

    private <V> void setParamSimple(HashMap<String, String> map, String key, V value) {
        if (value != null) {
            key = key.substring(0, 1).toUpperCase() + key.substring(1);
            key = key.replace("_", ".");
            map.put(key, String.valueOf(value));
        }

    }
}
