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
                Map.entry(new Position("a", "8"), new Rook(Side.BLACK)),
                Map.entry(new Position("h", "8"), new Rook(Side.BLACK)),
                Map.entry(new Position("a", "1"), new Rook(Side.WHITE)),
                Map.entry(new Position("h", "1"), new Rook(Side.WHITE))
        );
    }

    @DisplayName("검은색 나이트는 b8, g8에 위치한다. 흰색 나이트는 b1, g1에 위치한다.")
    @Test
    void knightPositionTest() {
        ChessBoard chessBoard = new ChessBoard(new LinkedHashMap<>());

        chessBoard.init();

        assertThat(chessBoard.getBoard()).contains(
                Map.entry(new Position("b", "8"), new Knight(Side.BLACK)),
                Map.entry(new Position("g", "8"), new Knight(Side.BLACK)),
                Map.entry(new Position("b", "1"), new Knight(Side.WHITE)),
                Map.entry(new Position("g", "1"), new Knight(Side.WHITE))
        );
    }

    @DisplayName("검은색 비숍은 c8, f8에 위치한다. 흰색 나이트는 c1, f1에 위치한다.")
    @Test
    void bishopPositionTest() {
        ChessBoard chessBoard = new ChessBoard(new LinkedHashMap<>());

        chessBoard.init();

        assertThat(chessBoard.getBoard()).contains(
                Map.entry(new Position("c", "8"), new Bishop(Side.BLACK)),
                Map.entry(new Position("f", "8"), new Bishop(Side.BLACK)),
                Map.entry(new Position("c", "1"), new Bishop(Side.WHITE)),
                Map.entry(new Position("f", "1"), new Bishop(Side.WHITE))
        );
    }

    @DisplayName("검은색 퀸은 d8에 위치한다. 흰색 퀸은 d1에 위치한다.")
    @Test
    void queenPositionTest() {
        ChessBoard chessBoard = new ChessBoard(new LinkedHashMap<>());

        chessBoard.init();

        assertThat(chessBoard.getBoard()).contains(
                Map.entry(new Position("d", "8"), new Queen(Side.BLACK)),
                Map.entry(new Position("d", "1"), new Queen(Side.WHITE))
        );
    }

    @DisplayName("검은색 킹은 e8에 위치한다. 흰색 킹은 e1에 위치한다.")
    @Test
    void kingPositionTest() {
        ChessBoard chessBoard = new ChessBoard(new LinkedHashMap<>());

        chessBoard.init();

        assertThat(chessBoard.getBoard()).contains(
                Map.entry(new Position("e", "8"), new King(Side.BLACK)),
                Map.entry(new Position("e", "1"), new King(Side.WHITE))
        );
    }
}
