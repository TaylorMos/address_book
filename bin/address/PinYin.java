package address;// ����ƴ��4j�⣬���ڴ���ƴ��ת��
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * address.PinYin ���ṩ��һϵ�з��������ڽ�����ת��Ϊƴ����
 */
public class PinYin {
    /**
     * address.PinYin ���캯������ʼ�� address.PinYin ��ʵ����
     */
    public PinYin() {
    }

    /**
     * ��ȡ�����ַ�����ȫƴ��
     *
     * @param src ��Ҫת��Ϊƴ�����ַ�����
     * @return ת�����ƴ���ַ�����
     */
    public static String getPinYin(String src) {
        // ���ַ���ת��Ϊ�ַ�����
        char[] hz = src.toCharArray();
        String[] py = new String[hz.length];
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

        // ����ƴ�������ʽ
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        String pys = "";
        int len = hz.length;

        try {
            // �����ַ����飬ת��ÿ�������ַ�Ϊƴ��
            for(int i = 0; i < len; ++i) {
                if (Character.toString(hz[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    py = PinyinHelper.toHanyuPinyinStringArray(hz[i], format);
                    pys = pys + py[0];
                } else {
                    // �������ַ�ֱ�����
                    pys = pys + Character.toString(hz[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination var7) {
            // ����ƴ�������ʽ��ϴ���
            var7.printStackTrace();
        }

        return pys;
    }

    /**
     * ��ȡ�����ַ�����ƴ������ĸ����д����
     *
     * @param str ��Ҫת�����ַ�����
     * @return ת���������ĸ��д�ַ�����
     */
    public static String getPinYinHeadCharUpper(String str) {
        String convert = "";

        // �����ַ�������ȡÿ���ַ���ƴ������ĸ
        for(int i = 0; i < str.length(); ++i) {
            char word = str.charAt(i);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                // ���ƴ������ĸ
                convert = convert + pinyinArray[0].charAt(0);
            } else {
                // �������ַ�ֱ�����
                convert = convert + word;
            }
        }

        return convert.toUpperCase();
    }

    /**
     * ��ȡ�����ַ�����ƴ������ĸ��Сд����
     *
     * @param str ��Ҫת�����ַ�����
     * @return ת���������ĸСд�ַ�����
     */
    public static String getPinYinHeadCharLower(String str) {
        String convert = "";

        // �����ַ�������ȡÿ���ַ���ƴ������ĸ
        for(int i = 0; i < str.length(); ++i) {
            char word = str.charAt(i);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                // ���ƴ������ĸ
                convert = convert + pinyinArray[0].charAt(0);
            } else {
                // �������ַ�ֱ�����
                convert = convert + word;
            }
        }

        return convert.toLowerCase();
    }
}
