package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

public class ContactAddressTest extends TestBase {

    @Test
    public void testContactAddress() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoEditForm = app.contact().infoFromEditForm(contact);

        assertThat(contact.getAddress(), equalTo(mergeAddress(contactInfoEditForm)));
    }

    private String mergeAddress(ContactData contact) {
        return Stream.of(contact.getAddress()).filter((s) -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }
}
