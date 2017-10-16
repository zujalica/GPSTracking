package zuja.buzz.gpstracking.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.text.TextUtils;

import java.util.Locale;

import zuja.buzz.gpstracking.Constants;

@Entity(tableName = "settings")
public class SettingsModel {

    @Ignore
    private Locale locale;

    @PrimaryKey
    private int settingsId = Constants.DEFAULT_SETTINGS_ID;
    private String localeLanguage;
    private String localeCountry;

    @Ignore
    public SettingsModel() {
    }

    @Ignore
    public SettingsModel(Locale locale) {
        this.locale = locale;
        this.localeLanguage = locale.getLanguage();
        this.localeCountry = locale.getCountry();
    }

    public SettingsModel(String localeLanguage, String localeCountry) {
        if(!TextUtils.isEmpty(localeLanguage)){
            this.localeLanguage = Constants.LOCALE_LANGUAGE_ENGLISH;
            this.localeCountry = Constants.LOCALE_COUNTRY_USA;
        }
        updateLocale(localeLanguage, localeCountry);
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
        updateLocaleStrings(locale);
    }

    public String getLocaleLanguage() {
        return localeLanguage;
    }

    public void setLocaleStrings(String localeLanguage, String localeCountry) {
        this.localeLanguage = localeLanguage;
        this.localeCountry = localeCountry;
        updateLocale(localeLanguage, localeCountry);
    }

    public int getSettingsId() {
        return settingsId;
    }

    public void setSettingsId(int ettingsId) {
        this.settingsId = ettingsId;
    }

    public void setLocaleLanguage(String localeLanguage) {
        this.localeLanguage = localeLanguage;
    }

    public String getLocaleCountry() {
        return localeCountry;
    }

    public void setLocaleCountry(String localeCountry) {
        this.localeCountry = localeCountry;
    }

    private void updateLocale(String localeLanguage, String localeCountry){
        if(!TextUtils.isEmpty(localeLanguage)) {
            this.locale = new Locale(localeLanguage, localeCountry);
        }
    }

    private void updateLocaleStrings(Locale locale){
        if(locale != null) {
            this.localeLanguage = locale.getLanguage();
            this.localeCountry = locale.getCountry();
        }
    }
}
