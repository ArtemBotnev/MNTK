package ru.artembotnev.mntk.model.net.pojo;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * POJO class match participant of json
 * <p>
 * Created by Artem Botnev on 27.05.2018
 */
public class Participant extends RealmObject {
    @PrimaryKey
    private String _id;

    private String firstName;

    private String lastName;
    private String middleName;

    private String companyName;
    private String companyPosition;

    private String addedTimeStamp;

    private String email;
    private String phone;
    private String hiddenEmail;
    private String hiddenPhone;

    private String biography;

    private String secretContacts;
    private String removed;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyPosition() {
        return companyPosition;
    }

    public void setCompanyPosition(String companyPosition) {
        this.companyPosition = companyPosition;
    }

    public String getAddedTimeStamp() {
        return addedTimeStamp;
    }

    public void setAddedTimeStamp(String addedTimeStamp) {
        this.addedTimeStamp = addedTimeStamp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHiddenEmail() {
        return hiddenEmail;
    }

    public void setHiddenEmail(String hiddenEmail) {
        this.hiddenEmail = hiddenEmail;
    }

    public String getHiddenPhone() {
        return hiddenPhone;
    }

    public void setHiddenPhone(String hiddenPhone) {
        this.hiddenPhone = hiddenPhone;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getSecretContacts() {
        return secretContacts;
    }

    public void setSecretContacts(String secretContacts) {
        this.secretContacts = secretContacts;
    }

    public String getRemoved() {
        return removed;
    }

    public void setRemoved(String removed) {
        this.removed = removed;
    }
}
