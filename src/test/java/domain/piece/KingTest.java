package domain.piece;

<<<<<<< HEAD
import static domain.game.File.C;
import static domain.game.Rank.FIVE;
import static domain.game.Rank.FOUR;
import static org.assertj.core.api.Assertions.assertThat;

import domain.game.File;
import domain.game.Position;
import domain.game.Rank;
import java.util.List;
=======
>>>>>>> 3ad1dbf (refactor: 패키지 분리)
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

<<<<<<< HEAD
@DisplayName("King은")
class KingTest {

    @DisplayName("Source position이 Target position까지 1칸 이면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"C,FIVE", "C,THREE", "D,FOUR", "B,FOUR", "B,FIVE", "D,FIVE", "D,THREE", "B,THREE"})
    void shouldReturnTrueWhenMovementIsOneStep(File targetFile, Rank targetRank) {
        King whiteKing = King.createOfWhite();
        boolean movable = whiteKing.isMovable(new EmptyPiece(), Position.of(C, FOUR),
                Position.of(targetFile, targetRank));
        assertThat(movable).isTrue();
    }

    @DisplayName("Source position이 Target position까지 1칸이 아니면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"C,SIX", "C,TWO", "E,FOUR", "A,FOUR"})
    void shouldReturnFalseWhenMovementIsNotOneStep(File targetFile, Rank targetRank) {
        King blackKing = King.createOfBlack();
        boolean movable = blackKing.isMovable(new EmptyPiece(), Position.of(C, FOUR),
                Position.of(targetFile, targetRank));
        assertThat(movable).isFalse();
    }

    @DisplayName("Source position이 Target position까지 1칸이지만, Target piece가 같은 진영의 말인 경우 false를 반환한다.")
    @Test
    void shouldReturnFalseWhenMovementIsOneStepButTargetPieceIsSameSide() {
        King whiteKing = King.createOfWhite();
        boolean movable = whiteKing.isMovable(Pawn.createOfWhite(), Position.of(C, FOUR), Position.of(C, FIVE));
        assertThat(movable).isFalse();
    }

    @DisplayName("Source position이 Target position까지 1칸이고, Target piece가 상대 진영의 말인 경우 true를 반환한다.")
    @Test
    void shouldReturnTrueWhenMovementIsOneStepAndTargetPieceIsOpponentSide() {
        King whiteKing = King.createOfWhite();
        boolean movable = whiteKing.isMovable(Pawn.createOfBlack(), Position.of(C, FOUR), Position.of(C, FIVE));
        assertThat(movable).isTrue();
    }

    @DisplayName("모든 방향으로의 움직임에 있어서 거쳐가는 경로의 길이가 0이다.")
    @ParameterizedTest
    @CsvSource(value = {"C,FIVE", "C,THREE", "D,FOUR", "B,FOUR", "B,FIVE", "D,FIVE", "D,THREE", "B,THREE"})
    void shouldReturnEmptyPathWhenMovementIsOneStep(File targetFile, Rank targetRank) {
        King whiteKing = King.createOfWhite();
        List<Position> path = whiteKing.collectPath(Position.of(C, FOUR), Position.of(targetFile, targetRank));
=======
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class KingTest {

    @DisplayName("source position이 target position까지 1칸 이면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"c,5", "c,3", "d,4", "b,4", "b,5", "d,5", "d,3", "b,3"})
    void shouldReturnTrueWhenMovementIsOneStep(String targetFile, String targetRank) {
        King whiteKing = King.createOfWhite();
        boolean movable = whiteKing.isMovable(new EmptyPiece(), Position.of("c", "4"), Position.of(targetFile, targetRank));

        assertThat(movable).isTrue();
    }

    @DisplayName("source position이 target position까지 1칸이 아니면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"c,6", "c,2", "e,4", "a,4"})
    void shouldReturnFalseWhenMovementIsNotOneStep(String targetFile, String targetRank) {
        King blackKing = King.createOfBlack();
        boolean movable = blackKing.isMovable(new EmptyPiece(), Position.of("c", "4"), Position.of(targetFile, targetRank));

        assertThat(movable).isFalse();
    }

    @DisplayName("source position이 target position까지 1칸이지만, target piece가 같은 진영의 말인 경우 false를 반환한다.")
    @Test
    void shouldReturnFalseWhenMovementIsOneStepButTargetPieceIsSameSide() {
        King whiteKing = King.createOfWhite();
        boolean movable = whiteKing.isMovable(Pawn.createOfWhite(), Position.of("c", "4"), Position.of("c", "5"));

        assertThat(movable).isFalse();
    }

    @DisplayName("source position이 target position까지 1칸이고, target piece가 상대 진영의 말인 경우 true를 반환한다.")
    @Test
    void shouldReturnTrueWhenMovementIsOneStepAndTargetPieceIsOpponentSide() {
        King whiteKing = King.createOfWhite();
        boolean movable = whiteKing.isMovable(Pawn.createOfBlack(), Position.of("c", "4"), Position.of("c", "5"));

        assertThat(movable).isTrue();
    }

    @DisplayName("king은 모든 방향으로의 움직임에 있어서 거쳐가는 경로의 길이가 0이다.")
    @ParameterizedTest
    @CsvSource(value = {"c,5", "c,3", "d,4", "b,4", "b,5", "d,5", "d,3", "b,3"})
    void shouldReturnEmptyPathWhenMovementIsOneStep(String targetFile, String targetRank) {
        King whiteKing = King.createOfWhite();
        List<Position> path = whiteKing.collectPath(Position.of("c", "4"), Position.of(targetFile, targetRank));

>>>>>>> 3ad1dbf (refactor: 패키지 분리)
        assertThat(path).isEmpty();
    }
}
