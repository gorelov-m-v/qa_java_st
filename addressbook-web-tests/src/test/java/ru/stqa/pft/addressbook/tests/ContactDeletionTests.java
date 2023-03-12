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
        app.goTo().homePage();
        if (!app.contact().exists()) {
            app.contact().create(new ContactData().withFirstName("Mikhail").withMiddleName("Junior")
                                                  .withLastName("Gorelov").withNickName("Junior")
                                                  .withMobile("89037776767").withEmail("testmail@testmail.com")
                                                  .withGroup("[none]"));
        }
    }

    @Test
    public void testsContactDeletion() {
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        app.contact().deleteContact(before, index);
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size() - 1);

        before.remove(index);
        Assert.assertEquals(before, after);
    }
}
