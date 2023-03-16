package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @Test
    public void testContactCreationTests() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstName("Mikhail").withMiddleName("Junior")
                                               .withLastName("Gorelov").withNickName("Junior")
                                               .withMobilePhone("89037776767").withHomePhone("234234234")
                                               .withWorkPhone("23423234234").withEmailOne("testmail@testmail.com")
                                               .withEmailTwo("testmail2@testmail.ru").withEmailThree("testmail3@testmail.ru")
                                               .withGroup("[none]").withAddress("Московская область, г. Москва,\n" +
                                                                                "3-я улица ямского поля,\n" +
                                                                                "Дом 2, кв 13.");
        app.contact().create(contact);
        Contacts after = app.contact().all();

        assertThat(after.size(), equalTo(before.size() + 1));
        assertThat(after, equalTo(
                before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));

    }

    @Test
    public void testBadContactCreationTests() {
        app.goTo().homePage();
        Contacts before = app.contact().all();
        ContactData contact = new ContactData().withFirstName("Mikhail").withMiddleName("Junior")
                                               .withLastName("Gorelov'").withNickName("Junior")
                                               .withMobilePhone("89037776767").withHomePhone("234234234")
                                               .withWorkPhone("23423234234").withEmailOne("testmail@testmail.com")
                                               .withGroup("[none]");
        app.contact().create(contact);
        assertThat(app.contact().count(), equalTo(before.size()));
        Contacts after = app.contact().all();
        assertThat(after, equalTo(before));
    }
}
