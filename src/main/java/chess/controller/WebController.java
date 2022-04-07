package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.dao.BoardDao;
import chess.dao.GameDao;
import chess.domain.position.Position;
import chess.dto.request.UpdatePiecePositionDto;
import chess.dto.response.BoardDto;
import chess.dto.response.PieceColorDto;
import chess.dto.response.PieceDto;
import chess.dto.response.PositionDto;
import chess.dto.response.ScoreResultDto;
import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import spark.Request;

public class WebController {

    private final static Gson GSON = new Gson();

    private static final String GAME_ID = "game-id"; // TODO: 여러 게임 방 기능 구현시 제거

    private final ChessService chessService;

    public WebController() {
        this.chessService = ChessService.of(new GameDao(), new BoardDao());
    }

    public void run() {
        get("/board", (req, res) -> getBoard());
        get("/turn", (req, res) -> getTurn());
        get("/score", (req, res) -> getScore());
        get("/winner", (req, res) -> getWinner());
        post("/move", (req, res) -> movePiece(req));
        post("/initialize", (req, res) -> initialize());
    }

    private String getBoard() {
        BoardDto boardDto = chessService.getBoardDto(GAME_ID);
        return boardDtoToJson(boardDto);
    }

    private String getTurn() {
        PieceColorDto pieceColorDto = chessService.getCurrentTurn(GAME_ID);
        return pieceColorDtoToJson(pieceColorDto);
    }

    private String getScore() {
        ScoreResultDto scoreResultDto = chessService.getScore(GAME_ID);
        return scoreResultDtoToJson(scoreResultDto);
    }

    private String getWinner() {
        PieceColorDto pieceColorDto = chessService.getWinColor(GAME_ID);
        return pieceColorDtoToJson(pieceColorDto);
    }

    private String movePiece(Request req) {
        try {
            String request = req.body();
            // TODO: 리팩토링
            // TODO: 입력값을 파싱하는게 컨트롤러의 책임일까?

            String fromText = request.split("from=")[1].split("&")[0];
            String toText = request.split("to=")[1];

            Position from = Position.from(fromText);
            Position to = Position.from(toText);

            chessService.movePiece(UpdatePiecePositionDto.of(GAME_ID, from, to));
        } catch (IllegalStateException e) {
            return e.getMessage();
        }

        return "success";
    }

    // TODO: Exception 으로 catch 하면 안됨
    private String initialize() {
        try {
            chessService.initializeGame(GAME_ID);
        } catch (Exception e) {
            return "fail";
        }
        return "success";
    }

    private String boardDtoToJson(BoardDto boardDto) {
        Map<String, String> coordinateAndPiece = new HashMap<>();
        for (Entry<PositionDto, PieceDto> entrySet : boardDto.getValue().entrySet()) {
            String coordinate = entrySet.getKey().toPosition().toCoordinate();
            String piece = entrySet.getValue().getPieceType().name() + "_" + entrySet.getValue().getPieceColor().name();

            coordinateAndPiece.put(coordinate, piece);
        }

        return GSON.toJson(coordinateAndPiece);
    }

    // TODO: null 처리 개선
    private String pieceColorDtoToJson(PieceColorDto pieceColorDto) {
        JsonObject jsonObject = new JsonObject();

        if (Objects.isNull(pieceColorDto)) {
            jsonObject.addProperty("pieceColor", "null");
            return GSON.toJson(jsonObject);
        }

        if (pieceColorDto.isWhiteTurn()) {
            jsonObject.addProperty("pieceColor", "WHITE");
            return GSON.toJson(jsonObject);
        }

        jsonObject.addProperty("pieceColor", "BLACK");
        return GSON.toJson(jsonObject);
    }

    private String scoreResultDtoToJson(ScoreResultDto scoreResultDto) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("white", scoreResultDto.getWhiteScore());
        jsonObject.addProperty("black", scoreResultDto.getBlackScore());

        return GSON.toJson(jsonObject);
    }
}
