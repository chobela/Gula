package com.appexpress.gula.util;

public class Config {

    // broadcast receiver intent filters
    public static final String SENT_TOKEN_TO_SERVER = "sentTokenToServer";
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";
    // id to handle the notification in the notification tray
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;
    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";
    public static final String SHARED_PREF = "ah_firebase";
    public static boolean appendNotificationMessages = true;
}
