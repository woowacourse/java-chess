package domain.piece;

import static domain.piece.File.C;
import static domain.piece.Rank.FIVE;
import static domain.piece.Rank.FOUR;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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

    @DisplayName("King은 모든 방향으로의 움직임에 있어서 거쳐가는 경로의 길이가 0이다.")
    @ParameterizedTest
    @CsvSource(value = {"C,FIVE", "C,THREE", "D,FOUR", "B,FOUR", "B,FIVE", "D,FIVE", "D,THREE", "B,THREE"})
    void shouldReturnEmptyPathWhenMovementIsOneStep(File targetFile, Rank targetRank) {
        King whiteKing = King.createOfWhite();
        List<Position> path = whiteKing.collectPath(Position.of(C, FOUR), Position.of(targetFile, targetRank));
        assertThat(path).isEmpty();
    }
}
