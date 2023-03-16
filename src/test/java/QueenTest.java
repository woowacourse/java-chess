import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class QueenTest {

    @DisplayName("source position이 target position까지 수직,수평 방향이면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"c,5", "c,2", "f,4", "a,4"})
    void shouldReturnTrueWhenMovementIsPerpendicular(String targetFile, String targetRank) {
        Queen whiteKing = Queen.createOfWhite();
        boolean movable = whiteKing.isMovable(new EmptyPiece(), Position.of("c", "4"), Position.of(targetFile, targetRank));

        assertThat(movable).isTrue();
    }

    @DisplayName("source position이 target position까지 대각선 방향이면 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"a,6", "d,5", "a,2", "f,1"})
    void shouldReturnTrueWhenMovementIsDiagonal(String targetFile, String targetRank) {
        Queen blackQueen = Queen.createOfBlack();
        boolean movable = blackQueen.isMovable(new EmptyPiece(), Position.of("c", "4"), Position.of(targetFile, targetRank));

        assertThat(movable).isTrue();
    }

    @DisplayName("source position이 target position까지 수직, 수평, 대각선이 아니면 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"d,6", "f,6"})
    void shouldReturnFalseWhenMovementIsNotPerpendicularAndDiagonal(String targetFile, String targetRank) {
        Queen whiteQueen = Queen.createOfWhite();
        boolean movable = whiteQueen.isMovable(new EmptyPiece(), Position.of("c", "4"), Position.of(targetFile, targetRank));

        assertThat(movable).isFalse();
    }

    @DisplayName("올바른 움직임이지만, target piece가 같은 진영의 말인 경우 false를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"c,5", "c,2", "f,4", "a,4"})
    void shouldReturnFalseWhenMovementIsCorrectButTargetPieceIsSameSide(String targetFile, String targetRank) {
        Queen whiteQueen = Queen.createOfWhite();
        boolean movable = whiteQueen.isMovable(Pawn.createOfWhite(), Position.of("c", "4"), Position.of(targetFile, targetRank));

        assertThat(movable).isFalse();
    }

    @DisplayName("올바른 움직임이고, target piece가 다른 진영의 말인 경우 true를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"a,6", "d,5", "a,2", "f,1"})
    void shouldReturnTrueWhenMovementIsCorrectAndTargetPieceIsOpponentSide(String targetFile, String targetRank) {
        Queen whiteQueen = Queen.createOfWhite();
        boolean movable = whiteQueen.isMovable(Pawn.createOfBlack(), Position.of("c", "4"), Position.of(targetFile, targetRank));

        assertThat(movable).isTrue();
    }

    @DisplayName("Queen이 오른쪽 위로 이동하면 해당 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenQueenMoveRightUpward() {
        Queen whiteQueen = Queen.createOfWhite();
        List<Position> path = whiteQueen.collectPath(Position.of("c", "2"), Position.of("g", "6"));

        assertThat(path).containsExactlyInAnyOrder(Position.of("d", "3"), Position.of("e", "4"), Position.of("f", "5"));
    }

    @DisplayName("Queen이 왼쪽 아래로 이동하면 해당 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenQueenMoveLeftDownward() {
        Queen whiteQueen = Queen.createOfWhite();
        List<Position> path = whiteQueen.collectPath(Position.of("g", "6"), Position.of("c", "2"));

        assertThat(path).containsExactlyInAnyOrder(Position.of("f", "5"), Position.of("e", "4"), Position.of("d", "3"));
    }

    @DisplayName("Queen이 수직 위로 이동하면 해당 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenQueenMoveUpwardPerpendicular() {
        Queen whiteQueen = Queen.createOfWhite();
        List<Position> path = whiteQueen.collectPath(Position.of("c", "4"), Position.of("c", "8"));

        assertThat(path).containsExactlyInAnyOrder(Position.of("c", "5"), Position.of("c", "6"), Position.of("c", "7"));
    }

    @DisplayName("Queen이 수평 왼쪽으로 이동하면 해당 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenQueenMoveLeftPerpendicular() {
        Queen whiteQueen = Queen.createOfWhite();
        List<Position> path = whiteQueen.collectPath(Position.of("d", "5"), Position.of("a", "5"));

        assertThat(path).containsExactlyInAnyOrder(Position.of("c", "5"), Position.of("b", "5"));
    }
}