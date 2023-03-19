package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
            String json = "";
            String line = reader.readLine();
            while(line != null) {
                json += line;
                line = reader.readLine();
            }
            Gson gson = new Gson();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>(){}.getType());
            return contacts.stream().map((g) -> new Object[] {g}).collect(Collectors.toList()).iterator();
        }
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testContactCreationTests(ContactData contact) {
        app.goTo().homePage();
        Contacts before = app.contact().all();
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
