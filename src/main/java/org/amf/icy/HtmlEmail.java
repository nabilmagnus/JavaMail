package org.amf.icy;

import javax.activation.DataHandler;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class HtmlEmail {

    /**
     * Utility method to send simple HTML email
     * @param session
     * @param toEmail
     * @param subject
     */

    public static void sendEmail(Session session, String toEmail, String subject , String htmlBody ,Map<String, String> mapInlineImages) {
        try
        {
            EmailUtil emailUtil = new EmailUtil();
            Properties properties = emailUtil.LoadProperties();

            // creates a new e-mail message
            Message msg = new MimeMessage(session);

            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(properties.getProperty("email.email_from"), "N.AMEZIANE"));

            msg.setReplyTo(InternetAddress.parse(properties.getProperty("email.email_from"), false));

            msg.setSubject(subject);

            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));


            // creates message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(htmlBody, "text/html");

            // creates multi-part
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);


            // adds inline image attachments
            if (mapInlineImages != null && mapInlineImages.size() > 0) {
                Set<String> setImageID = mapInlineImages.keySet();

                for (String contentId : setImageID) {
                    MimeBodyPart imagePart = new MimeBodyPart();
                    imagePart.setHeader("Content-ID", "<" + contentId + ">");
                    imagePart.setDisposition(MimeBodyPart.INLINE);
                    imagePart.setDisposition(Part.ATTACHMENT);

                    String imageFilePath = mapInlineImages.get(contentId);
                    URL url = HtmlEmail.class.getClassLoader().getResource(imageFilePath);

                    try {
                        //imagePart.attachFile(imageFilePath);
                        imagePart.setDataHandler(new DataHandler(url));

                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                    multipart.addBodyPart(imagePart);
                }
            }


            msg.setContent(multipart);
            System.out.println("Message is ready");
            Transport.send(msg);
            System.out.println("EMail Sent Successfully!!");

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
