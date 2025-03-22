package chess.view;

import chess.domain.board.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.piece.Column;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.piece.Row;

import java.util.List;

public class OutputVIew {
    public static void printChessBoard(ChessBoard chessBoard) {
        List<Piece> allPieces = chessBoard.getPieces();

        System.out.print("  ");
        for (Column column : Column.getAllColumns()) {
            System.out.printf("%s", convertToString(column));
        }

        System.out.println();

        for (Row row : Row.getAllRows()) {
            System.out.printf("%s", convertToString(row));
            for (Column column : Column.getAllColumns()) {
                if (hasPiece(allPieces, row, column)) {
                    Piece findPiece = getPieceByRowAndColum(allPieces, row, column);
                    System.out.printf((convertToString(findPiece.getPieceType(), findPiece.getColor())));
                    continue;
                }
                System.out.print(".");
            }
            System.out.println();
        }

    }

    private static Piece getPieceByRowAndColum(List<Piece> pieces, Row row, Column column) {
        return pieces.stream()
            .filter(piece -> piece.isExist(row, column))
            .findFirst().get();
    }

    private static boolean hasPiece(List<Piece> allPieces, Row row, Column column) {
        return allPieces.stream()
            .anyMatch(piece -> piece.isExist(row, column));
    }

    private static String convertToString(Row row) {
        if (row == Row.EIGHT) {
            return "8 ";
        }
        if (row == Row.SEVEN) {
            return "7 ";
        }
        if (row == Row.SIX) {
            return "6 ";
        }
        if (row == Row.FIVE) {
            return "5 ";
        }
        if (row == Row.FOUR) {
            return "4 ";
        }
        if (row == Row.THREE) {
            return "3 ";
        }
        if (row == Row.TWO) {
            return "2 ";
        }
        return "1 ";
    }

    private static String convertToString(PieceType pieceType, Color color) {
        if (color == Color.BLACK) {
            if (pieceType == PieceType.BISHOP) {
                return "B";
            }
            if (pieceType == PieceType.ROOK) {
                return "R";
            }
            if (pieceType == PieceType.KING) {
                return "K";
            }
            if (pieceType == PieceType.KNIGHT) {
                return "N";
            }
            if (pieceType == PieceType.QUEEN) {
                return "Q";
            }
            return "P";
        }
        if (pieceType == PieceType.BISHOP) {
            return "b";
        }
        if (pieceType == PieceType.ROOK) {
            return "r";
        }
        if (pieceType == PieceType.KING) {
            return "k";
        }
        if (pieceType == PieceType.KNIGHT) {
            return "n";
        }
        if (pieceType == PieceType.QUEEN) {
            return "q";
        }
        return "p";
    }

    private static String convertToString(Column column) {
        if (column == Column.A) {
            return "A";
        }
        if (column == Column.B) {
            return "B";
        }
        if (column == Column.C) {
            return "C";
        }
        if (column == Column.D) {
            return "D";
        }
        if (column == Column.E) {
            return "E";
        }
        if (column == Column.F) {
            return "F";
        }
        if (column == Column.G) {
            return "G";
        }
        return "H ";
    }
}
