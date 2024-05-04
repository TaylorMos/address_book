package address;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
        this.name = "";
        this.telephone = "";
        this.phone = "";
        this.email = "";
        this.homeaddress = "";
        this.birthday = "";
        this.group = "";
        this.note = "";
        this.photopath = "";
        this.postcode = "";
        this.workaddress = "";
    }
    public Person(String name, String telephone, String phone, String email, String homeaddress, String birthday, String group, String note, String photopath, String postcode, String workaddress) {
        // 校验电话号码
        if (!phone.matches("\\d{11}")) {
            throw new IllegalArgumentException("手机号码必须为11位数.");
        }
        if (!telephone.matches("\\d{11,12}")) {
            throw new IllegalArgumentException("固定电话长度必须为11位或12位数.");
        }

        // 校验邮编
        if (!postcode.matches("\\d{6}")) {
            throw new IllegalArgumentException("邮编必须为6位数");
        }

        // 校验生日
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false);
        try {
            sdf.parse(birthday);
        } catch (ParseException e) {
            throw new IllegalArgumentException("生日的格式应该类似于 '20030123'.");
        }

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

    public void setPhotopath(String imagePath) {
    }

    public String getPhotopath() {
        return this.photopath;
    }
}
