package address;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.UUID;

public class PhotoHandler {
    public static void copyPhotoToProjectFolder(String sourceFilePath, String targetFileName) {

        // 获取源文件路径
        File sourceFile = new File(sourceFilePath);

        // 获取目标文件路径
        String projectFolderPath = System.getProperty("user.dir");
        File targetFile = new File(projectFolderPath + "/photo/" + targetFileName);
        try {
            // 复制文件
            Files.copy(sourceFile.toPath(), targetFile.toPath());
            System.out.println("文件复制成功！");
        } catch (IOException e) {
            System.out.println("文件复制失败：" + e.getMessage());
        }
    }
}