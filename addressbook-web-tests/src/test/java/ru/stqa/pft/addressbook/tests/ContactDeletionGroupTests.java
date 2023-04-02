package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.generators.StringGenerator;
import ru.stqa.pft.addressbook.model.*;

import java.util.List;

import static org.testng.AssertJUnit.assertNull;
import static org.testng.AssertJUnit.assertTrue;

public class ContactDeletionGroupTests extends TestBase {

    StringGenerator generate = new StringGenerator();
    GroupData selectedGroup;
    ContactData selectedContact;

    @BeforeMethod
    public void ensurePreconditions() {
        List<AddressInGroups> addressInGroups = app.db().contactsInGroups();

        if(addressInGroups.isEmpty()) {
            if (app.db().contacts().size() == 0) {
                app.goTo().homePage();
                app.contact().create(new ContactData().withFirstName(generate.randomName()).withMiddleName(generate.randomName())
                                                      .withLastName(generate.randomName()).withNickName(generate.randomName())
                                                      .withMobilePhone(generate.randomPhone()).withHomePhone(generate.randomPhone())
                                                      .withWorkPhone(generate.randomPhone()).withEmailOne(generate.randomEmail())
                                                      .withEmailTwo(generate.randomEmail()).withEmailThree(generate.randomEmail())
                                                      .withAddress(generate.randomString(20)));
            }
            if(app.db().groups().size() == 0) {
                app.goTo().groupPage();
                app.group().create(new GroupData().withName(generate.randomName())
                                                  .withHeader(generate.randomName())
                                                  .withFooter(generate.randomName()));
            }
        }
            app.goTo().homePage();
            Groups groupList = app.db().groups();
            selectedGroup = groupList.iterator().next();
            Contacts contactList = app.db().contacts();
            selectedContact = contactList.iterator().next();

            app.contact().addToGroup(selectedGroup, selectedContact);
    }

    @Test
    public void deleteContactFromGroup() {
        app.contact().deletedFromGroup(selectedGroup, selectedContact);

        AddressInGroups addressInGroup = app.db().contactsInGroups(String.format(
                "from AddressInGroups where deprecated = '0000-00-00' and id = %s and group_id = %s",
                selectedContact.getId(), selectedGroup.getId()));

        assertNull(addressInGroup);
    }
}
