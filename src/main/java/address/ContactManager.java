package address;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static jdk.nashorn.tools.Shell.SUCCESS;
import static org.ietf.jgss.GSSException.FAILURE;

public class ContactManager {
    private static final int DUPLICATE =500;
    private ObservableList<String> contactGroups = FXCollections.observableArrayList();
    private ObservableList<Person> persons = FXCollections.observableArrayList();

    // ���캯��
    public ContactManager() {}

    // �����ϵ�˷���
    public int addContactGroup(String groupName) {
        if (!contactGroups.contains(groupName)) {
            contactGroups.add(groupName);
            return SUCCESS;
        } else {
            return FAILURE;
        }
    }

    // ɾ����ϵ�˷���
    public void removeContactGroup(String groupName) {
        contactGroups.remove(groupName);
    }

    // ��ѯ��ϵ�˷���
    public Set<String> getContactGroups() {
        return new HashSet<>(contactGroups);
    }

    // ���Person����
    public void addPerson(Person person) {
        persons.add(person);
    }

    // ���ݷ���������Person����ķ�����Ϣ
    public void updatePersonGroup(String oldGroupName, String newGroupName) {
        for (Person person : persons) {
            String[] groups = person.getGroup().split(",");
            if (Arrays.asList(groups).contains(oldGroupName)) {
                StringBuilder updatedGroups = new StringBuilder();
                for (String group : groups) {
                    if (!group.equals(oldGroupName)) {
                        updatedGroups.append(group).append(",");
                    } else {
                        updatedGroups.append(newGroupName).append(",");
                    }
                }
                person.setGroup(updatedGroups.toString().trim());
            }
        }
    }
    // ��Person�������ָ������ϵ������
    public int assignPersonToGroup(Person person, String groupName) {
        if (!contactGroups.contains(groupName)) {
            throw new IllegalArgumentException("�����ڸ���ϵ���飡");
        }

        // ��ȡ��ǰ��Ա���еķ����ַ���
        String currentGroups = person.getGroup();

        // �����ǰ��Ա��δ�����κη��飬��ֱ�������·���
        if (currentGroups.isEmpty()) {
            person.setGroup(groupName);
            return SUCCESS;
        } else {
            // ���з��������£����·�����ӵ������б���
            String[] existingGroups = currentGroups.split(",");
            Set<String> groupSet = new HashSet<>(Arrays.asList(existingGroups));
            if (groupSet.contains(groupName)) {
                return DUPLICATE;
            }
            groupSet.add(groupName);

            // ������Ա�ķ�����ϢΪ���ŷָ����ַ���
            StringBuilder updatedGroups = new StringBuilder();
            for (String group : groupSet) {
                updatedGroups.append(group).append(",");
            }
            person.setGroup(updatedGroups.toString().trim());
            return SUCCESS;
        }
    }
    // ���ݷ�������ȡ��Ӧ��Person�����б�
    public ObservableList<Person> getPersonsByGroup(String groupName) {
        ObservableList<Person> groupMembers = FXCollections.observableArrayList();

        for (Person person : persons) {
            String[] groups = person.getGroup().split(",");
            if (Arrays.asList(groups).contains(groupName)) {
                groupMembers.add(person);
            }
        }

        return groupMembers;
    }
    // ��ȡ����Person����
    public ObservableList<Person> getAllPersons() {
        return persons;
    }
}
