package chess.view;

import static chess.domain.piece.Column.A;
import static chess.domain.piece.Column.B;
import static chess.domain.piece.Column.C;
import static chess.domain.piece.Column.D;
import static chess.domain.piece.Column.E;
import static chess.domain.piece.Column.F;
import static chess.domain.piece.Column.G;
import static chess.domain.piece.Row.EIGHT;
import static chess.domain.piece.Row.FIVE;
import static chess.domain.piece.Row.FOUR;
import static chess.domain.piece.Row.SEVEN;
import static chess.domain.piece.Row.SIX;
import static chess.domain.piece.Row.THREE;
import static chess.domain.piece.Row.TWO;

import chess.domain.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Column;
import chess.domain.piece.Position;
import chess.domain.piece.Row;
import chess.domain.Turn;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Set;

public class OutputView {

    private static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_WHITE = "\u001B[37m";
    private static final String ANSI_BLACK = "\u001B[30m";

    public void printChessBoard(final Board board) {

        Set<Piece> pieces = board.getPieces();
        for (Row row : Row.values()) {
            System.out.print(getRowName(row) + " ");
            for (Column column : Column.values()) {
                boolean check = true;
                for (Piece piece : pieces) {
                    if (piece.samePosition(new Position(column, row))) {
                        System.out.print(getColorPieceName(piece) + " ");
                        check = false;
                        break;
                    }
                }
                if (check) {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.print("  ");
        for (Column column : Column.values()) {
            System.out.print(getColumnName(column) + " ");
        }
        System.out.println();
    }

    private String getColorPieceName(Piece piece) {
        if (piece.getColor() == Color.WHITE) {
            return ANSI_WHITE + getPieceName(piece) + ANSI_RESET;
        }
        return ANSI_BLACK + getPieceName(piece) + ANSI_RESET;
    }

    private String getPieceName(Piece piece) {
        if (piece.getClass() == King.class) {
            return "K";
        }
        if (piece.getClass() == Queen.class) {
            return "Q";
        }
        if (piece.getClass() == Bishop.class) {
            return "B";
        }
        if (piece.getClass() == Knight.class) {
            return "N";
        }
        if (piece.getClass() == Rook.class) {
            return "R";
        }
        return "P";
    }

    private String getRowName(Row row) {
        if (row == EIGHT) {
            return "8";
        }
        if (row == SEVEN) {
            return "7";
        }

        if (row == SIX) {
            return "6";
        }

        if (row == FIVE) {
            return "5";
        }

        if (row == FOUR) {
            return "4";
        }

        if (row == THREE) {
            return "3";
        }

        if (row == TWO) {
            return "2";
        }
        return "1";
    }

    private String getColumnName(Column column) {
        if (column == A) {
            return "A";
        }
        if (column == B) {
            return "B";
        }
        if (column == C) {
            return "C";
        }
        if (column == D) {
            return "D";
        }
        if (column == E) {
            return "E";
        }
        if (column == F) {
            return "F";
        }
        if (column == G) {
            return "G";
        }
        return "H";
    }


    public void printTurn(final Turn turn) {
        System.out.println(turn.getTurn().name() + "의 턴입니다.");
    }
}
