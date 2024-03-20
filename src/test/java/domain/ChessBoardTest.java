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
                Map.entry(new Position(Rank.A, File.EIGHTH), new Rook(Side.BLACK)),
                Map.entry(new Position(Rank.H, File.EIGHTH), new Rook(Side.BLACK)),
                Map.entry(new Position(Rank.A, File.FIRST), new Rook(Side.WHITE)),
                Map.entry(new Position(Rank.H, File.FIRST), new Rook(Side.WHITE))
        );
    }

    @DisplayName("검은색 나이트는 b8, g8에 위치한다. 흰색 나이트는 b1, g1에 위치한다.")
    @Test
    void knightPositionTest() {
        ChessBoard chessBoard = new ChessBoard(new LinkedHashMap<>());

        chessBoard.init();

        assertThat(chessBoard.getBoard()).contains(
                Map.entry(new Position(Rank.B, File.EIGHTH), new Knight(Side.BLACK)),
                Map.entry(new Position(Rank.G, File.EIGHTH), new Knight(Side.BLACK)),
                Map.entry(new Position(Rank.B, File.FIRST), new Knight(Side.WHITE)),
                Map.entry(new Position(Rank.G, File.FIRST), new Knight(Side.WHITE))
        );
    }

    @DisplayName("검은색 비숍은 c8, f8에 위치한다. 흰색 나이트는 c1, f1에 위치한다.")
    @Test
    void bishopPositionTest() {
        ChessBoard chessBoard = new ChessBoard(new LinkedHashMap<>());

        chessBoard.init();

        assertThat(chessBoard.getBoard()).contains(
                Map.entry(new Position(Rank.C, File.EIGHTH), new Bishop(Side.BLACK)),
                Map.entry(new Position(Rank.F, File.EIGHTH), new Bishop(Side.BLACK)),
                Map.entry(new Position(Rank.C, File.FIRST), new Bishop(Side.WHITE)),
                Map.entry(new Position(Rank.F, File.FIRST), new Bishop(Side.WHITE))
        );
    }

    @DisplayName("검은색 퀸은 d8에 위치한다. 흰색 퀸은 d1에 위치한다.")
    @Test
    void queenPositionTest() {
        ChessBoard chessBoard = new ChessBoard(new LinkedHashMap<>());

        chessBoard.init();

        assertThat(chessBoard.getBoard()).contains(
                Map.entry(new Position(Rank.D, File.EIGHTH), new Queen(Side.BLACK)),
                Map.entry(new Position(Rank.D, File.FIRST), new Queen(Side.WHITE))
        );
    }

    @DisplayName("검은색 킹은 e8에 위치한다. 흰색 킹은 e1에 위치한다.")
    @Test
    void kingPositionTest() {
        ChessBoard chessBoard = new ChessBoard(new LinkedHashMap<>());

        chessBoard.init();

        assertThat(chessBoard.getBoard()).contains(
                Map.entry(new Position(Rank.E, File.EIGHTH), new King(Side.BLACK)),
                Map.entry(new Position(Rank.E, File.FIRST), new King(Side.WHITE))
        );
    }

    @DisplayName("검은색 폰은 a7~h7에 위치한다. 흰색 폰은 a2~h2에 위치한다.")
    @Test
    void pawnPositionTest() {
        ChessBoard chessBoard = new ChessBoard(new LinkedHashMap<>());

        chessBoard.init();

        assertThat(chessBoard.getBoard()).contains(
                Map.entry(new Position(Rank.A, File.SEVENTH), new Pawn(Side.BLACK)),
                Map.entry(new Position(Rank.B, File.SEVENTH), new Pawn(Side.BLACK)),
                Map.entry(new Position(Rank.C, File.SEVENTH), new Pawn(Side.BLACK)),
                Map.entry(new Position(Rank.D, File.SEVENTH), new Pawn(Side.BLACK)),
                Map.entry(new Position(Rank.E, File.SEVENTH), new Pawn(Side.BLACK)),
                Map.entry(new Position(Rank.F, File.SEVENTH), new Pawn(Side.BLACK)),
                Map.entry(new Position(Rank.G, File.SEVENTH), new Pawn(Side.BLACK)),
                Map.entry(new Position(Rank.H, File.SEVENTH), new Pawn(Side.BLACK)),

                Map.entry(new Position(Rank.A, File.SECOND), new Pawn(Side.WHITE)),
                Map.entry(new Position(Rank.B, File.SECOND), new Pawn(Side.WHITE)),
                Map.entry(new Position(Rank.C, File.SECOND), new Pawn(Side.WHITE)),
                Map.entry(new Position(Rank.D, File.SECOND), new Pawn(Side.WHITE)),
                Map.entry(new Position(Rank.E, File.SECOND), new Pawn(Side.WHITE)),
                Map.entry(new Position(Rank.F, File.SECOND), new Pawn(Side.WHITE)),
                Map.entry(new Position(Rank.G, File.SECOND), new Pawn(Side.WHITE)),
                Map.entry(new Position(Rank.H, File.SECOND), new Pawn(Side.WHITE))
        );
    }
}
