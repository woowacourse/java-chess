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

@DisplayName("킹")
class KingTest {

    @ParameterizedTest
    @ValueSource(strings = {"b7", "c7", "d7", "d6", "d5", "c5", "b5", "b6"})
    @DisplayName("상하좌우 대각선 중 한칸 이동한 뒤 위치 상태를 목적지로 변경한다.")
    void moveTest(String targetInput) {
        // given
        Square source = Square.from("c6");
        Square target = Square.from(targetInput);
        King king = new King(PieceColor.BLACK, source);
        Board board = new Board(Set.of(king));

        // when
        king.move(board, target);

        // then
        assertThat(king.getSquare()).isEqualTo(target);
    }

    @Test
    @DisplayName("두 칸 이상 이동할 경우 예외가 발생한다.")
    void validateStepCountTest() {
        // given
        Square source = Square.from("c6");
        Square target = Square.from("c4");
        King king = new King(PieceColor.BLACK, source);
        Board board = new Board(Set.of(king));

        // when & then
        assertThatCode(() -> king.move(board, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("킹의 이동 방법으로 갈 수 없는 곳입니다.");
    }

    @Test
    @DisplayName("목적지에 적 기물이 존재하면 공격이 가능하다.")
    void attackOnTargetSquareTest() {
        // given
        Square source = Square.from("c6");
        Square target = Square.from("c5");
        King king = new King(PieceColor.BLACK, source);
        Bishop enemy = new Bishop(PieceColor.WHITE, Square.from("c5"));
        Board board = new Board(Set.of(king, enemy));

        // when
        king.move(board, target);

        // then
        assertThat(king.getSquare()).isEqualTo(target);
    }

    @Test
    @DisplayName("목적지에 아군 기물이 존재할 경우 예외가 발생한다.")
    void validateFriendlyTest() {
        // given
        Square source = Square.from("c6");
        Square target = Square.from("c5");
        King king = new King(PieceColor.BLACK, source);
        Bishop friendly = new Bishop(PieceColor.BLACK, Square.from("c5"));
        Board board = new Board(Set.of(king, friendly));

        // when & then
        assertThatCode(() -> king.move(board, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("킹의 목적지에 같은 색 기물이 존재합니다.");
    }

    @Test
    @DisplayName("타입을 반환한다.")
    void getTypeTest() {
        // given
        Square source = Square.from("c6");
        King king = new King(PieceColor.BLACK, source);

        // when
        PieceType type = king.getType();

        // then
        assertThat(type).isEqualTo(PieceType.KING);
    }
}
