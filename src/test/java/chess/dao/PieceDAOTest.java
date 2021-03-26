package chess.dao;

import static chess.domain.piece.type.PieceType.*;
import static chess.domain.player.type.TeamColor.*;
import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.entity.ChessRoomEntity;
import chess.dao.entity.PieceEntity;
import chess.dao.entity.PlayerEntity;
import chess.domain.piece.type.PieceType;
import chess.domain.player.type.TeamColor;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class PieceDAOTest {
    private static final String TEST_TITLE = "testTitle";

    private final PieceDAO pieceDAO = new PieceDAO();
    private final PlayerDAO playerDAO = new PlayerDAO();
    private final ChessRoomDAO chessRoomDAO = new ChessRoomDAO();

    @AfterEach
    void tearDown() throws SQLException {
        pieceDAO.deleteAll();
        playerDAO.deleteAll();
        chessRoomDAO.deleteAll();
    }

    @Test
    void add() throws SQLException {
        ChessRoomEntity chessRoomEntityToBeAdded = new ChessRoomEntity(TEST_TITLE);
        ChessRoomEntity chessRoomEntity = chessRoomDAO.add(chessRoomEntityToBeAdded);
        PlayerEntity playerEntityToBeAdded = new PlayerEntity(chessRoomEntity);
        PlayerEntity playerEntity = playerDAO.add(playerEntityToBeAdded);
        PieceEntity pieceEntity = new PieceEntity(PAWN, WHITE, playerEntity);

        pieceDAO.add(pieceEntity);
    }

    @Test
    void findById() throws SQLException {
        ChessRoomEntity chessRoomEntityToBeAdded = new ChessRoomEntity(TEST_TITLE);
        ChessRoomEntity chessRoomEntity = chessRoomDAO.add(chessRoomEntityToBeAdded);
        PlayerEntity playerEntityToBeAdded = new PlayerEntity(chessRoomEntity);
        PlayerEntity playerEntity = playerDAO.add(playerEntityToBeAdded);
        PieceEntity pieceEntity = new PieceEntity(PAWN, WHITE, playerEntity);
        PieceEntity addedPieceEntity = pieceDAO.add(pieceEntity);

        PieceEntity foundPieceEntity = pieceDAO.findById(addedPieceEntity.getId());

        assertThat(foundPieceEntity).isEqualTo(pieceEntity);
    }

    @Test
    void delete() throws SQLException {
        ChessRoomEntity chessRoomEntity = new ChessRoomEntity(TEST_TITLE);
        ChessRoomEntity addedChessRoom = chessRoomDAO.add(chessRoomEntity);
        PlayerEntity playerEntity = new PlayerEntity(addedChessRoom);
        playerDAO.add(playerEntity);

        playerDAO.delete(playerEntity);

        PlayerEntity deletedPlayerEntity = playerDAO.findById(playerEntity.getId());

        assertThat(deletedPlayerEntity).isNull();
    }
}