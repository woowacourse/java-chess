package chessgame.domain.piece;

import chessgame.domain.coordinate.Coordinate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PawnTest {

    @Test
    @DisplayName("화이트 폰은 위로 한 칸 이동할 수 있다")
    void isReachableByRuleUp() {
        Coordinate startCoordinate = Coordinate.createOnBoard(1, 0);
        Coordinate endCoordinate = Coordinate.createOnBoard(2, 0);
        Pawn pawn = new WhitePawn();

        assertThat(pawn.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @Test
    @DisplayName("화이트 폰은 위로 두 칸 이동할 수 있다")
    void isReachableByRuleUpTwo() {
        Coordinate startCoordinate = Coordinate.createOnBoard(1, 0);
        Coordinate endCoordinate = Coordinate.createOnBoard(3, 0);
        Pawn pawn = new WhitePawn();

        assertThat(pawn.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @Test
    @DisplayName("화이트 폰은 위로 세 칸 이동할 수 없다")
    void isReachableByRuleUpThree() {
        Coordinate startCoordinate = Coordinate.createOnBoard(1, 0);
        Coordinate endCoordinate = Coordinate.createOnBoard(4, 0);
        Pawn pawn = new WhitePawn();

        assertThat(pawn.isReachableByRule(startCoordinate, endCoordinate)).isFalse();
    }

    @Test
    @DisplayName("화이트 폰은 아래로 한 칸 이동할 수 없다")
    void isReachableByRuleCantDown() {
        Coordinate startCoordinate = Coordinate.createOnBoard(2, 0);
        Coordinate endCoordinate = Coordinate.createOnBoard(1, 0);
        Pawn pawn = new WhitePawn();

        assertThat(pawn.isReachableByRule(startCoordinate, endCoordinate)).isFalse();
    }

    @Test
    @DisplayName("화이트 폰은 대각선으로 한 칸 이동할 수 없다")
    void isReachableByRuleWhiteCantDiagonal() {
        Coordinate startCoordinate = Coordinate.createOnBoard(2, 0);
        Coordinate endCoordinate = Coordinate.createOnBoard(3, 1);
        Pawn pawn = new WhitePawn();

        assertThat(pawn.isReachableByRule(startCoordinate, endCoordinate)).isFalse();
    }

    @ParameterizedTest(name = "(2, 1)에서 (3, {0})로 이동할 수 있다")
    @ValueSource(ints = {0, 2})
    @DisplayName("화이트 폰은 상대 말을 잡는 경우 앞 대각선으로 한 칸 이동할 수 있다")
    void isReachableWhenCatch(int colum) {
        Coordinate startCoordinate = Coordinate.createOnBoard(2, 1);
        Coordinate endCoordinate = Coordinate.createOnBoard(3, colum);
        Pawn pawn = new WhitePawn();

        assertThat(pawn.isReachableWhenCatch(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "(6, 1)에서 (5, {0})로 이동할 수 없다")
    @ValueSource(ints = {0, 2})
    @DisplayName("화이트 폰은 상대 말을 잡는 경우 뒷 대각선 한 칸 이동할 수 없다")
    void isReachableWhenCatchCantBackDiagonal(int colum) {
        Coordinate startCoordinate = Coordinate.createOnBoard(6, 1);
        Coordinate endCoordinate = Coordinate.createOnBoard(5, colum);
        Pawn pawn = new WhitePawn();

        assertThat(pawn.isReachableWhenCatch(startCoordinate, endCoordinate)).isFalse();
    }

    @Test
    @DisplayName("블랙 폰은 아래로 한 칸 이동할 수 있다")
    void isReachableByRuleDown() {
        Coordinate startCoordinate = Coordinate.createOnBoard(6, 0);
        Coordinate endCoordinate = Coordinate.createOnBoard(5, 0);
        Pawn pawn = new BlackPawn();

        assertThat(pawn.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @Test
    @DisplayName("블랙 폰은 아래로 두 칸 이동할 수 있다")
    void isReachableByRuleDownTwo() {
        Coordinate startCoordinate = Coordinate.createOnBoard(6, 0);
        Coordinate endCoordinate = Coordinate.createOnBoard(4, 0);
        Pawn pawn = new BlackPawn();

        assertThat(pawn.isReachableByRule(startCoordinate, endCoordinate)).isTrue();
    }

    @Test
    @DisplayName("블랙 폰은 아래로 세 칸 이동할 수 없다")
    void isReachableByRuleDownThree() {
        Coordinate startCoordinate = Coordinate.createOnBoard(6, 0);
        Coordinate endCoordinate = Coordinate.createOnBoard(3, 0);
        Pawn pawn = new BlackPawn();

        assertThat(pawn.isReachableByRule(startCoordinate, endCoordinate)).isFalse();
    }

    @Test
    @DisplayName("블랙 폰은 위로 한 칸 이동할 수 없다")
    void isReachableByRuleCantUp() {
        Coordinate startCoordinate = Coordinate.createOnBoard(6, 0);
        Coordinate endCoordinate = Coordinate.createOnBoard(7, 0);
        Pawn pawn = new BlackPawn();

        assertThat(pawn.isReachableByRule(startCoordinate, endCoordinate)).isFalse();
    }

    @Test
    @DisplayName("블랙 폰은 대각선을 이동할 수 없다")
    void isReachableByRuleBlackCantDiagonal() {
        Coordinate startCoordinate = Coordinate.createOnBoard(6, 0);
        Coordinate endCoordinate = Coordinate.createOnBoard(5, 1);
        Pawn pawn = new BlackPawn();

        assertThat(pawn.isReachableByRule(startCoordinate, endCoordinate)).isFalse();
    }

    @ParameterizedTest(name = "(2, 1)에서 (3, {0})로 이동할 수 있다")
    @ValueSource(ints = {0, 2})
    @DisplayName("블랙 폰은 상대 말을 잡는 경우 뒷 대각선으로 한 칸 이동할 수 있다")
    void isReachableWhenCatchBlack(int colum) {
        Coordinate startCoordinate = Coordinate.createOnBoard(6, 1);
        Coordinate endCoordinate = Coordinate.createOnBoard(5, colum);
        Pawn pawn = new BlackPawn();

        assertThat(pawn.isReachableWhenCatch(startCoordinate, endCoordinate)).isTrue();
    }

    @ParameterizedTest(name = "(2, 1)에서 (3, {0})로 이동할 수 없다")
    @ValueSource(ints = {0, 2})
    @DisplayName("블랙 폰은 상대 말을 잡는 경우 앞 대각선 한 칸 이동할 수 없다")
    void isReachableWhenCatchBlackCantFrontDiagonal(int colum) {
        Coordinate startCoordinate = Coordinate.createOnBoard(2, 1);
        Coordinate endCoordinate = Coordinate.createOnBoard(3, colum);
        Pawn pawn = new BlackPawn();

        assertThat(pawn.isReachableWhenCatch(startCoordinate, endCoordinate)).isFalse();
    }
}
