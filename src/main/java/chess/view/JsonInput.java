package chess.view;

import chess.model.board.Column;
import chess.model.board.Row;
import chess.model.board.Square;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class JsonInput {
    private static final JsonParser JSON_PARSER = new JsonParser();
    private static final int FIRST = 0;
    private static final int SECOND = 1;
    private static final String EMPTY = "";
    private static final String UNDER_BAR = "_";

    public static Square getSquareFromJson(final String json, final String key) {
        final JsonElement element = JSON_PARSER.parse(json);
        final String data = element.getAsJsonObject().get(key).getAsString();
        return toSquare(data);
    }

    private static Square toSquare(final String position) {
        final String[] args = position.strip().toUpperCase().split(EMPTY);
        final Column col = Column.valueOf(args[FIRST]);
        final Row row = Row.valueOf(UNDER_BAR + args[SECOND]);
        return new Square(col, row);
    }
}
