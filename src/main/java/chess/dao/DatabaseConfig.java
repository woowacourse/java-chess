package chess.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.EnumMap;
import java.util.Map;
import java.util.stream.Stream;

public class DatabaseConfig {
    private static final String INVALID_DATABASE_CONFIG_EXCEPTION_MESSAGE = "[ERROR] 데이터베이스 설정 파일이 없습니다.";
    private static final String DB_CONFIG_PATH = "src/main/resources/db_config.txt";
    private static final String CONFIG_DELIMITER = "=";
    private static final int CONFIG_KEY = 0;
    private static final int CONFIG_VALUE = 1;
    private static final Map<ConfigKey, String> DB_CONFIG = new EnumMap<>(ConfigKey.class);

    static {
        initializeDatabaseConfig();
    }

    private static void initializeDatabaseConfig() {
        try (Stream<String> configLines = Files.lines(Path.of(DB_CONFIG_PATH))) {
            configLines.map(configLine -> configLine.split(CONFIG_DELIMITER, 2))
                    .forEach(configTuple -> DB_CONFIG.put(ConfigKey.valueOf(configTuple[CONFIG_KEY]),
                            configTuple[CONFIG_VALUE]));
        } catch (IOException e) {
            throw new IllegalStateException(INVALID_DATABASE_CONFIG_EXCEPTION_MESSAGE, e);
        }
    }

    private DatabaseConfig() {
    }

    public static String getUrl() {
        return DB_CONFIG.get(ConfigKey.URL);
    }

    public static String getUsername() {
        return DB_CONFIG.get(ConfigKey.USERNAME);
    }

    public static String getPassword() {
        return DB_CONFIG.get(ConfigKey.PASSWORD);
    }

    private enum ConfigKey {
        URL,
        USERNAME,
        PASSWORD
    }
}
