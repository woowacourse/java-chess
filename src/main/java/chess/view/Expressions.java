package chess.view;

import chess.command.Command;
import chess.domain.board.Column;
import chess.domain.board.Row;
import chess.domain.piece.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class Expressions {
    static final String COMMAND_START = "start";
    static final String COMMAND_END = "end";
    private static final String COMMAND_STATUS = "status";

    public static final Map<Character, Column> EXPRESSIONS_COLUMN;
    public static final Map<Character, Row> EXPRESSIONS_ROW;
    static final Map<String, Supplier<Command>> EXPRESSIONS_COMMAND;
    static final Map<Type, String> PIECE_EXPRESSIONS;

    static {
        EXPRESSIONS_COLUMN = new HashMap<>();
        EXPRESSIONS_COLUMN.put('a', Column.A);
        EXPRESSIONS_COLUMN.put('b', Column.B);
        EXPRESSIONS_COLUMN.put('c', Column.C);
        EXPRESSIONS_COLUMN.put('d', Column.D);
        EXPRESSIONS_COLUMN.put('e', Column.E);
        EXPRESSIONS_COLUMN.put('f', Column.F);
        EXPRESSIONS_COLUMN.put('g', Column.G);
        EXPRESSIONS_COLUMN.put('h', Column.H);

        EXPRESSIONS_ROW = new HashMap<>();
        EXPRESSIONS_ROW.put('1', Row.ONE);
        EXPRESSIONS_ROW.put('2', Row.TWO);
        EXPRESSIONS_ROW.put('3', Row.THREE);
        EXPRESSIONS_ROW.put('4', Row.FOUR);
        EXPRESSIONS_ROW.put('5', Row.FIVE);
        EXPRESSIONS_ROW.put('6', Row.SIX);
        EXPRESSIONS_ROW.put('7', Row.SEVEN);
        EXPRESSIONS_ROW.put('8', Row.EIGHT);

        EXPRESSIONS_COMMAND = new HashMap<>();
        EXPRESSIONS_COMMAND.put(COMMAND_START, Command::createStart);
        EXPRESSIONS_COMMAND.put(COMMAND_END, Command::createEnd);
        EXPRESSIONS_COMMAND.put(COMMAND_STATUS, Command::createStatus);

        PIECE_EXPRESSIONS = new LinkedHashMap<>();
        PIECE_EXPRESSIONS.put(Type.NONE, ".");
        PIECE_EXPRESSIONS.put(Type.BISHOP, "b");
        PIECE_EXPRESSIONS.put(Type.KING, "k");
        PIECE_EXPRESSIONS.put(Type.KNIGHT, "n");
        PIECE_EXPRESSIONS.put(Type.PAWN, "p");
        PIECE_EXPRESSIONS.put(Type.QUEEN, "q");
        PIECE_EXPRESSIONS.put(Type.ROOK, "r");
    }
}
