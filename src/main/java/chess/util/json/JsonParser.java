package chess.util.json;

import chess.Controller.dto.PiecesDto;
import chess.Controller.dto.ScoreDto;
import chess.util.json.strategy.PiecesDtoExclusiveStrategy;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonParser {

    public static String getPiecesAndGameStatus(final PiecesDto piecesDto) {
        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return gson.toJson(piecesDto);
    }

    public static String makePiecesToJsonArray(final PiecesDto piecesDto) {
        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .addSerializationExclusionStrategy(new PiecesDtoExclusiveStrategy())
                .create();
        return gson.toJson(piecesDto);
    }

    public static String scoreToJson(final ScoreDto scoreDto) {
        final Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        return gson.toJson(scoreDto);
    }

}
