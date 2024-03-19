package domain.board;

import static org.assertj.core.api.Assertions.assertThat;

import domain.piece.Type;
import java.util.List;
import java.util.Map;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.Test;

class ChessBoardTest {
    @Test
    void 체스판_모서리에_룩을_놓는다() {
        ChessBoard board = ChessBoard.createEmptyBoard();
        Map<Position, Type> pieces = Map.of(
                new Position(0, 0), Type.ROOK,
                new Position(0, 7), Type.ROOK,
                new Position(7, 0), Type.ROOK,
                new Position(7, 7), Type.ROOK
        );

        board.fillPieces(pieces);

        assertThat(board).extracting("board")
                .asInstanceOf(InstanceOfAssertFactories.list(List.class))
                .contains(
                        List.of(Type.ROOK, Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY,
                                Type.ROOK),
                        List.of(Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY,
                                Type.EMPTY),
                        List.of(Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY,
                                Type.EMPTY),
                        List.of(Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY,
                                Type.EMPTY),
                        List.of(Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY,
                                Type.EMPTY),
                        List.of(Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY,
                                Type.EMPTY),
                        List.of(Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY,
                                Type.EMPTY),
                        List.of(Type.ROOK, Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY, Type.EMPTY,
                                Type.ROOK)
                );
    }
}
