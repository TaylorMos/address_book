package address;

public class ModifyPerson {

    private Person person;

    public ModifyPerson(Person person) {
        this.person = person;
    }

    public void updatePersonInfo(String name, String telephone, String phone, String email,
                                 String birthday, String workaddress, String homeAddress, String postcode,
                                 String group, String note, String photoPath) {
        person.setName(name);
        person.setTelephone(telephone);
        person.setPhone(phone);
        person.setEmail(email);
        person.setBirthday(birthday);
        person.setWorkaddress(workaddress);
        person.setHomeaddress(homeAddress);
        person.setPostcode(postcode);
        person.setGroup(group);
        person.setNote(note);
        person.setPhotopath(photoPath);
    }

    public Person getPerson() {
        return person;
    }
}
