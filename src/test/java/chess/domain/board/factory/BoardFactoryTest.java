package chess.domain.board.factory;

import chess.domain.board.factory.BoardFactory;
import chess.domain.board.position.Position;
import chess.domain.piece.slider.Bishop;
import chess.domain.piece.jumper.King;
import chess.domain.piece.jumper.Knight;
import chess.domain.piece.pawn.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.slider.Queen;
import chess.domain.piece.slider.Rook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BoardFactoryTest {

    @Test
    @DisplayName("createInitialBoard() : 체스피스들의 위치를 초기화하는 Board를 생성할 수 있다.")
    void test_createInitialBoard() throws Exception {
        //given
        final BoardFactory boardFactory = new BoardFactory();

        //when
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

        //then
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
