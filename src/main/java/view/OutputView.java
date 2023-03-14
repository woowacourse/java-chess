package view;

import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import domain.board.File;
import domain.board.Rank;
import domain.board.Square;
import domain.piece.Piece;

public class OutputView {
    public static void printFuckingShit(Map<Square, Piece> board) {
        List<String> messages = new ArrayList<>();
        for (Rank value : Rank.values()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (File file : File.values()) {
                stringBuilder.append(pieceToString(board.get(new Square(file, value))));
            }
            messages.add(stringBuilder.toString());
        }

        Collections.reverse(messages);

        for (String message : messages) {
            System.out.println(message);
        }
    }

    private static String pieceToString(Piece piece) {
        if (piece.isPawn()) {
            return Type.PAWN.convertToCamp(piece);
        }
        if (piece.isRook()) {
            return Type.ROOK.convertToCamp(piece);
        }
        if (piece.isBishop()) {
            return Type.BISHOP.convertToCamp(piece);
        }
        if (piece.isKnight()) {
            return Type.KNIGHT.convertToCamp(piece);
        }
        if (piece.isQueen()) {
            return Type.QUEEN.convertToCamp(piece);
        }
        if (piece.isKing()) {
            return Type.KING.convertToCamp(piece);
        }
        return Type.EMPTY.convertToCamp(piece);
    }

    private enum Type {
        PAWN("p"),
        ROOK("r"),
        BISHOP("b"),
        KNIGHT("n"),
        QUEEN("q"),
        KING("k"),
        EMPTY(".");

        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String convertToCamp(Piece piece) {
            if (piece.isWhite()) {
                return this.value;
            }

            return value.toUpperCase();
        }
    }

}
