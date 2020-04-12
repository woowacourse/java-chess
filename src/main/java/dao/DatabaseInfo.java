package dao;

public class DatabaseInfo {
	final static String SERVER = "localhost:13306"; // MySQL 서버 주소
	final static String DATABASE = "woowachess"; // MySQL DATABASE 이름
	final static String OPTION = "?useSSL=false&serverTimezone=UTC&characterEncoding=utf8";
	final static String USER_NAME = "root"; //  MySQL 서버 아이디
	final static String PASSWORD = "root"; // MySQL 서버 비밀번호
	final static String PROTOCOL = "jdbc:mysql://";
	final static String DRIVER = "com.mysql.cj.jdbc.Driver";
}
