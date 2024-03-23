package chess.domain.piece;

import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("킹")
class KingTest {

    @ParameterizedTest
    @ValueSource(strings = {"b7", "c7", "d7", "d6", "d5", "c5", "b5", "b6"})
    @DisplayName("에 대한 이동 루트가 상하좌우 대각선 중 하나인지 판단한다.")
    void canMove(String target) {
        King king = new King(PieceColor.BLACK);

        boolean actual = king.canMove(Square.from("c6"), Square.from(target));

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("1칸 초과하여 이동 할 수 없다.")
    void cannotMoveMoreThanTwoStep() {
        King king = new King(PieceColor.BLACK);

        boolean actual = king.canMove(Square.from("c6"), Square.from("c4"));

        assertThat(actual).isFalse();
    }
}
