package chess.domain.piece;

import chess.domain.PieceColor;
import chess.domain.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("나이트")
class KnightTest {

    @Test
    @DisplayName("에 대한 이동 루트가 한칸 전진 후 대각선 한칸 전진 하는지 판단한다.")
    void canMove() {
        Knight knight = new Knight(PieceColor.BLACK);

        boolean actual = knight.canMove(Position.from("c6"), Position.from("a7"));

        assertThat(actual).isTrue();
    }
}
