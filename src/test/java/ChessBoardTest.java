import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ChessBoardTest {

    @DisplayName("검은색 룩은 a8, h8에 위치한다. 흰색 룩은 a1, h1에 위치한다.")
    @Test
    void rookPositionTest() {
        ChessBoard chessBoard = new ChessBoard(new LinkedHashMap<>());

        chessBoard.init();

        assertThat(chessBoard.getBoard()).contains(
                Map.entry(new Position("a", "8"), Piece.BLACK_ROOK),
                Map.entry(new Position("h", "8"), Piece.BLACK_ROOK),
                Map.entry(new Position("a", "1"), Piece.WHITE_ROOK),
                Map.entry(new Position("h", "1"), Piece.WHITE_ROOK));
    }
}
