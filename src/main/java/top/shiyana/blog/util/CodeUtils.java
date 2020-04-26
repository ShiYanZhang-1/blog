package top.shiyana.blog.util;

import java.util.Random;

/**
 * @ProjectName: hospital
 * @Package: top.shiyana.hospital.util
 * @ClassName: CodeUtils
 * @Author: dangerous
 * @Description:
 * @Date: 2020/4/20 17:52
 * @Version: 1.0
 */
public class CodeUtils {

    public static String getMathVal() {
        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb=new StringBuilder(6);
        for(int i=0;i<6;i++)
        {
            char ch=str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        return sb.toString();
    }
}
