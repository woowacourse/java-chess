package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KingTest {

    @Test
    @DisplayName("킹은 모든 방향으로 움직일 수 있다.")
    void movableTest() {
        Piece king = new King(Camp.WHITE);

        Position from = new Position(File.A, Rank.ONE);
        Position to = new Position(File.A, Rank.TWO);

        boolean result = king.isMovable(from, to);

        assertThat(result).isTrue();
    }


    @Test
    @DisplayName("킹은 한 칸만 움직일 수 있다.")
    void movableSizeTest() {
        Piece king = new King(Camp.WHITE);

        Position from = new Position(File.A, Rank.ONE);
        Position to = new Position(File.C, Rank.THREE);

        boolean result = king.isMovable(from, to);

        assertThat(result).isFalse();
    }
}
