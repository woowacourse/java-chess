package domain.piece;

<<<<<<< HEAD
import static domain.game.File.C;
import static domain.game.File.D;
import static domain.game.File.E;
import static domain.game.File.F;
import static domain.game.File.G;
import static domain.game.Rank.FIVE;
import static domain.game.Rank.FOUR;
import static domain.game.Rank.SIX;
import static domain.game.Rank.THREE;
import static domain.game.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import domain.game.Position;
import domain.game.Score;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Bishop은")
class BishopTest {

    @DisplayName("Source position이 Target position까지 대각선 방향이면 true를 반환한다. ")
    @Test
    void shouldReturnTrueWhenDirectionIsDiagonal() {
        Bishop whiteBishop = Bishop.createOfWhite();
        boolean movable = whiteBishop.isMovable(new EmptyPiece(), Position.of(C, TWO), Position.of(E, FOUR));
        assertThat(movable).isTrue();
    }

    @DisplayName("Source position이 Target position까지 대각선 방향이 아니면 false를 반환한다. ")
    @Test
    void shouldReturnFalseWhenBishopDirectionIsNotDiagonal() {
        Bishop whiteBishop = Bishop.createOfBlack();
        boolean movable = whiteBishop.isMovable(new EmptyPiece(), Position.of(C, TWO), Position.of(E, THREE));
        assertThat(movable).isFalse();
    }

    @DisplayName("Source position이 Target position까지 대각선 방향이지만, Target position에 같은 진영 말이 있으면 false를 반환한다.")
    @Test
    void shouldReturnFalseWhenBishopDirectionIsDiagonalButTargetPieceIsSameSide() {
        Bishop blackBishop = Bishop.createOfBlack();
        boolean movable = blackBishop.isMovable(Pawn.createOfBlack(), Position.of(C, TWO), Position.of(E, FOUR));
=======
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BishopTest {

    @DisplayName("source position이 target position까지 대각선 방향이면 true를 반환한다. ")
    @Test
    void shouldReturnTrueWhenBishopDirectionIsDiagonal() {
        Bishop whiteBishop = Bishop.createOfWhite();
        boolean movable = whiteBishop.isMovable(new EmptyPiece(), Position.of("c", "2"), Position.of("e", "4"));

        assertThat(movable).isTrue();
    }

    @DisplayName("source position이 target position까지 대각선 방향이 아니면 false를 반환한다. ")
    @Test
    void shouldReturnFalseWhenBishopDirectionIsNotDiagonal() {
        Bishop whiteBishop = Bishop.createOfBlack();
        boolean movable = whiteBishop.isMovable(new EmptyPiece(), Position.of("c", "2"), Position.of("e", "3"));

        assertThat(movable).isFalse();
    }

    @DisplayName("source position이 target position까지 대각선 방향이고, target position에 같은 진영 말이 있으면 false를 반환한다.")
    @Test
    void shouldReturnFalseWhenBishopDirectionIsDiagonalButTargetPieceIsSameSide() {
        Bishop blackBishop = Bishop.createOfBlack();
        boolean movable = blackBishop.isMovable(Pawn.createOfBlack(), Position.of("c", "2"), Position.of("e", "4"));

>>>>>>> 3ad1dbf (refactor: 패키지 분리)
        assertThat(movable).isFalse();
    }

    @DisplayName("source position이 target position까지 대각선 방향이고, target position에 상대 진영 말이 있으면 true를 반환한다.")
    @Test
    void shouldReturnTrueWhenBishopDirectionIsDiagonalAndTargetPieceIsOpponentSide() {
        Bishop whiteBishop = Bishop.createOfWhite();
<<<<<<< HEAD
        boolean movable = whiteBishop.isMovable(Pawn.createOfBlack(), Position.of(C, TWO), Position.of(E, FOUR));
        assertThat(movable).isTrue();
    }

    @DisplayName("오른쪽 위로 이동할 때 해당 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenBishopMoveRightUpward() {
        Bishop whiteBishop = Bishop.createOfWhite();
        List<Position> path = whiteBishop.collectPath(Position.of(C, TWO), Position.of(G, SIX));
        assertThat(path).containsExactlyInAnyOrder(Position.of(D, THREE), Position.of(E, FOUR), Position.of(F, FIVE));
    }

    @DisplayName("오른쪽 아래로 이동할 때 해당 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenBishopMoveRightDownward() {
        Bishop whiteBishop = Bishop.createOfWhite();
        List<Position> path = whiteBishop.collectPath(Position.of(C, SIX), Position.of(G, TWO));
        assertThat(path).containsExactlyInAnyOrder(Position.of(D, FIVE), Position.of(E, FOUR), Position.of(F, THREE));
    }

    @DisplayName("왼쪽 아래로 이동할 때 해당 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenBishopMoveLeftDownward() {
        Bishop whiteBishop = Bishop.createOfWhite();
        List<Position> path = whiteBishop.collectPath(Position.of(G, SIX), Position.of(C, TWO));
        assertThat(path).containsExactlyInAnyOrder(Position.of(F, FIVE), Position.of(E, FOUR), Position.of(D, THREE));
    }

    @DisplayName("왼쪽 위로 이동할 때 해당 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenBishopMoveLeftUpward() {
        Bishop whiteBishop = Bishop.createOfWhite();
        List<Position> path = whiteBishop.collectPath(Position.of(G, TWO), Position.of(C, SIX));
        assertThat(path).containsExactlyInAnyOrder(Position.of(F, THREE), Position.of(E, FOUR), Position.of(D, FIVE));
    }

    @DisplayName("점수를 요청하면 3점을 반환한다.")
    @Test
    void shouldReturnScoreOf3WhenRequestScore() {
        Bishop whiteBishop = Bishop.createOfWhite();
        assertThat(whiteBishop.getScore()).isEqualTo(new Score(3));
    }
}
=======
        boolean movable = whiteBishop.isMovable(Pawn.createOfBlack(), Position.of("c", "2"), Position.of("e", "4"));

        assertThat(movable).isTrue();
    }

