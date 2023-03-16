package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;

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
        assertThat(contact.getAddress(), equalTo(mergeStrings(contactInfoEditForm, "address")));
    }

    private String mergeStrings(ContactData contact, String dataType) {

        if(dataType.equals("phones")) {
            result =  Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
                    .stream().filter((s) -> !s.equals(""))
                    .map(ContactDataTest::cleaned)
                    .collect(Collectors.joining("\n"));
        } else if(dataType.equals("emails")) {
            result =  Arrays.asList(contact.getEmailOne(), contact.getEmailTwo(), contact.getEmailThree())
                    .stream().filter((s) -> !s.equals(""))
                    .collect(Collectors.joining("\n"));
        } else if(dataType.equals("address")) {
            result =  Arrays.asList(contact.getAddress())
                    .stream().filter((s) -> !s.equals(""))
                    .collect(Collectors.joining("\n"));
        }
        return result;
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
    }

}
