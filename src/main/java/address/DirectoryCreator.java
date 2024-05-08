package address;
import java.io.File;
/**
 * ��Ŀ�ļ������������ڴ�����Ŀ��������ļ���
 */
public class DirectoryCreator {
    public static void createDirectory() {
        // ��ȡ��Ŀ��Ŀ¼��·��
        String projectRootPath = System.getProperty("user.dir");

        // ����photo�ļ��е�����·��
        String photoDirectoryPath = projectRootPath + File.separator + "photo";
        //����personFile�ļ��е�����·��
        String personFileDirectoryPath = projectRootPath + File.separator + "personFile";
        //����/personFile/csv��/personFile/vcard�ļ��е�����·��
        String csvDirectoryPath = projectRootPath + File.separator + "personFile" + File.separator + "csv";
        String vcardDirectoryPath = projectRootPath + File.separator + "personFile" + File.separator + "vcard";

        // ����photo�ļ���
        File photoDirectory = new File(photoDirectoryPath);
        if (!photoDirectory.exists()) {
            boolean result = photoDirectory.mkdirs();
            if (result) {
                System.out.println("Photo�ļ��д����ɹ���·��Ϊ: " + photoDirectoryPath);
            } else {
                System.out.println("Photo�ļ��д���ʧ�ܣ�·��Ϊ: " + photoDirectoryPath);
            }
        } else {
            System.out.println("Photo�ļ����Ѵ�����·��Ϊ�� " + photoDirectoryPath);
        }
        //����personFile�ļ���
        File personFileDirectory = new File(personFileDirectoryPath);
        if (!personFileDirectory.exists()) {
            boolean result = personFileDirectory.mkdirs();
            if (result) {
                System.out.println("personFile�ļ��д����ɹ���·��Ϊ: " + personFileDirectoryPath);
            } else {
                System.out.println("personFile�ļ��д���ʧ�ܣ�·��Ϊ: " + personFileDirectoryPath);
            }
        } else {
            System.out.println("personFile�ļ����Ѵ�����·��Ϊ�� " + personFileDirectoryPath);
        }
        //����csv�ļ���
        File csvDirectory = new File(csvDirectoryPath);
        if (!csvDirectory.exists()) {
            boolean result = csvDirectory.mkdirs();
            if (result) {
                System.out.println("csv�ļ��д����ɹ���·��Ϊ: " + csvDirectoryPath);
            } else {
                System.out.println("csv�ļ��д���ʧ�ܣ�·��Ϊ: " + csvDirectoryPath);
            }
        } else {
            System.out.println("csv�ļ����Ѵ�����·��Ϊ�� " + csvDirectoryPath);
        }

        //����vcard�ļ���
        File vcardDirectory = new File(vcardDirectoryPath);

        if (!vcardDirectory.exists()) {
            boolean result = vcardDirectory.mkdirs();
            if (result) {
                System.out.println("vcard�ļ��д����ɹ���·��Ϊ: " + vcardDirectoryPath);
            } else {
                System.out.println("vcard�ļ��д���ʧ�ܣ�·��Ϊ: " + vcardDirectoryPath);
            }
        } else {
            System.out.println("vcard�ļ����Ѵ�����·��Ϊ�� " + vcardDirectoryPath);
        }
    }
}
