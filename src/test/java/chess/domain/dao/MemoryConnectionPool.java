package chess.domain.dao;

import java.sql.Connection;

public class MemoryConnectionPool {
    private static final String DEFAULT_SERVER = "jdbc:h2:~/test"; // 서버 주소
    private static final String DEFAULT_OPTION = "?useSSL=false&serverTimezone=UTC&characterEncoding=UTF-8"; // DATABASE 옵션
    private static final String DEFAULT_URL = DEFAULT_SERVER + "/" + DEFAULT_OPTION; // DATABASE 옵션
    private static final String DEFAULT_USER = ""; // 서버 아이디
    private static final String DEFAULT_PASSWORD = ""; // 서버 비밀번호

    public static ConnectionPool create(){
        return CustomConnectionPool.create(DEFAULT_URL, DEFAULT_USER, DEFAULT_PASSWORD);
    }
}
