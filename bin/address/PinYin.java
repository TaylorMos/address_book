package address;// 导入拼音4j库，用于处理拼音转换
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * address.PinYin 类提供了一系列方法，用于将中文转换为拼音。
 */
public class PinYin {
    /**
     * address.PinYin 构造函数，初始化 address.PinYin 类实例。
     */
    public PinYin() {
    }

    /**
     * 获取输入字符串的全拼。
     *
     * @param src 需要转换为拼音的字符串。
     * @return 转换后的拼音字符串。
     */
    public static String getPinYin(String src) {
        // 将字符串转换为字符数组
        char[] hz = src.toCharArray();
        String[] py = new String[hz.length];
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();

        // 设置拼音输出格式
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);
        String pys = "";
        int len = hz.length;

        try {
            // 遍历字符数组，转换每个中文字符为拼音
            for(int i = 0; i < len; ++i) {
                if (Character.toString(hz[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    py = PinyinHelper.toHanyuPinyinStringArray(hz[i], format);
                    pys = pys + py[0];
                } else {
                    // 非中文字符直接添加
                    pys = pys + Character.toString(hz[i]);
                }
            }
        } catch (BadHanyuPinyinOutputFormatCombination var7) {
            // 处理拼音输出格式组合错误
            var7.printStackTrace();
        }

        return pys;
    }

    /**
     * 获取输入字符串的拼音首字母（大写）。
     *
     * @param str 需要转换的字符串。
     * @return 转换后的首字母大写字符串。
     */
    public static String getPinYinHeadCharUpper(String str) {
        String convert = "";

        // 遍历字符串，获取每个字符的拼音首字母
        for(int i = 0; i < str.length(); ++i) {
            char word = str.charAt(i);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                // 添加拼音首字母
                convert = convert + pinyinArray[0].charAt(0);
            } else {
                // 非中文字符直接添加
                convert = convert + word;
            }
        }

        return convert.toUpperCase();
    }

    /**
     * 获取输入字符串的拼音首字母（小写）。
     *
     * @param str 需要转换的字符串。
     * @return 转换后的首字母小写字符串。
     */
    public static String getPinYinHeadCharLower(String str) {
        String convert = "";

        // 遍历字符串，获取每个字符的拼音首字母
        for(int i = 0; i < str.length(); ++i) {
            char word = str.charAt(i);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                // 添加拼音首字母
                convert = convert + pinyinArray[0].charAt(0);
            } else {
                // 非中文字符直接添加
                convert = convert + word;
            }
        }

        return convert.toLowerCase();
    }
}
