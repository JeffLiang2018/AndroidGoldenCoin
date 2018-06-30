package com.example.jeffliang.coen268finalproject.entities;

public class SettingItem {

    public final static String TBL_NAME = "setting";
    public final static String COL_EMAIL = "email";
    public final static String COL_SETTING_ITEM = "setting_item";
    public final static String COL_SETTING_VALUE = "setting_value";
    public final static String COL_LAST_UPD_DT = "last_upd_dt";

    public final static String ITEM_AUTHENTICATION = "AUTHENTICATION";
    public final static String ITEM_REMEMBER_EMAIL = "REMEMBER_EMAIL";
    public final static String ITEM_REMEMBER_PASSWORD = "REMEMBER_PASSWORD";
    public final static String ITEM_DEFAULT_CARD = "DEFAULT_CARD";
    public final static String ITEM_AUTO_TRAF_TO_GC = "AUTO_TRAF_TO_GC";
    public final static String ITEM_AUTO_TRAF_TO_BANK = "AUTO_TRAF_TO_BANK";

    private String email;
    private String setting_item;
    private String setting_value;
    private String last_upd_dt;

    public SettingItem() {
    }

    public SettingItem(String email, String setting_item, String setting_value, String last_upd_dt) {
        this.email = email;
        this.setting_item = setting_item;
        this.setting_value = setting_value;
        this.last_upd_dt = last_upd_dt;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSetting_item() {
        return setting_item;
    }

    public void setSetting_item(String setting_item) {
        this.setting_item = setting_item;
    }

    public String getSetting_value() {
        return setting_value;
    }

    public void setSetting_value(String setting_value) {
        this.setting_value = setting_value;
    }

    public String getLast_upd_dt() {
        return last_upd_dt;
    }

    public void setLast_upd_dt(String last_upd_dt) {
        this.last_upd_dt = last_upd_dt;
    }

    @Override
    public String toString() {
        String result = "SettingItem Record values: \n"
                 + "Email: " + this.email + "\n"
                 + "SettingItem Item: " + this.setting_item + "\n"
                 + "SettingItem Value: " + this.setting_value;
        return result;
    }
}
