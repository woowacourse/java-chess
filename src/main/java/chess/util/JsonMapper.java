package chess.util;

import chess.dto.response.BoardDto;
import chess.dto.response.PieceColorDto;
import chess.dto.response.PieceDto;
import chess.dto.response.PositionDto;
import chess.dto.response.ScoreResultDto;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class JsonMapper {
    private static final Gson GSON = new Gson();

    private static final String PIECE_NAME_FORMAT = "%s_%s";
    private static final String WHITE_PIECE_COLOR_NAME = "WHITE";
    private static final String BLACK_PIECE_COLOR_NAME = "BLACK";
    private static final String EMPTY_PIECE_COLOR_NAME = "null";

    public static String boardDtoToJson(BoardDto boardDto) {
        Map<String, String> coordinateAndPiece = new HashMap<>();
        for (Entry<PositionDto, PieceDto> entrySet : boardDto.getValue().entrySet()) {
            String coordinate = entrySet.getKey().toPosition().toCoordinate();
            String piece = generatePieceName(entrySet.getValue());
            coordinateAndPiece.put(coordinate, piece);
        }

        return GSON.toJson(coordinateAndPiece);
    }

    private static String generatePieceName(PieceDto pieceDto) {
        String pieceName = pieceDto.getPieceType().name();
        String pieceColorName = pieceDto.getPieceColor().name();
        return String.format(PIECE_NAME_FORMAT, pieceName, pieceColorName);
    }

    public static String turnToJson(PieceColorDto pieceColorDto) {
        String color = getColorFromPieceColorDto(pieceColorDto);
        return generatePieceColorJson(color);
    }

    public static String winnerToJson(Optional<PieceColorDto> pieceColorDto) {
        String color = pieceColorDto.map(JsonMapper::getColorFromPieceColorDto)
                .orElse(EMPTY_PIECE_COLOR_NAME);
        return generatePieceColorJson(color);
    }

    private static String generatePieceColorJson(String color) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("pieceColor", color);
        return GSON.toJson(jsonObject);
    }

    public static String getColorFromPieceColorDto(PieceColorDto pieceColorDto) {
        if (pieceColorDto.isWhiteTurn()) {
            return WHITE_PIECE_COLOR_NAME;
        }
        return BLACK_PIECE_COLOR_NAME;
    }

    public static String scoreResultDtoToJson(ScoreResultDto scoreResultDto) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("white", scoreResultDto.getWhiteScore());
        jsonObject.addProperty("black", scoreResultDto.getBlackScore());

        return GSON.toJson(jsonObject);
    }
}
