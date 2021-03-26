package chess.dao;

import static chess.domain.piece.type.PieceType.PAWN;
import static chess.domain.piece.type.PieceType.ROOK;
import static chess.domain.player.type.TeamColor.BLACK;
import static chess.domain.player.type.TeamColor.WHITE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.entity.ChessRoomEntity;
import chess.dao.entity.PieceEntity;
import chess.dao.entity.PlayerEntity;
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
        PlayerEntity playerEntityToBeAdded = new PlayerEntity(addedChessRoom);
        PlayerEntity playerEntity = playerDAO.add(playerEntityToBeAdded);
        PieceEntity pieceEntityToBeAdded = new PieceEntity(ROOK, BLACK, playerEntity);
        PieceEntity pieceEntity = pieceDAO.add(pieceEntityToBeAdded);

        pieceDAO.delete(pieceEntity);

        PieceEntity deletedPieceEntity = pieceDAO.findById(pieceEntity.getId());

        assertThat(deletedPieceEntity).isNull();
    }
}