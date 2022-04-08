package chess.util.json.strategy;

import chess.Controller.dto.PiecesDto;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class PiecesDtoExclusiveStrategy implements ExclusionStrategy {
    @Override
    public boolean shouldSkipField(FieldAttributes f) {
        if (f.getDeclaredClass() == PiecesDto.class && f.getName().equals("pieces")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldSkipClass(Class<?> clazz) {
        return false;
    }
}
