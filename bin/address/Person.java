package address;

public class Person {
    private String name = new String();
    private String telephone = new String();
    private String phone = new String();
    private String email = new String();
    private String homeaddress = new String();
    private String birthday = new String();
    private String group = new String();
    private String note = new String();
    private String photopath = new String();
    private String postcode = new String();
    private String workaddress = new String();

    public Person(){

    }
    public String getPhotopath() {
        return this.photopath;
    }

    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }

    public Person(String name, String telephone, String phone, String email, String homeaddress, String birthday, String group, String note, String photopath, String postcode, String workaddress) {
        this.name = name;
        this.telephone = telephone;
        this.phone = phone;
        this.email = email;
        this.homeaddress = homeaddress;
        this.birthday = birthday;
        this.group = group;
        this.note = note;
        this.photopath = photopath;
        this.postcode = postcode;
        this.workaddress = workaddress;
    }
    public String getTelephone() {
        return this.telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmail() {
        return this.email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getHomeaddress() {
        return this.homeaddress;
    }
    public void setHomeaddress(String homeaddress) {
        this.homeaddress = homeaddress;
    }
    public String getBirthday() {
        return this.birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getGroup() {
        return this.group;
    }
    public void setGroup(String group) {
        this.group = group;
    }
    public String getNote() {
        return this.note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    public String getPostcode() {
        return this.postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    public String getWorkaddress() {
        return this.workaddress;
    }
    public void setWorkaddress(String workaddress) {
        this.workaddress = workaddress;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
