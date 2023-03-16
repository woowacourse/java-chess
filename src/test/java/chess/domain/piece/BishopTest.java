package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @Test
    @DisplayName("비숍은 대각선으로 움직일 수 있다.")
    void movableTest() {
        Bishop bishop = new Bishop(Camp.WHITE);
        Position from = new Position(File.A, Rank.ONE);
        Position to = new Position(File.C, Rank.THREE);

        boolean result = bishop.movable(from, to);

        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("비숍은 직선으로 움직일 수 없다.")
    void movableFailStraightTest() {
        Bishop bishop = new Bishop(Camp.WHITE);
        Position from = new Position(File.A, Rank.ONE);
        Position to = new Position(File.A, Rank.THREE);

        boolean result = bishop.movable(from, to);

        assertThat(result).isFalse();
    }
}
