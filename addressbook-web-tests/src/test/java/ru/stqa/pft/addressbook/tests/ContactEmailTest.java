package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.stream.Collectors;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactEmailTest extends TestBase {

    @Test
    public void testContactEmails() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAllMails(), equalTo(mergeEmails(contactInfoEditForm)));
    }

    private String mergeEmails(ContactData contact) {
        return Arrays.asList(contact.getEmailOne(), contact.getEmailTwo(), contact.getEmailThree())
                .stream().filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}
