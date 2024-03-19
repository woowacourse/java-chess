package domain;

import domain.*;
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
                Map.entry(new Position(Horizontal.A, Vertical.EIGHTH), new Rook(Side.BLACK)),
                Map.entry(new Position(Horizontal.H, Vertical.EIGHTH), new Rook(Side.BLACK)),
                Map.entry(new Position(Horizontal.A, Vertical.FIRST), new Rook(Side.WHITE)),
                Map.entry(new Position(Horizontal.H, Vertical.FIRST), new Rook(Side.WHITE))
        );
    }

    @DisplayName("검은색 나이트는 b8, g8에 위치한다. 흰색 나이트는 b1, g1에 위치한다.")
    @Test
    void knightPositionTest() {
        ChessBoard chessBoard = new ChessBoard(new LinkedHashMap<>());

        chessBoard.init();

        assertThat(chessBoard.getBoard()).contains(
                Map.entry(new Position(Horizontal.B, Vertical.EIGHTH), new Knight(Side.BLACK)),
                Map.entry(new Position(Horizontal.G, Vertical.EIGHTH), new Knight(Side.BLACK)),
                Map.entry(new Position(Horizontal.B, Vertical.FIRST), new Knight(Side.WHITE)),
                Map.entry(new Position(Horizontal.G, Vertical.FIRST), new Knight(Side.WHITE))
        );
    }

    @DisplayName("검은색 비숍은 c8, f8에 위치한다. 흰색 나이트는 c1, f1에 위치한다.")
    @Test
    void bishopPositionTest() {
        ChessBoard chessBoard = new ChessBoard(new LinkedHashMap<>());

        chessBoard.init();

        assertThat(chessBoard.getBoard()).contains(
                Map.entry(new Position(Horizontal.C, Vertical.EIGHTH), new Bishop(Side.BLACK)),
                Map.entry(new Position(Horizontal.F, Vertical.EIGHTH), new Bishop(Side.BLACK)),
                Map.entry(new Position(Horizontal.C, Vertical.FIRST), new Bishop(Side.WHITE)),
                Map.entry(new Position(Horizontal.F, Vertical.FIRST), new Bishop(Side.WHITE))
        );
    }

    @DisplayName("검은색 퀸은 d8에 위치한다. 흰색 퀸은 d1에 위치한다.")
    @Test
    void queenPositionTest() {
        ChessBoard chessBoard = new ChessBoard(new LinkedHashMap<>());

        chessBoard.init();

        assertThat(chessBoard.getBoard()).contains(
                Map.entry(new Position(Horizontal.D, Vertical.EIGHTH), new Queen(Side.BLACK)),
                Map.entry(new Position(Horizontal.D, Vertical.FIRST), new Queen(Side.WHITE))
        );
    }

    @DisplayName("검은색 킹은 e8에 위치한다. 흰색 킹은 e1에 위치한다.")
    @Test
    void kingPositionTest() {
        ChessBoard chessBoard = new ChessBoard(new LinkedHashMap<>());

        chessBoard.init();

        assertThat(chessBoard.getBoard()).contains(
                Map.entry(new Position(Horizontal.E, Vertical.EIGHTH), new King(Side.BLACK)),
                Map.entry(new Position(Horizontal.E, Vertical.FIRST), new King(Side.WHITE))
        );
    }

    @DisplayName("검은색 폰은 a7~h7에 위치한다. 흰색 폰은 a2~h2에 위치한다.")
    @Test
    void pawnPositionTest() {
        ChessBoard chessBoard = new ChessBoard(new LinkedHashMap<>());

        chessBoard.init();

        assertThat(chessBoard.getBoard()).contains(
                Map.entry(new Position(Horizontal.A, Vertical.SEVENTH), new Pawn(Side.BLACK)),
                Map.entry(new Position(Horizontal.B, Vertical.SEVENTH), new Pawn(Side.BLACK)),
                Map.entry(new Position(Horizontal.C, Vertical.SEVENTH), new Pawn(Side.BLACK)),
                Map.entry(new Position(Horizontal.D, Vertical.SEVENTH), new Pawn(Side.BLACK)),
                Map.entry(new Position(Horizontal.E, Vertical.SEVENTH), new Pawn(Side.BLACK)),
                Map.entry(new Position(Horizontal.F, Vertical.SEVENTH), new Pawn(Side.BLACK)),
                Map.entry(new Position(Horizontal.G, Vertical.SEVENTH), new Pawn(Side.BLACK)),
                Map.entry(new Position(Horizontal.H, Vertical.SEVENTH), new Pawn(Side.BLACK)),

                Map.entry(new Position(Horizontal.A, Vertical.SECOND), new Pawn(Side.WHITE)),
                Map.entry(new Position(Horizontal.B, Vertical.SECOND), new Pawn(Side.WHITE)),
                Map.entry(new Position(Horizontal.C, Vertical.SECOND), new Pawn(Side.WHITE)),
                Map.entry(new Position(Horizontal.D, Vertical.SECOND), new Pawn(Side.WHITE)),
                Map.entry(new Position(Horizontal.E, Vertical.SECOND), new Pawn(Side.WHITE)),
                Map.entry(new Position(Horizontal.F, Vertical.SECOND), new Pawn(Side.WHITE)),
                Map.entry(new Position(Horizontal.G, Vertical.SECOND), new Pawn(Side.WHITE)),
                Map.entry(new Position(Horizontal.H, Vertical.SECOND), new Pawn(Side.WHITE))
        );
    }
}
