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

@DisplayName("흰색 폰")
class WhitePawnTest {

    public static final PieceColor PIECE_COLOR = PieceColor.WHITE;

    @ParameterizedTest
    @ValueSource(strings = {"c3", "c4"})
    @DisplayName("처음에 위로 최대 두 칸까지 이동할 수 있다.")
    void moveUpToTwoStepWhenBlackPawnFirstStepTest(String targetInput) {
        //given
        Square source = Square.from("c2");
        Square target = Square.from(targetInput);
        Pawn pawn = new Pawn(PIECE_COLOR, source);
        Board board = new Board(Set.of(pawn));

        // when
        pawn.move(board, target);

        // then
        assertThat(pawn.getSquare()).isEqualTo(target);
    }

    @Test
    @DisplayName("처음에 위로 세 칸 이상 이동할 경우 예외가 발생한다.")
    void validateStepLimitBlackPawnForFirstTest() {
        // given
        Square source = Square.from("c2");
        Square target = Square.from("c5");
        Pawn pawn = new Pawn(PIECE_COLOR, source);
        Board board = new Board(Set.of(pawn));

        // when & then
        assertThatCode(() -> pawn.move(board, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰의 이동 방법으로 갈 수 없는 곳입니다.");
    }

    @Test
    @DisplayName("두번째 이동부터 위로 한 칸만 이동할 수 있다.")
    void moveOneStepWhenBlackPawnAfterFirstStepTest() {
        //given
        Square source = Square.from("c2");
        Square stopover = Square.from("c4");
        Square target = Square.from("c5");
        Pawn pawn = new Pawn(PIECE_COLOR, source);
        Board board = new Board(Set.of(pawn));

        // when
        pawn.move(board, stopover);
        pawn.move(board, target);

        // then
        assertThat(pawn.getSquare()).isEqualTo(target);
    }

    @ParameterizedTest
    @ValueSource(strings = {"c6", "c7"})
    @DisplayName("두번째 이동부터 위로 두 칸 이상 이동할 경우 예외가 발생한다.")
    void validateStepLimitBlackPawnAfterFirstTest(String targetInput) {
        // given
        Square source = Square.from("c2");
        Square stopover = Square.from("c4");
        Square target = Square.from(targetInput);
        Pawn pawn = new Pawn(PIECE_COLOR, source);
        Board board = new Board(Set.of(pawn));

        // then
        pawn.move(board, stopover);

        // when & then
        assertThatCode(() -> pawn.move(board, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰의 이동 방법으로 갈 수 없는 곳입니다.");
    }

    @ParameterizedTest
    @ValueSource(strings = {"b2", "b1", "c1", "d1", "d2"})
    @DisplayName("위가 아닌 곳으로 이동할 경우 예외가 발생한다.")
    void validateDirectionBlackPawnTest(String targetInput) {
        // given
        Square source = Square.from("c2");
        Square target = Square.from(targetInput);
        Pawn pawn = new Pawn(PIECE_COLOR, source);
        Board board = new Board(Set.of(pawn));

        // when & then
        assertThatCode(() -> pawn.move(board, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰의 이동 방법으로 갈 수 없는 곳입니다.");
    }

    @Test
    @DisplayName("위 대각선 한 칸에 적 기물이 존재하면 이동하여 공격할 수 있다.")
    void attackDiagonalForBlackPawnTest() {
        // given
        Square source = Square.from("c2");
        Square target = Square.from("b3");
        Pawn pawn = new Pawn(PIECE_COLOR, source);
        Pawn enemy = new Pawn(PIECE_COLOR.opposite(), target);
        Board board = new Board(Set.of(pawn, enemy));

        // when
        pawn.move(board, target);

        // then
        assertThat(pawn.getSquare()).isEqualTo(target);
    }

    @Test
    @DisplayName("위 대각선으로 두 칸 이동하여 공격할 경우 예외가 발생한다.")
    void validateAttackStepLimitTest() {
        // given
        Square source = Square.from("c2");
        Square target = Square.from("a4");
        Pawn pawn = new Pawn(PIECE_COLOR, source);
        Pawn enemy = new Pawn(PIECE_COLOR.opposite(), target);
        Board board = new Board(Set.of(pawn, enemy));

        // when & then
        assertThatCode(() -> pawn.move(board, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰의 이동 방법으로 갈 수 없는 곳입니다.");
    }

    @Test
    @DisplayName("목적지에 기물이 존재할 경우 예외가 발생한다.")
    void validateObstacleTargetTest() {
        // given
        Square source = Square.from("c2");
        Square target = Square.from("c3");
        Pawn pawn = new Pawn(PIECE_COLOR, source);
        Pawn obstacle = new Pawn(PIECE_COLOR.opposite(), target);
        Board board = new Board(Set.of(pawn, obstacle));

        // when & then
        assertThatCode(() -> pawn.move(board, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰의 이동 경로 중 장애물이 존재합니다.");
    }

    @Test
    @DisplayName("출발지와 목적지 사이에 기물이 존재할 경우 예외가 발생한다.")
    void validateObstaclePathTest() {
        // given
        Square source = Square.from("c2");
        Square target = Square.from("c4");
        Pawn pawn = new Pawn(PIECE_COLOR, source);
        Pawn obstacle = new Pawn(PIECE_COLOR.opposite(), Square.from("c3"));
        Board board = new Board(Set.of(pawn, obstacle));

        // when & then
        assertThatCode(() -> pawn.move(board, target))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("폰의 이동 경로 중 장애물이 존재합니다.");
    }

    @Test
    @DisplayName("타입을 반환한다.")
    void getTypeTest() {
        // given
        Square source = Square.from("c2");
        Pawn pawn = new Pawn(PIECE_COLOR, source);

        // when
        PieceType type = pawn.getType();

        // then
        assertThat(type).isEqualTo(PieceType.PAWN);
    }
}
