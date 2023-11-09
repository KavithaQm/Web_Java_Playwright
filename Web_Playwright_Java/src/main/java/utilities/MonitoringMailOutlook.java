package utilities;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import extentlisteners.ExtentListeners;

public class MonitoringMailOutlook {
	
	public static StringBuilder testCase_Summary_Report = new StringBuilder();

    public void sendMail(String from, String[] to, String subject, String messageBody, String attachmentPath, String attachmentName) 
            throws MessagingException, AddressException {

        boolean debug = false;
        Properties props = new Properties();

        // Outlook SMTP settings
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.office365.com"); // Outlook SMTP server
        props.put("mail.smtp.port", "587"); // Outlook SMTP port
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        String username = "parameswarareddy@quality-matrix.com"; // Your Outlook email address
        String password = "qhub@2019"; // Your Outlook email password
        CustomSMTPAuthenticator customAuthenticator = new CustomSMTPAuthenticator(username, password);
        Session session = Session.getInstance(props, customAuthenticator);
        session.setDebug(debug);
        try {
            Transport bus = session.getTransport("smtp");
            bus.connect();
            Message message = new MimeMessage(session);
            message.setSubject("PlayWright Report " + LocalTime.now());

//            message.addHeader("X-Priority", "1");
            message.setFrom(new InternetAddress(from));
            InternetAddress[] addressTo = new InternetAddress[to.length];
            for (int i = 0; i < to.length; i++) {
                addressTo[i] = new InternetAddress(to[i]);
            }
            message.setRecipients(Message.RecipientType.TO, addressTo);

            BodyPart body = new MimeBodyPart();
            body.setContent(messageBody, "text/html");

            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(body);
            message.setContent(multipart);
            
            body.setText("Please Find The Attached Report File!");
            testCase_Summary_Report.append("<html>"
            + "<p style=\"color:#0082c3;\">Hi All, <br>Please find below list of <b>Automation Test Cases</b> triggered by Automation build.</p>"
            + "<style>table#t01, th, td {border: 1px solid black;border-collapse: collapse;}table#t01 th{background-color:#80e5ff; } table#t01 tr:nth-child(even) {background-color: #f2f2f2;} table#t01 tr:nth-child(odd) { background-color: #DFEDEC;}table#t01 th, td {padding: 5px;}table#t01 th {text-align: center;} table#t01 caption {color: #008ae6;font-weight: bold;}</style>"
            + "<h3 align=\"center\" style=\"color:#008ae6;\"> Daily Status Report for Automation (" + LocalDateTime.now()
            + ")</h3>");
            testCase_Summary_Report.append("<table style=\"width:100%\" id=\"t01\"><tr>"
            + "<td style=\"width:50%; background: #0082c3;color:#ffffff;\"><b> Total No Test Scenario's </b></td>"
            + "<td><b>" + ExtentListeners.totalTestCases.size() + "</b></td>" + " </tr>");
            testCase_Summary_Report
            .append(" <tr> " + "<td style=\"width:50%; background: #0082c3;color:#ffffff;\"><b>Pass</b> </td>"
            + "<td><b style='color:green;'>" + ExtentListeners.passedTests.size() + "</b> </td>" + " </tr>");

            testCase_Summary_Report.append(" <tr style=\"color:#008ae6;\"> "
            + "<td style=\"width:50%; background: #0082c3;color:#ffffff;\"><b>Fail</b> </td>"
            + "<td><b style='color:red;'>" + ExtentListeners.failedTests.size() + "</b> </td>" + " </tr>");

            testCase_Summary_Report.append(
            "</table><p style=\"color:#008ae6;\"><br><br><br> Thanks & Regards,<br>Automation Team</p> <html>");
            body.setContent(testCase_Summary_Report.toString(), "text/html; charset=ISO-8859-1");
           
            Transport.send(message);
            System.out.println("Successfully Sent mail via Outlook");
            bus.close();
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    public class CustomSMTPAuthenticator extends Authenticator {
        private String username;
        private String password;

        public CustomSMTPAuthenticator(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    }}
