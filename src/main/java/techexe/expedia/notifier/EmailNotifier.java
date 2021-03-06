package techexe.expedia.notifier;

import techexe.expedia.model.DynamoDBWrapper;
import techexe.expedia.model.Location;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

/**
 * Email Notifier sends email to users who have voted for places which has become popular after getting more than 5 votes.
 */
public class EmailNotifier {

    /**
     * The Logger.
     */
    Logger logger = Logger.getLogger(EmailNotifier.class.getName());

    private final String from = "expediasupport@expedia.com";
    private final String host = "expedia.mail.server";
    private DynamoDBWrapper dynamoDBWrapper;

    /**
     * Instantiates a new Email notifier.
     */
    public EmailNotifier() {
        this.dynamoDBWrapper = new DynamoDBWrapper();
    }

    /**
     * Send email notification.
     */
    public void sendEmailNotification() {
        logger.info("Sending Email Notification for Popular Places....");
        List<String> emailIds = getListofUserEmailIds();
        logger.info("Sending Email Notification to email Ids - "+emailIds.toString());
        if (emailIds == null || emailIds.isEmpty()) {
            logger.info("There are no users to be notified since the emailIds list is empty/null");
        }
        emailIds.forEach(id -> sendEmail(id));
    }

    /**
     * Get List of emailIds to be notified
     * @return
     */
    public List<String> getListofUserEmailIds() {
        logger.info("Fetch the list of emails Ids from data store ");
        List<Location> locations = dynamoDBWrapper.getListOfLocations();
        // Get Popular locations
        List<Location> popularLocations = locations.stream().filter(loc -> loc.isPopular()).collect(Collectors.toList());
        List<String> emailIdList = new ArrayList<>();
        for(Location loc : popularLocations){
            String emailIds=loc.getVotedBy();
            emailIdList.addAll(Arrays.asList(emailIds.split(",")));
        }
        return emailIdList;
    }

    /**
     * Send Email to dummy
     * @param emailId
     */
    private void sendEmail(String emailId) {

        //Get the session object
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);

        //compose the message
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(emailId));
            message.setSubject("Your favorite place is popular now!!");
            message.setText("Hello " + "\n" + " The place you have voted has become popular now.Check out!!!" + "\n\n" + "From Expedia Support");

            // Send message
            Transport.send(message);
            logger.info("Message has been sent successfully");

        } catch (MessagingException mex) {
            mex.printStackTrace();
            logger.severe("Exception thrown while sending notification to customers");
        }
    }

}
