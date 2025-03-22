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
        return new ChessPieces(Color.WHITE, List.of(
                new Rook(new Position(Row.ONE, Column.A)),
                new Knight(new Position(Row.ONE, Column.B)),
                new Bishop(new Position(Row.ONE, Column.C)),
                new Queen(new Position(Row.ONE, Column.D)),
                new King(new Position(Row.ONE, Column.E)),
                new Bishop(new Position(Row.ONE, Column.F)),
                new Knight(new Position(Row.ONE, Column.G)),
                new Rook(new Position(Row.ONE, Column.H)),
                new Pawn(new Position(Row.TWO, Column.A)),
                new Pawn(new Position(Row.TWO, Column.B)),
                new Pawn(new Position(Row.TWO, Column.C)),
                new Pawn(new Position(Row.TWO, Column.D)),
                new Pawn(new Position(Row.TWO, Column.E)),
                new Pawn(new Position(Row.TWO, Column.F)),
                new Pawn(new Position(Row.TWO, Column.G)),
                new Pawn(new Position(Row.TWO, Column.H))
        ));
    }

    @Override
    public ChessPieces blackPieces() {
        return new ChessPieces(Color.BLACK, List.of(
                new Rook(new Position(Row.EIGHT, Column.A)),
                new Knight(new Position(Row.EIGHT, Column.B)),
                new Bishop(new Position(Row.EIGHT, Column.C)),
                new Queen(new Position(Row.EIGHT, Column.D)),
                new King(new Position(Row.EIGHT, Column.E)),
                new Bishop(new Position(Row.EIGHT, Column.F)),
                new Knight(new Position(Row.EIGHT, Column.G)),
                new Rook(new Position(Row.EIGHT, Column.H)),
                new Pawn(new Position(Row.SEVEN, Column.A)),
                new Pawn(new Position(Row.SEVEN, Column.B)),
                new Pawn(new Position(Row.SEVEN, Column.C)),
                new Pawn(new Position(Row.SEVEN, Column.D)),
                new Pawn(new Position(Row.SEVEN, Column.E)),
                new Pawn(new Position(Row.SEVEN, Column.F)),
                new Pawn(new Position(Row.SEVEN, Column.G)),
                new Pawn(new Position(Row.SEVEN, Column.H))
        ));
    }
}
