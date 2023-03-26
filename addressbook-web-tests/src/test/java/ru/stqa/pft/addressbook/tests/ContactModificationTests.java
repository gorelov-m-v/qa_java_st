package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstName("Mikhail").withMiddleName("Junior")
                                                  .withLastName("Gorelov").withNickName("Junior").withHomePhone("1235124123")
                                                  .withWorkPhone("874654832").withMobilePhone("423476324")
                                                  .withMobilePhone("89037776767").withEmailOne("testmail@testmail.com")
                                                  .withGroup("[none]").withEmailTwo("12312@ff.re")
                                                  .withEmailThree("sadasd@dsfs.er").withAddress("asdasd"));
        }
    }

    @Test
    public void testsContactModification() {
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData().withId(modifiedContact.getId()).withFirstName("Mikhail").withMiddleName("Junior")
                                               .withLastName("Gorelov").withNickName("Junior").withHomePhone("123124")
                                               .withWorkPhone("874654832").withMobilePhone("423476324")
                                               .withMobilePhone("89037776767").withEmailOne("testmail@testmail.com")
                                               .withGroup("[none]").withEmailTwo("12312@ff.re").withEmailThree("sadasd@dsfs.er")
                                               .withAddress("asdasd");
        app.goTo().homePage();
        app.contact().modify(contact);

        assertThat(app.db().contacts().size(), equalTo(before.size()));
        Contacts after = app.db().contacts();

        assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
    }
}
