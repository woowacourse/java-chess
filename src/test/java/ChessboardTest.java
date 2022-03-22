import chess.Chessboard;
import chess.piece.Color;
import chess.piece.Piece;
import chess.piece.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessboardTest {

    List<List<Piece>> board;

    @BeforeEach
    void setUp() {
        board = Chessboard.initializedChessboard().getBoard();
    }

    @Test
    @DisplayName("체스판 row 사이즈 확인")
    void checkSizeOfChessboard() {
        assertThat(board.size()).isEqualTo(8);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7})
    @DisplayName("체스판 column 사이즈 확인")
    void checkSizeOfChessboard(int col) {

        assertThat(board.get(col).size()).isEqualTo(8);
    }

    @ParameterizedTest
    @MethodSource("pieces")
    @DisplayName("초기 기물 위치 확인")
    void checkPiece(int x, int y, Type type, Color color) {
        Piece piece = board.get(x).get(y);

        assertThat(piece.getType()).isEqualTo(type);
        assertThat(piece.getColor()).isEqualTo(color);
    }

    private static Stream<Arguments> pieces() {
        return Stream.of(
                Arguments.of(0, 0, Type.ROOK, Color.BLACK),
                Arguments.of(0, 1, Type.KNIGHT, Color.BLACK),
                Arguments.of(0, 2, Type.BISHOP, Color.BLACK),
                Arguments.of(0, 3, Type.QUEEN, Color.BLACK),
                Arguments.of(1, 4, Type.PAWN, Color.BLACK),

                Arguments.of(7, 4, Type.KING, Color.WHITE),
                Arguments.of(7, 5, Type.BISHOP, Color.WHITE),
                Arguments.of(7, 6, Type.KNIGHT, Color.WHITE),
                Arguments.of(7, 7, Type.ROOK, Color.WHITE),
                Arguments.of(6, 5, Type.PAWN, Color.WHITE)

        );
    }

}
