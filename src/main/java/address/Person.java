/**
 * Person�����ڱ�ʾһ���˵���Ϣ������������Ϣ����ϵ��Ϣ�ȡ�
 */
package address;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Person {
    private String name; // ����
    private String telephone; // �̶��绰
    private String phone; // �ֻ�����
    private String email; // ��������
    private String homeaddress; // ��ͥ��ַ
    private String birthday; // ����
    private String group; // ����
    private String note; // ��ע
    private String photopath; // ��Ƭ·��
    private String postcode; // �ʱ�
    private String workaddress; // ������ַ
    private String gender; // �Ա�

    /**
     * ���캯������ʼ��һ��Person�������������ΪĬ��ֵ��
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
     * ���캯������ʼ��һ��Person������������ԡ�
     *
     * @param name ����
     * @param telephone �̶��绰
     * @param phone �ֻ�����
     * @param email ��������
     * @param homeaddress ��ͥ��ַ
     * @param birthday ����
     * @param group ����
     * @param note ��ע
     * @param photopath ��Ƭ·��
     * @param postcode �ʱ�
     * @param workaddress ������ַ
     * @param gender �Ա�
     * @throws IllegalArgumentException ���绰���ֻ����롢�ʱ��ʽ��������ղ�����Ч����ʱ�׳���
     */
    public Person(String name, String telephone, String phone, String email, String homeaddress, String birthday, String group, String note, String photopath, String postcode, String workaddress,String gender) {
        // У��绰������ʱ��ʽ���Լ����պ��Ա����Ч��
        if (!phone.matches("\\d{11}")) {
            throw new IllegalArgumentException("�ֻ��������Ϊ11λ��.");
        }
        if (!telephone.matches("\\d{11,12}")) {
            throw new IllegalArgumentException("�̶��绰���ȱ���Ϊ11λ��12λ��.");
        }

        // У���ʱ�
        if (!postcode.matches("\\d{6}")) {
            throw new IllegalArgumentException("�ʱ����Ϊ6λ��");
        }

        // У������
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false);
        try {
            sdf.parse(birthday);
        } catch (ParseException e) {
            throw new IllegalArgumentException("���յĸ�ʽӦ�������� '20030123'.");
        }
        //У���Ա�
        if (!gender.equals("��") && !gender.equals("Ů")) {
            throw new IllegalArgumentException("�Ա�ֻ���� '��' �� 'Ů'");
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

    // ����Ϊ�����Ե�get��set����������������֤��

    public String getTelephone() {
        return this.telephone;
    }
    public void setTelephone(String telephone) {
        if (!telephone.matches("\\d{11,12}")) {
            throw new IllegalArgumentException("�̶��绰���ȱ���Ϊ11λ��12λ��.");
        }
        this.telephone = telephone;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        if (!phone.matches("\\d{11}")) {
            throw new IllegalArgumentException("�ֻ��������Ϊ11λ��.");
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
        // У�����յĸ�ʽ
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setLenient(false);
        try {
            sdf.parse(birthday);
        } catch (ParseException e) {
            throw new IllegalArgumentException("���յĸ�ʽӦ�������� '20030123'.");
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
            throw new IllegalArgumentException("�ʱ����Ϊ6λ��");
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

    // Getter��Setter����Ϊ��Ƭ·���ṩ֧�֡�
    public void setPhotopath(String imagePath) {
    }

    public String getPhotopath() {
        return this.photopath;
    }

    // ��ȡ�Ա�
    public String getGender() {
        return this.gender;
    }

    // �����Ա�У���Ա�ֵ����Ч��
    public void setGender(String gender) {
        if (!gender.equals("��") && !gender.equals("Ů")) {
            throw new IllegalArgumentException("�Ա�ֻ���� '��' �� 'Ů'");
        }
        this.gender = gender;
    }
}
