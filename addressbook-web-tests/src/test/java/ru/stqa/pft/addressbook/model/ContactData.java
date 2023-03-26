package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.Type;
import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
public class ContactData {

    @Id
    @Column(name = "id")
    private int id = Integer.MAX_VALUE;

    @Expose
    @Column(name = "firstname")
    private String firstName;
    @Expose
    @Column(name = "middlename")
    private String middleName;
    @Expose
    @Column(name = "lastname")
    private String lastName;
    @Expose
    @Column(name = "nickname")
    private String nickName;
    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String mobilePhone;
    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String homePhone;
    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String workPhone;
    @Transient
    private String allPhones;
    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String emailOne;
    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String emailTwo;
    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String emailThree;
    @Transient
    private String allMails;
    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;
    @Column(name = "photo")
    @Type(type = "text")
    private String photo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<GroupData>();

    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getNickName() {
        return nickName;
    }
    public String getMobilePhone() {
        return mobilePhone;
    }
    public String getHomePhone() {
        return homePhone;
    }
    public String getWorkPhone() {
        return workPhone;
    }
    public String getAllPhones() {
        return allPhones;
    }
    public String getAllMails() {
        return allMails;
    }
    public String getEmailOne() {
        return emailOne;
    }
    public String getEmailTwo() {
        return emailTwo;
    }
    public String getEmailThree() {
        return emailThree;
    }
    public String getAddress() {
        return address;
    }
    public File getPhoto() {
        return new File(photo);
    }
    public Groups getGroups() {
        return new Groups(groups);
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ContactData withMiddleName(String middleName) {
        this.middleName = middleName;
        return this;
    }

    public ContactData withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public ContactData withMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
        return this;
    }

    public ContactData withHomePhone(String homePhone) {
        this.homePhone = homePhone;
        return this;
    }

    public ContactData withWorkPhone(String workPhone) {
        this.workPhone = workPhone;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }

    public ContactData withEmailOne(String emailOne) {
        this.emailOne = emailOne;
        return this;
    }

    public ContactData withEmailTwo(String emailTwo) {
        this.emailTwo = emailTwo;
        return this;
    }

    public ContactData withEmailThree(String emailThree) {
        this.emailThree = emailThree;
        return this;
    }

    public ContactData withAllMails(String allMails) {
        this.allMails = allMails;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", mobilePhone='" + mobilePhone + '\'' +
                ", homePhone='" + homePhone + '\'' +
                ", workPhone='" + workPhone + '\'' +
                ", emailOne='" + emailOne + '\'' +
                ", emailTwo='" + emailTwo + '\'' +
                ", emailThree='" + emailThree + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (!Objects.equals(firstName, that.firstName)) return false;
        if (!Objects.equals(middleName, that.middleName)) return false;
        if (!Objects.equals(lastName, that.lastName)) return false;
        if (!Objects.equals(nickName, that.nickName)) return false;
        if (!Objects.equals(mobilePhone, that.mobilePhone)) return false;
        if (!Objects.equals(homePhone, that.homePhone)) return false;
        if (!Objects.equals(workPhone, that.workPhone)) return false;
        if (!Objects.equals(emailOne, that.emailOne)) return false;
        if (!Objects.equals(emailTwo, that.emailTwo)) return false;
        if (!Objects.equals(emailThree, that.emailThree)) return false;
        return Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
        result = 31 * result + (mobilePhone != null ? mobilePhone.hashCode() : 0);
        result = 31 * result + (homePhone != null ? homePhone.hashCode() : 0);
        result = 31 * result + (workPhone != null ? workPhone.hashCode() : 0);
        result = 31 * result + (emailOne != null ? emailOne.hashCode() : 0);
        result = 31 * result + (emailTwo != null ? emailTwo.hashCode() : 0);
        result = 31 * result + (emailThree != null ? emailThree.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    public ContactData inGroup(GroupData group) {
        groups.add(group);
        return this;
    }
}

