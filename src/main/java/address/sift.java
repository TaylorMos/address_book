package address;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * sift类用于管理和筛选联系人信息。
 */
public class sift {

    private List<Person> originalContacts; // 原始联系人列表
    private List<Person> filteredContacts; // 筛选后的联系人列表

    /**
     * 构造函数，初始化原始联系人和筛选后联系人列表。
     */
    public sift() {
        this.originalContacts = new ArrayList<>();
        this.filteredContacts = new ArrayList<>();
    }

    /**
     * 构造函数，通过数组初始化原始联系人列表，并添加信息。
     *
     * @param information 联系人信息数组
     */
    public sift(String[] information) {
        this();
        addInformation(information);
    }

    /**
     * 通过字符串数组添加联系人信息。
     *
     * @param strInformation 联系人信息数组
     */
    public void addInformation(String[] strInformation) {
        for (int i = 0; i < strInformation.length; ++i) {
            addInformation(strInformation[i]);
        }
    }

    /**
     * 通过单个字符串添加联系人信息。
     *
     * @param strInformation 单个联系人信息字符串
     */
    public void addInformation(String strInformation) {
        if (strInformation.trim().length() >= 1) {
            // 解析字符串并创建Person对象，添加到列表中
            String[] strPerson = strInformation.trim().split(",");
            Person person = new Person(
                    strPerson[0],
                    strPerson[1],
                    strPerson[2],
                    strPerson[3],
                    strPerson[4],
                    strPerson[5],
                    strPerson[6],
                    strPerson[7],
                    strPerson[8],
                    strPerson[9],
                    strPerson[10]
                    ,strPerson[11]
            );
            originalContacts.add(person);
            filteredContacts.add(person);
        }
    }

    /**
     * 根据索引获取联系人信息。
     *
     * @param index 联系人索引
     * @return 返回指定索引的联系人对象
     */
    public Person getInformation(int index) {
        return originalContacts.get(index);
    }

    /**
     * 获取原始联系人列表的长度。
     *
     * @return 返回原始联系人列表的大小
     */
    public int getInformationLength() {
        return originalContacts.size();
    }

    /**
     * 清除所有联系人信息。
     */
    public void cleanData() {
        filteredContacts.clear();
        originalContacts.clear();
    }

    /**
     * 验证字符串是否只包含英文字符。
     *
     * @param tap 待验证字符串
     * @return 如果只包含英文字符返回true，否则返回false
     */
    public static boolean isWord(String tap) {
        Pattern pattern = Pattern.compile("([a-z]|[A-Z])*");
        return pattern.matcher(tap).matches();
    }

    /**
     * 验证字符串是否只包含数字。
     *
     * @param str 待验证字符串
     * @return 如果只包含数字返回true，否则返回false
     */
    public static boolean isNumber(String str) {
        for (int i = 0; i < str.length(); ++i) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 验证字符串是否包含中文字符。
     *
     * @param str 待验证字符串
     * @return 如果包含中文字符返回true，否则返回false
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[一-龥]");
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * 根据文本筛选联系人信息。
     *
     * @param text 筛选文本
     */
    public void updateFilteredContacts(String text) {
        filteredContacts.clear();
        if (text.trim().length() < 1) {
            // 如果文本为空，则将所有联系人添加到筛选列表
            filteredContacts.addAll(originalContacts);
            System.out.println("字段为空");
        } else {
            // 根据文本类型（中文、数字、英文）筛选联系人
            Person data;
            for (Person person : originalContacts) {
                data = person;
                if (isContainChinese(text)) {
                    // 如果文本包含中文，筛选出姓名或组名包含文本的联系人
                    if (person.getName().indexOf(text) != -1 || person.getGroup().indexOf(text) != -1) {
                        filteredContacts.add(data);
                    }
                } else if (isNumber(text)) {
                    // 如果文本为数字，筛选出电话号码或传真号码包含文本的联系人
                    if (person.getPhone().indexOf(text) != -1 || person.getTelephone().indexOf(text) != -1) {
                        filteredContacts.add(data);
                    }
                } else if (isWord(text)) {
                    // 如果文本为英文，筛选出姓名的拼音或首字母包含文本的联系人
                    String pinyinname = PinYin.getPinYin(person.getName());
                    String capitalname = PinYin.getPinYinHeadCharLower(person.getName());
                    if (pinyinname.indexOf(text) != -1 || capitalname.indexOf(text) != -1) {
                        filteredContacts.add(data);
                    }
                }
            }
        }
    }

    /**
     * 获取筛选后的联系人列表。
     *
     * @return 返回筛选后的联系人列表
     */
    public List<Person> getFilteredContacts() {
        return filteredContacts;
    }

    /**
     * 获取原始联系人列表。
     *
     * @return 返回原始联系人列表
     */
    public List<Person> getOriginalContacts() {
        return originalContacts;
    }

    /**
     * 设置原始联系人列表。
     *
     * @param originalContacts 原始联系人列表
     */
    public void setOriginalContacts(List<Person> originalContacts) {
        this.originalContacts = originalContacts;
    }
}

