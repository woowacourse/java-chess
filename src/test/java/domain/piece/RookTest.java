package domain.piece;

import static domain.game.File.B;
import static domain.game.File.C;
import static domain.game.File.D;
import static domain.game.File.E;
import static domain.game.File.F;
import static domain.game.Rank.FOUR;
import static domain.game.Rank.ONE;
import static domain.game.Rank.THREE;
import static domain.game.Rank.TWO;
import static org.assertj.core.api.Assertions.assertThat;

import domain.game.Position;
import domain.game.Score;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Rook은")
class RookTest {

    @DisplayName("Source position이 Target position까지 수직 위 방향이면 true를 반환한다. ")
    @Test
    void shouldReturnTrueWhenRookDirectionIsPerpendicular() {
        Rook whiteRook = Rook.createOfWhite();
        boolean movable = whiteRook.isMovable(new EmptyPiece(), Position.of(C, TWO), Position.of(C, FOUR));
        assertThat(movable).isTrue();
    }

    @DisplayName("Source position이 Target position까지 수직 방향이 아니면 false를 반환한다. ")
    @Test
    void shouldReturnFalseWhenRookDirectionIsNotPerpendicular() {
        Rook whiteRook = Rook.createOfWhite();
        boolean movable = whiteRook.isMovable(new EmptyPiece(), Position.of(C, TWO), Position.of(D, FOUR));
        assertThat(movable).isFalse();
    }

    @DisplayName("Source position이 Target position까지 수직 방향이고, Target piece가 같은 진영이면 false를 반환한다. ")
    @Test
    void shouldReturnFalseWhenRookDirectionIsPerpendicularButTargetPieceIsSameSide() {
        Rook whiteRook = Rook.createOfWhite();
        boolean movable = whiteRook.isMovable(Bishop.createOfWhite(), Position.of(C, TWO), Position.of(C, FOUR));
        assertThat(movable).isFalse();
    }

    @DisplayName("Source position이 Target position까지 수직 방향이고, Target piece가 다른 진영이면 true를 반환한다. ")
    @Test
    void shouldReturnTrueWhenRookDirectionIsPerpendicularAndTargetPieceIsOpponentSide() {
        Rook blackRook = Rook.createOfBlack();
        boolean movable = blackRook.isMovable(Bishop.createOfWhite(), Position.of(C, TWO), Position.of(C, FOUR));
        assertThat(movable).isTrue();
    }

    @DisplayName("수직 위로 이동하면 해당 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenRookMoveUpward() {
        Rook whiteRook = Rook.createOfWhite();
        List<Position> path = whiteRook.collectPath(Position.of(C, ONE), Position.of(C, FOUR));
        assertThat(path).containsExactlyInAnyOrder(Position.of(C, TWO), Position.of(C, THREE));
    }

    @DisplayName("수직 아래로 이동하면 해당 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenRookMoveDownward() {
        Rook whiteRook = Rook.createOfWhite();
        List<Position> path = whiteRook.collectPath(Position.of(C, FOUR), Position.of(C, ONE));
        assertThat(path).containsExactlyInAnyOrder(Position.of(C, THREE), Position.of(C, TWO));
    }

    @DisplayName("왼쪽으로 이동하면 해당 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenRookMoveLeft() {
        Rook whiteRook = Rook.createOfWhite();
        List<Position> path = whiteRook.collectPath(Position.of(E, THREE), Position.of(B, THREE));
        assertThat(path).containsExactlyInAnyOrder(Position.of(D, THREE), Position.of(C, THREE));
    }

    @DisplayName("오른쪽으로 이동하면 해당 경로를 반환한다.")
    @Test
    void shouldReturnPathWhenRookMoveRight() {
        Rook whiteRook = Rook.createOfWhite();
        List<Position> path = whiteRook.collectPath(Position.of(C, THREE), Position.of(F, THREE));
        assertThat(path).containsExactlyInAnyOrder(Position.of(D, THREE), Position.of(E, THREE));
    }

    @DisplayName("점수를 요청하면 5점을 반환한다.")
    @Test
    void shouldReturnScoreOf5WhenRequestScore() {
        Rook whiteRook = Rook.createOfWhite();
        assertThat(whiteRook.getScore()).isEqualTo(new Score(5));
    }
}
