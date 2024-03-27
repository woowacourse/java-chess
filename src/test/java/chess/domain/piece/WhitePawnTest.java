package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Movement;
import chess.domain.Position;
import chess.domain.piece.abstractPiece.Piece;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WhitePawnTest {
    @DisplayName("흰색 폰은 1칸 움직일 수 있다.")
    @Test
    void whitePawnIsMovable() {
        Piece whitePawn = new WhitePawn().move();

        boolean movable = whitePawn.isMovable(new Movement(
                Position.of(3, 1), Position.of(4, 1)));

        assertThat(movable).isTrue();
    }

    @DisplayName("흰색 폰은 1칸 초과하여 움직일 수 없다.")
    @Test
    void whitePawnMoveIsNotMovableOverTwo() {
        Piece whitePawn = new WhitePawn().move();

        boolean movable = whitePawn.isMovable(new Movement(
                Position.of(3, 1), Position.of(5, 1)));

        assertThat(movable).isFalse();
    }

    @DisplayName("흰색 폰은 시작 지점에 있는 경우, 2칸 움직일 수 있다.")
    @Test
    void whitePawnIsMovableTwo() {
        Piece whitePawn = new WhitePawn();

        boolean movable = whitePawn.isMovable(new Movement(
                Position.of(2, 1), Position.of(4, 1)));

        assertThat(movable).isTrue();
    }

    @DisplayName("흰색 폰은 시작 지점에 있는 경우, 2칸 초과하여 움직일 수 없다.")
    @Test
    void startWhitePawnIsNotMovableOverTwo() {
        Piece whitePawn = new WhitePawn();

        boolean movable = whitePawn.isMovable(new Movement(
                Position.of(2, 1), Position.of(5, 1)));

        assertThat(movable).isFalse();
    }

    @DisplayName("폰은 옆으로 이동할 수 없다.")
    @Test
    void whitePawnIsNotMovableColumn() {
        Piece whitePawn = new WhitePawn();

        boolean movable = whitePawn.isMovable(new Movement(
                Position.of(2, 1), Position.of(2, 2)));

        assertThat(movable).isFalse();
    }

    @DisplayName("두 위치 사이의 폰이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPosition() {
        Piece whitePawn = new WhitePawn();

        Set<Position> betweenPositions = whitePawn.findBetweenPositions(new Movement(
                Position.of(2, 3), Position.of(4, 3)));
        Set<Position> expectedBetweenPositions = Set.of(Position.of(3, 3));

        assertThat(betweenPositions).isEqualTo(expectedBetweenPositions);
    }

    @DisplayName("두 위치 사이의 폰이 갈 수 있는 위치들을 반환한다.")
    @Test
    void betweenPositionHasMoved() {
        Piece whitePawn = new WhitePawn();

        Set<Position> betweenPositions = whitePawn.findBetweenPositions(new Movement(
                Position.of(3, 3), Position.of(4, 3)));

        assertThat(betweenPositions).isEmpty();
    }

    @DisplayName("공격할 때, 대각으로 움직일 수 있다.")
    @Test
    void movableDiagonalWhenAttack() {
        Piece whitePawn = new WhitePawn();

        boolean movable = whitePawn.isMovable(new Movement(
                Position.of(2, 2), Position.of(3, 3)), true);

        assertThat(movable).isTrue();
    }

    @DisplayName("공격할 때, 직선으로 움직일 수 없다.")
    @Test
    void cannotMoveStraightWhenAttack() {
        Piece whitePawn = new WhitePawn();

        boolean movable = whitePawn.isMovable(new Movement(
                Position.of(2, 2), Position.of(3, 2)), true);

        assertThat(movable).isFalse();
    }
}
