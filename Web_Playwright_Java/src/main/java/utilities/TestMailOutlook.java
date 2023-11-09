package utilities;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class TestMailOutlook {

	public static void main(String[] args) throws AddressException, MessagingException {
		
		MonitoringMailOutlook mail = new MonitoringMailOutlook();
		mail.sendMail( TestConfigOutlook.from, TestConfigOutlook.to,
				TestConfigOutlook.subject, TestConfigOutlook.messageBody, TestConfigOutlook.attachmentPath, TestConfigOutlook.attachmentName);
		
		

	}

}
