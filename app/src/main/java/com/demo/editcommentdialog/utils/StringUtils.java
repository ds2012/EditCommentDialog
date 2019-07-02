package com.demo.editcommentdialog.utils;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.StringRes;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.StyleSpan;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 字符串工具类
 *
 * @author tangjun
 */
public class StringUtils {

  public static final String EMPTY = "";

  /**
   * 用于生成文件
   */
  private static final double KB = 1024.0;
  public static final double MB = 1048576.0;
  private static final double GB = 1073741824.0;

  public static char chatAt(String pinyin, int index) {
    if (pinyin != null && pinyin.length() > 0) return pinyin.charAt(index);
    return ' ';
  }

  /**
   * 获取字符串宽度
   */
  public static float GetTextWidth(String Sentence, float Size) {
    if (isEmpty(Sentence)) return 0;
    TextPaint FontPaint = new TextPaint();
    FontPaint.setTextSize(Size);
    return FontPaint.measureText(Sentence.trim()) + (int) (Size * 0.1); // 留点余地
  }

  /**
   * 拼接数组
   */
  public static String join(final List<String> array, final String separator) {
    StringBuffer result = new StringBuffer();
    if (array != null && array.size() > 0) {
      for (String str : array) {
        result.append(str);
        result.append(separator);
      }
      result.delete(result.length() - 1, result.length());
    }
    return result.toString();
  }

  /**
   * 拼接数组
   *
   * @param filter 过滤掉某些字符
   */
  public static String join(final List<String> array, final String filter, final String separator) {
    StringBuffer result = new StringBuffer();
    if (array != null && array.size() > 0) {
      for (String str : array) {
        result.append(str.replace(filter, ""));
        result.append(separator);
      }
      result.delete(result.length() - 1, result.length());
    }
    return result.toString();
  }

  /**
   * 拼接数组
   *
   * @param filter 过滤掉某些字符
   */
  public static String newJoin(final List<String> array, final String filter, final String separator) {
    StringBuffer result = new StringBuffer();
    if (array != null && array.size() > 0) {
      for (String str : array) {
        result.append(str.replace(filter, ""));
        result.append(separator);
      }
      if (array.size() > 1)
        result.delete(result.length() - 1, result.length());
    }
    return result.toString();
  }

  /**
   * 拼接数组
   *
   * @param filter 过滤掉某些字符
   */
  public static String statisJoin(final List<String> array, final String filter, final String separator) {
    StringBuffer result = new StringBuffer();
    if (array != null && array.size() > 0) {
      for (String str : array) {
        result.append(separator);
        result.append(str.replace(filter, ""));
        result.append(separator);
      }
      //if (array.size() > 1)
      //    result.delete(result.length() - 1, result.length());
    }
    return result.toString();
  }

  public static String join(final String str, final String filter, final String separator) {
    StringBuffer result = new StringBuffer();
    if (StringUtils.isNotEmpty(str)) {
      result.append(str.replace(filter, ""));
      result.append(separator);
      result.delete(result.length() - 1, result.length());
    }
    return result.toString();
  }

  public static String join(final Iterator<String> iter, final String separator) {
    StringBuffer result = new StringBuffer();
    if (iter != null) {
      while (iter.hasNext()) {
        String key = iter.next();
        result.append(key);
        result.append(separator);
      }
      if (result.length() > 0) result.delete(result.length() - 1, result.length());
    }
    return result.toString();
  }

  /**
   * 判断字符串是否为空,或者为字符串的NULL,null
   *
   * @return false不为空
   */
  public static boolean isEmpty(String str) {
    return str == null || str.length() == 0 || str.equalsIgnoreCase("null");
  }

  public static boolean isEmptyTrim(String s) {
    return s == null || "".equals(s.trim());
  }

  public static boolean isNotEmpty(String str) {
    return !isEmpty(str);
  }

  public static String trim(String str) {
    return str == null ? EMPTY : str.trim();
  }

  /**
   * 判断对象是否为空
   *
   * @return false不为空
   */
  public static boolean isNullOrEmpty(final Object str) {
    return (str == null || str.toString().length() == 0);
  }

  /**
   * 判断一组字符串是否有一个为空
   */
  public static boolean isNullOrEmpty(final String... strs) {
    if (strs == null || strs.length == 0) {
      return true;
    }
    for (String str : strs) {
      if (str == null || str.length() == 0) {
        return true;
      }
    }
    return false;
  }

  /**
   * 判断子字符串是否有出现在指定字符串中
   */
  public static boolean find(String str, String c) {
    if (isNullOrEmpty(str)) {
      return false;
    }
    return str.indexOf(c) > -1;
  }

