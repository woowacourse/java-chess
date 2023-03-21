package domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BishopTest {

    @DisplayName("Source position이 Target position까지 대각선 방향이면 true를 반환한다. ")
    @Test
    void shouldReturnTrueWhenDirectionIsDiagonal() {
        Bishop whiteBishop = Bishop.createOfWhite();
        boolean movable = whiteBishop.isMovable(new EmptyPiece(), Position.of("c", "2"), Position.of("e", "4"));
        assertThat(movable).isTrue();
    }

    @DisplayName("Source position이 Target position까지 대각선 방향이 아니면 false를 반환한다. ")
    @Test
    void shouldReturnFalseWhenBishopDirectionIsNotDiagonal() {
        Bishop whiteBishop = Bishop.createOfBlack();
        boolean movable = whiteBishop.isMovable(new EmptyPiece(), Position.of("c", "2"), Position.of("e", "3"));
        assertThat(movable).isFalse();
    }

    @DisplayName("Source position이 Target position까지 대각선 방향이지만, Target position에 같은 진영 말이 있으면 false를 반환한다.")
    @Test
    void shouldReturnFalseWhenBishopDirectionIsDiagonalButTargetPieceIsSameSide() {
        Bishop blackBishop = Bishop.createOfBlack();
        boolean movable = blackBishop.isMovable(Pawn.createOfBlack(), Position.of("c", "2"), Position.of("e", "4"));
        assertThat(movable).isFalse();
    }

    @DisplayName("source position이 target position까지 대각선 방향이고, target position에 상대 진영 말이 있으면 true를 반환한다.")
    @Test
    void shouldReturnTrueWhenBishopDirectionIsDiagonalAndTargetPieceIsOpponentSide() {
        Bishop whiteBishop = Bishop.createOfWhite();
        boolean movable = whiteBishop.isMovable(Pawn.createOfBlack(), Position.of("c", "2"), Position.of("e", "4"));
        assertThat(movable).isTrue();
    }

    @DisplayName("Bishop이 오른쪽 위로 이동할 때 해당 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenBishopMoveRightUpward() {
        Bishop whiteBishop = Bishop.createOfWhite();
        List<Position> path = whiteBishop.collectPath(Position.of("c", "2"), Position.of("g", "6"));
        assertThat(path).containsExactlyInAnyOrder(Position.of("d", "3"), Position.of("e", "4"), Position.of("f", "5"));
    }

    @DisplayName("Bishop이 오른쪽 아래로 이동할 때 해당 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenBishopMoveRightDownward() {
        Bishop whiteBishop = Bishop.createOfWhite();
        List<Position> path = whiteBishop.collectPath(Position.of("c", "6"), Position.of("g", "2"));
        assertThat(path).containsExactlyInAnyOrder(Position.of("d", "5"), Position.of("e", "4"), Position.of("f", "3"));
    }

    @DisplayName("Bishop이 왼쪽 아래로 이동할 때 해당 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenBishopMoveLeftDownward() {
        Bishop whiteBishop = Bishop.createOfWhite();
        List<Position> path = whiteBishop.collectPath(Position.of("g", "6"), Position.of("c", "2"));
        assertThat(path).containsExactlyInAnyOrder(Position.of("f", "5"), Position.of("e", "4"), Position.of("d", "3"));
    }

    @DisplayName("Bishop이 왼쪽 위로 이동할 때 해당 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenBishopMoveLeftUpward() {
        Bishop whiteBishop = Bishop.createOfWhite();
        List<Position> path = whiteBishop.collectPath(Position.of("g", "2"), Position.of("c", "6"));
        assertThat(path).containsExactlyInAnyOrder(Position.of("f", "3"), Position.of("e", "4"), Position.of("d", "5"));
    }
}
