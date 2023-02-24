package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testsContactModification() {
        app.getNavigationHelper().goToHomePage();
        app.getContactHelper().initContactModification();
        app.getContactHelper().fillContactForm(new ContactData("newMikhail", "newJunior", "newGorelov", "newJunior", "new89037776767", "newtestmail@testmail.com", "test11"), false);
        app.getContactHelper().submitContactModification();
        app.returnToHomePage();
    }
}