  public static boolean findIgnoreCase(String str, String c) {
    if (isNullOrEmpty(str)) {
      return false;
    }
    return str.toLowerCase().indexOf(c.toLowerCase()) > -1;
  }

  /**
   * 比较两个字符串是否相
   */
  public static boolean equals(String str1, String str2) {
    if (str1 == str2) return true;

    if (str1 == null) str1 = "";
    return str1.equals(str2);
  }


  /**
   * 比较两个字符串是否相
   */
  public static boolean equalsStr(String str1, String str2) {
    if (str1 == str2) return true;

    if (str1 == null) str1 = "";
    return str1.equals(str2);
  }
  /**
   * 比较两个字符串是否相，忽略大小写
   */
  public static boolean equalsIgnoreCase(String str1, String str2) {
    if (str1 == str2) return true;

    if (str1 == null) str1 = "";
    return str1.equalsIgnoreCase(str2);
  }

  /**
   * 空值替换
   */
  public static String replaceEmpty(String str, String defaultValue) {
    return isEmpty(trim(str)) ? defaultValue : str;
  }


  public static boolean isBlank(String s) {
    return TextUtils.isEmpty(s);
  }

  /**
   * 转换文件大小
   */
  public static String generateFileSize(long size) {
    String fileSize;
    if (size < KB) {
      fileSize = size + "B";
    } else if (size < MB) {
      fileSize = String.format("%.1f", size / KB) + "KB";
    } else if (size < GB) {
      fileSize = String.format("%.1f", size / MB) + "MB";
    } else {
      fileSize = String.format("%.1f", size / GB) + "GB";
    }

    return fileSize;
  }

