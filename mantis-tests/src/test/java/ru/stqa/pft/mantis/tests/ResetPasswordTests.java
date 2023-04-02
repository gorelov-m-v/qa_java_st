package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.User;
import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ResetPasswordTests extends TestBase {

    List<User> userList;

    @BeforeMethod
    public void startMailServer() throws MessagingException, IOException, InterruptedException {
        app.mail().start();
        userList = app.db().getUsers();
        if(userList.size() == 1) {
            app.registration().registerUser();
        }
    }

    @Test
    public void resetPassword() throws MessagingException, IOException, InterruptedException {
        String newPassword = "newPassword";
        userList.remove(0); // Удаляем из полученного списка пользователей администратора.
        User user = userList.iterator().next();

        app.login().loginAsAdmin();
        app.manage().initPasswordReset(user.getUsername());

        List<MailMessage> mailMessages = app.mail().waitForMail(1, 10000);
        String confirmationLink = app.registration().findConfirmationLink(mailMessages,user.getEmail());
        app.registration().finish(confirmationLink, newPassword);

        HttpSession session = app.newSession();

        assertTrue(session.login(user.getUsername(), newPassword));
        assertTrue(session.isLoggedInAS(user.getUsername()));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}
