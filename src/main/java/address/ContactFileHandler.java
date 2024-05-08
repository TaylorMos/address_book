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
 * ������ϵ���ļ����࣬������CSV��vCard�ļ��ж�ȡ��ϵ����Ϣ���Լ�����ϵ����Ϣд�뵽CSV��vCard�ļ��С�
 */
public class ContactFileHandler {

    /**
     * ��CSV�ļ��ж�ȡ��ϵ����Ϣ��
     *
     * @param csvFile Ҫ��ȡ��CSV�ļ���
     * @return ����һ��Pair�������а���һ��HashMap����Ϊ������ֵΪ�������ϵ���б��Լ�һ����������ϵ���б�
     */
    public Pair<HashMap<String, List<Person>>, List<Person>> readContactsFromCSV(File csvFile) {
        List<Person> contacts = new ArrayList<>();
        HashMap<String, List<Person>> contactsGroup = new HashMap<>();
        CsvReader reader = null;
        try {
            reader = new CsvReader(new FileReader(csvFile));
            reader.readHeaders(); // ��ȡ��ͷ

            // ����ͷӳ�䵽������
            Map<String, Integer> headerMap = new HashMap<>();
            for (int i = 0; i < reader.getHeaderCount(); i++) {
                headerMap.put(reader.getHeader(i), i);
            }

            // ��ȡ��¼�������ϵ����Ϣ
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
            System.out.println("��ȡCSV�ļ�����" + e.getMessage());
            return new Pair<>(contactsGroup, contacts);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return new Pair<>(contactsGroup, contacts);
    }

    /**
     * ����ϵ����Ϣд��CSV�ļ���
     *
     * @param contacts Ҫд�����ϵ���б�
     * @param csvFile Ҫд���CSV�ļ���
     */
    public void writeContactsToCSV(List<Person> contacts, File csvFile) {
        CsvWriter writer = null;
        try {
            writer = new CsvWriter(new FileWriter(csvFile), ',');

            // �����ͷ
            String[] headers = {"Name", "Telephone", "Phone", "Email", "Home Address", "Birthday", "Group", "Note", "Photo Path", "Postcode", "Work Address", "Gender"};

            // д���ͷ
            writer.writeRecord(headers);

            // д����ϵ����Ϣ
            for (Person person : contacts) {
                String[] entries = {person.getName(), person.getTelephone(), person.getPhone(), person.getEmail(),
                        person.getHomeaddress(), person.getBirthday(), person.getGroup(), person.getNote(),
                        person.getPhotopath(), person.getPostcode(), person.getWorkaddress(), person.getGender()};
                writer.writeRecord(entries);
            }
        } catch (IOException e) {
            System.out.println("д��CSV�ļ�����" + e.getMessage());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * ����ϵ����Ϣ���浽�µ�CSV�ļ���
     *
     * @param contacts Ҫд�����ϵ���б�
     * @param name CSV�ļ�����
     */
    public void writeContactsToCSV(List<Person> contacts, String name) {
        File file = new File("/personFile/csv/" + name + ".csv");
        CsvWriter writer = null;
        try {
            writer = new CsvWriter(new FileWriter(file), ',');
            // ��ӱ�ͷ
            String[] headers = {"Name", "Telephone", "Phone", "Email", "HomeAddress", "Birthday", "Group", "Note", "PhotoPath", "Postcode", "WorkAddress", "Gender"};
            writer.writeRecord(headers);
            // ������ϵ���б�д��CSV�ļ�
            for (Person person : contacts) {
                String[] entries = {person.getName(), person.getTelephone(), person.getPhone(), person.getEmail(),
                        person.getHomeaddress(), person.getBirthday(), person.getGroup(), person.getNote(), person.getPhotopath(),
                        person.getPostcode(), person.getWorkaddress(), person.getGender()};
                writer.writeRecord(entries);
            }
        } catch (IOException e) {
            System.out.println("д��CSV�ļ�����" + e.getMessage());
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * ��vCard�ļ��ж�ȡ��ϵ����Ϣ��
     *
     * @param vCardFile Ҫ��ȡ��vCard�ļ���
     * @return ����һ��Pair�������а���һ��HashMap����Ϊ������ֵΪ�������ϵ���б��Լ�һ����������ϵ���б�
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
            System.out.println("��ȡvCard�ļ�����" + e.getMessage());
        }
        return new Pair<>(contactsGroup, contacts);
    }

    /**
     * ����ϵ����Ϣ���浽vCard�ļ���
     *
     * @param contacts Ҫд�����ϵ���б�
     * @param vCardFile Ҫд���vCard�ļ���
     */
    public void writeContactsToVCard(List<Person> contacts, File vCardFile) {
        List<VCard> vcards = new ArrayList<>();
        for (Person person : contacts) {
            VCard vcard = new VCard();
            vcard.setFormattedName(person.getName());
            // ��ӵ绰���룬�����Ϊ�����绰
            Telephone tel1 = new Telephone(person.getTelephone());
            tel1.addType(TelephoneType.WORK);
            vcard.addTelephoneNumber(tel1);

            // ��ӵ绰���룬�����Ϊ�ֻ�
            Telephone tel2 = new Telephone(person.getPhone());
            tel2.addType(TelephoneType.CELL);
            vcard.addTelephoneNumber(tel2);
            vcard.addEmail(person.getEmail());
            vcard.addNote(person.getNote());
            // ����Ա�
            if ("male".equalsIgnoreCase(person.getGender())) {
                vcard.setGender(Gender.male());
            } else if ("female".equalsIgnoreCase(person.getGender())) {
                vcard.setGender(Gender.female());
            }
            // ��Ӽ�ͥ��ַ
            Address homeAddress = new Address();
            homeAddress.setStreetAddress(person.getHomeaddress());
            homeAddress.setPostalCode(person.getPostcode()); // �����������
            vcard.addAddress(homeAddress);
            // �������
            LocalDate localDateBirthday = LocalDate.parse(person.getBirthday(), DateTimeFormatter.ofPattern("yyyyMMdd"));
            Date dateBirthday = Date.from(localDateBirthday.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Birthday birthday = new Birthday(dateBirthday);
            vcard.setBirthday(birthday);
            // ������
            vcard.addExtendedProperty("GROUP", person.getGroup());
            // ��ӹ�����ַ
            vcard.addExtendedProperty("WORKADR", person.getWorkaddress());
            // ������ϵ����Ƭ
            try (InputStream in = new URL(person.getPhotopath()).openStream()) {
                byte[] imageBytes = Files.readAllBytes(Paths.get(person.getPhotopath()));
                Photo photo = new Photo(imageBytes, ImageType.JPEG);
                vcard.addPhoto(photo);
            } catch (IOException e) {
                // �쳣����
                System.out.println("������ϵ����Ƭ����" + e.getMessage());
                continue;
            }
            vcards.add(vcard);
        }
        try {
            Ezvcard.write(vcards).go(vCardFile);
        } catch (IOException e) {
            // �쳣����
            System.out.println("д��vCard�ļ�����" + e.getMessage());
        }
    }

    /**
     * ����ϵ���б��浽�µ�VCard�ļ���
     * @param contacts ��ϵ���б�ÿ����ϵ�˰����������绰���������Ϣ��
     * @param name �����VCard�ļ�����
     */
    public void writeContactsToVCard(List<Person> contacts, String name) {
        //��/personFile/vCard/Ŀ¼�´���һ��name.vCard�ļ�
        File vCardFile = new File("/personFile/vCard/" + name + ".vcf");
        List<VCard> vcards = new ArrayList<>();
        for (Person person : contacts) {
            VCard vcard = new VCard();
            vcard.setFormattedName(person.getName());
            // ��ӵ绰���룬�����Ϊ�����绰
            Telephone tel1 = new Telephone(person.getTelephone());
            tel1.addType(TelephoneType.WORK);
            vcard.addTelephoneNumber(tel1);

            // ��ӵ绰���룬�����Ϊ�ֻ�
            Telephone tel2 = new Telephone(person.getPhone());
            tel2.addType(TelephoneType.CELL);
            vcard.addTelephoneNumber(tel2);
            vcard.addEmail(person.getEmail());
            vcard.addNote(person.getNote());
            // ����Ա�
            if ("male".equalsIgnoreCase(person.getGender())) {
                vcard.setGender(Gender.male());
            } else if ("female".equalsIgnoreCase(person.getGender())) {
                vcard.setGender(Gender.female());
            }
            // ��Ӽ�ͥ��ַ
            Address homeAddress = new Address();
            homeAddress.setStreetAddress(person.getHomeaddress());
            homeAddress.setPostalCode(person.getPostcode()); // �����������
            vcard.addAddress(homeAddress);
            // �������
            LocalDate localDateBirthday = LocalDate.parse(person.getBirthday(), DateTimeFormatter.ofPattern("yyyyMMdd"));
            Date dateBirthday = Date.from(localDateBirthday.atStartOfDay(ZoneId.systemDefault()).toInstant());
            Birthday birthday = new Birthday(dateBirthday);
            vcard.setBirthday(birthday);
            // ������
            vcard.addExtendedProperty("GROUP", person.getGroup());
            // ��ӹ�����ַ
            vcard.addExtendedProperty("WORKADR", person.getWorkaddress());
            // ������ϵ����Ƭ
            try (InputStream in = new URL(person.getPhotopath()).openStream()) {
                byte[] imageBytes = Files.readAllBytes(Paths.get(person.getPhotopath()));
                Photo photo = new Photo(imageBytes, ImageType.JPEG);
                vcard.addPhoto(photo);
            } catch (IOException e) {
                // �쳣����
                System.out.println("������ϵ����Ƭ����" + e.getMessage());
                continue;
            }
            vcards.add(vcard);
        }
        try {
            Ezvcard.write(vcards).go(vCardFile);
        } catch (IOException e) {
            // �쳣����
            System.out.println("д��vCard�ļ�����" + e.getMessage());
        }
    }
}

