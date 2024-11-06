package org.assignment2.utils;

import javax.mail.*;
import javax.mail.search.SubjectTerm;
import java.util.Properties;

public class EmailVerification {
    public static boolean checkForConfirmationEmail(String email, String password, String subject) {
        Properties props = new Properties();
        props.put("mail.store.protocol", "imap");
        props.put("mail.imap.host", "imap.gmail.com");
        props.put("mail.imap.port", "993");

        try {
            // Connect to the email account
            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imap");
            store.connect("imap.gmail.com", email, password);

            // Open the inbox folder
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // Search for the email by subject
            Message[] messages = inbox.search(new SubjectTerm(subject));

            for (Message message : messages) {
                if (message.getSubject().contains(subject)) {
                    return true; // Email found
                }
            }

            inbox.close(false);
            store.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
