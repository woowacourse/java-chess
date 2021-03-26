package chess.dao;

import static chess.domain.piece.type.PieceType.PAWN;
import static chess.domain.player.type.TeamColor.WHITE;
import static chess.domain.position.type.File.A;
import static chess.domain.position.type.File.H;
import static chess.domain.position.type.Rank.EIGHT;
import static chess.domain.position.type.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import chess.dao.entity.ChessRoomEntity;
import chess.dao.entity.PieceEntity;
import chess.dao.entity.PlayerEntity;
import chess.dao.entity.PositionEntity;
import chess.domain.position.type.File;
import chess.domain.position.type.Rank;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class PositionDAOTest {
    private static final String TEST_TITLE = "testTitle";

    private final PositionDAO positionDAO = new PositionDAO();
    private final PieceDAO pieceDAO = new PieceDAO();
    private final PlayerDAO playerDAO = new PlayerDAO();
    private final ChessRoomDAO chessRoomDAO = new ChessRoomDAO();

    @AfterEach
    void tearDown() throws SQLException {
        positionDAO.deleteAll();
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
        PieceEntity pieceEntityToBeAdded = new PieceEntity(PAWN, WHITE, playerEntity);
        PieceEntity pieceEntity = pieceDAO.add(pieceEntityToBeAdded);
        PositionEntity positionEntityToBeAdded = new PositionEntity(A, TWO, pieceEntity);

        PositionEntity positionEntity = positionDAO.add(positionEntityToBeAdded);

        assertThat(positionEntity).isEqualTo(positionEntityToBeAdded);
    }

    @Test
    void findById() throws SQLException {
        ChessRoomEntity chessRoomEntityToBeAdded = new ChessRoomEntity(TEST_TITLE);
        ChessRoomEntity chessRoomEntity = chessRoomDAO.add(chessRoomEntityToBeAdded);
        PlayerEntity playerEntityToBeAdded = new PlayerEntity(chessRoomEntity);
        PlayerEntity playerEntity = playerDAO.add(playerEntityToBeAdded);
        PieceEntity pieceEntityToBeAdded = new PieceEntity(PAWN, WHITE, playerEntity);
        PieceEntity pieceEntity = pieceDAO.add(pieceEntityToBeAdded);
        PositionEntity positionEntityToBeAdded = new PositionEntity(A, TWO, pieceEntity);
        PositionEntity positionEntity = positionDAO.add(positionEntityToBeAdded);

        PositionEntity foundPositionEntity = positionDAO.findById(positionEntity.getId());

        assertThat(foundPositionEntity).isEqualTo(positionEntity);
    }

    @Test
    void update() throws SQLException {
        ChessRoomEntity chessRoomEntityToBeAdded = new ChessRoomEntity(TEST_TITLE);
        ChessRoomEntity chessRoomEntity = chessRoomDAO.add(chessRoomEntityToBeAdded);
        PlayerEntity playerEntityToBeAdded = new PlayerEntity(chessRoomEntity);
        PlayerEntity playerEntity = playerDAO.add(playerEntityToBeAdded);
        PieceEntity pieceEntityToBeAdded = new PieceEntity(PAWN, WHITE, playerEntity);
        PieceEntity pieceEntity = pieceDAO.add(pieceEntityToBeAdded);
        PositionEntity positionEntityToBeAdded = new PositionEntity(A, TWO, pieceEntity);
        PositionEntity positionToUpdate = positionDAO.add(positionEntityToBeAdded);

        File newFile = H;
        Rank newRank = EIGHT;
        positionToUpdate.setFile(newFile);
        positionToUpdate.setRank(newRank);

        PositionEntity updatedPositionEntity = positionDAO.update(positionToUpdate);

        assertThat(updatedPositionEntity).isEqualTo(positionToUpdate);
        assertThat(updatedPositionEntity.getFileValue()).isEqualTo(newFile.value());
        assertThat(updatedPositionEntity.getRankValue()).isEqualTo(String.valueOf(newRank.value()));
    }

    @Test
    void delete() throws SQLException {
        ChessRoomEntity chessRoomEntityToBeAdded = new ChessRoomEntity(TEST_TITLE);
        ChessRoomEntity chessRoomEntity = chessRoomDAO.add(chessRoomEntityToBeAdded);
        PlayerEntity playerEntityToBeAdded = new PlayerEntity(chessRoomEntity);
        PlayerEntity playerEntity = playerDAO.add(playerEntityToBeAdded);
        PieceEntity pieceEntityToBeAdded = new PieceEntity(PAWN, WHITE, playerEntity);
        PieceEntity pieceEntity = pieceDAO.add(pieceEntityToBeAdded);
        PositionEntity positionEntityToBeAdded = new PositionEntity(A, TWO, pieceEntity);
        PositionEntity positionToBeDeleted = positionDAO.add(positionEntityToBeAdded);

        positionDAO.delete(positionToBeDeleted);

        PositionEntity deletedPositionEntity = positionDAO.findById(positionToBeDeleted.getId());

        assertThat(deletedPositionEntity).isNull();
    }
}