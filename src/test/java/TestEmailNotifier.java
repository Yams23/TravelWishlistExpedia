import org.testng.Assert;
import org.testng.annotations.Test;
import techexe.expedia.notifier.EmailNotifier;

import java.util.List;

public class TestEmailNotifier {
    private EmailNotifier notifier;

    public TestEmailNotifier(){
        notifier= new EmailNotifier();
    }


    @Test(description = "Verify if email notification is being sent",enabled = false)
    public void testEmailNotification(){
        notifier.sendEmailNotification();
    }

    @Test(description = "Verify the no of emails Ids to be notified")
    public void testListOfEmailIds(){
        List<String> emailIds=notifier.getListofUserEmailIds();
        Assert.assertEquals(emailIds.size(),2,"EmailIds Coutn doesnot match");
    }
}
