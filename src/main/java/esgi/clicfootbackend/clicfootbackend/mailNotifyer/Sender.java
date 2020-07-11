package esgi.clicfootbackend.clicfootbackend.mailNotifyer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.logging.Logger;

@Component
@Slf4j
public class Sender {
    private final Logger logger = Logger.getLogger(String.valueOf(Sender.class));

    @Value("${notification.mail.comming.from}")
    private String emailCommingFrom;
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendMail(String emailTo, String message, String subject) throws MessagingException {

        final String emailBodyF =
                "<font face=\"arial\">Hello there !<br>" +
                message +
                "<br>Should you have any question regarding this mail, thank you to contact msouissi2@myges.fr\">" +
                ".<br>Best regards,<br><br><strong>ClicFoot team.</strong></font>";

        logger.info("sending message to email address : " + emailTo + " in process ...");
        MimeMessage messenge = javaMailSender.createMimeMessage();
        MimeMessageHelper sendMail = new MimeMessageHelper(messenge, true, "UTF-8");
        sendMail.setTo(emailTo.split(";"));
        sendMail.setFrom(emailCommingFrom);
        sendMail.setSubject(subject);
        sendMail.setText(emailBodyF, true);
        javaMailSender.send(messenge);
        logger.info("The message was sent succesfully to : "+ emailTo);
    }
}
