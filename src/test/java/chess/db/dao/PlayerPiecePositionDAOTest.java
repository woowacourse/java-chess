package chess.db.dao;

import static chess.domain.piece.type.PieceType.PAWN;
import static chess.domain.piece.type.PieceType.ROOK;
import static chess.domain.player.type.TeamColor.WHITE;
import static chess.domain.position.type.File.D;
import static chess.domain.position.type.File.H;
import static chess.domain.position.type.Rank.FIVE;
import static chess.domain.position.type.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import chess.db.domain.piece.PieceEntity;
import chess.db.domain.position.PositionEntity;
import chess.db.entity.ChessGameEntity;
import chess.db.entity.PlayerEntity;
import chess.db.entity.PlayerPiecePositionEntity;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerPiecePositionDAOTest {
    private final ChessGameDAO chessGameDAO = new ChessGameDAO();
    private final PlayerDAO playerDAO = new PlayerDAO();
    private final PieceDAO pieceDAO = new PieceDAO();
    private final PositionDAO positionDAO = new PositionDAO();
    private final PlayerPiecePositionDAO playerPiecePositionDAO = new PlayerPiecePositionDAO();
    private PlayerEntity playerEntity;
    private PieceEntity pieceEntity;
    private PositionEntity positionEntity;
    private PlayerPiecePositionEntity playerPiecePositionEntity;

    @BeforeEach
    void setUp() throws SQLException {
        positionEntity = positionDAO.findByFileAndRank(D, FIVE);
        pieceEntity = pieceDAO.findByPieceTypeAndTeamColor(PAWN, WHITE);
        ChessGameEntity chessGameEntity = chessGameDAO.save(new ChessGameEntity("test"));
        playerEntity = playerDAO.save(new PlayerEntity(WHITE, chessGameEntity));
        playerPiecePositionEntity = playerPiecePositionDAO.save(
            new PlayerPiecePositionEntity(playerEntity, pieceEntity, positionEntity));
    }

    @AfterEach
    void tearDown() throws SQLException {
        playerPiecePositionDAO.deleteAll();
        playerDAO.deleteAll();
        chessGameDAO.deleteAll();
    }

    @Test
    void saveAndFind() throws SQLException {

        PlayerPiecePositionEntity foundPlayerPiecePositionEntity = playerPiecePositionDAO
            .findByPlayerAndPieceAndPosition(playerEntity, pieceEntity, positionEntity);

        assertThat(foundPlayerPiecePositionEntity.getPlayerEntity()).isEqualTo(playerEntity);
        assertThat(foundPlayerPiecePositionEntity.getPieceEntity()).isEqualTo(pieceEntity);
        assertThat(foundPlayerPiecePositionEntity.getPositionEntity()).isEqualTo(positionEntity);
    }

    @Test
    void update() throws SQLException {
        PieceEntity newPieceEntity = pieceDAO.findByPieceTypeAndTeamColor(ROOK, WHITE);
        PositionEntity newPositionEntity = positionDAO.findByFileAndRank(H, TWO);
        playerPiecePositionEntity.setPieceEntity(newPieceEntity);
        playerPiecePositionEntity.setPositionEntity(newPositionEntity);

        PlayerPiecePositionEntity updatedPlayerPiecePositionDAO = playerPiecePositionDAO
            .updatePieceAndPosition(playerPiecePositionEntity);

        assertThat(updatedPlayerPiecePositionDAO.getPlayerEntity()).isEqualTo(playerEntity);
        assertThat(updatedPlayerPiecePositionDAO.getPieceEntity()).isEqualTo(newPieceEntity);
        assertThat(updatedPlayerPiecePositionDAO.getPositionEntity()).isEqualTo(newPositionEntity);
    }

    @Test
    void delete() throws SQLException {
        playerPiecePositionDAO.remove(playerPiecePositionEntity);

        PlayerPiecePositionEntity deletedPlayerPiecePosition = playerPiecePositionDAO
            .findByPlayerAndPieceAndPosition(playerEntity, pieceEntity, positionEntity);

        assertThat(deletedPlayerPiecePosition).isNull();
    }
}