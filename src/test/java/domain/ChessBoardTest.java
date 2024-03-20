package domain;

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
                Map.entry(new Position(File.A, Rank.EIGHT), new Rook(Side.BLACK)),
                Map.entry(new Position(File.H, Rank.EIGHT), new Rook(Side.BLACK)),
                Map.entry(new Position(File.A, Rank.ONE), new Rook(Side.WHITE)),
                Map.entry(new Position(File.H, Rank.ONE), new Rook(Side.WHITE))
        );
    }

    @DisplayName("검은색 나이트는 b8, g8에 위치한다. 흰색 나이트는 b1, g1에 위치한다.")
    @Test
    void knightPositionTest() {
        ChessBoard chessBoard = new ChessBoard(new LinkedHashMap<>());

        chessBoard.init();

        assertThat(chessBoard.getBoard()).contains(
                Map.entry(new Position(File.B, Rank.EIGHT), new Knight(Side.BLACK)),
                Map.entry(new Position(File.G, Rank.EIGHT), new Knight(Side.BLACK)),
                Map.entry(new Position(File.B, Rank.ONE), new Knight(Side.WHITE)),
                Map.entry(new Position(File.G, Rank.ONE), new Knight(Side.WHITE))
        );
    }

    @DisplayName("검은색 비숍은 c8, f8에 위치한다. 흰색 나이트는 c1, f1에 위치한다.")
    @Test
    void bishopPositionTest() {
        ChessBoard chessBoard = new ChessBoard(new LinkedHashMap<>());

        chessBoard.init();

        assertThat(chessBoard.getBoard()).contains(
                Map.entry(new Position(File.C, Rank.EIGHT), new Bishop(Side.BLACK)),
                Map.entry(new Position(File.F, Rank.EIGHT), new Bishop(Side.BLACK)),
                Map.entry(new Position(File.C, Rank.ONE), new Bishop(Side.WHITE)),
                Map.entry(new Position(File.F, Rank.ONE), new Bishop(Side.WHITE))
        );
    }

    @DisplayName("검은색 퀸은 d8에 위치한다. 흰색 퀸은 d1에 위치한다.")
    @Test
    void queenPositionTest() {
        ChessBoard chessBoard = new ChessBoard(new LinkedHashMap<>());

        chessBoard.init();

        assertThat(chessBoard.getBoard()).contains(
                Map.entry(new Position(File.D, Rank.EIGHT), new Queen(Side.BLACK)),
                Map.entry(new Position(File.D, Rank.ONE), new Queen(Side.WHITE))
        );
    }

    @DisplayName("검은색 킹은 e8에 위치한다. 흰색 킹은 e1에 위치한다.")
    @Test
    void kingPositionTest() {
        ChessBoard chessBoard = new ChessBoard(new LinkedHashMap<>());

        chessBoard.init();

        assertThat(chessBoard.getBoard()).contains(
                Map.entry(new Position(File.E, Rank.EIGHT), new King(Side.BLACK)),
                Map.entry(new Position(File.E, Rank.ONE), new King(Side.WHITE))
        );
    }

    @DisplayName("검은색 폰은 a7~h7에 위치한다. 흰색 폰은 a2~h2에 위치한다.")
    @Test
    void pawnPositionTest() {
        ChessBoard chessBoard = new ChessBoard(new LinkedHashMap<>());

        chessBoard.init();

        assertThat(chessBoard.getBoard()).contains(
                Map.entry(new Position(File.A, Rank.SEVEN), new Pawn(Side.BLACK)),
                Map.entry(new Position(File.B, Rank.SEVEN), new Pawn(Side.BLACK)),
                Map.entry(new Position(File.C, Rank.SEVEN), new Pawn(Side.BLACK)),
                Map.entry(new Position(File.D, Rank.SEVEN), new Pawn(Side.BLACK)),
                Map.entry(new Position(File.E, Rank.SEVEN), new Pawn(Side.BLACK)),
                Map.entry(new Position(File.F, Rank.SEVEN), new Pawn(Side.BLACK)),
                Map.entry(new Position(File.G, Rank.SEVEN), new Pawn(Side.BLACK)),
                Map.entry(new Position(File.H, Rank.SEVEN), new Pawn(Side.BLACK)),

                Map.entry(new Position(File.A, Rank.TWO), new Pawn(Side.WHITE)),
                Map.entry(new Position(File.B, Rank.TWO), new Pawn(Side.WHITE)),
                Map.entry(new Position(File.C, Rank.TWO), new Pawn(Side.WHITE)),
                Map.entry(new Position(File.D, Rank.TWO), new Pawn(Side.WHITE)),
                Map.entry(new Position(File.E, Rank.TWO), new Pawn(Side.WHITE)),
                Map.entry(new Position(File.F, Rank.TWO), new Pawn(Side.WHITE)),
                Map.entry(new Position(File.G, Rank.TWO), new Pawn(Side.WHITE)),
                Map.entry(new Position(File.H, Rank.TWO), new Pawn(Side.WHITE))
        );
    }
}
