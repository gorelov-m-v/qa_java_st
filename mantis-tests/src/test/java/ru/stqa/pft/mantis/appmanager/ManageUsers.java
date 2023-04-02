package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class ManageUsers extends HelperBase {

    public ManageUsers(ApplicationManager app) {
        super(app);
    }

    public void initPasswordReset(String username) {
        wd.get(app.getProperty("web.baseUrl") + "/manage_user_page.php");
        click(By.linkText(username));
        click(By.cssSelector("input[value='Сбросить пароль']"));
    }
}
