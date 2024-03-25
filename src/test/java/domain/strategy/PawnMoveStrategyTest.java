package domain.strategy;

import domain.board.Board;
import domain.board.BoardInitiator;
import domain.board.Position;
import domain.piece.Pawn;
import domain.piece.Piece;
import domain.piece.info.Color;
import domain.piece.info.File;
import domain.piece.info.Rank;
import domain.piece.info.Type;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnMoveStrategyTest {
    @Test
    @DisplayName("폰이 적 기물 앞에 있을 때 이동 가능한 경우의 수는 두 가지다")
    void pawnMovablePosition() {
        final Position position = new Position(File.of(4), Rank.of(5));
        final Piece pawn = new Pawn(Color.WHITE, Type.PAWN);
        final Board board = new Board(BoardInitiator.init());
        final MoveStrategy strategy = pawn.strategy();

        final List<Position> positions = strategy.movablePositions(position, pawn.movableDirections(), board.squares());

        Assertions.assertThat(positions).hasSize(2);
    }

    @Test
    @DisplayName("폰의 첫 위치에서 폰 앞에 적 기물이 없고 대각선 방향에도 없을 때 이동 가능한 경우의 수는 두 가지다")
    void pawnMovablePosition2() {
        final Position position = new Position(File.of(0), Rank.of(1));
        final Piece pawn = new Pawn(Color.WHITE, Type.PAWN);
        final Board board = new Board(BoardInitiator.init());
        final MoveStrategy strategy = pawn.strategy();

        final List<Position> positions = strategy.movablePositions(position, pawn.movableDirections(), board.squares());

        Assertions.assertThat(positions).hasSize(2);
    }

    @Test
    @DisplayName("폰의 첫 위치가 아니고 폰 앞에 적 기물이 없고 대각선 방향에도 없을 때 이동 가능한 경우의 수는 한 가지다")
    void pawnMovablePosition3() {
        final Position position = new Position(File.of(0), Rank.of(2));
        final Piece pawn = new Pawn(Color.BLACK, Type.PAWN);
        final Board board = new Board(BoardInitiator.init());
        pawn.movableDirections();
        final MoveStrategy strategy = pawn.strategy();

        final List<Position> positions = strategy.movablePositions(position, pawn.movableDirections(), board.squares());

        Assertions.assertThat(positions).hasSize(1);
    }
}
