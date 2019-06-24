package chess.view;

import chess.model.Side;
import chess.model.board.Position;
import chess.model.board.Square;
import chess.model.unit.Piece;
import chess.model.unit.UnitClass;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonOutput {
    private static final String SQUARE_BRACKET_OPEN = "{";
    private static final String SQUARE_BRACKET_CLOSE = "}";
    private static final String PIECE_SIDE_BLACK = "b";
    private static final String PIECE_SIDE_WHITE = "w";
    private static final String COLON_DELIMITER = ": ";
    private static final String COMMA_SEPARATOR = ", ";
    private static final String DOUBLE_QUOTE = "\"";
    private static final String QUERY_STATUS = "queryStatus";
    private static final String STATUS_OK = "ok";
    private static final String STATUS_FAILED = "failed";
    private static final String ERROR = "error";
    private static final Map<UnitClass, String> UNIT_CLASS_STRING_MAP = new HashMap<>();

    static {
        UNIT_CLASS_STRING_MAP.put(UnitClass.KING, "K");
        UNIT_CLASS_STRING_MAP.put(UnitClass.QUEEN, "Q");
        UNIT_CLASS_STRING_MAP.put(UnitClass.ROOK, "R");
        UNIT_CLASS_STRING_MAP.put(UnitClass.BISHOP, "B");
        UNIT_CLASS_STRING_MAP.put(UnitClass.KNIGHT, "N");
        UNIT_CLASS_STRING_MAP.put(UnitClass.PAWN, "P");
    }

    public static String responseOk(final String keyName, final String value) {
        return squareBracketWrap(
                keyAndStringValue(QUERY_STATUS, STATUS_OK)
                + COMMA_SEPARATOR
                + keyAndJsonValue(keyName, value)
        );
    }

    public static String responseFailed(final String errorMessage) {
        return squareBracketWrap(
                keyAndStringValue(QUERY_STATUS, STATUS_FAILED)
                + COMMA_SEPARATOR
                + keyAndStringValue(ERROR, errorMessage)
        );
    }

    public static String board(final List<Position> positionList) {
        return squareBracketWrap(
                positionList.stream()
                .map(JsonOutput::positionToJsonKeyValue)
                .collect(Collectors.joining(COMMA_SEPARATOR))
        );
    }

    private static String positionToJsonKeyValue(final Position position) {
        final Square square = position.getSquare();
        final Piece piece = position.getPiece();
        return keyAndStringValue(squareToString(square), pieceToString(piece));
    }

    private static String squareToString(final Square square) {
        return square.getColumn().getColumnName().toLowerCase()
                + square.getRow().getRowName();
    }

    private static String pieceToString(final Piece piece) {
        final String pieceSide = piece.getSide() == Side.WHITE
                ? PIECE_SIDE_WHITE
                : PIECE_SIDE_BLACK;
        final String unitClass = UNIT_CLASS_STRING_MAP.get(piece.getUnitClass());
        return pieceSide + unitClass;
    }

    private static String quotationWrap(final String string) {
        return DOUBLE_QUOTE + string + DOUBLE_QUOTE;
    }

    private static String squareBracketWrap(final String string) {
        return SQUARE_BRACKET_OPEN + string + SQUARE_BRACKET_CLOSE;
    }

    private static String keyAndJsonValue(final String key, final String value) {
        return quotationWrap(key) + COLON_DELIMITER + value;
    }

    private static String keyAndStringValue(final String key, final String value) {
        return keyAndJsonValue(key, quotationWrap(value));
    }
}
