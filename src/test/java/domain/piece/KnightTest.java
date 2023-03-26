package domain.piece;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import view.PieceCategory;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    @DisplayName("source position이 수직으로 2칸 이동하고, 수평으로 1칸 이동하면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"b,2", "d,2", "b,6", "d,6"})
    void shouldReturnTrueWhenKnightMoveTwoStepVerticalAndOneStepHorizon(String targetFile, String targetRank) {
        Knight whiteKnight = new Knight(Side.WHITE);
        boolean movable = whiteKnight.isMovable(new EmptyPiece(), Position.of("c", "4"), Position.of(targetFile, targetRank));

        Assertions.assertThat(movable).isTrue();
    }

    @DisplayName("source position이 수직으로 1칸 이동하고, 수평으로 2칸 이동하면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"a,3", "a,5", "e,3", "e,5"})
    void shouldReturnTrueWhenKnightMoveOneStepVerticalAndTwoStepHorizon(String targetFile, String targetRank) {
        Knight blackKnight = new Knight(Side.BLACK);
        boolean movable = blackKnight.isMovable(new EmptyPiece(), Position.of("c", "4"), Position.of(targetFile, targetRank));

        Assertions.assertThat(movable).isTrue();
    }

    @DisplayName("올바른 움직임이 아닐 경우 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"b,3", "c,6", "e,7", "c,1"})
    void shouldReturnFalseWhenKnightMoveInvalidMovement(String targetFile, String targetRank) {
        Knight blackKnight = new Knight(Side.BLACK);
        boolean movable = blackKnight.isMovable(new EmptyPiece(), Position.of("c", "4"), Position.of(targetFile, targetRank));

        Assertions.assertThat(movable).isFalse();
    }

    @DisplayName("올바른 움직임 이지만, target piece가 같은 진영일 경우, false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"b,2", "d,2", "b,6", "d,6"})
    void shouldReturnFalseWhenKnightMoveCorrectlyButTargetPieceIsSameSide(String targetFile, String targetRank) {
        Knight blackKnight = new Knight(Side.BLACK);
        boolean movable = blackKnight.isMovable(new Pawn(Side.BLACK), Position.of("c", "4"), Position.of(targetFile, targetRank));

        Assertions.assertThat(movable).isFalse();
    }

    @DisplayName("올바른 움직임이고, target piece가 다른 진영일 경우, true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"a,3", "a,5", "e,3", "e,5"})
    void shouldReturnTrueWhenKnightMoveCorrectlyAndTargetPieceIsOpponentSide(String targetFile, String targetRank) {
        Knight blackKnight = new Knight(Side.BLACK);
        boolean movable = blackKnight.isMovable(new Pawn(Side.WHITE)
                , Position.of("c", "4"), Position.of(targetFile, targetRank));

        Assertions.assertThat(movable).isTrue();
    }

    @DisplayName("Knight는 모든 방향으로의 움직임에 있어서 거쳐가는 경로의 길이가 0이다.")
    @ParameterizedTest
    @CsvSource(value = {"b,2", "d,2", "b,6", "d,6", "a,3", "a,5", "e,3", "e,5"})
    void shouldReturnEmptyPathWhenMovementCorrectly(String targetFile, String targetRank) {
        Knight whiteKnight = new Knight(Side.WHITE);
        List<Position> path = whiteKnight.collectPath(Position.of("c", "4"), Position.of(targetFile, targetRank));

        assertThat(path).isEmpty();
    }

    @DisplayName("black knight의 경우 black knight카테고리를 반환한다.")
    @Test
    void blackBishopCategoryTest() {
        assertThat(new Knight(Side.BLACK).getCategory()).isEqualTo(PieceCategory.BLACK_KNIGHT);
    }

    @DisplayName("white knight의 경우 white knight카테고리를 반환한다.")
    @Test
    void whiteBishopCategoryTest() {
        assertThat(new Knight(Side.WHITE).getCategory()).isEqualTo(PieceCategory.WHITE_KNIGHT);
    }
}