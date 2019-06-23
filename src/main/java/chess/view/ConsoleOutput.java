package chess.view;

import chess.model.Side;
import chess.model.board.Board;
import chess.model.board.Column;
import chess.model.board.Row;
import chess.model.board.Square;
import chess.model.unit.Piece;
import chess.model.unit.UnitClass;

import java.text.DecimalFormat;
import java.util.*;

public class ConsoleOutput {
    private static final Map<UnitClass, String> PIECE_SYMBOL = new HashMap<>();
    private static final String EMPTY_SQUARE;
    private static final String NEW_LINE;
    private static final String BLACK;
    private static final String WHITE;
    private static final String TURN;
    private static final String COLON;
    private static final String SCORE;
    private static final String MESSAGE_START;
    private static final DecimalFormat FORMAT;

    static {
        FORMAT = new DecimalFormat("0.#");
        NEW_LINE = "\n";
        EMPTY_SQUARE = ".";
        COLON = " : ";
        SCORE = "점";
        BLACK = "흑";
        WHITE = "백";
        TURN = "이 움직일 차례.";
        MESSAGE_START =
                "> 체스 게임을 시작합니다.\n" +
                        "> 게임 시작 : start\n" +
                        "> 게임 종료 : end\n" +
                        "> 게임 이동 : move source위치 target위치 (예시 : move b2 b3)";
    }

    static {
        PIECE_SYMBOL.put(UnitClass.KING, "K");
        PIECE_SYMBOL.put(UnitClass.QUEEN, "Q");
        PIECE_SYMBOL.put(UnitClass.ROOK, "R");
        PIECE_SYMBOL.put(UnitClass.BISHOP, "B");
        PIECE_SYMBOL.put(UnitClass.KNIGHT, "N");
        PIECE_SYMBOL.put(UnitClass.PAWN, "P");
    }

    private static String getPieceSymbol(final Board board, final Square square) {
        String symbol = EMPTY_SQUARE;
        try {
            final Piece piece = board.getPiece(square);
            symbol = getSymbolBySide(piece);
        } catch (Exception e) {
            // pass
        }
        return symbol;
    }

    private static String getSymbolBySide(final Piece piece) {
        String symbol = PIECE_SYMBOL.get(piece.getUnitClass());
        if (piece.getSide().equals(Side.WHITE)) {
            symbol = symbol.toLowerCase();
        }
        return symbol;
    }

    public static String getBoardString(final Board board) {
        final StringBuilder result = new StringBuilder();
        final List<StringBuilder> sbList = new ArrayList<>();
        for (Row row : Row.values()) {
            sbList.add(boardRow(board, row));
        }
        Collections.reverse(sbList);
        for (StringBuilder sb : sbList) {
            result.append(sb);
            result.append(NEW_LINE);
        }
        return result.toString();
    }

    public static void board(final Board board) {
        System.out.println(getBoardString(board));
    }

    private static StringBuilder boardRow(final Board board, final Row row) {
        Square square;
        StringBuilder sb = new StringBuilder();
        for (Column column : Column.values()) {
            square = new Square(column, row);
            sb.append(getPieceSymbol(board, square));
        }
        return sb;
    }

    public static void startMessage() {
        System.out.println(MESSAGE_START);
    }

    public static void side(final Side side) {
        final String sideTurn = side == Side.WHITE ? WHITE : BLACK;
        System.out.println(sideTurn + TURN);
    }

    public static void status(final Side side, final double score) {
        final String sideTurn = side == Side.WHITE ? WHITE : BLACK;
        System.out.println(sideTurn + COLON + FORMAT.format(score) + SCORE);
    }
}
