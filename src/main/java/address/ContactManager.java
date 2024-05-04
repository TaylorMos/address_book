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

    // 构造函数
    public ContactManager() {}

    // 添加联系人分组
    public int addContactGroup(String groupName) {
        if (!contactGroups.contains(groupName)) {
            contactGroups.add(groupName);
            return SUCCESS;
        } else {
            return FAILURE;
        }
    }

    // 删除联系人分组
    public void removeContactGroup(String groupName) {
        contactGroups.remove(groupName);
    }

    // 查询联系人分组
    public Set<String> getContactGroups() {
        return new HashSet<>(contactGroups);
    }

    // 添加Person对象
    public void addPerson(Person person) {
        persons.add(person);
    }

    // 根据分组名更新Person对象的分组信息
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
    // 将Person对象放入指定的联系人组中
    public int assignPersonToGroup(Person person, String groupName) {
        if (!contactGroups.contains(groupName)) {
            throw new IllegalArgumentException("不存在该联系人组！");
        }

        // 获取当前人员已有的分组字符串
        String currentGroups = person.getGroup();

        // 如果当前人员尚未分配任何分组，则直接设置新分组
        if (currentGroups.isEmpty()) {
            person.setGroup(groupName);
            return SUCCESS;
        } else {
            // 已有分组的情况下，将新分组添加到分组列表中
            String[] existingGroups = currentGroups.split(",");
            Set<String> groupSet = new HashSet<>(Arrays.asList(existingGroups));
            if (groupSet.contains(groupName)) {
                return DUPLICATE;
            }
            groupSet.add(groupName);

            // 更新人员的分组信息为逗号分隔的字符串
            StringBuilder updatedGroups = new StringBuilder();
            for (String group : groupSet) {
                updatedGroups.append(group).append(",");
            }
            person.setGroup(updatedGroups.toString().trim());
            return SUCCESS;
        }
    }
    // 根据分组名获取对应的Person对象列表
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
    // 获取所有Person对象
    public ObservableList<Person> getAllPersons() {
        return persons;
    }
}
