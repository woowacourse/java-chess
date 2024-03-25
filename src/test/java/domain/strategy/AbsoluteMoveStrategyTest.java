package domain.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import domain.board.Board;
import domain.board.BoardInitiator;
import domain.board.Position;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Piece;
import domain.piece.info.Color;
import domain.piece.info.File;
import domain.piece.info.Rank;
import domain.piece.info.Type;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AbsoluteMoveStrategyTest {

    @Test
    @DisplayName("가운데에서 나이트를 움직였을 때 피스의 유무와 무관하게 이동 가능한 경우의 수는 8 가지다")
    void knightMovablePositions() {
        final Position position = new Position(File.of(4), Rank.of(4));
        final Piece knight = new Knight(Color.WHITE, Type.KNIGHT);
        final Board board = new Board(BoardInitiator.init());
        final MoveStrategy strategy = knight.strategy();

        final List<Position> positions = strategy.movablePositions(position, knight.movableDirections(),
                board.squares());

        assertThat(positions).hasSize(8);
    }

    @Test
    @DisplayName("가운데에서 킹을 움직였을 때 킹의 유무와 무관하게 이동 가능한 경우의 수는 8 가지다")
    void kingMovablePositions() {
        final Position position = new Position(File.of(4), Rank.of(4));
        final Piece king = new King(Color.WHITE, Type.KING);
        final Board board = new Board(BoardInitiator.init());
        final MoveStrategy strategy = king.strategy();

        final List<Position> positions = strategy.movablePositions(position, king.movableDirections(), board.squares());

        assertThat(positions).hasSize(8);
    }
}
