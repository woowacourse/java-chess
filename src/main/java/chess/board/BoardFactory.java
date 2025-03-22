package chess.board;

import chess.position.Color;
import chess.position.Column;
import chess.position.Position;
import chess.position.Row;
import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Pieces;
import chess.piece.Queen;
import chess.piece.Rook;
import java.util.ArrayList;
import java.util.List;

public class BoardFactory {

    private BoardFactory() {
    }

    public static Board init() {
        List<Piece> initPieces = new ArrayList<>();
        for (Column column : Column.values()) {
            initPieces.add(new Pawn(Color.WHITE, new Position(column, Row.TWO)));
            initPieces.add(new Pawn(Color.BLACK, new Position(column, Row.SEVEN)));
        }
        initPieces.add(new Rook(Color.WHITE, new Position(Column.A, Row.ONE)));
        initPieces.add(new Rook(Color.WHITE, new Position(Column.H, Row.ONE)));
        initPieces.add(new Rook(Color.BLACK, new Position(Column.A, Row.EIGHT)));
        initPieces.add(new Rook(Color.BLACK, new Position(Column.H, Row.EIGHT)));

        initPieces.add(new Knight(Color.WHITE, new Position(Column.B, Row.ONE)));
        initPieces.add(new Knight(Color.WHITE, new Position(Column.G, Row.ONE)));
        initPieces.add(new Knight(Color.BLACK, new Position(Column.B, Row.EIGHT)));
        initPieces.add(new Knight(Color.BLACK, new Position(Column.G, Row.EIGHT)));

        initPieces.add(new Bishop(Color.WHITE, new Position(Column.C, Row.ONE)));
        initPieces.add(new Bishop(Color.WHITE, new Position(Column.F, Row.ONE)));
        initPieces.add(new Bishop(Color.BLACK, new Position(Column.C, Row.EIGHT)));
        initPieces.add(new Bishop(Color.BLACK, new Position(Column.F, Row.EIGHT)));

        initPieces.add(new King(Color.WHITE, new Position(Column.D, Row.ONE)));
        initPieces.add(new Queen(Color.WHITE, new Position(Column.E, Row.ONE)));

        initPieces.add(new King(Color.BLACK, new Position(Column.D, Row.EIGHT)));
        initPieces.add(new Queen(Color.BLACK, new Position(Column.E, Row.EIGHT)));

        Pieces pieces = new Pieces(initPieces);
        return new Board(pieces);
    }
}
