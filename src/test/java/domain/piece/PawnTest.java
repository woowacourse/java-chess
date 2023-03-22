package domain.piece;

import static domain.game.File.A;
import static domain.game.File.B;
import static domain.game.File.C;
import static domain.game.Rank.FIVE;
import static domain.game.Rank.FOUR;
import static domain.game.Rank.ONE;
import static domain.game.Rank.SEVEN;
import static domain.game.Rank.SIX;
import static domain.game.Rank.THREE;
import static domain.game.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import domain.game.File;
import domain.game.Position;
import domain.game.Rank;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PawnTest {

    @DisplayName("White 진영인 경우 - Target position이 Source position보다 rank가 1높고, Target position이 비어있으면 true를 반환한다.")
    @Test
    void shouldReturnIfIsMovableToTargetPositionWhenPawnIsWhiteSide() {
        Pawn whitePawn = Pawn.createOfWhite();
        boolean movable = whitePawn.isMovable(new EmptyPiece(), Position.of(A, TWO), Position.of(A, THREE));
        assertThat(movable).isTrue();
    }

    @DisplayName("Black 진영인 경우 - Target position이 Source position보다 rank가 1낮고, Target position이 비어있으면 true를 반환한다.")
    @Test
    void shouldReturnIfIsMovableToTargetPositionWhenPawnIsBlackSide() {
        Pawn blackPawn = Pawn.createOfBlack();
        boolean movable = blackPawn.isMovable(new EmptyPiece(), Position.of(A, SEVEN), Position.of(A, SIX));
        assertThat(movable).isTrue();
    }

    @DisplayName("White 진영인 경우 - 처음 움직일 때 Target position이 Source position의 rank보다 2높으면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"TWO,FOUR,true", "TWO,THREE,true", "TWO,FIVE,false", "THREE,FOUR,true", "THREE,FIVE,false"})
    void shouldReturnIfIsMovableTwoStepTargetPositionWhenPawnIsWhiteSideAndFirstMoving(
            Rank sourceRank,
            Rank targetRank,
            boolean result) {
        Pawn whitePawn = Pawn.createOfWhite();
        boolean movable = whitePawn.isMovable(new EmptyPiece(), Position.of(A, sourceRank),
                Position.of(A, targetRank));
        assertThat(movable).isEqualTo(result);
    }

    @DisplayName("Black 진영인 경우 - 처음 움직일 때 Target position이 Source position의 rank보다 2낮으면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"SEVEN, FIVE, true", "SEVEN,SIX,true", "SEVEN,FOUR,false", "SIX,FIVE,true", "SIX,FOUR,false"})
    void shouldReturnIfIsMovableTwoStepTargetPositionWhenPawnIsBlackSideAndFirstMoving(
            Rank sourceRank,
            Rank targetRank,
            boolean result) {
        Pawn blackPawn = Pawn.createOfBlack();
        boolean movable = blackPawn.isMovable(new EmptyPiece(), Position.of(A, sourceRank),
                Position.of(A, targetRank));
        assertThat(movable).isEqualTo(result);
    }

    @DisplayName("Target piece와 동일한 팀이라면 true를 반환한다.")
    @Test
    void shouldReturnIfSameSideWhenTargetIsWhiteSideAndSourceSideIsWhite() {
        Pawn sourcePawn = Pawn.createOfWhite();
        Pawn targetPawn = Pawn.createOfWhite();

        assertThat(sourcePawn.isSameSideWith(targetPawn)).isEqualTo(true);
    }

    @DisplayName("Target piece와 다른 팀이라면 false를 반환한다.")
    @Test
    void shouldReturnIfDifferentSideWhenTargetIsWhiteSideAndSourceSideIsBlack() {
        Pawn sourcePawn = Pawn.createOfWhite();
        Pawn targetPawn = Pawn.createOfBlack();

        assertThat(sourcePawn.isSameSideWith(targetPawn)).isEqualTo(false);
    }

    @DisplayName("Target piece의 상대 팀이라면 true를 반환한다.")
    @Test
    void shouldReturnTrueWhenTargetPieceIsOpponentSide() {
        Pawn sourcePawn = Pawn.createOfWhite();
        Pawn targetPawn = Pawn.createOfBlack();

        assertThat(sourcePawn.isOpponentSideWith(targetPawn)).isEqualTo(true);
    }

    @DisplayName("Target piece의 같은 팀이라면 false를 반환한다.")
    @Test
    void shouldReturnFalseWhenTargetPieceIsSameSide() {
        Pawn sourcePawn = Pawn.createOfWhite();
        Pawn targetPawn = Pawn.createOfWhite();

        assertThat(sourcePawn.isOpponentSideWith(targetPawn)).isEqualTo(false);
    }

    @DisplayName("White 진영인 경우 - 위쪽 대각선에 상대편 말이 있는 경우 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"C,TWO,B,THREE,true", "C,TWO,D,THREE,true", "C,TWO,C,THREE,false"})
    void shouldReturnTrueWhenMoveToOpponentPieceWhitePawn(
            File sourceFile, Rank sourceRank,
            File targetFile, Rank targetRank,
            boolean result) {
        Pawn sourcePawn = Pawn.createOfWhite();
        boolean movable = sourcePawn.isMovable(
                Pawn.createOfBlack(),
                Position.of(sourceFile, sourceRank),
                Position.of(targetFile, targetRank));
        assertThat(movable).isEqualTo(result);
    }

    @DisplayName("Black 진영인 경우 - 아래쪽 대각선에 상대편 말이 있는 경우 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"C,SEVEN,B,SIX,true", "C,SEVEN,D,SIX,true", "C,SEVEN,C,SIX,false"})
    void shouldReturnTrueWhenMoveToOpponentPieceBlackPawn(
            File sourceFile, Rank sourceRank,
            File targetFile, Rank targetRank,
            boolean result) {
        Pawn sourcePawn = Pawn.createOfBlack();
        boolean movable = sourcePawn.isMovable(
                Pawn.createOfWhite(),
                Position.of(sourceFile, sourceRank),
                Position.of(targetFile, targetRank));
        assertThat(movable).isEqualTo(result);
    }

    @DisplayName("White 진영의 Pawn이 위로 한 칸 이동하면, 이동 경로는 위치를 0개 가진다.")
    @Test
    void shouldHasNoPositionWhenGetPathWhitePawn() {
        Pawn pawn = Pawn.createOfWhite();
        List<Position> path = pawn.collectPath(Position.of(B, TWO), Position.of(B, THREE));
        assertThat(path).isEmpty();
    }

    @DisplayName("White 진영의 Pawn이 위로 두 칸 이동할 때 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenWhitePawnMoveTwoSteps() {
        Pawn pawn = Pawn.createOfWhite();
        List<Position> path = pawn.collectPath(Position.of(B, TWO), Position.of(B, FOUR));
        assertThat(path).containsExactlyInAnyOrder(Position.of(B, THREE));
    }

    @DisplayName("White 진영의 Pawn이 오른쪽 위로 이동하면, 이동 경로는 위치를 0개 가진다.")
    @Test
    void shouldReturnPathWhenWhitePawnMoveRightUpward() {
        Pawn pawn = Pawn.createOfWhite();
        List<Position> path = pawn.collectPath(Position.of(B, TWO), Position.of(C, THREE));
        assertThat(path).isEmpty();
    }

    @DisplayName("White 진영의 Pawn이 왼쪽 위로 이동하면, 이동 경로는 위치를 0개 가진다.")
    @Test
    void shouldReturnPathWhenWhitePawnMoveLeftUpward() {
        Pawn pawn = Pawn.createOfWhite();
        List<Position> path = pawn.collectPath(Position.of(B, TWO), Position.of(C, ONE));
        assertThat(path).isEmpty();
    }

    @DisplayName("Black 진영의 Pawn이 아래로 한 칸 이동하면, 이동 경로는 위치를 0개 가진다.")
    @Test
    void shouldHasNoPositionWhenGetPathBlackPawn() {
        Pawn pawn = Pawn.createOfBlack();
        List<Position> path = pawn.collectPath(Position.of(B, SEVEN), Position.of(B, SIX));
        assertThat(path).isEmpty();
    }

    @DisplayName("Black 진영의 Pawn이 아래로 두 칸 이동할 때 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenBlackPawnMoveTwoSteps() {
        Pawn pawn = Pawn.createOfBlack();
        List<Position> path = pawn.collectPath(Position.of(B, SEVEN), Position.of(B, FIVE));
        assertThat(path).containsExactlyInAnyOrder(Position.of(B, SIX));
    }

    @DisplayName("Black 진영의 Pawn이 오른쪽 아래로 이동하면, 이동 경로는 위치를 0개 가진다.")
    @Test
    void shouldReturnPathWhenBLackPawnMoveRightDownward() {
        Pawn pawn = Pawn.createOfBlack();
        List<Position> path = pawn.collectPath(Position.of(B, SEVEN), Position.of(C, SIX));
        assertThat(path).isEmpty();
    }

    @DisplayName("Black 진영의 Pawn이 왼쪽 아래로 이동하면, 이동 경로는 위치를 0개 가진다.")
    @Test
    void shouldReturnPathWhenBlackPawnMoveLeftDownward() {
        Pawn pawn = Pawn.createOfBlack();
        List<Position> path = pawn.collectPath(Position.of(B, SEVEN), Position.of(A, SIX));
        assertThat(path).isEmpty();
    }
}
