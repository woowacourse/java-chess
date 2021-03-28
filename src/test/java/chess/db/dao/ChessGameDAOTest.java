package chess.db.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.db.entity.ChessGameEntity;
import chess.beforedb.domain.player.type.TeamColor;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDAOTest {
    private static final String TEST_TITLE = "testTitle";

    private final ChessGameDAO chessGameDAO = new ChessGameDAO();
    private ChessGameEntity chessGameEntity;

    @BeforeEach
    void setUp() throws SQLException {
        chessGameEntity = chessGameDAO.save(new ChessGameEntity(TEST_TITLE));
    }

    @AfterEach
    void tearDown() throws SQLException {
        chessGameDAO.removeAll();
    }

    @Test
    void addAndFind() throws SQLException {

        ChessGameEntity foundChessGameEntity = chessGameDAO.findById(chessGameEntity.getId());

        assertThat(foundChessGameEntity).isEqualTo(chessGameEntity);
    }

    @DisplayName("현재 턴의 팀 색깔 업데이트")
    @Test
    void updateCurrentTurnTeamColor() throws SQLException {
        TeamColor nextTurnTeamColor = chessGameEntity.getCurrentTurnTeamColor().oppositeTeamColor();

        chessGameEntity.setCurrentTurnTeamColor(nextTurnTeamColor);
        chessGameDAO.updateCurrentTurnTeamColor(chessGameEntity);

        ChessGameEntity foundChessGameEntity = chessGameDAO.findById(chessGameEntity.getId());

        assertThat(foundChessGameEntity.getCurrentTurnTeamColor()).isSameAs(nextTurnTeamColor);
    }

    @Test
    void remove() throws SQLException {

        chessGameDAO.remove(chessGameEntity);
        ChessGameEntity deletedChessRoom = chessGameDAO.findById(chessGameEntity.getId());

        assertThat(deletedChessRoom).isNull();
    }
}