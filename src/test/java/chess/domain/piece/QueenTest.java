package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.piece.move.BlockingMove;
import chess.domain.piece.move.PieceMove;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @Test
    @DisplayName("퀸는 대각선으로 움직일 수 있다.")
    void isMovableDiagonal() {
        Piece queen = new Queen(Camp.WHITE);

        Position from = Position.of(File.A, Rank.ONE);
        Position to = Position.of(File.C, Rank.THREE);

        PieceMove result = queen.getMovement(from, to);

        assertThat(result).isInstanceOf(BlockingMove.class);
    }

    @Test
    @DisplayName("퀸는 직선으로 움직일 수 있다.")
    void isMovable() {
        Piece queen = new Queen(Camp.WHITE);

        Position from = Position.of(File.A, Rank.ONE);
        Position to = Position.of(File.A, Rank.EIGHT);

        PieceMove result = queen.getMovement(from, to);

        assertThat(result).isInstanceOf(BlockingMove.class);
    }

    @Test
    @DisplayName("퀸은 기물 위치와 관계없이 9점을 추가한다.")
    void queenScoreTest() {
        Piece queen = new Queen(Camp.WHITE);
        double sourceScore = 0;

        double appendScore = queen.appendPieceScore(sourceScore, true);

        assertThat(appendScore).isEqualTo(9d);
    }
}
