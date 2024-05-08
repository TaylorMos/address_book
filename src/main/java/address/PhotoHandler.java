package address;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.UUID;

public class PhotoHandler {
    public static void copyPhotoToProjectFolder(String sourceFilePath, String targetFileName) {

        // ��ȡԴ�ļ�·��
        File sourceFile = new File(sourceFilePath);

        // ��ȡĿ���ļ�·��
        String projectFolderPath = System.getProperty("user.dir");
        File targetFile = new File(projectFolderPath + "/photo/" + targetFileName);
        try {
            // �����ļ�
            Files.copy(sourceFile.toPath(), targetFile.toPath());
            System.out.println("�ļ����Ƴɹ���");
        } catch (IOException e) {
            System.out.println("�ļ�����ʧ�ܣ�" + e.getMessage());
        }
    }
}