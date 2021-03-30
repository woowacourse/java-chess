package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.piece.Piece;
import chess.domain.piece.type.PieceType;
import chess.domain.player.type.TeamColor;
import java.sql.SQLException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PieceDAOTest {
    private final PieceDAO pieceDAO = new PieceDAO();

    @DisplayName("모든 색깔과 종류의 기물들이 DB에서 조회되는지 테스트")
    @Test
    void findById() throws SQLException {
        for (PieceType pieceType : PieceType.values()) {
            findByTeamColorAndPieceTypeOf(pieceType);
        }
    }

    private void findByTeamColorAndPieceTypeOf(PieceType pieceType) throws SQLException {
        for (TeamColor teamColor : TeamColor.values()) {
            Piece piece = pieceDAO.findByPieceTypeAndTeamColor(pieceType, teamColor);
            assertThat(piece.getPieceType()).isSameAs(pieceType);
            assertThat(piece.getTeamColor()).isSameAs(teamColor);
        }
    }
}