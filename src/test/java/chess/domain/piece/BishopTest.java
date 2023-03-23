package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.piece.move.BlockingMove;
import chess.domain.piece.move.InvalidMove;
import chess.domain.piece.move.PieceMove;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @Test
    @DisplayName("비숍은 대각선으로 움직일 수 있다.")
    void movableTest() {
        Piece bishop = new Bishop(Camp.WHITE);
        Position from = Position.of(File.A, Rank.ONE);
        Position to = Position.of(File.C, Rank.THREE);

        PieceMove result = bishop.getMovement(from, to);

        assertThat(result).isInstanceOf(BlockingMove.class);
    }

    @Test
    @DisplayName("비숍은 직선으로 움직일 수 없다.")
    void movableFailStraightTest() {
        Piece bishop = new Bishop(Camp.WHITE);
        Position from = Position.of(File.A, Rank.ONE);
        Position to = Position.of(File.A, Rank.THREE);

        PieceMove result = bishop.getMovement(from, to);

        assertThat(result).isInstanceOf(InvalidMove.class);
    }

    @Test
    @DisplayName("비숍은 기물 위치와 관계없이 3점을 추가한다.")
    void bishopScoreTest() {
        Piece bishop = new Bishop(Camp.WHITE);
        double sourceScore = 0;

        double appendScore = bishop.appendScore(sourceScore, true);

        assertThat(appendScore).isEqualTo(3d);
    }
}
