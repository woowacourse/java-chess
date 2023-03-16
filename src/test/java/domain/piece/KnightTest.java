package domain.piece;

<<<<<<< HEAD
import static domain.game.File.C;
import static domain.game.Rank.FOUR;
import static org.assertj.core.api.Assertions.assertThat;

import domain.game.File;
import domain.game.Position;
import domain.game.Rank;
import domain.game.Score;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Knight는")
class KnightTest {
    @DisplayName("Source position이 수직으로 2칸 이동하고, 수평으로 1칸 이동하면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"B,TWO", "D,TWO", "B,SIX", "D,SIX"})
    void shouldReturnTrueWhenKnightMoveTwoStepVerticalAndOneStepHorizon(File targetFile, Rank targetRank) {
        Knight whiteKnight = Knight.createOfWhite();
        boolean movable = whiteKnight.isMovable(new EmptyPiece(), Position.of(C, FOUR),
                Position.of(targetFile, targetRank));
        Assertions.assertThat(movable).isTrue();
    }

    @DisplayName("Source position이 수직으로 1칸 이동하고, 수평으로 2칸 이동하면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"A,THREE", "A,FIVE", "E,THREE", "E,FIVE"})
    void shouldReturnTrueWhenKnightMoveOneStepVerticalAndTwoStepHorizon(File targetFile, Rank targetRank) {
        Knight blackKnight = Knight.createOfBlack();
        boolean movable = blackKnight.isMovable(new EmptyPiece(), Position.of(C, FOUR),
                Position.of(targetFile, targetRank));
=======
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KnightTest {
    @DisplayName("source position이 수직으로 2칸 이동하고, 수평으로 1칸 이동하면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"b,2", "d,2", "b,6", "d,6"})
    void shouldReturnTrueWhenKnightMoveTwoStepVerticalAndOneStepHorizon(String targetFile, String targetRank) {
        Knight whiteKnight = Knight.createOfWhite();
        boolean movable = whiteKnight.isMovable(new EmptyPiece(), Position.of("c", "4"), Position.of(targetFile, targetRank));

        Assertions.assertThat(movable).isTrue();
    }

    @DisplayName("source position이 수직으로 1칸 이동하고, 수평으로 2칸 이동하면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"a,3", "a,5", "e,3", "e,5"})
    void shouldReturnTrueWhenKnightMoveOneStepVerticalAndTwoStepHorizon(String targetFile, String targetRank) {
        Knight blackKnight = Knight.createOfBlack();
        boolean movable = blackKnight.isMovable(new EmptyPiece(), Position.of("c", "4"), Position.of(targetFile, targetRank));

>>>>>>> 3ad1dbf (refactor: 패키지 분리)
        Assertions.assertThat(movable).isTrue();
    }

    @DisplayName("올바른 움직임이 아닐 경우 false를 반환한다.")
    @ParameterizedTest
<<<<<<< HEAD
    @CsvSource(value = {"B,THREE", "C,SIX", "E,SEVEN", "C,ONE"})
    void shouldReturnFalseWhenKnightMoveInvalidMovement(File targetFile, Rank targetRank) {
        Knight blackKnight = Knight.createOfBlack();
        boolean movable = blackKnight.isMovable(new EmptyPiece(), Position.of(C, FOUR),
                Position.of(targetFile, targetRank));
        Assertions.assertThat(movable).isFalse();
    }

    @DisplayName("올바른 움직임 이지만, Target piece가 같은 진영일 경우, false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"B,TWO", "D,TWO", "B,SIX", "D,SIX"})
    void shouldReturnFalseWhenKnightMoveCorrectlyButTargetPieceIsSameSide(File targetFile, Rank targetRank) {
        Knight blackKnight = Knight.createOfBlack();
        boolean movable = blackKnight.isMovable(Pawn.createOfBlack(), Position.of(C, FOUR),
                Position.of(targetFile, targetRank));
        Assertions.assertThat(movable).isFalse();
    }

    @DisplayName("올바른 움직임이고, Target piece가 다른 진영일 경우, true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"A,THREE", "A,FIVE", "E,THREE", "E,FIVE"})
    void shouldReturnTrueWhenKnightMoveCorrectlyAndTargetPieceIsOpponentSide(File targetFile, Rank targetRank) {
        Knight blackKnight = Knight.createOfBlack();
        boolean movable = blackKnight.isMovable(Pawn.createOfWhite(), Position.of(C, FOUR),
                Position.of(targetFile, targetRank));
        Assertions.assertThat(movable).isTrue();
    }

    @DisplayName("모든 방향으로의 움직임에 있어서 거쳐가는 경로의 길이가 0이다.")
    @ParameterizedTest
    @CsvSource(value = {"B,TWO", "D,TWO", "B,SIX", "D,SIX", "A,THREE", "A,FIVE", "E,THREE", "E,FIVE"})
    void shouldReturnEmptyPathWhenMovementCorrectly(File targetFile, Rank targetRank) {
        Knight whiteKnight = Knight.createOfWhite();
        List<Position> path = whiteKnight.collectPath(Position.of(C, FOUR), Position.of(targetFile, targetRank));
        assertThat(path).isEmpty();
    }

    @DisplayName("점수를 요청하면 2.5점을 반환한다.")
    @Test
    void shouldReturnScore2AndHalfWhenRequestScore() {
        Knight whiteKnight = Knight.createOfWhite();
        assertThat(whiteKnight.getScore()).isEqualTo(new Score(2.5));
    }
}
=======
    @CsvSource(value = {"b,3", "c,6", "e,7", "c,1"})
    void shouldReturnFalseWhenKnightMoveInvalidMovement(String targetFile, String targetRank) {
        Knight blackKnight = Knight.createOfBlack();
        boolean movable = blackKnight.isMovable(new EmptyPiece(), Position.of("c", "4"), Position.of(targetFile, targetRank));

        Assertions.assertThat(movable).isFalse();
    }

    @DisplayName("올바른 움직임 이지만, target piece가 같은 진영일 경우, false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"b,2", "d,2", "b,6", "d,6"})
    void shouldReturnFalseWhenKnightMoveCorrectlyButTargetPieceIsSameSide(String targetFile, String targetRank) {
        Knight blackKnight = Knight.createOfBlack();
        boolean movable = blackKnight.isMovable(Pawn.createOfBlack(), Position.of("c", "4"), Position.of(targetFile, targetRank));

        Assertions.assertThat(movable).isFalse();
    }

    @DisplayName("올바른 움직임이고, target piece가 다른 진영일 경우, true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"a,3", "a,5", "e,3", "e,5"})
    void shouldReturnTrueWhenKnightMoveCorrectlyAndTargetPieceIsOpponentSide(String targetFile, String targetRank) {
        Knight blackKnight = Knight.createOfBlack();
        boolean movable = blackKnight.isMovable(Pawn.createOfWhite(), Position.of("c", "4"), Position.of(targetFile, targetRank));

        Assertions.assertThat(movable).isTrue();
    }

    @DisplayName("Knight는 모든 방향으로의 움직임에 있어서 거쳐가는 경로의 길이가 0이다.")
    @ParameterizedTest
    @CsvSource(value = {"b,2", "d,2", "b,6", "d,6", "a,3", "a,5", "e,3", "e,5"})
    void shouldReturnEmptyPathWhenMovementCorrectly(String targetFile, String targetRank) {
        Knight whiteKnight = Knight.createOfWhite();
        List<Position> path = whiteKnight.collectPath(Position.of("c", "4"), Position.of(targetFile, targetRank));

        assertThat(path).isEmpty();
    }
}
>>>>>>> 3ad1dbf (refactor: 패키지 분리)
