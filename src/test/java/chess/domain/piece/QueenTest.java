package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.position.move.BlockingMove;
import chess.domain.position.move.PieceMove;
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
}
