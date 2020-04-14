package chess;

import chess.dao.IsFinishedDao;
import chess.dao.PiecesDao;
import chess.dao.TurnDao;
import chess.piece.Piece;
import chess.piece.Team;
import chess.position.Position;
import chess.service.ChessGameService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessGameServiceTest {

    private PiecesDao piecesDao = new PiecesDao();
    private TurnDao turnDao = new TurnDao();
    private IsFinishedDao isFinishedDao = new IsFinishedDao();
    private Map<Position, Piece> savedPieces;
    private Team savedTurn;
    private boolean savedIsFinished;

    @BeforeEach
    void setUp() throws SQLException {
        savedPieces = piecesDao.load();
        savedTurn = turnDao.load();
        savedIsFinished = isFinishedDao.load();
    }

    @DisplayName("아무 데이터가 없을 때에도 정상적으로 작동하는지 테스트")
    @Test
    void initTest() throws SQLException {
        ChessGameService expectedService = new ChessGameService(piecesDao, turnDao, isFinishedDao);

        ChessGameService actualService = new ChessGameService(piecesDao, turnDao, isFinishedDao);
        actualService.delete();
        actualService.init();

        assertThat(actualService.getBoard()).isEqualTo(expectedService.getBoard());
        assertThat(actualService.isTurnWhite()).isEqualTo(expectedService.isTurnWhite());
        assertThat(actualService.isFinished()).isEqualTo(expectedService.isFinished());
    }

    @AfterEach
    void tearDown() throws SQLException {
        piecesDao.save(savedPieces);
        turnDao.save(savedTurn);
        isFinishedDao.save(savedIsFinished);
    }
}
