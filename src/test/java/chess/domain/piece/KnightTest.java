package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("나이트")
class KnightTest {

    @Test
    @DisplayName("에 대한 이동 루트가 한칸 전진 후 대각선 한칸 전진 하는지 판단한다.")
    void canMove() {
        Knight knight = new Knight(Team.BLACK);

        boolean actual = knight.canMove(Square.from("c6"), Square.from("a7"));

        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("이동 경로에 기물이 존재하여도 뛰어넘어 갈 수 있다.")
    void canMoveIfPieceExistsOnPath() {
        Knight knight = new Knight(Team.BLACK);

        boolean actual = knight.canMove(Square.from("c6"), Square.from("b4"));

        assertThat(actual).isTrue();
    }
}
