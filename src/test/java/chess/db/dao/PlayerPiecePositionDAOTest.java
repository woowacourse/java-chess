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
import chess.db.entity.PlayerPiecePosition;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerPiecePositionDAOTest {
//    private final ChessGameDAO chessGameDAO = new ChessGameDAO();
//    private final PlayerDAO playerDAO = new PlayerDAO();
//    private final PieceDAO pieceDAO = new PieceDAO();
//    private final PositionDAO positionDAO = new PositionDAO();
//    private final PlayerPiecePositionDAO playerPiecePositionDAO = new PlayerPiecePositionDAO();
//    private PlayerEntity playerEntity;
//    private PieceEntity pieceEntity;
//    private PositionEntity positionEntity;
//    private PlayerPiecePosition PlayerPiecePositionEntity;
//
//    @BeforeEach
//    void setUp() throws SQLException {
//        positionEntity = positionDAO.findByFileAndRank(D, FIVE);
//        pieceEntity = pieceDAO.findByPieceTypeAndTeamColor(PAWN, WHITE);
//        ChessGameEntity chessGameEntity = chessGameDAO.save(new ChessGameEntity("test"));
//        playerEntity = playerDAO.save(new PlayerEntity(WHITE, chessGameEntity));
//        PlayerPiecePositionEntity = playerPiecePositionDAO.save(
//            new PlayerPiecePosition(
//                playerEntity, new PiecePosition(pieceEntity, positionEntity)));
//    }
//
//    @AfterEach
//    void tearDown() throws SQLException {
//        playerPiecePositionDAO.removeAll();
//        playerDAO.removeAll();
//        chessGameDAO.removeAll();
//    }
//
//    @Test
//    void saveAndFind() throws SQLException {
//
//        PlayerPiecePosition foundPlayerPiecePosition = playerPiecePositionDAO
//            .findByPlayerAndPieceAndPosition(playerEntity, pieceEntity, positionEntity);
//
//        assertThat(foundPlayerPiecePosition.getPlayerEntity()).isEqualTo(playerEntity);
//        assertThat(foundPlayerPiecePosition.getPieceEntity()).isEqualTo(pieceEntity);
//        assertThat(foundPlayerPiecePosition.getPositionEntity()).isEqualTo(positionEntity);
//    }
//
//    @Test
//    void updatePiecePosition() throws SQLException {
//        PieceEntity newPieceEntity = pieceDAO.findByPieceTypeAndTeamColor(ROOK, WHITE);
//        PositionEntity newPositionEntity = positionDAO.findByFileAndRank(H, TWO);
//        PlayerPiecePositionEntity.setPieceEntity(newPieceEntity);
//        PlayerPiecePositionEntity.setPositionEntity(newPositionEntity);
//
//        PlayerPiecePosition updatedPiecePositionDAO = playerPiecePositionDAO
//            .updatePiecePosition(PlayerPiecePositionEntity);
//
//        assertThat(updatedPiecePositionDAO.getPlayerEntity()).isEqualTo(playerEntity);
//        assertThat(updatedPiecePositionDAO.getPieceEntity()).isEqualTo(newPieceEntity);
//        assertThat(updatedPiecePositionDAO.getPositionEntity()).isEqualTo(newPositionEntity);
//    }
//
//    @Test
//    void remove() throws SQLException {
//        playerPiecePositionDAO.removePiece(PlayerPiecePositionEntity);
//
//        PlayerPiecePosition deletedPiecePosition = playerPiecePositionDAO
//            .findByPlayerAndPieceAndPosition(playerEntity, pieceEntity, positionEntity);
//
//        assertThat(deletedPiecePosition).isNull();
//    }
}