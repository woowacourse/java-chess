package chess.view;

import chess.model.Play;
import chess.model.Side;
import chess.model.board.Position;
import chess.model.board.Square;
import chess.model.unit.Piece;

import java.text.DecimalFormat;
import java.util.List;
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
    private static final DecimalFormat decimalFormat = new DecimalFormat("0.#");

    public static String responseOk(final String keyName, final String value) {
        return squareBracketWrap(
                keyAndStringValue(QUERY_STATUS, STATUS_OK)
                + COMMA_SEPARATOR
                + keyAndJsonValue(keyName, value)
        );
    }

    public static String responseOk() {
        return squareBracketWrap(keyAndStringValue(QUERY_STATUS, STATUS_OK));
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

    public static String scoreAll(final Play play) {
        return squareBracketWrap(
                keyAndStringValue(PIECE_SIDE_WHITE,
                        decimalFormat.format(play.calcScore(Side.WHITE)))
                + COMMA_SEPARATOR
                + keyAndStringValue(PIECE_SIDE_BLACK,
                        decimalFormat.format(play.calcScore(Side.BLACK)))
        );
    }

    private static String positionToJsonKeyValue(final Position position) {
        final Square square = position.getSquare();
        final Piece piece = position.getPiece();
        return keyAndStringValue(square.toString(), piece.toString());
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
