package address;
import java.io.File;

public class DirectoryCreator {
    public static void createPhotoDirectory() {
        // 获取项目根目录的路径
        String projectRootPath = System.getProperty("user.dir");

        // 构造photo文件夹的完整路径
        String photoDirectoryPath = projectRootPath + File.separator + "photo";

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
    }
}
