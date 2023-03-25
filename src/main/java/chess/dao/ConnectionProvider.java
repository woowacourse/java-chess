package chess.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

public class ConnectionProvider {
    private static final Map<ConfigKey, String> DB_CONFIG = new EnumMap<>(ConfigKey.class);

    static {
        try (Stream<String> lines = Files.lines(Path.of("src/main/resources/db_config.txt"))) {
            lines.map(line -> line.split("=", 2))
                    .forEach(line -> DB_CONFIG.put(ConfigKey.valueOf(line[0]), line[1]));
        } catch (IOException e) {
            throw new IllegalStateException("[ERROR] 데이터베이스 설정 파일이 없습니다.", e);
        }
    }

    private ConnectionProvider() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                DB_CONFIG.get(ConfigKey.URL),
                DB_CONFIG.get(ConfigKey.USERNAME),
                DB_CONFIG.get(ConfigKey.PASSWORD));
    }

    private enum ConfigKey {
        URL,
        USERNAME,
        PASSWORD
    }
}
