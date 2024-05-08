package address;
import java.util.*;

/**
 * 联系人管理器类，用于管理联系人分组和联系人信息。
 */
public class ContactManager {
    // 存储联系人分组和对应联系人列表的哈希映射
    private HashMap<String,List <Person>> contactGroups = new HashMap<>();
    // 定义一个常量，表示重复的错误码
    private static final int DUPLICATE =500;

    /**
     * 无参构造函数，创建一个空的联系人管理器。
     */
    public ContactManager() {}

    /**
     * 带参数构造函数，初始化联系人管理器。
     *
     * @param contactGroups 联系人分组的哈希映射
     */
    public ContactManager(HashMap<String,List <Person>> contactGroups){
        this.contactGroups = contactGroups;
    }

    /**
     * 添加一个新的联系人分组。
     *
     * @param groupName 要添加的分组名称
     * @throws IllegalArgumentException 如果分组已存在，则抛出异常
     */
    public void addContactGroup(String groupName) {
        if (contactGroups.containsKey(groupName)) {
            throw new IllegalArgumentException("分组已存在！");
        }
        contactGroups.put(groupName, new ArrayList<Person>());
    }

    /**
     * 从联系人管理器中删除一个联系人分组。
     *
     * @param groupName 要删除的分组名称
     * @throws IllegalArgumentException 如果分组不存在，则抛出异常
     */
    public void removeContactGroup(String groupName) {
        if (!contactGroups.containsKey(groupName)) {
            throw new IllegalArgumentException("分组不存在！");
        }
        List<Person> persons= contactGroups.get(groupName);
        contactGroups.remove(groupName);
        for(Person person:persons) {
            // 更新联系人的分组信息，从当前分组中移除
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
     * 查询所有的联系人分组名称。
     *
     * @return 返回一个包含所有分组名称的集合
     */
    public Set<String> getContactGroups() {
        return contactGroups.keySet();
    }

    /**
     * 将一个联系人添加到指定的联系人分组。
     *
     * @param person 要添加的联系人
     * @param groupName 目标分组名称
     * @throws IllegalArgumentException 如果分组不存在，则抛出异常
     */
    public void addPerson(Person person, String groupName) {
        if (!contactGroups.containsKey(groupName)) {
            throw new IllegalArgumentException("分组不存在！");
        }
        contactGroups.get(groupName).add(person);
        // 更新联系人分组信息，添加新分组
        person.setGroup(person.getGroup()+groupName+",");
    }

    /**
     * 从指定的联系人分组中移除一个联系人。
     *
     * @param person 要移除的联系人
     * @param groupName 目标分组名称
     * @throws IllegalArgumentException 如果分组不存在或者联系人不在该分组中，则抛出异常
     */
    public void removePerson(Person person, String groupName) {
        if (!contactGroups.containsKey(groupName)) {
            throw new IllegalArgumentException("分组不存在！");
        }
        List<Person> persons = contactGroups.get(groupName);
        if (!persons.contains(person)) {
            throw new IllegalArgumentException("该联系人不在此联系人组");
        }
        persons.remove(person);
        // 更新联系人分组信息，从分组中移除
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
     * 更新联系人管理器中的联系人分组信息。
     *
     * @param contactGroups 新的联系人分组哈希映射
     */
    public void updateContactGroups(HashMap<String,List <Person>> contactGroups) {
        this.contactGroups = contactGroups;
    }
}
