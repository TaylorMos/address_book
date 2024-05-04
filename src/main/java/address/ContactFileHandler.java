package address;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.parameter.ImageType;
import ezvcard.property.Photo;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 处理联系人文件的类，包括从CSV和vCard文件中读取联系人信息，以及将联系人信息写入到CSV和vCard文件中。
 */
public class ContactFileHandler {

    /**
     * 从CSV文件中读取联系人信息。
     *
     * @param csvFile 要读取的CSV文件。
     * @return 从CSV文件中读取的联系人列表。
     */
    public List<Person> readContactsFromCSV(File csvFile) {
        List<Person> contacts = new ArrayList<>();
        CsvReader reader = null;
        try {
            reader = new CsvReader(new FileReader(csvFile), ',');
            while (reader.readRecord()) {
                // 读取CSV文件中的一行数据，并创建一个Person对象
                String name = reader.get(0);
                String telephone = reader.get(1);
                String phone = reader.get(2);
                String email = reader.get(3);
                String homeaddress = reader.get(4);
                String birthday = reader.get(5);
                String group = reader.get(6);
                String note = reader.get(7);
                String photopath = reader.get(8);
                String postcode = reader.get(9);
                String workaddress = reader.get(10);
                Person person = new Person(name, telephone, phone, email, homeaddress, birthday, group, note, photopath, postcode, workaddress);
                contacts.add(person);
            }
        } catch (IOException e) {
            // 异常处理
            System.out.println("读取CSV文件出错：" + e.getMessage());
            return new ArrayList<>(); // 返回空列表
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return contacts;
    }

    /**
     * 将联系人信息写入到CSV文件中。
     *
     * @param contacts 要写入的联系人列表。
     * @param csvFile 要写入的CSV文件。
     */
    public void writeContactsToCSV(List<Person> contacts, File csvFile) {
        CsvWriter writer = null;
        try {
            writer = new CsvWriter(new FileWriter(csvFile), ',');
            // 遍历联系人列表，将信息写入CSV文件
            for (Person person : contacts) {
                String[] entries = {person.getName(), person.getTelephone(), person.getPhone(), person.getEmail(), person.getHomeaddress(), person.getBirthday(), person.getGroup(), person.getNote(), person.getPhotopath(), person.getPostcode(), person.getWorkaddress()};
                writer.writeRecord(entries);
            }
        } catch (IOException e) {
            // 异常处理
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
     * @return 从vCard文件中读取的联系人列表。
     */
    public List<Person> readContactsFromVCard(File vCardFile) {
        List<Person> contacts = new ArrayList<>();
        try {
            List<VCard> vcards = Ezvcard.parse(vCardFile).all();
            for (VCard vcard : vcards) {
                String name = vcard.getFormattedName().getValue();
                String telephone = vcard.getTelephoneNumbers().isEmpty() ? "" : vcard.getTelephoneNumbers().get(0).getText();
                String email = vcard.getEmails().isEmpty() ? "" : vcard.getEmails().get(0).getValue();
                String note = vcard.getNotes().isEmpty() ? "" : vcard.getNotes().get(0).getValue();
                String photopath = vcard.getPhotos().isEmpty() ? "" : vcard.getPhotos().get(0).getUrl();
                // Convert URL to local file path
                if (photopath.startsWith("file://")) {
                    photopath = photopath.substring(7);
                }
                Person person = new Person(name, telephone, "", email, "", "", "", note, photopath, "", "");
                contacts.add(person);
            }
        } catch (IOException e) {
            // Handle exception
        }
        return contacts;
    }

    /**
     * 将联系人信息写入到vCard文件中。
     *
     * @param contacts 要写入的联系人列表。
     * @param vCardFile 要写入的vCard文件。
     */
    public void writeContactsToVCard(List<Person> contacts, File vCardFile) {
        List<VCard> vcards = new ArrayList<>();
        for (Person person : contacts) {
            VCard vcard = new VCard();
            vcard.setFormattedName(person.getName());
            vcard.addTelephoneNumber(person.getTelephone());
            vcard.addEmail(person.getEmail());
            vcard.addNote(person.getNote());

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
