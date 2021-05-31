package org.amf.icy;

import java.util.*;
import javax.mail.Session;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SendEmail {

    public static void main(String[] args) {

        EmailUtil emailUtil = new EmailUtil();
        Properties properties = emailUtil.LoadProperties();

        Session session = Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                properties.getProperty("email.username"),
                                properties.getProperty("email.password"));
                    }
                });
        try {
            /*
            SimpleEmail.sendEmail(session,
                    properties.getProperty("email.email_to")
                    ,"Objet de test"
                    , " **** Test Reussi **** "
            );
            */
            // inline images

            StringBuffer body = new StringBuffer("<html><h1><i>ALERTE!</i></h1><br>");
            body.append("Nabil AMEZIANE<br>");
            body.append("<font color=red>Data Geek</font><br>");
            body.append("<hr>");
            body.append("<img src=\"cid:git\" width=\"15%\" height=\"15%\" /><br>");
            body.append("<hr>");
            body.append("</html>");

            Map<String, String> inlineImages = new HashMap<String, String>();
            inlineImages.put("git", "/mnt/c/Users/Nabil AMEZIANE/IdeaProjects/JavaMail/src/main/resources/git.png");

            HtmlEmail.sendEmail(session,
                    properties.getProperty("email.email_to"),
                    "ALERTE : problem with apps",
                    body.toString(),
                    inlineImages
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


