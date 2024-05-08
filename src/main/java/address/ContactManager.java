package address;
import java.util.*;

/**
 * ��ϵ�˹������࣬���ڹ�����ϵ�˷������ϵ����Ϣ��
 */
public class ContactManager {
    // �洢��ϵ�˷���Ͷ�Ӧ��ϵ���б�Ĺ�ϣӳ��
    private HashMap<String,List <Person>> contactGroups = new HashMap<>();
    // ����һ����������ʾ�ظ��Ĵ�����
    private static final int DUPLICATE =500;

    /**
     * �޲ι��캯��������һ���յ���ϵ�˹�������
     */
    public ContactManager() {}

    /**
     * ���������캯������ʼ����ϵ�˹�������
     *
     * @param contactGroups ��ϵ�˷���Ĺ�ϣӳ��
     */
    public ContactManager(HashMap<String,List <Person>> contactGroups){
        this.contactGroups = contactGroups;
    }

    /**
     * ���һ���µ���ϵ�˷��顣
     *
     * @param groupName Ҫ��ӵķ�������
     * @throws IllegalArgumentException ��������Ѵ��ڣ����׳��쳣
     */
    public void addContactGroup(String groupName) {
        if (contactGroups.containsKey(groupName)) {
            throw new IllegalArgumentException("�����Ѵ��ڣ�");
        }
        contactGroups.put(groupName, new ArrayList<Person>());
    }

    /**
     * ����ϵ�˹�������ɾ��һ����ϵ�˷��顣
     *
     * @param groupName Ҫɾ���ķ�������
     * @throws IllegalArgumentException ������鲻���ڣ����׳��쳣
     */
    public void removeContactGroup(String groupName) {
        if (!contactGroups.containsKey(groupName)) {
            throw new IllegalArgumentException("���鲻���ڣ�");
        }
        List<Person> persons= contactGroups.get(groupName);
        contactGroups.remove(groupName);
        for(Person person:persons) {
            // ������ϵ�˵ķ�����Ϣ���ӵ�ǰ�������Ƴ�
            String currentGroups = person.getGroup();
            String[] groups = currentGroups.split(",");
            for (int i = 0; i < groups.length; i++) {
                if (groups[i].equals(groupName)) {
                    groups[i] = "";
                }
            }
            StringBuilder updatedGroups = new StringBuilder();
            for (String group : groups) {
                updatedGroups.append(group).append(",");
            }
            person.setGroup(updatedGroups.toString().trim());
        }
    }

    /**
     * ��ѯ���е���ϵ�˷������ơ�
     *
     * @return ����һ���������з������Ƶļ���
     */
    public Set<String> getContactGroups() {
        return contactGroups.keySet();
    }

    /**
     * ��һ����ϵ����ӵ�ָ������ϵ�˷��顣
     *
     * @param person Ҫ��ӵ���ϵ��
     * @param groupName Ŀ���������
     * @throws IllegalArgumentException ������鲻���ڣ����׳��쳣
     */
    public void addPerson(Person person, String groupName) {
        if (!contactGroups.containsKey(groupName)) {
            throw new IllegalArgumentException("���鲻���ڣ�");
        }
        contactGroups.get(groupName).add(person);
        // ������ϵ�˷�����Ϣ������·���
        person.setGroup(person.getGroup()+groupName+",");
    }

    /**
     * ��ָ������ϵ�˷������Ƴ�һ����ϵ�ˡ�
     *
     * @param person Ҫ�Ƴ�����ϵ��
     * @param groupName Ŀ���������
     * @throws IllegalArgumentException ������鲻���ڻ�����ϵ�˲��ڸ÷����У����׳��쳣
     */
    public void removePerson(Person person, String groupName) {
        if (!contactGroups.containsKey(groupName)) {
            throw new IllegalArgumentException("���鲻���ڣ�");
        }
        List<Person> persons = contactGroups.get(groupName);
        if (!persons.contains(person)) {
            throw new IllegalArgumentException("����ϵ�˲��ڴ���ϵ����");
        }
        persons.remove(person);
        // ������ϵ�˷�����Ϣ���ӷ������Ƴ�
        String currentGroups = person.getGroup();
        String[] groups = currentGroups.split(",");
        for (int i = 0; i < groups.length; i++) {
            if (groups[i].equals(groupName)) {
                groups[i] = "";
            }
        }
        StringBuilder updatedGroups = new StringBuilder();
        for (String group : groups) {
            updatedGroups.append(group).append(",");
        }
        person.setGroup(updatedGroups.toString().trim());
    }

    /**
     * ������ϵ�˹������е���ϵ�˷�����Ϣ��
     *
     * @param contactGroups �µ���ϵ�˷����ϣӳ��
     */
    public void updateContactGroups(HashMap<String,List <Person>> contactGroups) {
        this.contactGroups = contactGroups;
    }
}
