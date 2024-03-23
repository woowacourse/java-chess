package chess.domain.piece;

import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("킹")
class KingTest {

    @ParameterizedTest
    @ValueSource(strings = {"b7", "c7", "d7", "d6", "d5", "c5", "b5", "b6"})
    @DisplayName("상하좌우 대각선 중 한칸 이동 한다.")
    void move(String targetInput) {
        // given
        Square source = Square.from("c6");
        Square target = Square.from(targetInput);
        King king = new King(PieceColor.BLACK, source);

        // when
        king.move(target);

        // then
        assertThat(king.getSquare()).isEqualTo(target);
    }
}
