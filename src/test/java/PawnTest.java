import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

//    @DisplayName("White 진영의 Pawn이 위로 한 칸 이동하면, 이동 경로는 위치를 0개 가진다.")
//    @Test
//    void shouldHasNoPositionWhenGetPath() {
//        Pawn pawn = Pawn.createOfWhite();
//        List<Position> path = pawn.getPath(Position.of("b", "2"), Position.of("b", "3"));
//        assertThat(path).hasSize(0);
//    }

    @DisplayName("White 진영인 경우 - target position이 source position보다 rank가 1높고, target position이 비어있으면 true를 반환한다.")
    @Test
    void shouldReturnIfIsMovableToTargetPositionWhenPawnIsWhiteSide() {
        Pawn whitePawn = Pawn.createOfWhite();
        boolean movable = whitePawn.isMovable(new EmptyPiece(), Position.of("a", "2"), Position.of("a", "3"));
        assertThat(movable).isTrue();
    }

    @DisplayName("Black 진영인 경우 - target position이 source position보다 rank가 1낮고, target position이 비어있으면 true를 반환한다.")
    @Test
    void shouldReturnIfIsMovableToTargetPositionWhenPawnIsBlackSide() {
        Pawn blackPawn = Pawn.createOfBlack();
        boolean movable = blackPawn.isMovable(new EmptyPiece(), Position.of("a", "7"), Position.of("a", "6"));
        assertThat(movable).isTrue();
    }

    @DisplayName("White 진영인 경우 - 처음 움직일 때 target position이 source position의 rank보다 2높으면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"2, 4, true", "2,3,true", "2,5,false", "3,4,true", "3,5,false"})
    void shouldReturnIfIsMovableTwoStepTargetPositionWhenPawnIsWhiteSideAndFirstMoving(String sourceRank, String targetRank, boolean result) {
        Pawn whitePawn = Pawn.createOfWhite();
        boolean movable = whitePawn.isMovable(new EmptyPiece(), Position.of("a", sourceRank), Position.of("a", targetRank));
        assertThat(movable).isEqualTo(result);
    }

    @DisplayName("Black 진영인 경우 - 처음 움직일 때 target position이 source position의 rank보다 2낮으면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"7, 5, true", "7,6,true", "7,4,false", "6,5,true", "6,4,false"})
    void shouldReturnIfIsMovableTwoStepTargetPositionWhenPawnIsBlackSideAndFirstMoving(String sourceRank, String targetRank, boolean result) {
        Pawn blackPawn = Pawn.createOfBlack();
        boolean movable = blackPawn.isMovable(new EmptyPiece(), Position.of("a", sourceRank), Position.of("a", targetRank));
        assertThat(movable).isEqualTo(result);
    }

    @DisplayName("targetPiece와 동일한 팀이라면 true를 반환한다.")
    @Test
    void shouldReturnIfSameSideWhenTargetIsWhiteSideAndSourceSideIsWhite() {
        Pawn sourcePawn = Pawn.createOfWhite();
        Pawn targetPawn = Pawn.createOfWhite();

        assertThat(sourcePawn.isSameSide(targetPawn)).isEqualTo(true);
    }

    @DisplayName("targetPiece와 다른 팀이라면 false를 반환한다.")
    @Test
    void shouldReturnIfDifferentSideWhenTargetIsWhiteSideAndSourceSideIsBlack() {
        Pawn sourcePawn = Pawn.createOfWhite();
        Pawn targetPawn = Pawn.createOfBlack();

        assertThat(sourcePawn.isSameSide(targetPawn)).isEqualTo(false);
    }

    @DisplayName("targetPiece의 상대 팀이라면 true를 반환한다.")
    @Test
    void shouldReturnTrueWhenTargetPieceIsOpponentSide() {
        Pawn sourcePawn = Pawn.createOfWhite();
        Pawn targetPawn = Pawn.createOfBlack();

        assertThat(sourcePawn.isOpponentSide(targetPawn)).isEqualTo(true);
    }

    @DisplayName("targetPiece의 같은 팀이라면 false를 반환한다.")
    @Test
    void shouldReturnFalseWhenTargetPieceIsSameSide() {
        Pawn sourcePawn = Pawn.createOfWhite();
        Pawn targetPawn = Pawn.createOfWhite();

        assertThat(sourcePawn.isOpponentSide(targetPawn)).isEqualTo(false);
    }

    @DisplayName("White 진영인 경우 - 위쪽 대각선에 상대편 말이 있는 경우 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"c,2,b,3,true", "c,2,d,3,true", "c,2,c,3,false"})
    void shouldReturnTrueWhenMoveToOpponentPieceWhitePawn(
            String sourceFile, String sourceRank,
            String targetFile, String targetRank,
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
    @CsvSource(value = {"c,7,b,6,true", "c,7,d,6,true", "c,7,c,6,false"})
    void shouldReturnTrueWhenMoveToOpponentPieceBlackPawn(
            String sourceFile, String sourceRank,
            String targetFile, String targetRank,
            boolean result) {
        Pawn sourcePawn = Pawn.createOfBlack();
        boolean movable = sourcePawn.isMovable(
                Pawn.createOfBlack(),
                Position.of(sourceFile, sourceRank),
                Position.of(targetFile, targetRank));
        assertThat(movable).isEqualTo(result);
    }
}
