package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactDataTest extends TestBase {
    String result;

    @Test
    public void testContactPhones() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergeStrings(contactInfoEditForm, "phones")));
        assertThat(contact.getAllMails(), equalTo(mergeStrings(contactInfoEditForm, "emails")));
        assertThat(contact.getAddress(), equalTo(contactInfoEditForm.getAddress()));
    }

    private String mergeStrings(ContactData contact, String dataType) {

        switch (dataType) {
            case "phones":
                result = Stream.of(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                        .filter((s) -> !s.equals(""))
                        .map(ContactDataTest::cleaned)
                        .collect(Collectors.joining("\n"));
                break;
            case "emails":
                result = Stream.of(contact.getEmailOne(), contact.getEmailTwo(), contact.getEmailThree())
                        .filter((s) -> !s.equals(""))
                        .collect(Collectors.joining("\n"));
                break;
        }
        return result;
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

}
