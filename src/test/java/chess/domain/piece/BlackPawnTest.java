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

@DisplayName("검은색 폰")
class BlackPawnTest {

    @ParameterizedTest
    @ValueSource(strings = {"c6", "c5"})
    @DisplayName("처음에 아래로 최대 두 칸까지 이동할 수 있다.")
    void moveUpToTwoStepWhenBlackPawnFirstStep(String targetInput) {
        //given
        Square source = Square.from("c7");
        Square target = Square.from(targetInput);
        Pawn pawn = new Pawn(PieceColor.BLACK, source);
        Board board = new Board(Set.of(pawn));

        // when
        pawn.move(board, target);

        // then
        assertThat(pawn.getSquare()).isEqualTo(target);
    }

    @Test
    @DisplayName("처음에 아래로 세 칸 이상 이동할 경우 예외가 발생한다.")
    void validateStepLimitBlackPawnForFirst() {
        // given
        Square source = Square.from("c7");
        Square target = Square.from("c4");
        Pawn pawn = new Pawn(PieceColor.BLACK, source);
        Board board = new Board(Set.of(pawn));

        // when & then
        assertThatCode(() -> pawn.move(board, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰의 이동 방법으로 갈 수 없는 곳입니다.");
    }

    @Test
    @DisplayName("두번째 이동부터 아래로 한 칸만 이동할 수 있다.")
    void moveOneStepWhenBlackPawnAfterFirstStep() {
        //given
        Square source = Square.from("c7");
        Square stopover = Square.from("c5");
        Square target = Square.from("c4");
        Pawn pawn = new Pawn(PieceColor.BLACK, source);
        Board board = new Board(Set.of(pawn));

        // when
        pawn.move(board, stopover);
        pawn.move(board, target);

        // then
        assertThat(pawn.getSquare()).isEqualTo(target);
    }

    @ParameterizedTest
    @ValueSource(strings = {"c3", "c2"})
    @DisplayName("두번째 이동부터 아래로 두 칸 이상 이동할 경우 예외가 발생한다.")
    void validateStepLimitBlackPawnAfterFirst(String targetInput) {
        // given
        Square source = Square.from("c7");
        Square stopover = Square.from("c5");
        Square target = Square.from(targetInput);
        Pawn pawn = new Pawn(PieceColor.BLACK, source);
        Board board = new Board(Set.of(pawn));

        // then
        pawn.move(board, stopover);

        // when & then
        assertThatCode(() -> pawn.move(board, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰의 이동 방법으로 갈 수 없는 곳입니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"b7", "b8", "c8", "d8", "d7"})
    @DisplayName("아래가 아닌 곳으로 이동할 경우 예외가 발생한다.")
    void validateDirectionBlackPawn(String targetInput) {
        // given
        Square source = Square.from("c7");
        Square target = Square.from(targetInput);
        Pawn pawn = new Pawn(PieceColor.BLACK, source);
        Board board = new Board(Set.of(pawn));

        // when & then
        assertThatCode(() -> pawn.move(board, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰의 이동 방법으로 갈 수 없는 곳입니다.");
    }

    @Test
    @DisplayName("아래 대각선 한 칸에 적 기물이 존재하면 이동하여 공격할 수 있다.")
    void attackDiagonalForBlackPawn() {
        // given
        Square source = Square.from("c7");
        Square target = Square.from("b6");
        Pawn pawn = new Pawn(PieceColor.BLACK, source);
        Pawn enemy = new Pawn(PieceColor.WHITE, target);
        Board board = new Board(Set.of(pawn, enemy));

        // when
        pawn.move(board, target);

        // then
        assertThat(pawn.getSquare()).isEqualTo(target);
    }

    @Test
    @DisplayName("목적지에 기물이 존재할 경우 예외가 발생한다.")
    void validateObstacleTarget() {
        // given
        Square source = Square.from("c7");
        Square target = Square.from("c6");
        Pawn pawn = new Pawn(PieceColor.BLACK, source);
        Pawn obstacle = new Pawn(PieceColor.WHITE, Square.from("c6"));
        Board board = new Board(Set.of(pawn, obstacle));

        // when & then
        assertThatCode(() -> pawn.move(board, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰의 이동 경로 중 장애물이 존재합니다.");
    }

    @Test
    @DisplayName("출발지와 목적지 사이에 기물이 존재할 경우 예외가 발생한다.")
    void validateObstaclePath() {
        // given
        Square source = Square.from("c7");
        Square target = Square.from("c5");
        Pawn pawn = new Pawn(PieceColor.BLACK, source);
        Pawn obstacle = new Pawn(PieceColor.WHITE, Square.from("c6"));
        Board board = new Board(Set.of(pawn, obstacle));

        // when & then
        assertThatCode(() -> pawn.move(board, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰의 이동 경로 중 장애물이 존재합니다.");
    }
}
