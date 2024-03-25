package domain.strategy;

import domain.board.Board;
import domain.board.BoardInitiator;
import domain.board.Position;
import domain.piece.Bishop;
import domain.piece.Piece;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.info.Color;
import domain.piece.info.File;
import domain.piece.info.Rank;
import domain.piece.info.Type;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SelectiveMoveStrategyTest {
    @Test
    @DisplayName("가운데에서 룩을 움직였을 때 피스의 유무와 무관하게 이동 가능한 경우의 수는 14 가지다")
    void rookMovablePositions() {
        final Position position = new Position(File.of(4), Rank.of(4));
        final Piece rook = new Rook(Color.WHITE, Type.ROOK);
        final Board board = new Board(BoardInitiator.init());
        final MoveStrategy strategy = rook.strategy();

        final List<Position> positions = strategy.movablePositions(position, rook.movableDirections(), board.squares());

        Assertions.assertThat(positions).hasSize(14);
    }

    @Test
    @DisplayName("가운데에서 비숍을 움직였을 때 피스의 유무와 무관하게 이동 가능한 경우의 수는 14가지다")
    void bishopMovablePositions() {
        final Position position = new Position(File.of(4), Rank.of(4));
        final Piece bishop = new Bishop(Color.WHITE, Type.BISHOP);
        final Board board = new Board(BoardInitiator.init());
        final MoveStrategy strategy = bishop.strategy();

        final List<Position> positions = strategy.movablePositions(position, bishop.movableDirections(),
                board.squares());

        Assertions.assertThat(positions).hasSize(13);
    }

    @Test
    @DisplayName("가운데에서 퀸을 움직였을 때 피스의 유무와 무관하게 이동 가능한 경우의 수는 27가지다")
    void queenMovablePositions() {
        final Position position = new Position(File.of(4), Rank.of(4));
        final Piece queen = new Queen(Color.WHITE, Type.QUEEN);
        final Board board = new Board(BoardInitiator.init());
        final MoveStrategy strategy = queen.strategy();

        final List<Position> positions = strategy.movablePositions(position, queen.movableDirections(),
                board.squares());

        Assertions.assertThat(positions).hasSize(27);
    }

}
