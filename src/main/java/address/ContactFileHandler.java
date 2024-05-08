package address;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.parameter.ImageType;
import ezvcard.parameter.TelephoneType;
import ezvcard.property.*;
import javafx.util.Pair;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.io.InputStream;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 处理联系人文件的类，包括从CSV和vCard文件中读取联系人信息，以及将联系人信息写入到CSV和vCard文件中。
 */
public class ContactFileHandler {

    /**
     * 从CSV文件中读取联系人信息。
     *
     * @param csvFile 要读取的CSV文件。
     * @return 返回一个Pair对象，其中包含一个HashMap，键为组名，值为该组的联系人列表；以及一个独立的联系人列表。
     */
    public Pair<HashMap<String, List<Person>>, List<Person>> readContactsFromCSV(File csvFile) {
        List<Person> contacts = new ArrayList<>();
        HashMap<String, List<Person>> contactsGroup = new HashMap<>();
        CsvReader reader = null;
        try {
            reader = new CsvReader(new FileReader(csvFile));
            reader.readHeaders(); // 读取表头

            // 将表头映射到列索引
            Map<String, Integer> headerMap = new HashMap<>();
            for (int i = 0; i < reader.getHeaderCount(); i++) {
                headerMap.put(reader.getHeader(i), i);
            }

            // 读取记录并填充联系人信息
            while (reader.readRecord()) {
                String name = reader.get(headerMap.get("Name"));
                String telephone = reader.get(headerMap.get("Telephone"));
                String phone = reader.get(headerMap.get("Phone"));
                String email = reader.get(headerMap.get("Email"));
                String homeaddress = reader.get(headerMap.get("HomeAddress"));
                String birthday = reader.get(headerMap.get("Birthday"));
                String group = reader.get(headerMap.get("Group"));
                String note = reader.get(headerMap.get("Note"));
                String photopath = reader.get(headerMap.get("PhotoPath"));
                String postcode = reader.get(headerMap.get("Postcode"));
                String workaddress = reader.get(headerMap.get("WorkAddress"));
                String gender = reader.get(headerMap.get("Gender"));
                Person person = new Person(name, telephone, phone, email, homeaddress, birthday, group, note, photopath, postcode, workaddress, gender);
                contacts.add(person);
                if (!contactsGroup.containsKey(group)) {
                    contactsGroup.put(group, new ArrayList<>());
                }
                contactsGroup.get(group).add(person);
            }
        } catch (IOException e) {
            System.out.println("读取CSV文件出错：" + e.getMessage());
            return new Pair<>(contactsGroup, contacts);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return new Pair<>(contactsGroup, contacts);
    }

    /**
     * 将联系人信息写入CSV文件。
     *
     * @param contacts 要写入的联系人列表。
     * @param csvFile 要写入的CSV文件。
     */
    public void writeContactsToCSV(List<Person> contacts, File csvFile) {
        CsvWriter writer = null;
        try {
            writer = new CsvWriter(new FileWriter(csvFile), ',');

            // 定义表头
            String[] headers = {"Name", "Telephone", "Phone", "Email", "Home Address", "Birthday", "Group", "Note", "Photo Path", "Postcode", "Work Address", "Gender"};

            // 写入表头
            writer.writeRecord(headers);

            // 写入联系人信息
            for (Person person : contacts) {
                String[] entries = {person.getName(), person.getTelephone(), person.getPhone(), person.getEmail(),
                        person.getHomeaddress(), person.getBirthday(), person.getGroup(), person.getNote(),
                        person.getPhotopath(), person.getPostcode(), person.getWorkaddress(), person.getGender()};
                writer.writeRecord(entries);
            }
        } catch (IOException e) {
            System.out.println("写入CSV文件出错：" + e.getMessage());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * 将联系人信息保存到新的CSV文件。
     *
     * @param contacts 要写入的联系人列表。
     * @param name CSV文件名字
     */
    public void writeContactsToCSV(List<Person> contacts, String name) {
        File file = new File("/personFile/csv/" + name + ".csv");
        CsvWriter writer = null;
        try {
            writer = new CsvWriter(new FileWriter(file), ',');
            // 添加表头
            String[] headers = {"Name", "Telephone", "Phone", "Email", "HomeAddress", "Birthday", "Group", "Note", "PhotoPath", "Postcode", "WorkAddress", "Gender"};
            writer.writeRecord(headers);
            // 遍历联系人列表，写入CSV文件
            for (Person person : contacts) {
                String[] entries = {person.getName(), person.getTelephone(), person.getPhone(), person.getEmail(),
                        person.getHomeaddress(), person.getBirthday(), person.getGroup(), person.getNote(), person.getPhotopath(),
                        person.getPostcode(), person.getWorkaddress(), person.getGender()};
                writer.writeRecord(entries);
            }
        } catch (IOException e) {
            System.out.println("写入CSV文件出错：" + e.getMessage());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * 从vCard文件中读取联系人信息。
     *
     * @param vCardFile 要读取的vCard文件。
     * @return 返回一个Pair对象，其中包含一个HashMap，键为组名，值为该组的联系人列表；以及一个独立的联系人列表。
     */
    public Pair<HashMap<String, List<Person>>, List<Person>> readContactsFromVCard(File vCardFile) {
        List<Person> contacts = new ArrayList<>();
        HashMap<String, List<Person>> contactsGroup = new HashMap<>();

        try {
            List<VCard> vcards = Ezvcard.parse(vCardFile).all();
            for (VCard vcard : vcards) {
                String name = vcard.getFormattedName().getValue();
                String email = vcard.getEmails().get(0).getValue();
                String note = vcard.getNotes().get(0).getValue();
                String gender = vcard.getGender().getGender();
                String homeaddress = vcard.getAddresses().get(0).getStreetAddress();
                String birthday = vcard.getBirthday().getDate().toString();
                String group = vcard.getExtendedProperty("GROUP").getValue();
                String workaddress = vcard.getExtendedProperty("WORKADR").getValue();
                String photopath = vcard.getPhotos().get(0).getUrl();
                String postcode = vcard.getAddresses().get(0).getPostalCode();
                String telephone = null;
                String phone = null;
                for (Telephone tel : vcard.getTelephoneNumbers()) {
                    if (tel.getTypes().contains(TelephoneType.WORK)) {
                        telephone = tel.getText();
                    } else if (tel.getTypes().contains(TelephoneType.CELL)) {
                        phone = tel.getText();
                    }
                }
                Person person = new Person(name, telephone, phone, email, homeaddress, birthday, group, note, photopath, postcode, workaddress, gender);
                person.setGender(gender);
                contacts.add(person);
                if (!contactsGroup.containsKey(group)) {
                    contactsGroup.put(group, new ArrayList<>());
                }
                contactsGroup.get(group).add(person);
            }
        } catch (IOException e) {
            System.out.println("读取vCard文件出错：" + e.getMessage());
        }
        return new Pair<>(contactsGroup, contacts);
    }

    /**
     * 将联系人信息保存到vCard文件。
     *
     * @param contacts 要写入的联系人列表。
     * @param vCardFile 要写入的vCard文件。
     */
    public void writeContactsToVCard(List<Person> contacts, File vCardFile) {
        List<VCard> vcards = new ArrayList<>();
        for (Person person : contacts) {
            VCard vcard = new VCard();
            vcard.setFormattedName(person.getName());
            // 添加电话号码，并标记为工作电话
            Telephone tel1 = new Telephone(person.getTelephone());
            tel1.addType(TelephoneType.WORK);
            vcard.addTelephoneNumber(tel1);

            // 添加电话号码，并标记为手机
            Telephone tel2 = new Telephone(person.getPhone());
            tel2.addType(TelephoneType.CELL);
            vcard.addTelephoneNumber(tel2);
            vcard.addEmail(person.getEmail());
            vcard.addNote(person.getNote());
            // 添加性别
            if ("male".equalsIgnoreCase(person.getGender())) {
                vcard.setGender(Gender.male());
            } else if ("female".equalsIgnoreCase(person.getGender())) {
                vcard.setGender(Gender.female());
            }
            // 添加家庭地址
            Address homeAddress = new Address();
            homeAddress.setStreetAddress(person.getHomeaddress());
            homeAddress.setPostalCode(person.getPostcode()); // 添加邮政编码
            vcard.addAddress(homeAddress);
            // 添加生日
            LocalDate localDateBirthday = LocalDate.parse(person.getBirthday(), DateTimeFormatter.ofPattern("yyyyMMdd"));
            Date dateBirthday = Date.from(localDateBirthday.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Birthday birthday = new Birthday(dateBirthday);
            vcard.setBirthday(birthday);
            // 添加组别
            vcard.addExtendedProperty("GROUP", person.getGroup());
            // 添加工作地址
            vcard.addExtendedProperty("WORKADR", person.getWorkaddress());
            // 处理联系人照片
            try (InputStream in = new URL(person.getPhotopath()).openStream()) {
                byte[] imageBytes = Files.readAllBytes(Paths.get(person.getPhotopath()));
                Photo photo = new Photo(imageBytes, ImageType.JPEG);
                vcard.addPhoto(photo);
            } catch (IOException e) {
                // 异常处理
                System.out.println("处理联系人照片出错：" + e.getMessage());
                continue;
            }
            vcards.add(vcard);
        }
        try {
            Ezvcard.write(vcards).go(vCardFile);
        } catch (IOException e) {
            // 异常处理
            System.out.println("写入vCard文件出错：" + e.getMessage());
        }
    }

    /**
     * 将联系人列表保存到新的VCard文件中
     * @param contacts 联系人列表，每个联系人包含姓名、电话、邮箱等信息。
     * @param name 保存的VCard文件名。
     */
    public void writeContactsToVCard(List<Person> contacts, String name) {
        //在/personFile/vCard/目录下创建一个name.vCard文件
        File vCardFile = new File("/personFile/vCard/" + name + ".vcf");
        List<VCard> vcards = new ArrayList<>();
        for (Person person : contacts) {
            VCard vcard = new VCard();
            vcard.setFormattedName(person.getName());
            // 添加电话号码，并标记为工作电话
            Telephone tel1 = new Telephone(person.getTelephone());
            tel1.addType(TelephoneType.WORK);
            vcard.addTelephoneNumber(tel1);

            // 添加电话号码，并标记为手机
            Telephone tel2 = new Telephone(person.getPhone());
            tel2.addType(TelephoneType.CELL);
            vcard.addTelephoneNumber(tel2);
            vcard.addEmail(person.getEmail());
            vcard.addNote(person.getNote());
            // 添加性别
            if ("male".equalsIgnoreCase(person.getGender())) {
                vcard.setGender(Gender.male());
            } else if ("female".equalsIgnoreCase(person.getGender())) {
                vcard.setGender(Gender.female());
            }
            // 添加家庭地址
            Address homeAddress = new Address();
            homeAddress.setStreetAddress(person.getHomeaddress());
            homeAddress.setPostalCode(person.getPostcode()); // 添加邮政编码
            vcard.addAddress(homeAddress);
            // 添加生日
            LocalDate localDateBirthday = LocalDate.parse(person.getBirthday(), DateTimeFormatter.ofPattern("yyyyMMdd"));
            Date dateBirthday = Date.from(localDateBirthday.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Birthday birthday = new Birthday(dateBirthday);
            vcard.setBirthday(birthday);
            // 添加组别
            vcard.addExtendedProperty("GROUP", person.getGroup());
            // 添加工作地址
            vcard.addExtendedProperty("WORKADR", person.getWorkaddress());
            // 处理联系人照片
            try (InputStream in = new URL(person.getPhotopath()).openStream()) {
                byte[] imageBytes = Files.readAllBytes(Paths.get(person.getPhotopath()));
                Photo photo = new Photo(imageBytes, ImageType.JPEG);
                vcard.addPhoto(photo);
            } catch (IOException e) {
                // 异常处理
                System.out.println("处理联系人照片出错：" + e.getMessage());
                continue;
            }
            vcards.add(vcard);
        }
        try {
            Ezvcard.write(vcards).go(vCardFile);
        } catch (IOException e) {
            // 异常处理
            System.out.println("写入vCard文件出错：" + e.getMessage());
        }
    }
}

