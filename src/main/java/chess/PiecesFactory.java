package chess;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Piece;
import chess.piece.Queen;
import chess.piece.Rook;
import java.util.List;
import java.util.Map;

public class PiecesFactory {

    private static final List<Piece> pieces;

    static {
        pieces=List.of(
                new Pawn(Color.WHITE,new Position(Row.TWO,Column.A)),
                new Pawn(Color.WHITE,new Position(Row.TWO,Column.B)),
                new Pawn(Color.WHITE,new Position(Row.TWO,Column.C)),
                new Pawn(Color.WHITE,new Position(Row.TWO,Column.D)),
                new Pawn(Color.WHITE,new Position(Row.TWO,Column.E)),
                new Pawn(Color.WHITE,new Position(Row.TWO,Column.F)),
                new Pawn(Color.WHITE,new Position(Row.TWO,Column.G)),
                new Pawn(Color.WHITE,new Position(Row.TWO,Column.H)),
                new Pawn(Color.BLACK,new Position(Row.SEVEN,Column.A)),
                new Pawn(Color.BLACK,new Position(Row.SEVEN,Column.B)),
                new Pawn(Color.BLACK,new Position(Row.SEVEN,Column.C)),
                new Pawn(Color.BLACK,new Position(Row.SEVEN,Column.D)),
                new Pawn(Color.BLACK,new Position(Row.SEVEN,Column.E)),
                new Pawn(Color.BLACK,new Position(Row.SEVEN,Column.F)),
                new Pawn(Color.BLACK,new Position(Row.SEVEN,Column.G)),
                new Pawn(Color.BLACK,new Position(Row.SEVEN,Column.H)),
                new King(Color.WHITE,new Position(Row.ONE,Column.E)),
                new King(Color.BLACK,new Position(Row.EIGHT,Column.E)),
                new Queen(Color.WHITE,new Position(Row.ONE,Column.D)),
                new Queen(Color.BLACK,new Position(Row.EIGHT,Column.D)),
                new Rook(Color.WHITE,new Position(Row.ONE,Column.A)),
                new Rook(Color.WHITE,new Position(Row.ONE,Column.H)),
                new Rook(Color.BLACK,new Position(Row.EIGHT,Column.A)),
                new Rook(Color.BLACK,new Position(Row.EIGHT,Column.H)),
                new Knight(Color.WHITE,new Position(Row.ONE,Column.B)),
                new Knight(Color.WHITE,new Position(Row.ONE,Column.G)),
                new Knight(Color.BLACK,new Position(Row.EIGHT,Column.B)),
                new Knight(Color.BLACK,new Position(Row.EIGHT,Column.G)),
                new Bishop(Color.WHITE,new Position(Row.ONE,Column.C)),
                new Bishop(Color.WHITE,new Position(Row.ONE,Column.F)),
                new Bishop(Color.BLACK,new Position(Row.EIGHT,Column.C)),
                new Bishop(Color.BLACK,new Position(Row.EIGHT,Column.F))
        );
    }

    public static List<Piece> getInitializedPieces(){
        return pieces;
    }
}
