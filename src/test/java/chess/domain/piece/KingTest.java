package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.PieceColor;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("킹")
class KingTest {

    @ParameterizedTest
    @ValueSource(strings = {"b7", "c7", "d7", "d6", "d5", "c5", "b5", "b6"})
    @DisplayName("에 대한 이동 루트가 상하좌우 대각선 중 하나인지 판단한다.")
    void canMove(String target) {
        King king = new King(PieceColor.BLACK);

        boolean actual = king.canMove(Position.from("c6"), Position.from(target));

        assertThat(actual).isTrue();
    }
}
