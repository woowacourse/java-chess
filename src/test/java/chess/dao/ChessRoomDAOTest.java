package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.game.ChessRoomEntity;
import chess.domain.player.type.TeamColor;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ChessRoomDAOTest {
    private static final String TEST_TITLE = "testTitle";
    private final ChessRoomDAO chessRoomDAO = new ChessRoomDAO();

    @AfterEach
    void tearDown() throws SQLException {
        chessRoomDAO.deleteAll();
    }

    @Test
    void add() throws SQLException {
        ChessRoomEntity chessRoomEntity = new ChessRoomEntity(TEST_TITLE);

        ChessRoomEntity addedChessRoom = chessRoomDAO.add(chessRoomEntity);

        assertThat(chessRoomEntity).isEqualTo(addedChessRoom);
    }

    @Test
    void findById() throws SQLException {
        ChessRoomEntity chessRoomEntity = new ChessRoomEntity(TEST_TITLE);
        ChessRoomEntity addedChessRoom = chessRoomDAO.add(chessRoomEntity);

        ChessRoomEntity foundByIdChessRoom = chessRoomDAO.findById(addedChessRoom.getId());

        assertThat(addedChessRoom).isEqualTo(foundByIdChessRoom);
    }

    @Test
    void update() throws SQLException {
        ChessRoomEntity chessRoomEntity = new ChessRoomEntity(TEST_TITLE);
        ChessRoomEntity chessRoomToUpdate = chessRoomDAO.add(chessRoomEntity);
        String newTitle = "newTitle";
        String newCurrentTurnTeamColor = TeamColor.BLACK.getValue();
        chessRoomToUpdate.setTitle(newTitle);
        chessRoomToUpdate.setCurrentTeamColor(newCurrentTurnTeamColor);

        ChessRoomEntity updatedChessRoom = chessRoomDAO.update(chessRoomEntity);

        assertThat(updatedChessRoom).isEqualTo(chessRoomToUpdate);
    }

    @Test
    void delete() throws SQLException {
        ChessRoomEntity chessRoomEntity = new ChessRoomEntity(TEST_TITLE);
        chessRoomDAO.add(chessRoomEntity);

        chessRoomDAO.delete(chessRoomEntity);

        ChessRoomEntity deletedChessRoom = chessRoomDAO.findById(chessRoomEntity.getId());

        assertThat(deletedChessRoom).isNull();
    }
}