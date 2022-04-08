package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;

import chess.domain.board.BoardInitializer;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.dto.PieceDto;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.Map;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BoardDaoTest {
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

    @BeforeEach
    void insertGameData() {
        GameDao gameDao = new GameDao(databaseConnector);
        try {
            gameDao.save();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @DisplayName("DB에 보드를 저장한다.")
    @Test
    void saveTo() {
        BoardDao boardDao = new BoardDao(databaseConnector);
        Map<Position, Piece> squares = BoardInitializer.get().getSquares();

        assertThatNoException().isThrownBy(() -> boardDao.save(squares));
    }

    @DisplayName("DB에 초기 보드를 저장한 후 load하면 a1 위치에 흰색 룩이 있다.")
    @Test
    void load_a1_white_rook() {
        BoardDao boardDao = new BoardDao(databaseConnector);
        Map<Position, Piece> squares = BoardInitializer.get().getSquares();
        Map<String, PieceDto> board = null;

        try {
            boardDao.save(squares);
            board = boardDao.load();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        PieceDto pieceAtA1 = board.get("a1");
        assertThat(pieceAtA1.getCamp() + pieceAtA1.getType()).isEqualTo("whiterook");
    }
}
