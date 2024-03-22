package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.position.Position;
import chess.fixture.PositionFixture;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {
    @DisplayName("화이트 폰은 북쪽으로만 전진할 수 있다.")
    @Test
    void blackPawnCanMoveToNorth() {
        Pawn pawn = Pawn.WHITE;
        Position currentPosition = PositionFixture.H3;
        Map<Position, Piece> board = Map.of(currentPosition, pawn);

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(Set.of(PositionFixture.H4));
    }

    @DisplayName("블랙 폰은 남쪽으로만 전진할 수 있다.")
    @Test
    void whitePawnCanMoveToSouth() {
        Pawn pawn = Pawn.BLACK;
        Position currentPosition = PositionFixture.H3;
        Map<Position, Piece> board = Map.of(currentPosition, pawn);

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(Set.of(PositionFixture.H2));
    }

    @DisplayName("화이트 폰은 초기 위치에서는 북쪽으로 1칸 또는 2칸 전진할 수 있다.")
    @Test
    void whenInitialPositionThenCanMoveForwardTwoStep() {
        Pawn pawn = Pawn.WHITE;
        Position currentPosition = PositionFixture.H2;
        Map<Position, Piece> board = Map.of(currentPosition, pawn);

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(PositionFixture.H3, PositionFixture.H4));
    }

    @DisplayName("블랙 폰은 초기 위치에서는 남쪽으로 1칸 또는 2칸 전진할 수 있다.")
    @Test
    void whenInitialPositionThenCanMoveForwardTwoStepBlack() {
        Pawn pawn = Pawn.BLACK;
        Position currentPosition = PositionFixture.H7;
        Map<Position, Piece> board = Map.of(currentPosition, pawn);

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(PositionFixture.H6, PositionFixture.H5));
    }

    @DisplayName("화이트 폰은 초기 위치에서 방해물이 있을 시 건너 뛸 수 없다")
    @Test
    void whenInitialPositionThenCantMoveForward() {
        Pawn pawn = Pawn.WHITE;
        Position currentPosition = PositionFixture.H2;
        Map<Position, Piece> board = Map.of(
                currentPosition, pawn,
                PositionFixture.H3, Pawn.BLACK,
                PositionFixture.G3, Pawn.BLACK
        );

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(PositionFixture.G3));
    }

    @DisplayName("블랙 폰은 초기 위치에서 방해물이 있을 시 건너 뛸 수 없다")
    @Test
    void whenInitialPositionThenCantMoveForwardBlack() {
        Pawn pawn = Pawn.BLACK;
        Position currentPosition = PositionFixture.H7;
        Map<Position, Piece> board = Map.of(
                currentPosition, pawn,
                PositionFixture.H6, Pawn.WHITE,
                PositionFixture.G6, Pawn.WHITE
        );

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(Set.of(PositionFixture.G6));
    }


    @DisplayName("화이트 폰은 북쪽 방향의 대각선의 적 위치로 갈 수 있다.")
    @Test
    void canMoveDiagonalOfTheForwardDirectionWhenEnemyExists() {
        Pawn pawn = Pawn.WHITE;
        Position currentPosition = PositionFixture.H2;
        Map<Position, Piece> board = Map.of(
                currentPosition, pawn,
                PositionFixture.G3, Pawn.BLACK
        );

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(Set.of(PositionFixture.H3,
                PositionFixture.H4, PositionFixture.G3)
        );
    }

    @DisplayName("블랙 폰은 남쪽 방향의 대각선의 적 위치로 갈 수 있다.")
    @Test
    void canMoveDiagonalOfTheForwardDirectionWhenEnemyExistsBlack() {
        Pawn pawn = Pawn.BLACK;
        Position currentPosition = PositionFixture.H7;
        Map<Position, Piece> board = Map.of(
                currentPosition, pawn,
                PositionFixture.G6, Pawn.WHITE
        );

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(Set.of(PositionFixture.H6,
                PositionFixture.H5, PositionFixture.G6)
        );
    }
}
