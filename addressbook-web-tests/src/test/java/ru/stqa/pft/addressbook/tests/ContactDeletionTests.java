package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.List;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().goToHomePage();
        if (! app.getContactHelper().isElementPresent(By.name("selected[]"))) {
            app.getContactHelper().createContact(new ContactData("Mikhail", "Junior",
                    "Gorelov", "Junior",
                    "89037776767", "testmail@testmail.com",
                    "[none]"));
        }
    }

    @Test
    public void testsContactDeletion() {
        List<ContactData> before = app.getContactHelper().getContactList();
        int index = before.size() - 1;
        app.getContactHelper().deleteContact(before, index);
        List<ContactData> after = app.getContactHelper().getContactList();
        Assert.assertEquals(after.size(), index);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}
