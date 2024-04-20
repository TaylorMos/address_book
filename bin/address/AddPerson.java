package address;

public class AddPerson {

    /**
     * �����û���������ݴ���Person����
     *
     * @param name          ����
     * @param telephone     �绰
     * @param phone         �ֻ�
     * @param email         ��������
     * @param homeAddress   ��ͥסַ
     * @param birthday      ����
     * @param group         ���
     * @param note          ��ע
     * @param imagePath     ͼƬ·��
     * @param postcode      �ʱ�
     * @param workAddress   ������
     * @return ������Person����
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
