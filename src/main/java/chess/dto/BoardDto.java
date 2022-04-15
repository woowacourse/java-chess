package chess.dto;

import chess.model.piece.Piece;
import chess.model.position.Position;
import java.util.HashMap;
import java.util.Map;

public class BoardDto {

    private Map<String, String> values;

    private BoardDto(Map<String, String> values) {
        this.values = values;
    }

    public static BoardDto from(final Map<Position, Piece> board) {
        return new BoardDto(createValues(board));
    }

    private static Map<String, String> createValues(Map<Position, Piece> board) {
        Map<String, String> values = new HashMap<>();
        for (Position position : board.keySet()) {
            values.put(position.getKey(), createPieceKey(board.get(position)));
        }
        return values;
    }

    private static String createPieceKey(Piece piece) {
        return piece.getTeam() + "_" + piece.getSymbol();
    }

    public Map<String, String> getValues() {
        return values;
    }
}
