package chess;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import chess.board.BoardFactory;
import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import chess.position.Position;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardFactoryTest {

    @Test
    @DisplayName("체스말들의 위치를 초기화하는 Board를 생성할 수 있다.")
    void test_createInitialBoard() {
        final BoardFactory boardFactory = new BoardFactory();

        final Map<Position, Piece> board = boardFactory.createInitialBoard();
        final Map<Class<? extends Piece>, Integer> result =
                board.values()
                        .stream()
                        .collect(
                                Collectors.groupingBy(Piece::getClass,
                                        Collectors.summingInt(
                                                value -> 1)
                                )
                        );

        assertAll(
                () -> assertEquals(board.size(), 32),
                () -> assertEquals(result.get(King.class), 2),
                () -> assertEquals(result.get(Queen.class), 2),
                () -> assertEquals(result.get(Bishop.class), 4),
                () -> assertEquals(result.get(Knight.class), 4),
                () -> assertEquals(result.get(Rook.class), 4),
                () -> assertEquals(result.get(Pawn.class), 16)
        );
    }
}
