package address;

public class AddPerson {

    /**
     * 根据用户输入的数据创建Person对象。
     *
     * @param name          姓名
     * @param telephone     电话
     * @param phone         手机
     * @param email         电子邮箱
     * @param homeAddress   家庭住址
     * @param birthday      生日
     * @param group         组别
     * @param note          备注
     * @param imagePath     图片路径
     * @param postcode      邮编
     * @param workAddress   工作地
     * @return 创建的Person对象
     */
    public static Person createPerson(String name, String telephone, String phone, String email, String homeAddress, String birthday, String group, String note, String imagePath, String postcode, String workAddress) {
        Person person = new Person();
        person.setName(name);
        person.setTelephone(telephone);
        person.setPhone(phone);
        person.setEmail(email);
        person.setHomeaddress(homeAddress);
        person.setBirthday(birthday);
        person.setGroup(group);
        person.setNote(note);
        person.setPhotopath(imagePath);
        person.setPostcode(postcode);
        person.setWorkaddress(workAddress);
        return person;
    }
}
