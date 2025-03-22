package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Column;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Position;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.piece.Row;

import java.util.ArrayList;
import java.util.List;

public class InitBoardGenerator {

    public static List<Piece> initChessBoard() {
        List<Piece> initPieces = new ArrayList<>();

        for (Row row : Row.getAllRows()) {
            if (row == Row.EIGHT) {
                initPieces.add(new Rook(PieceType.ROOK, new Position(row, Column.A), Color.BLACK));
                initPieces.add(new Knight(PieceType.KNIGHT, new Position(row, Column.B), Color.BLACK));
                initPieces.add(new Bishop(PieceType.BISHOP, new Position(row, Column.C), Color.BLACK));
                initPieces.add(new Queen(PieceType.QUEEN, new Position(row, Column.D), Color.BLACK));
                initPieces.add(new King(PieceType.KING, new Position(row, Column.E), Color.BLACK));
                initPieces.add(new Bishop(PieceType.BISHOP, new Position(row, Column.F), Color.BLACK));
                initPieces.add(new Knight(PieceType.KNIGHT, new Position(row, Column.G), Color.BLACK));
                initPieces.add(new Rook(PieceType.ROOK, new Position(row, Column.H), Color.BLACK));
            }

            if (row == Row.SEVEN) {
                for (Column column : Column.getAllColumns()) {
                    initPieces.add(new Pawn(PieceType.PAWN, new Position(row, column), Color.BLACK));
                }
            }

            if (row == Row.TWO) {
                for (Column column : Column.getAllColumns()) {
                    initPieces.add(new Pawn(PieceType.PAWN, new Position(row, column), Color.WHITE));
                }
            }

            if (row == Row.ONE) {
                initPieces.add(new Rook(PieceType.ROOK, new Position(row, Column.A), Color.WHITE));
                initPieces.add(new Knight(PieceType.KNIGHT, new Position(row, Column.B), Color.WHITE));
                initPieces.add(new Bishop(PieceType.BISHOP, new Position(row, Column.C), Color.WHITE));
                initPieces.add(new Queen(PieceType.QUEEN, new Position(row, Column.D), Color.WHITE));
                initPieces.add(new King(PieceType.KING, new Position(row, Column.E), Color.WHITE));
                initPieces.add(new Bishop(PieceType.BISHOP, new Position(row, Column.F), Color.WHITE));
                initPieces.add(new Knight(PieceType.KNIGHT, new Position(row, Column.G), Color.WHITE));
                initPieces.add(new Rook(PieceType.ROOK, new Position(row, Column.H), Color.WHITE));
            }
        }
        return initPieces;
    }
}
