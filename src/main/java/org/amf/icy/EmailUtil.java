package org.amf.icy;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailUtil {

    public Properties LoadProperties() {

        Properties properties = new Properties();

        try{
            try {
                properties.load(EmailUtil.class.getClassLoader().getResourceAsStream("config.properties"));

            } catch (IOException ioex) {
                Logger.getLogger(EmailUtil.class.getName()).log(Level.ALL, "IOException Occured while loading properties file::::" + ioex.getMessage());
            }

            System.out.println("Loading properties ******************************************************************* ");

            //LOAD properties
            final String USERNAME = properties.getProperty("email.username", "XXX");
            Logger.getLogger(EmailUtil.class.getName()).log(Level.INFO, "||| Reading Property : " + USERNAME);

            final String PASSWORD = properties.getProperty("email.password", "XXX");
            Logger.getLogger(EmailUtil.class.getName()).log(Level.INFO, "||| Reading Property : " + PASSWORD);

            final String EMAIL_FROM = properties.getProperty("email.email_from", "XXX");
            Logger.getLogger(EmailUtil.class.getName()).log(Level.INFO, "||| Reading Property : " + EMAIL_FROM);

            final String EMAIL_TO = properties.getProperty("email.email_to", "XXX");
            Logger.getLogger(EmailUtil.class.getName()).log(Level.INFO, "||| Reading Property : " + EMAIL_TO);

            final String SMTP_HOST = properties.getProperty("smtp.host", "XXX");
            Logger.getLogger(EmailUtil.class.getName()).log(Level.INFO, "||| Reading Property : " + SMTP_HOST);

            final String SMTP_PORT = properties.getProperty("smtp.port", "XXX");
            Logger.getLogger(EmailUtil.class.getName()).log(Level.INFO, "||| Reading Property : " + SMTP_PORT);

            final String SMTP_AUTH = properties.getProperty("smtp.auth", "true");
            Logger.getLogger(EmailUtil.class.getName()).log(Level.INFO, "||| Reading Property : " + SMTP_AUTH);

            final String SMTP_START_TLS = properties.getProperty("smtp.start_tls", "true");
            Logger.getLogger(EmailUtil.class.getName()).log(Level.INFO, "||| Reading Property : " + SMTP_START_TLS);

            properties.put("email.username", USERNAME);
            properties.put("email.password", PASSWORD);
            properties.put("email.email_to", EMAIL_TO);
            properties.put("mail.smtp.host", SMTP_HOST);
            properties.put("mail.smtp.port", SMTP_PORT);
            properties.put("mail.smtp.auth", SMTP_AUTH);
            properties.put("mail.smtp.starttls.enable", SMTP_START_TLS); //TLS

            return properties;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }
}

