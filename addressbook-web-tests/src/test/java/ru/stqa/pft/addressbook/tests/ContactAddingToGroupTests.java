package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.generators.StringGenerator;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

import static org.testng.AssertJUnit.assertTrue;

public class ContactAddingToGroupTests extends TestBase {

    StringGenerator generate = new StringGenerator();
    Contacts contactList;
    Groups groupList;

    @BeforeMethod
    public void ensurePreconditions() {
        // Запрашиваем списки групп и контактов.
        contactList = app.db().contacts();
        groupList = app.db().groups();

        // Проверяем полученные списки групп и контактов, если они пустые - создаем и обновляем списки.
        if (contactList.size() == 0) {
            app.goTo().homePage();
            app.contact().create(new ContactData().withFirstName(generate.randomName()).withMiddleName(generate.randomName())
                                                  .withLastName(generate.randomName()).withNickName(generate.randomName())
                                                  .withMobilePhone(generate.randomPhone()).withHomePhone(generate.randomPhone())
                                                  .withWorkPhone(generate.randomPhone()).withEmailOne(generate.randomEmail())
                                                  .withEmailTwo(generate.randomEmail()).withEmailThree(generate.randomEmail())
                                                  .withAddress(generate.randomString(20)));
            contactList = app.db().contacts();
        }
        if(groupList.size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName(generate.randomName())
                                              .withHeader(generate.randomName())
                                              .withFooter(generate.randomName()));
            groupList = app.db().groups();
        }
        // Если список контактов не пустой, ищем в нем контакты, которые присутствует во всех существующих группах и удаляем их из списка.
        for (ContactData contact : contactList) {
            if(contact.getGroups().equals(app.db().groups())) {
                contactList.remove(contact);
            }
            // Если список после преобразования оказался пустым - создаем новый контакт и обновляем список.
            if(contactList.isEmpty()) {
                app.goTo().homePage();
                app.contact().create(new ContactData().withFirstName(generate.randomName()).withMiddleName(generate.randomName())
                                                      .withLastName(generate.randomName()).withNickName(generate.randomName())
                                                      .withMobilePhone(generate.randomPhone()).withHomePhone(generate.randomPhone())
                                                      .withWorkPhone(generate.randomPhone()).withEmailOne(generate.randomEmail())
                                                      .withEmailTwo(generate.randomEmail()).withEmailThree(generate.randomEmail())
                                                      .withAddress(generate.randomString(20)));
                contactList = app.db().contacts();
            }
        }
    }

    @Test
    public void addContactToGroup() {
        // В тесте работаем с преобразованным списком.
        GroupData selectedGroup = groupList.iterator().next();
        ContactData selectedContact = contactList.iterator().next();

        app.contact().addToGroup(selectedGroup, selectedContact);

        ContactData linkedContact = app.db().contact(
                String.format("from ContactData where deprecated = '0000-00-00' and id = '%s'", selectedContact.getId()));
        Groups contactGroups = linkedContact.getGroups();

        assertTrue(contactGroups.contains(selectedGroup));
    }
}