    @DisplayName("Bishop이 오른쪽 위로 이동하면 해당 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenBishopMoveRightUpward() {
        Bishop whiteBishop = Bishop.createOfWhite();
        List<Position> path = whiteBishop.collectPath(Position.of("c", "2"), Position.of("g", "6"));

        assertThat(path).containsExactlyInAnyOrder(Position.of("d", "3"), Position.of("e", "4"), Position.of("f", "5"));
    }

    @DisplayName("Bishop이 오른쪽 아래로 이동하면 해당 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenBishopMoveRightDownward() {
        Bishop whiteBishop = Bishop.createOfWhite();
        List<Position> path = whiteBishop.collectPath(Position.of("c", "6"), Position.of("g", "2"));

        assertThat(path).containsExactlyInAnyOrder(Position.of("d", "5"), Position.of("e", "4"), Position.of("f", "3"));
    }

    @DisplayName("Bishop이 왼쪽 아래로 이동하면 해당 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenBishopMoveLeftDownward() {
        Bishop whiteBishop = Bishop.createOfWhite();
        List<Position> path = whiteBishop.collectPath(Position.of("g", "6"), Position.of("c", "2"));

        assertThat(path).containsExactlyInAnyOrder(Position.of("f", "5"), Position.of("e", "4"), Position.of("d", "3"));
    }

    @DisplayName("Bishop이 왼쪽 위로 이동하면 해당 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenBishopMoveLeftUpward() {
        Bishop whiteBishop = Bishop.createOfWhite();
        List<Position> path = whiteBishop.collectPath(Position.of("g", "2"), Position.of("c", "6"));

        assertThat(path).containsExactlyInAnyOrder(Position.of("f", "3"), Position.of("e", "4"), Position.of("d", "5"));
    }
}
>>>>>>> 3ad1dbf (refactor: 패키지 분리)
