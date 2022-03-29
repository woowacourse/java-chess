package chess.piece;

import chess.chessgame.MovingPosition;
import chess.chessgame.Position;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

    @ParameterizedTest
    @ValueSource(strings = {"b5", "d5"})
    @DisplayName("white pawn 대각선 위치 검증 - true")
    void checkDiagonalWhenWhiteTrue(String input) {
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.isDiagonal(new MovingPosition("c4", input))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"f5", "h5"})
    @DisplayName("black pawn 대각선 위치 검증 - true")
    void checkDiagonalWhenBlackTrue(String input) {
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.isDiagonal(new MovingPosition("g6", input))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"c5", "b3", "d3"})
    @DisplayName("white pawn 대각선 위치 검증 - false")
    void checkDiagonalWhenWhiteFalse(String input) {
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.isDiagonal(new MovingPosition("c4", input))).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"g5", "f7", "h7"})
    @DisplayName("black pawn 대각선 위치 검증 - false")
    void checkDiagonalWhenBlackFalse(String input) {
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.isDiagonal(new MovingPosition("g6", input))).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"b3", "b4"})
    @DisplayName("pawn 기물 이동 위치 검증 -  (white , 첫번째 턴) -> true")
    void checkPositionWhenWhiteFirstTurnTrue(String input) {
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.isMovable(new MovingPosition("b2", input))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"f6", "f5"})
    @DisplayName("pawn 기물 이동 위치 검증 -  (black , 첫번째 턴) -> true")
    void checkPositionWhenBlackFirstTurnTrue(String input) {
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.isMovable(new MovingPosition("f7", input))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"b5", "c3", "f6", "f5"})
    @DisplayName("pawn 기물 이동 위치 검증 -  (white , 첫번째 턴) -> false")
    void checkPositionWhenWhiteFirstTurnFalse(String input) {
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.isMovable(new MovingPosition("b2", input))).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"f4", "c7", "b3", "b4"})
    @DisplayName("pawn 기물 이동 위치 검증 -  (black , 첫번째 턴) -> false")
    void checkPositionWhenBlackFirstTurnFalse(String input) {
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.isMovable(new MovingPosition("f7", input))).isFalse();
    }

    @Test
    @DisplayName("pawn 기물 이동 위치 검증 -  (white , 첫번째 턴 X) -> true")
    void checkPositionWhenWhiteTurnTrue() {
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.isMovable(new MovingPosition("b2", "b3"))).isTrue();
    }

    @Test
    @DisplayName("pawn 기물 이동 위치 검증 -  (black , 첫번째 턴 X) -> true")
    void checkPositionWhenBlackTurnTrue() {
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.isMovable(new MovingPosition("f7", "f6"))).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = {"b3", "h4", "f6", "f5"})
    @DisplayName("pawn 기물 이동 위치 검증 -  (white , 첫번째 턴 X) -> false")
    void checkPositionWhenWhiteTurnFalse(String input) {
        Pawn pawn = new Pawn(Color.WHITE);
        assertThat(pawn.isMovable(new MovingPosition("b4", input))).isFalse();
    }

    @ParameterizedTest
    @ValueSource(strings = {"b3", "h4", "f6", "a5"})
    @DisplayName("pawn 기물 이동 위치 검증 -  (black , 첫번째 턴 X) -> false")
    void checkPositionWhenBlackTurnTrue(String input) {
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.isMovable(new MovingPosition("f5", input))).isFalse();
    }

    @Test
    @DisplayName("from과 to 사이에 폰이 이동가능한 위치 리스트 반환 - white")
    void checkAllPositionOfPossibleWhite() {
        Pawn pawn = new Pawn(Color.WHITE);
        List<Position> l = pawn.computeMiddlePosition(new MovingPosition("c2", "c4"));
        assertThat(pawn.computeMiddlePosition(new MovingPosition("c2", "c4")))
                .isEqualTo(List.of(new Position(5, 2)));
    }

    @Test
    @DisplayName("from과 to 사이에 폰이 이동가능한 위치 리스트 반환 - black")
    void checkAllPositionOfPossibleBlack() {
        Pawn pawn = new Pawn(Color.BLACK);
        assertThat(pawn.computeMiddlePosition(new MovingPosition("e7", "e5")))
                .isEqualTo(List.of(new Position(2, 4)));
    }
}
