package address;
import java.io.File;
/**
 * 项目文件创建器，用于创建项目所必须的文件。
 */
public class DirectoryCreator {
    public static void createDirectory() {
        // 获取项目根目录的路径
        String projectRootPath = System.getProperty("user.dir");

        // 构造photo文件夹的完整路径
        String photoDirectoryPath = projectRootPath + File.separator + "photo";
        //构建personFile文件夹的完整路径
        String personFileDirectoryPath = projectRootPath + File.separator + "personFile";
        //创建/personFile/csv和/personFile/vcard文件夹的完整路径
        String csvDirectoryPath = projectRootPath + File.separator + "personFile" + File.separator + "csv";
        String vcardDirectoryPath = projectRootPath + File.separator + "personFile" + File.separator + "vcard";

        // 创建photo文件夹
        File photoDirectory = new File(photoDirectoryPath);
        if (!photoDirectory.exists()) {
            boolean result = photoDirectory.mkdirs();
            if (result) {
                System.out.println("Photo文件夹创建成功，路径为: " + photoDirectoryPath);
            } else {
                System.out.println("Photo文件夹创建失败，路径为: " + photoDirectoryPath);
            }
        } else {
            System.out.println("Photo文件夹已创建，路径为： " + photoDirectoryPath);
        }
        //创建personFile文件夹
        File personFileDirectory = new File(personFileDirectoryPath);
        if (!personFileDirectory.exists()) {
            boolean result = personFileDirectory.mkdirs();
            if (result) {
                System.out.println("personFile文件夹创建成功，路径为: " + personFileDirectoryPath);
            } else {
                System.out.println("personFile文件夹创建失败，路径为: " + personFileDirectoryPath);
            }
        } else {
            System.out.println("personFile文件夹已创建，路径为： " + personFileDirectoryPath);
        }
        //创建csv文件夹
        File csvDirectory = new File(csvDirectoryPath);
        if (!csvDirectory.exists()) {
            boolean result = csvDirectory.mkdirs();
            if (result) {
                System.out.println("csv文件夹创建成功，路径为: " + csvDirectoryPath);
            } else {
                System.out.println("csv文件夹创建失败，路径为: " + csvDirectoryPath);
            }
        } else {
            System.out.println("csv文件夹已创建，路径为： " + csvDirectoryPath);
        }

        //创建vcard文件夹
        File vcardDirectory = new File(vcardDirectoryPath);

        if (!vcardDirectory.exists()) {
            boolean result = vcardDirectory.mkdirs();
            if (result) {
                System.out.println("vcard文件夹创建成功，路径为: " + vcardDirectoryPath);
            } else {
                System.out.println("vcard文件夹创建失败，路径为: " + vcardDirectoryPath);
            }
        } else {
            System.out.println("vcard文件夹已创建，路径为： " + vcardDirectoryPath);
        }
    }
}
