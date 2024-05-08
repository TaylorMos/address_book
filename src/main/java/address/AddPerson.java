package address;

import java.io.File;

public class AddPerson {

    /**
     * �����û���������ݴ���Person����
     *
     * @param name          ����
     * @param telephone     �绰
     * @param phone         �ֻ�
     * @param email         ��������
     * @param homeAddress   ��ͥסַ
     * @param birthday      ����
     * @param group         ���
     * @param note          ��ע
     * @param imagePath     ͼƬ·��
     * @param postcode      �ʱ�
     * @param workAddress   ������
     * @param gender         �Ա�
     * @return ������Person����
     */
    public static Person createPerson(String name, String telephone, String phone, String email, String homeAddress, String birthday, String group, String note, String imagePath, String postcode, String workAddress,String gender) {
        // ��ȡ��Ŀ��Ŀ¼��·��
        String projectRootPath = System.getProperty("user.dir");

        // ����Ŀ���ļ�������·��
        String targetFileName = imagePath.substring(imagePath.lastIndexOf(File.separator) + 1);
        String targetFilePath = projectRootPath + File.separator + "photo" + File.separator + targetFileName;

        // ʹ��PhotoHandler���copyPhotoToProjectFolder��������ͼƬ
        PhotoHandler.copyPhotoToProjectFolder(imagePath, targetFileName);

        Person person = new Person(name, telephone, phone, email, homeAddress, birthday, group, note, targetFilePath, postcode, workAddress,gender);
        return person;
    }
}