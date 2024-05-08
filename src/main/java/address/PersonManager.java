package address;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * PersonManager�����ڹ��������ϵ����Ϣ��
 * ���ṩ����ӡ�ɾ������ѯ���޸���ϵ����Ϣ�ķ�����
 */
public class PersonManager {
    private List<Person> personList = new ArrayList<>(); // �洢������ϵ��
    private sift contactSifter = new sift(); // ����ɸѡ��ϵ��
    private AddPerson addPerson = new AddPerson(); // ���������ϵ��

    /**
     * ���캯������ʼ��һ���յ���ϵ���б�
     */
    PersonManager() {
    }

    /**
     * ���캯������ʼ��ʱ�����ṩ����ϵ���б����personList��
     *
     * @param personList ��ʼ����ϵ���б�
     */
    PersonManager(List<Person> personList) {
        this.personList = personList;
        this.contactSifter.setOriginalContacts(personList);
    }

    /**
     * ���һ���µ���ϵ�˵��������С�
     *
     * @param name ��ϵ������
     * @param telephone ��ϵ�˵绰
     * @param phone ��ϵ���ֻ�
     * @param email ��ϵ������
     * @param birthday ��ϵ������
     * @param workaddress ��ϵ�˹�����ַ
     * @param homeaddress ��ϵ�˼�ͥ��ַ
     * @param postcode ��ϵ����������
     * @param group ��ϵ���������
     * @param note ��ϵ�˱�ע��Ϣ
     * @param photopath ��ϵ����Ƭ·��
     * @param gender ��ϵ���Ա�
     * @param contactManager ��ϵ��������������ڹ�����ϵ�˷���
     * @param groupName ��ϵ����������
     */
    public void addPerson(String name, String telephone, String phone, String email, String birthday, String workaddress, String homeaddress, String postcode, String group, String note, String photopath,String gender,ContactManager contactManager, String groupName) {
        Person person = new Person();
        addPerson.createPerson(name, telephone, phone, email, birthday, workaddress, homeaddress, postcode, group, note, photopath, gender);
        personList.add(person); // �����ϵ�˵�personList
        contactManager.addPerson(person, groupName); // �����ϵ�˵�ָ���ķ���
    }

    /**
     * �ӹ�������ɾ��һ����ϵ�ˡ�
     *
     * @param person Ҫɾ������ϵ�˶���
     * @param contactManager ��ϵ��������������ڴ�����ɾ����ϵ��
     */
    public void deletePerson(Person person, ContactManager contactManager) {
        personList.remove(person); // ��personList��ɾ����ϵ��

        String[] groupNames = person.getGroup().split(","); // ����ϵ�����������ָ�Ϊ����

        for (String groupName : groupNames) {
            contactManager.removePerson(person, groupName); // ��ÿ������ɾ����ϵ��
        }
    }

    /**
     * ���ݲ�ѯ����������ϵ�ˡ�
     *
     * @param query ��ѯ����
     * @return ����ƥ�����ϵ���б�
     */
    public List<Person> searchContacts(String query) {
        contactSifter.updateFilteredContacts(query); // ���ݲ�ѯ��������ɸѡ�����ϵ���б�
        return contactSifter.getFilteredContacts(); // ����ɸѡ�����ϵ���б�
    }

    /**
     * �޸�һ���Ѵ��ڵ���ϵ�˵���Ϣ��
     *
     * @param name �µ���ϵ������
     * @param telephone �µ���ϵ�˵绰
     * @param phone �µ���ϵ���ֻ�
     * @param email �µ���ϵ������
     * @param birthday �µ���ϵ������
     * @param workaddress �µ���ϵ�˹�����ַ
     * @param homeaddress �µ���ϵ�˼�ͥ��ַ
     * @param postcode �µ���ϵ����������
     * @param group �µ���ϵ���������
     * @param note �µ���ϵ�˱�ע��Ϣ
     * @param photopath �µ���ϵ����Ƭ·��
     * @param gender �µ���ϵ���Ա�
     */
    public void modifyPerson(String name, String telephone, String phone, String email, String birthday, String workaddress, String homeaddress, String postcode, String group, String note, String photopath,String gender) {

    }
}
