package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.generators.StringGenerator;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddingToGroupTests extends TestBase {

    StringGenerator generate = new StringGenerator();

    @BeforeMethod
    public void ensurePreconditions() {
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

    @Test
    public void addContactToGroup() {
        Groups groupList = app.db().groups();
        GroupData selectedGroup = groupList.iterator().next();
        Contacts contactList = app.db().contacts();
        ContactData selectedContact = contactList.iterator().next();

        app.contact().addToGroup(selectedGroup, selectedContact);

//         Groups linkedGroups = app.db().groups(
//                String.format("from GroupData where id = '%s'", selectedGroup.getId()));  // <- Алексей, скажите пожалуйста, этот блок выглядит как костыль?
//         GroupData linkedGroup = linkedGroups.iterator().next();                          // Я получаю список групп, заведомо зная что он будет из одного элемента.
//                                                                                          // Потом беру из него один элемент.

        GroupData linkedGroup = app.db().group(
                String.format("from GroupData where id = '%s'", selectedGroup.getId()));  // <- Алексей, скажите пожалуйста, этот блок выглядит как костыль?


        assertThat(selectedGroup.getId(), equalTo(linkedGroup.getId()));
    }
}
