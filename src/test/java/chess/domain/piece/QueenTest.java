package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class QueenTest {

    @Test
    @DisplayName("퀸는 대각선으로 움직일 수 있다.")
    void isMovableDiagonal() {
        Piece queen = new Queen(Camp.WHITE);

        Position from = new Position(File.A, Rank.ONE);
        Position to = new Position(File.C, Rank.THREE);

        boolean result = queen.isMovable(from, to);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("퀸는 직선으로 움직일 수 있다.")
    void isMovable() {
        Piece queen = new Queen(Camp.WHITE);

        Position from = new Position(File.A, Rank.ONE);
        Position to = new Position(File.A, Rank.EIGHT);

        boolean result = queen.isMovable(from, to);

        assertThat(result).isTrue();
    }

}
