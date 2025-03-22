package chess;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import java.util.HashMap;
import java.util.Map;

public class BoardFactory {

    public static Board makeBoard() {
        Map<Position, Piece> pieces = new HashMap<>();
        Piece whitePawn = new Piece(null, new Pawn(Team.WHITE), null);
        Piece blackPawn = new Piece(null, new Pawn(Team.BLACK), null);
        Piece whiteRook = new Piece(null, new Rook(Team.WHITE), null);
        Piece blackRook = new Piece(null, new Rook(Team.BLACK), null);
        Piece whiteBishop = new Piece(new Bishop(Team.WHITE), null, null);
        Piece blackBishop = new Piece(new Bishop(Team.BLACK), null, null);
        Piece whiteQueen = new Piece(new Queen(Team.WHITE), new Queen(Team.WHITE), null);
        Piece blackQueen = new Piece(new Queen(Team.BLACK), new Queen(Team.BLACK), null);
        Piece whiteKing = new Piece(new King(Team.WHITE), new Queen(Team.WHITE), null);
        Piece blackKing = new Piece(new King(Team.BLACK), new Queen(Team.WHITE), null);
        Piece whiteKnight = new Piece(null, null, new Knight(Team.WHITE));
        Piece blackKnight = new Piece(null, null, new Knight(Team.WHITE));

        pieces.put(new Position(Column.A, Row.TWO), whitePawn);
        pieces.put(new Position(Column.B, Row.TWO), whitePawn);
        pieces.put(new Position(Column.C, Row.TWO), whitePawn);
        pieces.put(new Position(Column.D, Row.TWO), whitePawn);
        pieces.put(new Position(Column.E, Row.TWO), whitePawn);
        pieces.put(new Position(Column.F, Row.TWO), whitePawn);
        pieces.put(new Position(Column.G, Row.TWO), whitePawn);
        pieces.put(new Position(Column.H, Row.TWO), whitePawn);
        pieces.put(new Position(Column.A, Row.ONE), whiteRook);
        pieces.put(new Position(Column.B, Row.ONE), whiteKnight);
        pieces.put(new Position(Column.C, Row.ONE), whiteBishop);
        pieces.put(new Position(Column.D, Row.ONE), whiteQueen);
        pieces.put(new Position(Column.E, Row.ONE), whiteKing);
        pieces.put(new Position(Column.F, Row.ONE), whiteBishop);
        pieces.put(new Position(Column.G, Row.ONE), whiteKnight);
        pieces.put(new Position(Column.H, Row.ONE), whiteRook);

        pieces.put(new Position(Column.A, Row.SEVEN), blackPawn);
        pieces.put(new Position(Column.B, Row.SEVEN), blackPawn);
        pieces.put(new Position(Column.C, Row.SEVEN), blackPawn);
        pieces.put(new Position(Column.D, Row.SEVEN), blackPawn);
        pieces.put(new Position(Column.E, Row.SEVEN), blackPawn);
        pieces.put(new Position(Column.F, Row.SEVEN), blackPawn);
        pieces.put(new Position(Column.G, Row.SEVEN), blackPawn);
        pieces.put(new Position(Column.H, Row.SEVEN), blackPawn);
        pieces.put(new Position(Column.A, Row.EIGHT), blackRook);
        pieces.put(new Position(Column.B, Row.EIGHT), blackKnight);
        pieces.put(new Position(Column.C, Row.EIGHT), blackBishop);
        pieces.put(new Position(Column.D, Row.EIGHT), blackQueen);
        pieces.put(new Position(Column.E, Row.EIGHT), blackKing);
        pieces.put(new Position(Column.F, Row.EIGHT), blackBishop);
        pieces.put(new Position(Column.G, Row.EIGHT), blackKnight);
        pieces.put(new Position(Column.H, Row.EIGHT), blackRook);

        return new Board(pieces);
    }
}
