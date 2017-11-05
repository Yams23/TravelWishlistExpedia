import org.testng.Assert;
import org.testng.annotations.Test;
import techexe.expedia.notifier.EmailNotifier;

import java.util.List;

/**
 * Test email notifier contains tests to verify
 * email notifications sent to users who have voted for popular places
 */
public class TestEmailNotifier {
    private EmailNotifier notifier;

    /**
     * Instantiates a new Test email notifier.
     */
    public TestEmailNotifier(){
        notifier= new EmailNotifier();
    }


    /**
     * Test email notification.
     */
    @Test(description = "Verify if email notification is being sent",enabled = false)
    public void testEmailNotification(){
        notifier.sendEmailNotification();
    }

    /**
     * Test list of email ids to send notofications.
     */
    @Test(description = "Verify the no of emails Ids to be notified")
    public void testListOfEmailIds(){
        List<String> emailIds=notifier.getListofUserEmailIds();
        Assert.assertEquals(emailIds.size(),2,"EmailIds Count doesnot match");
    }
}
