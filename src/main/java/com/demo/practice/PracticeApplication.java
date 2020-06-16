package com.demo.practice;

import com.demo.practice.pojo.City;
import com.demo.practice.utils.DButil;
import com.demo.practice.utils.xmlParser;
import lombok.Data;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.bind.JAXBContext;
import java.sql.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@SpringBootApplication
public class PracticeApplication {

    public static void main(String[] args) {
        //SpringApplication.run(PracticeApplication.class, args);
        practice();
    }

    private static void practice(){
//        System.getProperties().list(System.out);
//        System.out.println("============================================");
//        System.out.println(System.getProperty("user.name"));
//        System.out.println("============================================");
//        System.out.println(System.getProperty("java.library.path"));

//        int i = -1;
//        int ia = -2;
//        int ii = 1;
//        System.out.println(i);
//        System.out.println(Integer.toBinaryString(i));
//        System.out.println(Integer.toBinaryString(ia));
//        System.out.println(Integer.toBinaryString(-i));
//        System.out.println(Integer.toBinaryString(i<<=1));
//        System.out.println(Integer.toBinaryString((ii)<<=1));
//        System.out.println(Integer.toBinaryString((ii)>>=2));
        List<City> cities = MySQLDemo.getData();
        try{
            City ele = cities.get(0);
//            JAXBContext context = JAXBContext.newInstance(ele.getClass());
//            int i=1;
            System.out.println(xmlParser.convertToXml(ele));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}

class MySQLDemo {

    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/world?serverTimezone=UTC";

    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    //static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&serverTimezone=UTC";


    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "wwdwwd";

    public static List<City> getData(){
        Connection conn = null;
        Statement stmt = null;
        List<City> cities = new ArrayList<>();
        try {
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            City c = null;


            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM world.city limit 10";
            ResultSet rs = stmt.executeQuery(sql);


            cities = DButil.putResult(rs, City.class);
            // 展开结果集数据库
            for (City ele : cities
            ) {
                System.out.println(ele.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return cities;
    }
    public static void main() {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            City c = null;


            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT * FROM world.city limit 10";
            ResultSet rs = stmt.executeQuery(sql);

            List<City> cities = new ArrayList<>();
            cities = DButil.putResult(rs, City.class);
            stmt.close();
            conn.close();
            // 展开结果集数据库
            for (City ele: cities
                 ) {
                System.out.println(ele.toString());
            }
            while(rs.next()){
                // 通过字段检索
                c = (City)rs;
//                System.out.print("ID: " + c.getID());
                System.out.print(", city Name: " + c.getName());
                System.out.print(", city CountryCode: " + c.getCountryCode());
                System.out.print(", city District: " + c.getDistrict());
                System.out.print(", city Population: " + c.getPopulation());

                Long id  = rs.getLong("ID");
                String name = rs.getString("Name");
                String CountryCode = rs.getString("CountryCode");
                String District = rs.getString("District");
                int Population = rs.getInt("Population");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", city Name: " + name);
                System.out.print(", city CountryCode: " + CountryCode);
                System.out.print(", city District: " + District);
                System.out.print(", city Population: " + Population);
                System.out.print("\n");
            }
            // 完成后关闭
//            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
}
