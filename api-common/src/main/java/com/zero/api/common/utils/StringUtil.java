package com.zero.api.common.utils;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Pattern;

public class StringUtil {
    private static final Log f = LogFactory.getLog(StringUtil.class);

    String a = "^1(3[0-9]|5[0-35-9]|8[05-9])\\d{8}$";

    String b = "^1(34[0-8]|(3[5-9]|5[017-9]|8[78])\\d)\\d{7}$";

    String c = "^1(3[0-2]|5[256]|8[56])\\d{8}$";

    String d = "^1((33|53|8[09])[0-9]|349)\\d{7}$";

    String e = "^0(10|2[0-5789]|\\d{3})\\d{7,8}$";

    public static final String[][] SAFESTRING_REPLACE = {{"\"", "'"}, {"&quot;", "&apos;"}};

    public static String encoder(String paramString) {
        String str = null;
        try {
            if (paramString == null) {
                return "";
            }
            str = URLEncoder.encode(paramString, "utf-8");
        } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
            str = paramString;
            f.error("encoder error", localUnsupportedEncodingException);
        }
        return str;
    }

    public static String decoder(String paramString) {
        String str = null;
        try {
            if (paramString == null) {
                return "";
            }
            str = URLDecoder.decode(paramString, "utf-8");
        } catch (UnsupportedEncodingException localUnsupportedEncodingException) {
            str = paramString;
            f.error("decoder error", localUnsupportedEncodingException);
        }
        return str;
    }

    public static String removeWhitespace(String paramString) {
        return StringUtils.deleteWhitespace(paramString);
    }

    public static String removeString(String paramString1, String paramString2) {
        return StringUtils.remove(paramString1, paramString2);
    }

    public static String getString(Object paramObject) {
        return ObjectUtils.toString(paramObject, "");
    }

    public static String getString(Object paramObject, String paramString) {
        String str = ObjectUtils.toString(paramObject, paramString);
        if (isEmpty(str)) {
            str = paramString;
        }
        return str;
    }

    public static String subString(String paramString1, String paramString2, String paramString3) {
        return StringUtils.substringBetween(paramString1, paramString2, paramString3);
    }

    @Deprecated
    public static String getStringTrim(Object paramObject) {
        return ObjectUtils.toString(paramObject, "").trim();
    }

    public static String trim(Object paramObject) {
        return ObjectUtils.toString(paramObject, "").trim();
    }

    public static String replace(String paramString1, String paramString2, String paramString3) {
        return StringUtils.replace(paramString1, paramString2, paramString3);
    }


    public static String replaceFirst(String paramString1, String paramString2, String paramString3) {
        return StringUtils.replaceOnce(paramString1, paramString2, paramString3);
    }

    public static String getUUIDString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Deprecated
    public static Integer[] getIntegerArray(String paramString) {
        if ((paramString == null) || (paramString.trim().length() == 0)) {
            return null;
        }
        String[] arrayOfString = paramString.split(",");
        Integer[] arrayOfInteger = null;
        if ((arrayOfString != null) && (arrayOfString.length > 0)) {
            arrayOfInteger = new Integer[arrayOfString.length];
            for (int i = 0; i < arrayOfString.length; ++i) {
                arrayOfInteger[i] = Integer.valueOf(Integer.parseInt(arrayOfString[i]));
            }
        }
        return arrayOfInteger;
    }

    public static String join(Collection<?> paramCollection, String paramString) {
        return StringUtils.join(paramCollection, paramString);
    }

    public static String join(Object[] paramArrayOfObject, String paramString) {
        return StringUtils.join(paramArrayOfObject, paramString);
    }

    public static void cleanStringBuffer(StringBuffer[] paramArrayOfStringBuffer) {
        for (int i = 0; i < paramArrayOfStringBuffer.length; ++i)
            paramArrayOfStringBuffer[i].delete(0, paramArrayOfStringBuffer[i].length());
    }

    public static int countMatches(String paramString1, String paramString2) {
        return StringUtils.countMatches(paramString1, paramString2);
    }

    public static boolean contains(String paramString1, String paramString2) {
        return StringUtils.contains(paramString1, paramString2);
    }

    public static String repeat(String paramString, int paramInt) {
        return StringUtils.repeat(paramString, paramInt);
    }

    public static Integer[] toIntegerArray(String[] paramArrayOfString) {
        if (paramArrayOfString == null) {
            return null;
        }
        Integer[] arrayOfInteger = new Integer[paramArrayOfString.length];
        for (int i = 0; i < paramArrayOfString.length; ++i) {
            arrayOfInteger[i] = Integer.valueOf(Integer.parseInt(paramArrayOfString[i]));
        }
        return arrayOfInteger;
    }

    public static String paresUrl(String paramString1, String paramString2) {
        if ((paramString1 == null) || (paramString1.trim().length() == 0)) {
            return paramString1;
        }
        paramString1 = StringUtils.replace(paramString1, "\\", "/");
        String[] arrayOfString = StringUtils.split(paramString1, paramString2);
        StringBuffer localStringBuffer = new StringBuffer(0);
        for (int i = 0; i < arrayOfString.length; ++i) {
            if ((paramString2 != null) && (paramString2.length() > 0)) {
                localStringBuffer.append(paramString2);
            }
            if (!arrayOfString[i].startsWith("/")) {
                localStringBuffer.append("/");
            }
            localStringBuffer.append(arrayOfString[i]);
        }
        if ((paramString2 != null) && (paramString2.length() > 0)) {
            localStringBuffer.delete(0, 1);
        }
        paramString1 = localStringBuffer.toString();
        cleanStringBuffer(new StringBuffer[]{
                localStringBuffer});
        return paramString1;
    }

    public static String convertPath(String paramString, boolean paramBoolean) {
        if (isEmpty(paramString)) {
            return paramString;
        }
        paramString = StringUtils.replace(paramString, "\\", "/");
        if ((paramBoolean) && (!paramString.endsWith("/"))) {
            paramString = paramString + "/";
        }
        return paramString;
    }

    public static boolean equals(String paramString1, String paramString2) {
        return StringUtils.equals(paramString1, paramString2);
    }

    public static boolean equalsIgnoreCase(String paramString1, String paramString2) {
        return StringUtils.equalsIgnoreCase(paramString1, paramString2);
    }

    public static boolean isEmpty(String paramString) {
        return StringUtils.isBlank(paramString);
    }

    public static boolean isNotEmpty(String paramString) {
        return StringUtils.isNotBlank(paramString);
    }

    public static String[] split(String paramString1, String paramString2) {
        return StringUtils.splitByWholeSeparator(paramString1, paramString2);
    }

    public static List<String> toStringList(String paramString1, String paramString2) {
        String[] arrayOfString = split(paramString1, paramString2);
        return Arrays.asList(arrayOfString);
    }

    public static List<String> toStringList(String paramString) {
        return toStringList(paramString, ",");
    }

    public static List<Integer> toIntegerList(String paramString1, String paramString2) {
        String[] arrayOfString1 = split(paramString1, paramString2);
        ArrayList localArrayList = new ArrayList();
        if (arrayOfString1 != null) {
            for (String str : arrayOfString1) {
                if (NumberUtil.isNotNumeric(str)) {
                    return null;
                }
                localArrayList.add(Integer.valueOf(NumberUtil.getInt(str)));
            }
        }
        return localArrayList;
    }

    public static List<Integer> toIntegerList(String paramString) {
        return toIntegerList(paramString, ",");
    }

    public static String limitLen(String paramString, int paramInt) {
        return limitLen(paramString, paramInt, "utf-8");
    }

    public static String limitLen(String paramString1, int paramInt, String paramString2) {
        int i = 0;
        String str1 = "";
        if ((paramString1 != null) && (paramString1.length() > 0)) {
            if ((paramString2 == null) || (paramString2.length() == 0))
                paramString2 = "UTF-8";
            try {
                int j = 0;
                j = paramString1.getBytes(paramString2).length;
                if (j <= paramInt) {
                    str1 = paramString1;
                } else {
                    char[] arrayOfChar = paramString1.toCharArray();
                    for (int k = 0; k < arrayOfChar.length; ++k) {
                        String str2 = String.valueOf(arrayOfChar[k]);
                        byte[] arrayOfByte = str2.getBytes("UTF-8");
                        i += arrayOfByte.length;
                        if (i < paramInt) {
                            str1 = str1 + arrayOfChar[k];
                        }
                    }
                }
            } catch (Exception localException) {
                f.error("截取字符串长度时发生异常!", localException);
            }
        }
        return str1;
    }

    public static String limitLen(String paramString1, int paramInt, String paramString2, String paramString3) {
        paramString3 = getString(paramString3);
        int i = 0;
        String str1 = "";
        if ((paramString1 != null) && (paramString1.length() > 0)) {
            if ((paramString2 == null) || (paramString2.length() == 0))
                paramString2 = "UTF-8";
            try {
                int j = 0;
                j = paramString1.getBytes(paramString2).length;
                if (j <= paramInt) {
                    str1 = paramString1;
                } else {
                    char[] arrayOfChar = paramString1.toCharArray();
                    for (int k = 0; k < arrayOfChar.length; ++k) {
                        String str2 = String.valueOf(arrayOfChar[k]);
                        byte[] arrayOfByte = str2.getBytes("UTF-8");
                        i += arrayOfByte.length;
                        if (i >= paramInt)
                            break;
                        str1 = str1 + arrayOfChar[k];
                    }
                    str1 = str1 + paramString3;
                }
            } catch (Exception localException) {
                f.error("截取字符串长度时发生异常!", localException);
            }
        }
        return str1;
    }

    public static String toFullUrl(String paramString1, String paramString2) {
        try {
            if ((isNotEmpty(paramString1)) &&
                    (!contains(replace(paramString1, "//", ""), "/"))) {
                paramString1 = paramString1 + "/";
            }
            java.net.URI localURI1 = new java.net.URI(paramString1);
            java.net.URI localURI2 = localURI1.resolve(paramString2);
            URL localURL = localURI2.toURL();
            paramString2 = localURL.toString();
        } catch (Exception localException) {
        }
        return paramString2;
    }

    public static String convertToDicValue(String paramString, Map<String, String> paramMap) {
        String str1 = null;
        if (isNotEmpty(paramString)) {
            String[] arrayOfString = split(paramString, ",");
            ArrayList localArrayList = new ArrayList(2);
            for (int i = 0; i < arrayOfString.length; ++i) {
                String str2 = arrayOfString[i];
                String str3 = (String) paramMap.get(str2);
                if (isNotEmpty(str3)) {
                    localArrayList.add(str3);
                }
            }
            str1 = join(localArrayList, ",");
        }
        return str1;
    }

    private static boolean a(char paramChar) {
        Character.UnicodeBlock localUnicodeBlock = Character.UnicodeBlock.of(paramChar);

        return (localUnicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) ||
                (localUnicodeBlock == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS) ||
                (localUnicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A) ||
                (localUnicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B) ||
                (localUnicodeBlock == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION) ||
                (localUnicodeBlock == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) ||
                (localUnicodeBlock == Character.UnicodeBlock.GENERAL_PUNCTUATION);
    }

    public static boolean isChinese(String paramString) {
        if (isEmpty(paramString)) {
            return false;
        }
        char[] arrayOfChar = paramString.toCharArray();
        for (int i = 0; i < arrayOfChar.length; ++i) {
            char c1 = arrayOfChar[i];
            if (a(c1)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isChineseByREG(String paramString) {
        if (isEmpty(paramString)) {
            return false;
        }
        Pattern localPattern = Pattern.compile("[\\u4E00-\\u9FBF]+");
        return localPattern.matcher(paramString.trim()).find();
    }
}