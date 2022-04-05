package chess.util;

import chess.Controller.dto.PieceDto;
import chess.Controller.dto.PiecesDto;
import chess.Controller.dto.ScoreDto;
import chess.Controller.dto.StateDto;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonParser {

    public static JSONObject makePiecesToJsonArray(final PiecesDto piecesDto) {
        final List<PieceDto> currentPieces = piecesDto.getPieces();
        JSONArray jsonArray = new JSONArray();
        for (PieceDto piece : currentPieces) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("position", piece.getPosition());
            jsonObject.put("piece_url", piece.getImageUrl());
            jsonObject.put("symbol", piece.getSymbol());
            jsonArray.add(jsonObject);
        }
        JSONObject responseJson = new JSONObject();
        responseJson.put("game_status", piecesDto.getGameStatus());
        responseJson.put("pieces", jsonArray);
        return responseJson;
    }

    public static JSONObject scoreToJson(final ScoreDto scoreDto, final StateDto stateDto) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("black_score", scoreDto.getBlackScore());
        jsonObject.put("white_score", scoreDto.getWhiteScore());
        jsonObject.put("game_status", stateDto.getState());
        return jsonObject;
    }

}
