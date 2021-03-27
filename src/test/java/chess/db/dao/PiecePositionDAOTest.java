package chess.db.dao;

import static chess.domain.piece.type.PieceType.PAWN;
import static chess.domain.player.type.TeamColor.WHITE;
import static chess.domain.position.type.File.D;
import static chess.domain.position.type.Rank.FIVE;
import static org.assertj.core.api.Assertions.assertThat;

import chess.db.entity.PieceEntity;
import chess.db.entity.PiecePositionEntity;
import chess.db.entity.PositionEntity;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class PiecePositionDAOTest {
    private final PlayerDAO playerDAO = new PlayerDAO();
    private final PieceDAO pieceDAO = new PieceDAO();
    private final PositionDAO positionDAO = new PositionDAO();
    private final PiecePositionDAO piecePositionDAO = new PiecePositionDAO();

    @AfterEach
    void tearDown() throws SQLException {
        piecePositionDAO.deleteAll();
    }

    @Test
    void save() throws SQLException {
        PieceEntity pieceEntity = pieceDAO.findByPieceTypeAndTeamColor(PAWN, WHITE);
        PositionEntity positionEntity = positionDAO.findByFileAndRank(D, FIVE);
        PiecePositionEntity piecePositionEntity
            = new PiecePositionEntity(pieceEntity, positionEntity);

        PiecePositionEntity savedPiecePositionEntity
            = piecePositionDAO.save(piecePositionEntity);

        assertThat(savedPiecePositionEntity.getPieceEntity()).isEqualTo(pieceEntity);
        assertThat(savedPiecePositionEntity.getPositionEntity()).isEqualTo(positionEntity);
    }
}