package chess;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Rook;
import java.util.Arrays;
import java.util.List;

public class InitiatePieces {
    private final List<Piece> pawns = List.of(
            new Pawn(new Position(Row.TWO,Column.A),Color.WHITE),
            new Pawn(new Position(Row.TWO,Column.B),Color.WHITE),
            new Pawn(new Position(Row.TWO,Column.C),Color.WHITE),
            new Pawn(new Position(Row.TWO,Column.D),Color.WHITE),
            new Pawn(new Position(Row.TWO,Column.E),Color.WHITE),
            new Pawn(new Position(Row.TWO,Column.F),Color.WHITE),
            new Pawn(new Position(Row.TWO,Column.G),Color.WHITE),
            new Pawn(new Position(Row.TWO,Column.H),Color.WHITE),

            new Pawn(new Position(Row.SEVEN,Column.A),Color.BLACK),
            new Pawn(new Position(Row.SEVEN,Column.B),Color.BLACK),
            new Pawn(new Position(Row.SEVEN,Column.C),Color.BLACK),
            new Pawn(new Position(Row.SEVEN,Column.D),Color.BLACK),
            new Pawn(new Position(Row.SEVEN,Column.E),Color.BLACK),
            new Pawn(new Position(Row.SEVEN,Column.F),Color.BLACK),
            new Pawn(new Position(Row.SEVEN,Column.G),Color.BLACK),
            new Pawn(new Position(Row.SEVEN,Column.H),Color.BLACK)
    );

    private final List<Piece> kings = List.of(
            new King(new Position(Row.EIGHT,Column.E),Color.BLACK),
            new King(new Position(Row.ONE,Column.E),Color.BLACK)
    );

    private final List<Piece> queens = List.of(
            new King(new Position(Row.EIGHT,Column.D),Color.BLACK),
            new King(new Position(Row.ONE,Column.D),Color.WHITE)
    );

    private final List<Piece> rooks = List.of(
            new Rook(new Position(Row.ONE,Column.A),Color.WHITE),
            new Rook(new Position(Row.ONE,Column.H),Color.WHITE),
            new Rook(new Position(Row.EIGHT,Column.A),Color.BLACK),
            new Rook(new Position(Row.EIGHT,Column.H),Color.BLACK)
    );

    private final List<Piece> bishops = List.of(
            new Bishop(new Position(Row.ONE,Column.C),Color.WHITE),
            new Bishop(new Position(Row.ONE,Column.F),Color.WHITE),
            new Bishop(new Position(Row.EIGHT,Column.C),Color.BLACK),
            new Bishop(new Position(Row.EIGHT,Column.F),Color.BLACK)
    );

    private final List<Piece> knights = List.of(
            new Knight(new Position(Row.ONE,Column.B),Color.WHITE),
            new Knight(new Position(Row.ONE,Column.G),Color.WHITE),
            new Knight(new Position(Row.EIGHT,Column.B),Color.BLACK),
            new Knight(new Position(Row.EIGHT,Column.G),Color.BLACK)
    );
}


