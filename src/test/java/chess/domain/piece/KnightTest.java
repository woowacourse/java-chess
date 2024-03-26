package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.square.Square;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DisplayName("나이트")
class KnightTest {

    @ParameterizedTest
    @ValueSource(strings = {"a5", "a7", "e5", "e7", "b4", "d4", "b8", "d8"})
    @DisplayName("앞으로 두칸 후 옆으로 한칸 이동한 뒤 위치 상태를 목적지로 변경한다.")
    void moveTest(String targetInput) {
        // given
        Square source = Square.from("c6");
        Square target = Square.from(targetInput);
        Knight knight = new Knight(PieceColor.BLACK, source);
        Board board = new Board(Set.of(knight));

        // when
        knight.move(board, target);

        // then
        assertThat(knight.getSquare()).isEqualTo(target);
    }

    @Test
    @DisplayName("앞으로 두칸 후 옆 한칸이 아닌 곳으로 이동할 경우 예외가 발생한다.")
    void validateDirectionTest() {
        // given
        Square source = Square.from("c6");
        Square target = Square.from("b5");
        Knight knight = new Knight(PieceColor.BLACK, source);
        Board board = new Board(Set.of(knight));

        // when & then
        assertThatCode(() -> knight.move(board, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("나이트의 이동 방법으로 갈 수 없는 곳입니다.");
    }

    @Test
    @DisplayName("목적지에 적 기물이 존재하면 공격이 가능하다.")
    void attackOnTargetSquareTest() {
        // given
        Square source = Square.from("c6");
        Square target = Square.from("a5");
        Knight knight = new Knight(PieceColor.BLACK, source);
        Bishop enemy = new Bishop(PieceColor.WHITE, Square.from("a5"));
        Board board = new Board(Set.of(knight, enemy));

        // when
        knight.move(board, target);

        // then
        assertThat(knight.getSquare()).isEqualTo(target);
    }

    @Test
    @DisplayName("목적지에 아군 기물이 존재할 경우 예외가 발생한다.")
    void validateFriendlyTest() {
        // given
        Square source = Square.from("c6");
        Square target = Square.from("a5");
        Knight knight = new Knight(PieceColor.BLACK, source);
        Bishop friendly = new Bishop(PieceColor.BLACK, Square.from("a5"));
        Board board = new Board(Set.of(knight, friendly));

        // when & then
        assertThatCode(() -> knight.move(board, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("나이트의 목적지에 같은 색 기물이 존재합니다.");
    }

    @Test
    @DisplayName("타입을 반환한다.")
    void getTypeTest() {
        // given
        Square source = Square.from("c6");
        Knight knight = new Knight(PieceColor.BLACK, source);

        // when
        PieceType type = knight.getType();

        // then
        assertThat(type).isEqualTo(PieceType.KNIGHT);
    }
}
