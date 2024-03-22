package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @DisplayName("화이트 폰은 북쪽으로만 전진할 수 있다.")
    @Test
    void blackPawnCanMoveToNorth() {
        Pawn pawn = Pawn.ofWhite();
        Position currentPosition = Position.from(File.H, Rank.THREE);
        Map<Position, Piece> board = Map.of(currentPosition, pawn);

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(Set.of(Position.from(File.H, Rank.FOUR)));
    }

    @DisplayName("블랙 폰은 남쪽으로만 전진할 수 있다.")
    @Test
    void whitePawnCanMoveToSouth() {
        Pawn pawn = Pawn.ofBlack();
        Position currentPosition = Position.from(File.H, Rank.THREE);
        Map<Position, Piece> board = Map.of(currentPosition, pawn);

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(Set.of(Position.from(File.H, Rank.TWO)));
    }

    @DisplayName("화이트 폰은 초기 위치에서는 북쪽으로 1칸 또는 2칸 전진할 수 있다.")
    @Test
    void whenInitialPositionThenCanMoveForwardTwoStep() {
        Pawn pawn = Pawn.ofWhite();
        Position currentPosition = Position.from(File.H, Rank.TWO);
        Map<Position, Piece> board = Map.of(currentPosition, pawn);

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.from(File.H, Rank.THREE), Position.from(File.H, Rank.FOUR)));
    }

    @DisplayName("블랙 폰은 초기 위치에서는 남쪽으로 1칸 또는 2칸 전진할 수 있다.")
    @Test
    void whenInitialPositionThenCanMoveForwardTwoStepBlack() {
        Pawn pawn = Pawn.ofBlack();
        Position currentPosition = Position.from(File.H, Rank.SEVEN);
        Map<Position, Piece> board = Map.of(currentPosition, pawn);

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.from(File.H, Rank.SIX), Position.from(File.H, Rank.FIVE)));
    }

    @DisplayName("화이트 폰은 초기 위치에서 방해물이 있을 시 건너 뛸 수 없다")
    @Test
    void whenInitialPositionThenCantMoveForward() {
        Pawn pawn = Pawn.ofWhite();
        Position currentPosition = Position.from(File.H, Rank.TWO);
        Map<Position, Piece> board = Map.of(
                currentPosition, pawn,
                Position.from(File.H, Rank.THREE), Pawn.ofBlack(),
                Position.from(File.G, Rank.THREE), Pawn.ofBlack()
        );

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.from(File.G, Rank.THREE)));
    }

    @DisplayName("블랙 폰은 초기 위치에서 방해물이 있을 시 건너 뛸 수 없다")
    @Test
    void whenInitialPositionThenCantMoveForwardBlack() {
        Pawn pawn = Pawn.ofBlack();
        Position currentPosition = Position.from(File.H, Rank.SEVEN);
        Map<Position, Piece> board = Map.of(
                currentPosition, pawn,
                Position.from(File.H, Rank.SIX), Pawn.ofWhite(),
                Position.from(File.G, Rank.SIX), Pawn.ofWhite()
        );

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(
                Set.of(Position.from(File.G, Rank.SIX)));
    }


    @DisplayName("화이트 폰은 북쪽 방향의 대각선의 적 위치로 갈 수 있다.")
    @Test
    void canMoveDiagonalOfTheForwardDirectionWhenEnemyExists() {
        Pawn pawn = Pawn.ofWhite();
        Position currentPosition = Position.from(File.H, Rank.TWO);
        Map<Position, Piece> board = Map.of(
                currentPosition, pawn,
                Position.from(File.G, Rank.THREE), Pawn.ofBlack()
        );

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(Set.of(Position.from(File.H, Rank.THREE),
                Position.from(File.H, Rank.FOUR), Position.from(File.G, Rank.THREE))
        );
    }

    @DisplayName("블랙 폰은 남쪽 방향의 대각선의 적 위치로 갈 수 있다.")
    @Test
    void canMoveDiagonalOfTheForwardDirectionWhenEnemyExistsBlack() {
        Pawn pawn = Pawn.ofBlack();
        Position currentPosition = Position.from(File.H, Rank.SEVEN);
        Map<Position, Piece> board = Map.of(
                currentPosition, pawn,
                Position.from(File.G, Rank.SIX), Pawn.ofWhite()
        );

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(Set.of(Position.from(File.H, Rank.SIX),
                Position.from(File.H, Rank.FIVE), Position.from(File.G, Rank.SIX))
        );
    }
}
