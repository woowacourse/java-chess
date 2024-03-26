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

@DisplayName("비숍")
class BishopTest {

    @ParameterizedTest
    @ValueSource(strings = {"a8", "e8", "a4", "h1"})
    @DisplayName("대각선으로 이동한 뒤 위치 상태를 목적지로 변경한다.")
    void moveTest(String targetInput) {
        // given
        Square source = Square.from("c6");
        Square target = Square.from(targetInput);
        Bishop bishop = new Bishop(PieceColor.BLACK, source);
        Board board = new Board(Set.of(bishop));

        // when
        bishop.move(board, target);

        // then
        assertThat(bishop.getSquare()).isEqualTo(target);
    }

    @Test
    @DisplayName("대각선이 아닌 곳으로 이동할 경우 예외가 발생한다.")
    void validateDirectionTest() {
        // given
        Square source = Square.from("c6");
        Square target = Square.from("b6");
        Bishop bishop = new Bishop(PieceColor.BLACK, source);
        Board board = new Board(Set.of(bishop));

        // when & then
        assertThatCode(() -> bishop.move(board, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비숍의 이동 방법으로 갈 수 없는 곳입니다.");
    }

    @Test
    @DisplayName("목적지에 적 기물이 존재하면 공격이 가능하다.")
    void attackOnTargetSquareTest() {
        // given
        Square source = Square.from("c6");
        Square target = Square.from("a8");
        Bishop bishop = new Bishop(PieceColor.BLACK, source);
        Bishop enemy = new Bishop(PieceColor.WHITE, Square.from("a8"));
        Board board = new Board(Set.of(bishop, enemy));

        // when
        bishop.move(board, target);

        // then
        assertThat(bishop.getSquare()).isEqualTo(target);
    }

    @Test
    @DisplayName("목적지에 아군 기물이 존재할 경우 예외가 발생한다.")
    void validateFriendlyTest() {
        // given
        Square source = Square.from("c6");
        Square target = Square.from("a8");
        Bishop bishop = new Bishop(PieceColor.BLACK, source);
        Bishop friendly = new Bishop(PieceColor.BLACK, Square.from("a8"));
        Board board = new Board(Set.of(bishop, friendly));

        // when & then
        assertThatCode(() -> bishop.move(board, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비숍의 목적지에 같은 색 기물이 존재합니다.");
    }

    @Test
    @DisplayName("출발지와 목적지 사이에 기물이 존재할 경우 예외가 발생한다.")
    void validateObstacleTest() {
        // given
        Square source = Square.from("c6");
        Square target = Square.from("a8");
        Bishop bishop = new Bishop(PieceColor.BLACK, source);
        Rook obstacle = new Rook(PieceColor.WHITE, Square.from("b7"));
        Board board = new Board(Set.of(bishop, obstacle));

        // when & then
        assertThatCode(() -> bishop.move(board, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("비숍의 이동 경로 중 장애물이 존재합니다.");
    }

    @Test
    @DisplayName("타입을 반환한다.")
    void getTypeTest() {
        // given
        Square source = Square.from("c6");
        Bishop bishop = new Bishop(PieceColor.BLACK, source);

        // when
        PieceType type = bishop.getType();

        // then
        assertThat(type).isEqualTo(PieceType.BISHOP);
    }
}
