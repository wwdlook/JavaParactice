package com.demo.practice.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class DButil {
    /**
     * 把ResultSet的结果放到java对象中
     *
     * @param <T>
     * @param rs
     *            ResultSet
     * @param obj
     *            java类的class
     * @return
     */
    public static <T> ArrayList<T> putResult(ResultSet rs, Class<T> obj) {
        try {
            ArrayList<T> arrayList = new ArrayList<T>();
            ResultSetMetaData metaData = rs.getMetaData();
            /**
             * 获取总列数
             */
            int count = metaData.getColumnCount();
            while (rs.next()) {
                /**
                 * 创建对象实例
                 */
                T newInstance = obj.newInstance();
                for (int i = 1; i <= count; i++) {
                    /**
                     * 给对象的某个属性赋值
                     */
                    String name = metaData.getColumnName(i);
                    name = toJavaField(name);// 改变列名格式成java命名格式
                    String substring = name.substring(0, 1);// 首字母大写
                    String replace = name.replaceFirst(substring, substring.toUpperCase());
                    Class<?> type = obj.getDeclaredField(replace).getType();// 获取字段类型
                    Method method = obj.getMethod("set" + replace, type);
                    /**
                     * 判断读取数据的类型
                     */
                    if(type.isAssignableFrom(String.class)){
                        method.invoke(newInstance, rs.getString(i));
                    }else if(type.isAssignableFrom(int.class) || type.isAssignableFrom(Integer.class)){
                        method.invoke(newInstance, rs.getInt(i));
                    }else if(type.isAssignableFrom(long.class) || type.isAssignableFrom(Long.class)){
                        method.invoke(newInstance, rs.getLong(i));
                    }else if(type.isAssignableFrom(Boolean.class) || type.isAssignableFrom(boolean.class)){
                        method.invoke(newInstance, rs.getBoolean(i));
                    }else if(type.isAssignableFrom(Date.class)){
                        method.invoke(newInstance, rs.getDate(i));
                    }
                }
                arrayList.add(newInstance);
            }
            return arrayList;

        } catch (InstantiationException | IllegalAccessException | SQLException | SecurityException
                | NoSuchMethodException | IllegalArgumentException | InvocationTargetException
                | NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;
    }
    /**
     * 数据库命名格式转java命名格式
     *
     * @param str
     *            数据库字段名
     * @return java字段名
     */
    public static String toJavaField(String str) {


        String[] split = str.split("_");
        StringBuilder builder = new StringBuilder();
        builder.append(split[0]);// 拼接第一个字符


        // 如果数组不止一个单词
        if (split.length > 1) {
            for (int i = 1; i < split.length; i++) {
                // 去掉下划线，首字母变为大写
                String string = split[i];
                String substring = string.substring(0, 1);
                split[i] = string.replaceFirst(substring, substring.toUpperCase());
                builder.append(split[i]);
            }
        }


        return builder.toString();
    }

}
