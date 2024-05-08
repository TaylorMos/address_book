/**
 * Person类用于表示一个人的信息，包括基本信息和联系信息等。
 */
package address;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Person {
    private String name; // 姓名
    private String telephone; // 固定电话
    private String phone; // 手机号码
    private String email; // 电子邮箱
    private String homeaddress; // 家庭地址
    private String birthday; // 生日
    private String group; // 分组
    private String note; // 备注
    private String photopath; // 照片路径
    private String postcode; // 邮编
    private String workaddress; // 工作地址
    private String gender; // 性别

    /**
     * 构造函数，初始化一个Person对象的所有属性为默认值。
     */
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
        this.gender = "";
    }

    /**
     * 构造函数，初始化一个Person对象的所有属性。
     *
     * @param name 姓名
     * @param telephone 固定电话
     * @param phone 手机号码
     * @param email 电子邮箱
     * @param homeaddress 家庭地址
     * @param birthday 生日
     * @param group 分组
     * @param note 备注
     * @param photopath 照片路径
     * @param postcode 邮编
     * @param workaddress 工作地址
     * @param gender 性别
     * @throws IllegalArgumentException 当电话、手机号码、邮编格式错误或生日不是有效日期时抛出。
     */
    public Person(String name, String telephone, String phone, String email, String homeaddress, String birthday, String group, String note, String photopath, String postcode, String workaddress,String gender) {
        // 校验电话号码和邮编格式，以及生日和性别的有效性
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
        //校验性别
        if (!gender.equals("男") && !gender.equals("女")) {
            throw new IllegalArgumentException("性别只能是 '男' 或 '女'");
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
        this.gender = gender;
    }

    // 以下为各属性的get和set方法，包括参数验证。

    public String getTelephone() {
        return this.telephone;
    }
    public void setTelephone(String telephone) {
        if (!telephone.matches("\\d{11,12}")) {
            throw new IllegalArgumentException("固定电话长度必须为11位或12位数.");
        }
        this.telephone = telephone;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        if (!phone.matches("\\d{11}")) {
            throw new IllegalArgumentException("手机号码必须为11位数.");
        }
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
        // 校验生日的格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false);
        try {
            sdf.parse(birthday);
        } catch (ParseException e) {
            throw new IllegalArgumentException("生日的格式应该类似于 '20030123'.");
        }
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
        if (!postcode.matches("\\d{6}")) {
            throw new IllegalArgumentException("邮编必须为6位数");
        }
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

    // Getter和Setter方法为照片路径提供支持。
    public void setPhotopath(String imagePath) {
    }

    public String getPhotopath() {
        return this.photopath;
    }

    // 获取性别
    public String getGender() {
        return this.gender;
    }

    // 设置性别，校验性别值的有效性
    public void setGender(String gender) {
        if (!gender.equals("男") && !gender.equals("女")) {
            throw new IllegalArgumentException("性别只能是 '男' 或 '女'");
        }
        this.gender = gender;
    }
}
