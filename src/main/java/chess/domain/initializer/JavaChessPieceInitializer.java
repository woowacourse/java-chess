package chess.domain.initializer;

import chess.domain.piece.*;
import chess.domain.pieces.ChessPieces;
import chess.domain.pieces.Color;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;

import java.util.List;

public class JavaChessPieceInitializer implements ChessPieceInitializer {

    @Override
    public ChessPieces whitePieces() {
        return new ChessPieces(List.of(
                new Rook(Color.WHITE, new Position(Row.ONE, Column.A)),
                new Knight(Color.WHITE, new Position(Row.ONE, Column.B)),
                new Bishop(Color.WHITE, new Position(Row.ONE, Column.C)),
                new Queen(Color.WHITE, new Position(Row.ONE, Column.D)),
                new King(Color.WHITE, new Position(Row.ONE, Column.E)),
                new Bishop(Color.WHITE, new Position(Row.ONE, Column.F)),
                new Knight(Color.WHITE, new Position(Row.ONE, Column.G)),
                new Rook(Color.WHITE, new Position(Row.ONE, Column.H)),
                new Pawn(Color.WHITE, new Position(Row.TWO, Column.A)),
                new Pawn(Color.WHITE, new Position(Row.TWO, Column.B)),
                new Pawn(Color.WHITE, new Position(Row.TWO, Column.C)),
                new Pawn(Color.WHITE, new Position(Row.TWO, Column.D)),
                new Pawn(Color.WHITE, new Position(Row.TWO, Column.E)),
                new Pawn(Color.WHITE, new Position(Row.TWO, Column.F)),
                new Pawn(Color.WHITE, new Position(Row.TWO, Column.G)),
                new Pawn(Color.WHITE, new Position(Row.TWO, Column.H))
        ));
    }

    @Override
    public ChessPieces blackPieces() {
        return new ChessPieces(List.of(
                new Rook(Color.BLACK, new Position(Row.EIGHT, Column.A)),
                new Knight(Color.BLACK, new Position(Row.EIGHT, Column.B)),
                new Bishop(Color.BLACK, new Position(Row.EIGHT, Column.C)),
                new Queen(Color.BLACK, new Position(Row.EIGHT, Column.D)),
                new King(Color.BLACK, new Position(Row.EIGHT, Column.E)),
                new Bishop(Color.BLACK, new Position(Row.EIGHT, Column.F)),
                new Knight(Color.BLACK, new Position(Row.EIGHT, Column.G)),
                new Rook(Color.BLACK, new Position(Row.EIGHT, Column.H)),
                new Pawn(Color.BLACK, new Position(Row.SEVEN, Column.A)),
                new Pawn(Color.BLACK, new Position(Row.SEVEN, Column.B)),
                new Pawn(Color.BLACK, new Position(Row.SEVEN, Column.C)),
                new Pawn(Color.BLACK, new Position(Row.SEVEN, Column.D)),
                new Pawn(Color.BLACK, new Position(Row.SEVEN, Column.E)),
                new Pawn(Color.BLACK, new Position(Row.SEVEN, Column.F)),
                new Pawn(Color.BLACK, new Position(Row.SEVEN, Column.G)),
                new Pawn(Color.BLACK, new Position(Row.SEVEN, Column.H))
        ));
    }
}
