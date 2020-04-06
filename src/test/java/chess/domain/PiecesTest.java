package chess.domain;

import chess.domain.piece.King;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class PiecesTest {
    @Test
    @DisplayName("킹이 죽었을 때 인식하는지 확인")
    void kingKillTest() {
        Pieces pieces = new Pieces(PieceFactory.getInstance().getPieces());
        pieces.killPiece(new King(new Position("e1"), Team.WHITE));
        assertFalse(pieces.isBothKingAlive());
    }

    @Test
    @DisplayName("킹이 남아있는 팀이 어느쪽인지 인식하는지 확인")
    void aliveKingTest() {
        Pieces pieces = new Pieces(PieceFactory.getInstance().getPieces());
        pieces.killPiece(new King(new Position("e1"), Team.WHITE));
        assertThat(pieces.teamWithAliveKing()).isEqualTo(Team.BLACK);
    }
}
