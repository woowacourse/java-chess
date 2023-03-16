package chess.domain.piece;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EmptyTest {

    @Test
    @DisplayName("빈 공간 선택 시 항상 움직일 수 없다.")
    void movableUnsupportedExceptionTest() {
        Piece empty = new Empty();
        Position from = new Position(File.A, Rank.ONE);
        Position to = new Position(File.A, Rank.THREE);

        boolean result = empty.isMovable(from, to);

        assertThat(result).isFalse();
    }
}
