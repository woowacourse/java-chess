package domain.piece;

import static domain.game.File.A;
import static domain.game.File.B;
import static domain.game.File.C;
import static domain.game.File.D;
import static domain.game.File.E;
import static domain.game.File.F;
import static domain.game.File.G;
import static domain.game.Rank.EIGHT;
import static domain.game.Rank.FIVE;
import static domain.game.Rank.FOUR;
import static domain.game.Rank.SEVEN;
import static domain.game.Rank.SIX;
import static domain.game.Rank.THREE;
import static domain.game.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import domain.game.File;
import domain.game.Position;
import domain.game.Rank;
import domain.game.Score;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Queen은")
class QueenTest {

    @DisplayName("Source position이 Target position까지 수직,수평 방향이면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"C,FIVE", "C,TWO", "F,FOUR", "A,FOUR"})
    void shouldReturnTrueWhenMovementIsPerpendicular(File targetFile, Rank targetRank) {
        Queen whiteKing = Queen.createOfWhite();
        boolean movable = whiteKing.isMovable(
                new EmptyPiece(),
                Position.of(C, FOUR),
                Position.of(targetFile, targetRank));
        assertThat(movable).isTrue();
    }

    @DisplayName("Source position이 Target position까지 대각선 방향이면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"A,SIX", "D,FIVE", "A,TWO", "F,ONE"})
    void shouldReturnTrueWhenMovementIsDiagonal(File targetFile, Rank targetRank) {
        Queen blackQueen = Queen.createOfBlack();
        boolean movable = blackQueen.isMovable(new EmptyPiece(), Position.of(C, FOUR),
                Position.of(targetFile, targetRank));
        assertThat(movable).isTrue();
    }

    @DisplayName("Source position이 Target position까지 수직, 수평, 대각선이 아니면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"D,SIX", "F,SIX"})
    void shouldReturnFalseWhenMovementIsNotPerpendicularAndDiagonal(File targetFile, Rank targetRank) {
        Queen whiteQueen = Queen.createOfWhite();
        boolean movable = whiteQueen.isMovable(new EmptyPiece(), Position.of(C, FOUR),
                Position.of(targetFile, targetRank));
        assertThat(movable).isFalse();
    }

    @DisplayName("올바른 움직임이지만, Target piece가 같은 진영의 말인 경우 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"C,FIVE", "C,TWO", "F,FOUR", "A,FOUR"})
    void shouldReturnFalseWhenMovementIsCorrectButTargetPieceIsSameSide(File targetFile, Rank targetRank) {
        Queen whiteQueen = Queen.createOfWhite();
        boolean movable = whiteQueen.isMovable(Pawn.createOfWhite(), Position.of(C, FOUR),
                Position.of(targetFile, targetRank));
        assertThat(movable).isFalse();
    }

    @DisplayName("올바른 움직임이고, Target piece가 다른 진영의 말인 경우 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"A,SIX", "D,FIVE", "A,TWO", "F,ONE"})
    void shouldReturnTrueWhenMovementIsCorrectAndTargetPieceIsOpponentSide(File targetFile, Rank targetRank) {
        Queen whiteQueen = Queen.createOfWhite();
        boolean movable = whiteQueen.isMovable(Pawn.createOfBlack(), Position.of(C, FOUR),
                Position.of(targetFile, targetRank));
        assertThat(movable).isTrue();
    }

    @DisplayName("오른쪽 위로 이동하면 해당 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenQueenMoveRightUpward() {
        Queen whiteQueen = Queen.createOfWhite();
        List<Position> path = whiteQueen.collectPath(Position.of(C, TWO), Position.of(G, SIX));
        assertThat(path).containsExactlyInAnyOrder(Position.of(D, THREE), Position.of(E, FOUR), Position.of(F, FIVE));
    }

    @DisplayName("왼쪽 아래로 이동하면 해당 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenQueenMoveLeftDownward() {
        Queen whiteQueen = Queen.createOfWhite();
        List<Position> path = whiteQueen.collectPath(Position.of(G, SIX), Position.of(C, TWO));
        assertThat(path).containsExactlyInAnyOrder(Position.of(F, FIVE), Position.of(E, FOUR), Position.of(D, THREE));
    }

    @DisplayName("수직 위로 이동하면 해당 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenQueenMoveUpwardPerpendicular() {
        Queen whiteQueen = Queen.createOfWhite();
        List<Position> path = whiteQueen.collectPath(Position.of(C, FOUR), Position.of(C, EIGHT));
        assertThat(path).containsExactlyInAnyOrder(Position.of(C, FIVE), Position.of(C, SIX), Position.of(C, SEVEN));
    }

    @DisplayName("수평 왼쪽으로 이동하면 해당 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenQueenMoveLeftPerpendicular() {
        Queen whiteQueen = Queen.createOfWhite();
        List<Position> path = whiteQueen.collectPath(Position.of(D, FIVE), Position.of(A, FIVE));
        assertThat(path).containsExactlyInAnyOrder(Position.of(C, FIVE), Position.of(B, FIVE));
    }

    @DisplayName("점수를 요청하면 9점을 반환한다.")
    @Test
    void shouldReturnScoreOf9WhenRequestScore() {
        Queen whiteQueen = Queen.createOfWhite();
        assertThat(whiteQueen.getScore()).isEqualTo(new Score(9));
    }
}
