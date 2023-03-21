package domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import view.PieceCategory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {

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

        assertThat(sourcePawn.isSameSideWith(targetPawn)).isEqualTo(true);
    }

    @DisplayName("targetPiece와 다른 팀이라면 false를 반환한다.")
    @Test
    void shouldReturnIfDifferentSideWhenTargetIsWhiteSideAndSourceSideIsBlack() {
        Pawn sourcePawn = Pawn.createOfWhite();
        Pawn targetPawn = Pawn.createOfBlack();

        assertThat(sourcePawn.isSameSideWith(targetPawn)).isEqualTo(false);
    }

    @DisplayName("targetPiece의 상대 팀이라면 true를 반환한다.")
    @Test
    void shouldReturnTrueWhenTargetPieceIsOpponentSide() {
        Pawn sourcePawn = Pawn.createOfWhite();
        Pawn targetPawn = Pawn.createOfBlack();

        assertThat(sourcePawn.isOpponentSideWith(targetPawn)).isEqualTo(true);
    }

    @DisplayName("targetPiece의 같은 팀이라면 false를 반환한다.")
    @Test
    void shouldReturnFalseWhenTargetPieceIsSameSide() {
        Pawn sourcePawn = Pawn.createOfWhite();
        Pawn targetPawn = Pawn.createOfWhite();

        assertThat(sourcePawn.isOpponentSideWith(targetPawn)).isEqualTo(false);
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
                Pawn.createOfWhite(),
                Position.of(sourceFile, sourceRank),
                Position.of(targetFile, targetRank));
        assertThat(movable).isEqualTo(result);
    }

    @DisplayName("White 진영의 Pawn이 위로 한 칸 이동하면, 이동 경로는 위치를 0개 가진다.")
    @Test
    void shouldHasNoPositionWhenGetPathWhitePawn() {
        Pawn pawn = Pawn.createOfWhite();
        List<Position> path = pawn.collectPath(Position.of("b", "2"), Position.of("b", "3"));
        assertThat(path).isEmpty();
    }

    @DisplayName("White 진영의 Pawn이 위로 두 칸 이동할 때 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenWhitePawnMoveTwoSteps() {
        Pawn pawn = Pawn.createOfWhite();
        List<Position> path = pawn.collectPath(Position.of("b", "2"), Position.of("b", "4"));
        assertThat(path).containsExactlyInAnyOrder(Position.of("b", "3"));
    }

    @DisplayName("White 진영의 Pawn이 오른쪽 위로 이동하면, 이동 경로는 위치를 0개 가진다.")
    @Test
    void shouldReturnPathWhenWhitePawnMoveRightUpward() {
        Pawn pawn = Pawn.createOfWhite();
        List<Position> path = pawn.collectPath(Position.of("b", "2"), Position.of("c", "3"));
        assertThat(path).isEmpty();
    }

    @DisplayName("White 진영의 Pawn이 왼쪽 위로 이동하면, 이동 경로는 위치를 0개 가진다.")
    @Test
    void shouldReturnPathWhenWhitePawnMoveLeftUpward() {
        Pawn pawn = Pawn.createOfWhite();
        List<Position> path = pawn.collectPath(Position.of("b", "2"), Position.of("c", "1"));
        assertThat(path).isEmpty();
    }

    @DisplayName("Black 진영의 Pawn이 아래로 한 칸 이동하면, 이동 경로는 위치를 0개 가진다.")
    @Test
    void shouldHasNoPositionWhenGetPathBlackPawn() {
        Pawn pawn = Pawn.createOfBlack();
        List<Position> path = pawn.collectPath(Position.of("b", "7"), Position.of("b", "6"));
        assertThat(path).isEmpty();
    }

    @DisplayName("Black 진영의 Pawn이 아래로 두 칸 이동할 때 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenBlackPawnMoveTwoSteps() {
        Pawn pawn = Pawn.createOfBlack();
        List<Position> path = pawn.collectPath(Position.of("b", "7"), Position.of("b", "5"));
        assertThat(path).containsExactlyInAnyOrder(Position.of("b", "6"));
    }

    @DisplayName("Black 진영의 Pawn이 오른쪽 아래로 이동하면, 이동 경로는 위치를 0개 가진다.")
    @Test
    void shouldReturnPathWhenBLackPawnMoveRightDownward() {
        Pawn pawn = Pawn.createOfBlack();
        List<Position> path = pawn.collectPath(Position.of("b", "7"), Position.of("c", "6"));
        assertThat(path).isEmpty();
    }

    @DisplayName("Black 진영의 Pawn이 왼쪽 아래로 이동하면, 이동 경로는 위치를 0개 가진다.")
    @Test
    void shouldReturnPathWhenBlackPawnMoveLeftDownward() {
        Pawn pawn = Pawn.createOfBlack();
        List<Position> path = pawn.collectPath(Position.of("b", "7"), Position.of("a", "6"));
        assertThat(path).isEmpty();
    }

    @DisplayName("black pawn의 경우 black pawn카테고리를 반환한다.")
    @Test
    void blackBishopCategoryTest() {
        assertThat(Pawn.createOfBlack().getCategory()).isEqualTo(PieceCategory.BLACK_PAWN);
    }

    @DisplayName("white pawn의 경우 white pawn카테고리를 반환한다.")
    @Test
    void whiteBishopCategoryTest() {
        assertThat(Pawn.createOfWhite().getCategory()).isEqualTo(PieceCategory.WHITE_PAWN);
    }
}
