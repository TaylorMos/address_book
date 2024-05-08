package address;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * sift�����ڹ����ɸѡ��ϵ����Ϣ��
 */
public class sift {

    private List<Person> originalContacts; // ԭʼ��ϵ���б�
    private List<Person> filteredContacts; // ɸѡ�����ϵ���б�

    /**
     * ���캯������ʼ��ԭʼ��ϵ�˺�ɸѡ����ϵ���б�
     */
    public sift() {
        this.originalContacts = new ArrayList<>();
        this.filteredContacts = new ArrayList<>();
    }

    /**
     * ���캯����ͨ�������ʼ��ԭʼ��ϵ���б��������Ϣ��
     *
     * @param information ��ϵ����Ϣ����
     */
    public sift(String[] information) {
        this();
        addInformation(information);
    }

    /**
     * ͨ���ַ������������ϵ����Ϣ��
     *
     * @param strInformation ��ϵ����Ϣ����
     */
    public void addInformation(String[] strInformation) {
        for (int i = 0; i < strInformation.length; ++i) {
            addInformation(strInformation[i]);
        }
    }

    /**
     * ͨ�������ַ��������ϵ����Ϣ��
     *
     * @param strInformation ������ϵ����Ϣ�ַ���
     */
    public void addInformation(String strInformation) {
        if (strInformation.trim().length() >= 1) {
            // �����ַ���������Person������ӵ��б���
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
     * ����������ȡ��ϵ����Ϣ��
     *
     * @param index ��ϵ������
     * @return ����ָ����������ϵ�˶���
     */
    public Person getInformation(int index) {
        return originalContacts.get(index);
    }

    /**
     * ��ȡԭʼ��ϵ���б�ĳ��ȡ�
     *
     * @return ����ԭʼ��ϵ���б�Ĵ�С
     */
    public int getInformationLength() {
        return originalContacts.size();
    }

    /**
     * ���������ϵ����Ϣ��
     */
    public void cleanData() {
        filteredContacts.clear();
        originalContacts.clear();
    }

    /**
     * ��֤�ַ����Ƿ�ֻ����Ӣ���ַ���
     *
     * @param tap ����֤�ַ���
     * @return ���ֻ����Ӣ���ַ�����true�����򷵻�false
     */
    public static boolean isWord(String tap) {
        Pattern pattern = Pattern.compile("([a-z]|[A-Z])*");
        return pattern.matcher(tap).matches();
    }

    /**
     * ��֤�ַ����Ƿ�ֻ�������֡�
     *
     * @param str ����֤�ַ���
     * @return ���ֻ�������ַ���true�����򷵻�false
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
     * ��֤�ַ����Ƿ���������ַ���
     *
     * @param str ����֤�ַ���
     * @return ������������ַ�����true�����򷵻�false
     */
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[һ-��]");
        Matcher m = p.matcher(str);
        return m.find();
    }

    /**
     * �����ı�ɸѡ��ϵ����Ϣ��
     *
     * @param text ɸѡ�ı�
     */
    public void updateFilteredContacts(String text) {
        filteredContacts.clear();
        if (text.trim().length() < 1) {
            // ����ı�Ϊ�գ���������ϵ����ӵ�ɸѡ�б�
            filteredContacts.addAll(originalContacts);
            System.out.println("�ֶ�Ϊ��");
        } else {
            // �����ı����ͣ����ġ����֡�Ӣ�ģ�ɸѡ��ϵ��
            Person data;
            for (Person person : originalContacts) {
                data = person;
                if (isContainChinese(text)) {
                    // ����ı��������ģ�ɸѡ�����������������ı�����ϵ��
                    if (person.getName().indexOf(text) != -1 || person.getGroup().indexOf(text) != -1) {
                        filteredContacts.add(data);
                    }
                } else if (isNumber(text)) {
                    // ����ı�Ϊ���֣�ɸѡ���绰��������������ı�����ϵ��
                    if (person.getPhone().indexOf(text) != -1 || person.getTelephone().indexOf(text) != -1) {
                        filteredContacts.add(data);
                    }
                } else if (isWord(text)) {
                    // ����ı�ΪӢ�ģ�ɸѡ��������ƴ��������ĸ�����ı�����ϵ��
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
     * ��ȡɸѡ�����ϵ���б�
     *
     * @return ����ɸѡ�����ϵ���б�
     */
    public List<Person> getFilteredContacts() {
        return filteredContacts;
    }

    /**
     * ��ȡԭʼ��ϵ���б�
     *
     * @return ����ԭʼ��ϵ���б�
     */
    public List<Person> getOriginalContacts() {
        return originalContacts;
    }

    /**
     * ����ԭʼ��ϵ���б�
     *
     * @param originalContacts ԭʼ��ϵ���б�
     */
    public void setOriginalContacts(List<Person> originalContacts) {
        this.originalContacts = originalContacts;
    }
}

