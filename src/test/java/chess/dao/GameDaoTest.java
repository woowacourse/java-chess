package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

import chess.domain.Camp;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameDaoTest {
    private static DatabaseConnector databaseConnector;

    @BeforeAll
    static void initializeDatabaseConnector() {
        databaseConnector = new DatabaseConnector("jdbc:h2:~/test", "tester", "");
        String rootPath = System.getProperty("user.dir");
        try {
            RunScript.execute(
                    databaseConnector.getConnection(),
                    new FileReader(rootPath + "/docker/db/mysql/init/init.sql")
            );
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @DisplayName("기존 data를 덮어쓸 수 있다.")
    @Test
    void save() {
        GameDao gameDao = new GameDao(databaseConnector);

        assertThatNoException().isThrownBy(gameDao::save);
    }

    @DisplayName("흑색 진영의 차례일 때 게임을 저장하고 불러오면 백색 진영의 차례가 아니다.")
    @Test
    void isWhiteTurnIn_false() {
        GameDao gameDao = new GameDao(databaseConnector);
        Camp.initializeTurn();
        Camp.switchTurn();

        boolean isWhiteTurn = false;
        try {
            gameDao.save();
            isWhiteTurn = gameDao.isWhiteTurn();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        assertThat(isWhiteTurn).isFalse();
    }
}