  /**
   * 查找字符串，找到返回，没找到返回空
   */
  public static String findString(String search, String start, String end) {
    try {
      if (StringUtils.isNotEmpty(search) && StringUtils.isNotEmpty(start) && StringUtils.isNotEmpty(
          end)) {
        int start_len = start.length();
        int start_pos = StringUtils.isNotEmpty(start) ? search.indexOf(start) : 0;
        if (start_pos > -1) {
          int end_pos =
              StringUtils.isNotEmpty(end) ? search.indexOf(end, start_pos + start_len) : -1;
          if (end_pos > -1) return search.substring(start_pos + start.length(), end_pos);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return "";
  }

  /**
   * 截取字符串
   *
   * @param search 待搜索的字符串
   * @param start  起始字符串 例如：<title>
   * @param end    结束字符串 例如：</title>
   */
  public static String substring(String search, String start, String end, String defaultValue) {
    int start_len = start.length();
    int start_pos = StringUtils.isEmpty(start) ? 0 : search.indexOf(start);
    if (start_pos > -1) {
      int end_pos = StringUtils.isEmpty(end) ? -1 : search.indexOf(end, start_pos + start_len);
      if (end_pos > -1) {
        return search.substring(start_pos + start.length(), end_pos);
      } else {
        return search.substring(start_pos + start.length());
      }
    }
    return defaultValue;
  }

  /**
   * 截取字符串
   *
   * @param search 待搜索的字符串
   * @param start  起始字符串 例如：<title>
   * @param end    结束字符串 例如：</title>
   */
  public static String substring(String search, String start, String end) {
    return substring(search, start, end, "");
  }

  /**
   * 拼接字符串
   */
  public static String concat(String... strs) {
    StringBuffer result = new StringBuffer();
    if (strs != null) {
      for (String str : strs) {
        if (str != null) result.append(str);
      }
    }
    return result.toString();
  }

  /**
   * 获取中文字符个数
   */
  public static int getChineseCharCount(String str) {
    String tempStr;
    int count = 0;
    for (int i = 0; i < str.length(); i++) {
      tempStr = String.valueOf(str.charAt(i));
      if (tempStr.getBytes().length == 3) {
        count++;
      }
    }
    return count;
  }

  /**
   * 获取指定宽度的可见字符 如果有多余 则用'...'表示
   */
  public static String getNewVisibleStringAt(int width, Paint paint, String text) {
    try {
      if (text == null || text.length() == 0) {
        return "";
      }
      StringBuilder stringBuilder = new StringBuilder();
      int length = text.length(); // 字符串的长度
      char[] msg_chr = text.toCharArray(); // 存储text的字符形式
      int x = 0;
      int start = 0; // 字符串的开始位置
      int index = 0; // 当前检索字符的索引
      while (index <= length - 1) {
        int tempWidth = (int) paint.measureText(msg_chr, index, 1);
        x += tempWidth;
        if (x > width) {
          String temp1 = new String(msg_chr, start, index - start);
          if (index <= length - 1) {
            // 有省略
            stringBuilder.append(temp1 + "...");
          } else {
            stringBuilder.append(temp1);
          }
          return stringBuilder.toString();
        }
        index++;
      }
      // 画最后的子字符串
      index = length;
      if (index > start) {// 如果字符串不为空
        stringBuilder.append(new String(msg_chr, start, index - start));
      }
      return stringBuilder.toString();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private int getSubLength(String content, int maxLength) {
    if (StringUtils.isEmpty(content)) return 0;
    int chineseCount = 0;
    double englishCount = 0;

    String tempStr;

    for (int i = content.length() - 1; i >= 0; i--) {
      tempStr = content.substring(0, i);
      chineseCount = StringUtils.getChineseCharCount(tempStr);// 中文字符
      englishCount = StringUtils.getEnglishCount(tempStr) / (double) 2;// 英文字符

      if (maxLength - chineseCount - englishCount >= 0.0) {
        return i;
      }
    }

    return 0;
  }

  /**
   * 获取英文字符个数
   */
  public static int getEnglishCount(String str) {
    String tempStr;
    int count = 0;
    for (int i = 0; i < str.length(); i++) {
      tempStr = String.valueOf(str.charAt(i));
      if (!(tempStr.getBytes().length == 3)) {
        count++;
      }
    }
    return count;
  }

  public static String encode(String url) {
    try {
      return URLEncoder.encode(url, "UTF-8");
    } catch (UnsupportedEncodingException e) {
//      Logger.e(e);
    }
    return url;
  }

  /**
   * Helper function for making null strings safe for comparisons, etc.
   *
   * @return (s == null) ? "" : s;
   */
  public static String makeSafe(String s) {
    return (s == null) ? "" : s;
  }

  //  public static String getNumberFormat(int value) {
  //    String str = Utils.parseCount(value);
  //    return str;
  //    //if (value < 100000) {
  //    //  return value + "";
  //    //} else if (value < 100000 || value < 100000000) {
  //    //  String s = String.valueOf(Math.round(Math.floor(value / 10000)));
  //    //  String[] str = s.split("[.]");
  //    //  if (str != null) {
  //    //    if (str.length > 1) {
  //    //      if (str[1].equals("0")) {
  //    //        return str[0] + VideoApplication.getInstance().getString(R.string.wan);
  //    //      }
  //    //    }
  //    //  }
  //    //
  //    //  return s + VideoApplication.getInstance().getString(R.string.wan);
  //    //}
  //    //return Math.round(Math.floor(value / 100000000)) + VideoApplication.getInstance()
  //    //    .getString(R.string.yi);
  //  }

  //  public static String getNumberFormat(long value) {
  //    return Utils.parseCount(value);
  //  }

  //  public static String getNumberFormatForMyCount(int value) {
  //    if (value <= 9999) {
  //      return value + "";
  //    } else if (value < 9999 || value <= 999000) {
  //      String s = String.valueOf(Math.floor(value) / 10000);
  //      String[] str = s.split("[.]");
  //      if (str != null) {
  //        if (str.length > 1) {
  //          if (str[1].equals("0")) {
  //            return str[0] + VideoApplication.getInstance().getString(R.string.wan);
  //          }
  //        }
  //      }
  //      return s + VideoApplication.getInstance().getString(R.string.wan);
  //    }
  //    return "99" + VideoApplication.getInstance().getString(R.string.wan) + "+";
  //  }
  //
  //  public static String getNumberFormat1(int value) {
  //    if (value < 10000) {
  //      return value + "";
  //    } else if (value < 10000 || value < 100000000) {
  //      String s = String.valueOf(Math.round(value) / 10000.0);
  //      String[] str = s.split("[.]");
  //      if (str != null) {
  //        if (str.length > 1) {
  //          try {
  //            return str[0] + "." + str[1].charAt(0) + VideoApplication.getInstance()
  //                .getString(R.string.wan);
  //          } catch (Exception e) {
  //            e.printStackTrace();
  //          }
  //        }
  //      }
  //      return s + VideoApplication.getInstance().getString(R.string.wan);
  //    }
  //    return Math.round(Math.floor(value / 100000000)) + VideoApplication.getInstance()
  //        .getString(R.string.yi);
  //  }

  public static boolean isCorrectTextCount(String string, int startCount, int endCount) {
    String content = StringUtils.trim(string);
    int chineseCount = StringUtils.getChineseCharCount(content);//中文字符
    double englishCount = StringUtils.getEnglishCount(content) / (double) 2;//英文字符
    //			double englishCount2 = (double) englishCount / (double) 2;

    double totalCount = chineseCount + englishCount;
    if (totalCount >= startCount && totalCount <= endCount) {

      return true;
    }

    return false;
  }

  public static String getUUID() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }

  //清除回车字符
  public static String removeEnter(String resource, char ch) {
    if (StringUtils.isEmpty(resource)) {
      return "";
    }
    StringBuffer buffer = new StringBuffer();
    int position = 0;
    char currentChar;
    int index = 0;
    while (position < resource.length()) {
      currentChar = resource.charAt(position++);
      if (currentChar == ch) {
        if (index == 0) {
          index++;
          buffer.append(" ");
        } else {
          buffer.append("");
        }
      } else {
        index = 0;
        buffer.append(currentChar);
      }
    }
    return buffer.toString();
  }

  /**
   * 加粗全部字体
   */
  public static SpannableString bold(String str) {
    SpannableString spanString = new SpannableString(str);
    StyleSpan span = new StyleSpan(Typeface.BOLD);//加粗
    spanString.setSpan(span, 0, str.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    return spanString;
  }

  public static boolean contains(String strs, String s) {
    if (strs == null || s == null) return false;
    return strs.contains(s);
  }


  /***
   * 去掉字符串前后的空格，中间的空格保留
   * @param str
   * @return
   */
  public static String trimInnerSpaceStr(String str) {
    str = str.trim();
    while (str.startsWith(" ")) {
      str = str.substring(1, str.length()).trim();
    }
    while (str.endsWith(" ")) {
      str = str.substring(0, str.length() - 1).trim();
    }


    return str;
  }

  /***
   * 去掉字符串前后的回车，中间的回车保留
   * @param str
   * @return
   */
  public static String trimInnerEnterStr(String str) {
    str = str.trim();
    while (str.startsWith(" ")) {
      str = str.substring(1, str.length()).trim();
    }
    while (str.endsWith(" ")) {
      str = str.substring(0, str.length() - 1).trim();
    }
    return str;
  }

  /**
   * @return String
   */
  public static String unicodeToUtf8(String theString) {
    char aChar;
    if (theString == null) {
      return "";
    }
    int len = theString.length();
    StringBuffer outBuffer = new StringBuffer(len);
    for (int x = 0; x < len; ) {
      aChar = theString.charAt(x++);
      if (aChar == '\\') {
        aChar = theString.charAt(x++);
        if (aChar == 'u') {
          // Read the xxxx
          int value = 0;
          for (int i = 0; i < 4; i++) {
            aChar = theString.charAt(x++);
            switch (aChar) {
              case '0':
              case '1':
              case '2':
              case '3':
              case '4':
              case '5':
              case '6':
              case '7':
              case '8':
              case '9':
                value = (value << 4) + aChar - '0';
                break;
              case 'a':
              case 'b':
              case 'c':
              case 'd':
              case 'e':
              case 'f':
                value = (value << 4) + 10 + aChar - 'a';
                break;
              case 'A':
              case 'B':
              case 'C':
              case 'D':
              case 'E':
              case 'F':
                value = (value << 4) + 10 + aChar - 'A';
                break;
              default:
                throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
            }
          }
          outBuffer.append((char) value);
        } else {
          if (aChar == 't') {
            aChar = 't';
          } else if (aChar == 'r') {
            aChar = 'r';
          } else if (aChar == 'n') {
            aChar = 'n';
          } else if (aChar == 'f') aChar = 'f';
          outBuffer.append(aChar);
        }
      } else {
        outBuffer.append(aChar);
      }
    }

    return outBuffer.toString();
  }



  public static boolean matchSequence(boolean mode, String format, String sequence) {
    boolean result = false;
    Pattern pattern = Pattern.compile(format);
    Matcher matcher = pattern.matcher(sequence);
    if (mode) {
      result = matcher.find();
    } else {
      result = matcher.matches();
    }
    return result;
  }


  /**
   * 获取字符数
   *
   * @param text
   * @return
   */
  public static int getStringLength(String text) {
    if (TextUtils.isEmpty(text)) {
      return 0;
    }
    int length = 0;
    for (int i = 0; i < text.length(); i++) {
      int ascii = Character.codePointAt(text, i);
      if (ascii >= 0 && ascii <= 255) {
        length++;
      } else {
        length += 2;
      }
    }
    return length;
  }

  /**
   * 获取字数
   *
   * @param len
   * @return
   */
  public static String getLenText(int len) {
    if (len % 2 == 0) {
      return (len / 2) + "";
    } else {
      return (len / 2) + 1 + "";
    }
  }

//  public static String getString(@StringRes int strRes) {
//    return BaseApp.get().getContext().getResources().getString(strRes);
//  }

}
