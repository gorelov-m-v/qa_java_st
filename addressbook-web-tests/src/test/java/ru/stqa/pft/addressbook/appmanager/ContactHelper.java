package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static ru.stqa.pft.addressbook.tests.TestBase.*;

public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }
    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getFirstName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNickName());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("email"), contactData.getEmailOne());
        type(By.name("email2"), contactData.getEmailTwo());
        type(By.name("email3"), contactData.getEmailThree());
        type(By.name("address"), contactData.getAddress());
//        attach(By.name("photo"), contactData.getPhoto());
        if (contactData.getGroups().size() > 0) {
            Assert.assertTrue(contactData.getGroups().size() == 1);
            new Select(wd.findElement(By.xpath(".//select[@name='new_group']")))
                    .selectByVisibleText(contactData.getGroups().iterator().next().getName());
        } else {
            Assert.assertFalse(isElementPresent(By.xpath(".//select[@name='new_group']")));
        }
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void submitContactCreation() {
        click(By.xpath("//div[@id='content']/form/input[21]"));
    }

    public void initContactModificationById(int id) {
        wd.findElement(By.cssSelector(String.format("a[href='edit.php?id=%s']", id))).click();
    }

    public void submitContactModification() {
        click(By.xpath("//div[@id='content']/form/input[22]"));
    }

    public void selectContact(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }
    public void selectContactById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact, true);
        submitContactCreation();
        contactCache = null;
        returnToHomePage();
    }

    public void delete(Set<ContactData> before, int index) {
        selectContact(index);
        deleteSelectedContact();
        app.alertAccept();
        waitingDeleteButton();
    }

    public void delete(Set<ContactData> before, ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        app.alertAccept();
        waitingDeleteButton();
        contactCache = null;
    }

    public void modify(ContactData contact) {
        initContactModificationById(contact.getId());
        fillContactForm(contact, false);
        submitContactModification();
        contactCache = null;
        returnToHomePage();
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    public void waitingDeleteButton() {
        (new WebDriverWait(wd, 10)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Delete']")));
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<ContactData>();
        List<WebElement> elements = wd.findElements(By.xpath(".//tr[@name='entry']"));
        for(WebElement element : elements) {
            String lastName = element.findElement(By.xpath(".//td[2]")).getText();
            String firstName = element.findElement(By.xpath(".//td[3]")).getText();
            String[] phones = element.findElement(By.xpath(".//td[6]")).getText().split("\n");
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            ContactData contact = new ContactData().withId(id).withFirstName(firstName).withLastName(lastName);
            contacts.add(contact);
        }
        return contacts;
    }

    private Contacts contactCache = null;

    public Contacts all() {
        if(contactCache != null) {
            return new Contacts(contactCache);
        }
        contactCache = new Contacts();
        List<WebElement> elements = wd.findElements(By.name("entry"));
        for(WebElement element : elements) {
            List<WebElement> cells = element.findElements(By.tagName("td"));
            int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
            String lastName = cells.get(1).getText();
            String firstName = cells.get(2).getText();
            String address = cells.get(3).getText();
            String allMails = cells.get(4).getText();
            String allPhones = cells.get(5).getText();

                contactCache.add(new ContactData().withId(id).withFirstName(firstName)
                        .withLastName(lastName).withAllPhones(allPhones).withAllMails(allMails).withAddress(address));
        }
        return new Contacts(contactCache);
    }

    public boolean exists() {
        try {
            wd.findElement(By.name("selected[]"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initContactModificationById(contact.getId());

        String firstName = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastName = wd.findElement(By.name("lastname")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhone = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhone = wd.findElement(By.name("work")).getAttribute("value");
        String emailOne = wd.findElement(By.name("email")).getAttribute("value");
        String emailTwo = wd.findElement(By.name("email2")).getAttribute("value");
        String emailThree = wd.findElement(By.name("email3")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");

        return new ContactData().withId(contact.getId()).withFirstName(firstName)
                .withLastName(lastName).withHomePhone(homePhone)
                .withMobilePhone(mobilePhone).withWorkPhone(workPhone)
                .withEmailOne(emailOne).withEmailTwo(emailTwo)
                .withEmailThree(emailThree).withAddress(address);
    }
}
