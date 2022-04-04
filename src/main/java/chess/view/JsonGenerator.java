package chess.view;

import chess.domain.Score;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import chess.domain.position.Position;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

public class JsonGenerator {

    private static final String ERROR = "error";
    private static final String KING_DIE = "isKingDie";
    private static final String CURRENT_TURN = "current_turn";
    private static final String WIN_COLOR = "win_color";
    private static final String DRAW = "draw";

    private final Gson gson;
    private final Map<String, Object> model;

    private JsonGenerator() {
        this.gson = new Gson();
        this.model = new HashMap<>();
    }

    public static JsonGenerator create() {
        return new JsonGenerator();
    }

    public void add(final String key, final String value) {
        model.put(key, value);
    }

    public void addError(final String errorMessage) {
        model.put(ERROR, errorMessage);
    }

    public void addAllPiece(final Map<Position, ChessPiece> pieceByPosition) {
        for (final Entry<Position, ChessPiece> entry : pieceByPosition.entrySet()) {
            final Position position = entry.getKey();
            final ChessPiece chessPiece = entry.getValue();
            model.put(position.getValue(), PieceName.findWebImagePath(chessPiece));
        }
    }

    public void addKingDieResult(final boolean kingDie) {
        model.put(KING_DIE, kingDie);
    }

    public void addScore(final Score score) {
        for (final Color color : Color.values()) {
            model.put(color.getValue(), score.findScore(color));
        }
    }

    public void addCurrentTurn(final Color currentTurn) {
        model.put(CURRENT_TURN, currentTurn.getValue());
    }

    public void addWinColor(final Color winColor) {
        if (Objects.isNull(winColor)) {
            model.put(WIN_COLOR, DRAW);
            return;
        }
        model.put(WIN_COLOR, winColor.getValue());
    }

    public String toJson() {
        return gson.toJson(model);
    }
}
