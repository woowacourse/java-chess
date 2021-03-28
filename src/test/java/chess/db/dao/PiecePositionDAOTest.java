package chess.db.dao;

import static chess.beforedb.domain.piece.type.PieceType.PAWN;
import static chess.beforedb.domain.piece.type.PieceType.ROOK;
import static chess.beforedb.domain.player.type.TeamColor.WHITE;
import static chess.beforedb.domain.position.type.File.D;
import static chess.beforedb.domain.position.type.File.H;
import static chess.beforedb.domain.position.type.Rank.FIVE;
import static chess.beforedb.domain.position.type.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import chess.db.domain.piece.PieceEntity;
import chess.db.domain.position.PositionEntity;
import chess.db.entity.ChessGameEntity;
import chess.db.entity.PlayerEntity;
import chess.db.entity.PiecePositionEntity;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PiecePositionDAOTest {
    private final ChessGameDAO chessGameDAO = new ChessGameDAO();
    private final PlayerDAO playerDAO = new PlayerDAO();
    private final PieceDAO pieceDAO = new PieceDAO();
    private final PositionDAO positionDAO = new PositionDAO();
    private final PiecePositionDAO piecePositionDAO = new PiecePositionDAO();
    private PlayerEntity playerEntity;
    private PieceEntity pieceEntity;
    private PositionEntity positionEntity;
    private PiecePositionEntity piecePositionEntity;

    @BeforeEach
    void setUp() throws SQLException {
        positionEntity = positionDAO.findByFileAndRank(D, FIVE);
        pieceEntity = pieceDAO.findByPieceTypeAndTeamColor(PAWN, WHITE);
        ChessGameEntity chessGameEntity = chessGameDAO.save(new ChessGameEntity("test"));
        playerEntity = playerDAO.save(new PlayerEntity(WHITE, chessGameEntity));
        piecePositionEntity = piecePositionDAO.save(
            new PiecePositionEntity(
                playerEntity, new PiecePositionEntities(pieceEntity, positionEntity)));
    }

    @AfterEach
    void tearDown() throws SQLException {
        piecePositionDAO.removeAll();
        playerDAO.removeAll();
        chessGameDAO.removeAll();
    }

    @Test
    void saveAndFind() throws SQLException {

        PiecePositionEntity foundPiecePositionEntity = piecePositionDAO
            .findByPlayerAndPieceAndPosition(playerEntity, pieceEntity, positionEntity);

        assertThat(foundPiecePositionEntity.getPlayerEntity()).isEqualTo(playerEntity);
        assertThat(foundPiecePositionEntity.getPieceEntity()).isEqualTo(pieceEntity);
        assertThat(foundPiecePositionEntity.getPositionEntity()).isEqualTo(positionEntity);
    }

    @Test
    void update() throws SQLException {
        PieceEntity newPieceEntity = pieceDAO.findByPieceTypeAndTeamColor(ROOK, WHITE);
        PositionEntity newPositionEntity = positionDAO.findByFileAndRank(H, TWO);
        piecePositionEntity.setPieceEntity(newPieceEntity);
        piecePositionEntity.setPositionEntity(newPositionEntity);

        PiecePositionEntity updatedPiecePositionDAO = piecePositionDAO
            .updatePieceAndPosition(piecePositionEntity);

        assertThat(updatedPiecePositionDAO.getPlayerEntity()).isEqualTo(playerEntity);
        assertThat(updatedPiecePositionDAO.getPieceEntity()).isEqualTo(newPieceEntity);
        assertThat(updatedPiecePositionDAO.getPositionEntity()).isEqualTo(newPositionEntity);
    }

    @Test
    void delete() throws SQLException {
        piecePositionDAO.remove(piecePositionEntity);

        PiecePositionEntity deletedPiecePosition = piecePositionDAO
            .findByPlayerAndPieceAndPosition(playerEntity, pieceEntity, positionEntity);

        assertThat(deletedPiecePosition).isNull();
    }
}