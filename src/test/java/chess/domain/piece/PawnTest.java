package chess.domain.piece;

import static org.assertj.core.api.Assertions.assertThat;

import chess.domain.Board;
import chess.domain.File;
import chess.domain.Position;
import chess.domain.Rank;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {
    @DisplayName("화이트 폰은 북쪽으로만 전진할 수 있다.")
    @Test
    public void blackPawnCanMoveToNorth() {
        Pawn pawn = Pawn.ofWhite();
        Position currentPosition = new Position(File.H, Rank.THREE);
        Map<Position, Piece> board = Map.of(currentPosition, pawn);

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(Set.of(new Position(File.H, Rank.FOUR)));
    }

    @DisplayName("화이트 폰은 초기 위치에서는 북쪽으로 1칸 또는 2칸 전진할 수 있다.")
    @Test
    public void whenInitialPositionThenCanMoveForwardTwoStep() {
        Pawn pawn = Pawn.ofWhite();
        Position currentPosition = new Position(File.H, Rank.TWO);
        Map<Position, Piece> board = Map.of(currentPosition, pawn);

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(Set.of(new Position(File.H, Rank.THREE), new Position(File.H, Rank.FOUR)));
    }

    @DisplayName("화이트 폰은 북쪽 방향의 대각선의 적 위치로 갈 수 있다.")
    @Test
    public void canMoveDiagonalOfTheForwardDirectionWhenEnemyExists() {
        Pawn pawn = Pawn.ofWhite();
        Position currentPosition = new Position(File.H, Rank.TWO);
        Map<Position, Piece> board = Map.of(
                currentPosition, pawn,
                new Position(File.G, Rank.THREE), Pawn.ofBlack()
        );

        Set<Position> movablePositions = pawn.calculateMovablePositions(currentPosition, new Board(board));

        assertThat(movablePositions).isEqualTo(Set.of(new Position(File.H, Rank.THREE),
                new Position(File.H, Rank.FOUR), new Position(File.G, Rank.THREE))
        );
    }
}
