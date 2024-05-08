package address;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * PersonManager类用于管理个人联系人信息。
 * 它提供了添加、删除、查询和修改联系人信息的方法。
 */
public class PersonManager {
    private List<Person> personList = new ArrayList<>(); // 存储所有联系人
    private sift contactSifter = new sift(); // 用于筛选联系人
    private AddPerson addPerson = new AddPerson(); // 辅助添加联系人

    /**
     * 构造函数，初始化一个空的联系人列表。
     */
    PersonManager() {
    }

    /**
     * 构造函数，初始化时根据提供的联系人列表填充personList。
     *
     * @param personList 初始的联系人列表
     */
    PersonManager(List<Person> personList) {
        this.personList = personList;
        this.contactSifter.setOriginalContacts(personList);
    }

    /**
     * 添加一个新的联系人到管理器中。
     *
     * @param name 联系人姓名
     * @param telephone 联系人电话
     * @param phone 联系人手机
     * @param email 联系人邮箱
     * @param birthday 联系人生日
     * @param workaddress 联系人工作地址
     * @param homeaddress 联系人家庭地址
     * @param postcode 联系人邮政编码
     * @param group 联系人所属组别
     * @param note 联系人备注信息
     * @param photopath 联系人照片路径
     * @param gender 联系人性别
     * @param contactManager 联系人组管理器，用于管理联系人分组
     * @param groupName 联系人所属组名
     */
    public void addPerson(String name, String telephone, String phone, String email, String birthday, String workaddress, String homeaddress, String postcode, String group, String note, String photopath,String gender,ContactManager contactManager, String groupName) {
        Person person = new Person();
        addPerson.createPerson(name, telephone, phone, email, birthday, workaddress, homeaddress, postcode, group, note, photopath, gender);
        personList.add(person); // 添加联系人到personList
        contactManager.addPerson(person, groupName); // 添加联系人到指定的分组
    }

    /**
     * 从管理器中删除一个联系人。
     *
     * @param person 要删除的联系人对象
     * @param contactManager 联系人组管理器，用于从组中删除联系人
     */
    public void deletePerson(Person person, ContactManager contactManager) {
        personList.remove(person); // 从personList中删除联系人

        String[] groupNames = person.getGroup().split(","); // 将联系人所属组名分割为数组

        for (String groupName : groupNames) {
            contactManager.removePerson(person, groupName); // 从每个组中删除联系人
        }
    }

    /**
     * 根据查询条件搜索联系人。
     *
     * @param query 查询条件
     * @return 返回匹配的联系人列表
     */
    public List<Person> searchContacts(String query) {
        contactSifter.updateFilteredContacts(query); // 根据查询条件更新筛选后的联系人列表
        return contactSifter.getFilteredContacts(); // 返回筛选后的联系人列表
    }

    /**
     * 修改一个已存在的联系人的信息。
     *
     * @param name 新的联系人姓名
     * @param telephone 新的联系人电话
     * @param phone 新的联系人手机
     * @param email 新的联系人邮箱
     * @param birthday 新的联系人生日
     * @param workaddress 新的联系人工作地址
     * @param homeaddress 新的联系人家庭地址
     * @param postcode 新的联系人邮政编码
     * @param group 新的联系人所属组别
     * @param note 新的联系人备注信息
     * @param photopath 新的联系人照片路径
     * @param gender 新的联系人性别
     */
    public void modifyPerson(String name, String telephone, String phone, String email, String birthday, String workaddress, String homeaddress, String postcode, String group, String note, String photopath,String gender) {

    }
}
