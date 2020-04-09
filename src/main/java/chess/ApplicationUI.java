package chess;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.model.domain.board.ChessGame;
import chess.model.domain.piece.Color;
import chess.model.dto.MoveDto;
import chess.model.dto.PromotionTypeDto;
import chess.model.dto.ResultDto;
import chess.model.dto.SourceDto;
import chess.model.service.ChessGameService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class ApplicationUI {

    public static void main(String[] args) {
        ChessGame chessGame = new ChessGame();
        ChessGameService chessGameService = ChessGameService.getInstance();
        Gson gson = new Gson();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "/start.html");
        });

        post("/game", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Map<Color, String> userNames = getUserNames(req);
            model.put("gameId", chessGameService.createChessGame(1, userNames));
            return render(model, "/game.html");
        });

        post("/followGame", (req, res) -> {
            Map<Color, String> userNames = new HashMap<>();
            userNames.put(Color.BLACK, "BLACK");
            userNames.put(Color.WHITE, "WHITE");
            Map<String, Object> model = new HashMap<>();
            model.put("gameId", chessGameService.getIdBefore(1));
            return render(model, "/game.html");
        });

        post("/move", (req, res) -> {
            MoveDto moveDTO = gson.fromJson(req.body(), MoveDto.class);
            return new Gson().toJson(chessGameService.move(moveDTO));
        });

        post("/path", (req, res) -> {
            SourceDto sourceDto = gson.fromJson(req.body(), SourceDto.class);
            return new Gson().toJson(chessGameService.getPath(sourceDto));
        });

        post("/promotion", (req, res) -> {
            PromotionTypeDto promotionTypeDTO = gson.fromJson(req.body(), PromotionTypeDto.class);
            return new Gson().toJson(chessGameService.promotion(promotionTypeDTO));
        });

        post("/board", (req, res) -> {
            MoveDto moveDTO = gson.fromJson(req.body(), MoveDto.class);
            return new Gson().toJson(chessGameService.loadChessGame(moveDTO.getGameId()));
        });

        get("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ResultDto resultDTO = new ResultDto(chessGame.getTeamScore());
            model.put("winner", resultDTO.getWinner());
            model.put("blackScore", resultDTO.getBlackScore());
            model.put("whiteScore", resultDTO.getWhiteScore());
            return render(model, "/end.html");
        });
    }

    private static Map<Color, String> getUserNames(Request req) {
        Map<Color, String> userNames = new HashMap<>();
        userNames.put(Color.BLACK, getUserName(req.queryParams("BlackName"), "BLACK"));
        userNames.put(Color.WHITE, getUserName(req.queryParams("WhiteName"), "WHITE"));
        return userNames;
    }

    private static String getUserName(String inputName, String defaultName) {
        if (inputName == null || inputName.trim().isEmpty()) {
            return defaultName;
        }
        return inputName;
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
