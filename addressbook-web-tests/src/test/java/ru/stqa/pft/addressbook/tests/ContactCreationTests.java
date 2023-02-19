package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreationTests(){
    app.initContactCreation();
    app.fillContactForm(new ContactData("Mikhail", "Junior", "Gorelov", "Junior", "89037776767", "testmail@testmail.com"));
    app.submitContactCreation();
    app.returnToHomePage();
  }

}
