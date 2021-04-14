package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.service.WebChessService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessWebController {

    private final WebChessService webChessService;
    private final Gson GSON;

    public ChessWebController() {
        this.webChessService = new WebChessService();
        this.GSON = new Gson();
    }

    public void run() {

        staticFiles.location("/static");

        get("/", (req, res) -> {
           Map<String, Object> submitData = new HashMap<>();
           return render(submitData, "Index.html");
        });

        get("/allPlayer", (req, res) -> {
            Map<String, Object> submitData = new HashMap<>();
            submitData.put("players", webChessService.loadPlayers());
            return GSON.toJson(submitData);
        });

        get("/play/:index", (req, res) -> {
            Map<String, Object> submitData = new HashMap<>();
            return render(submitData, "Chess.html");
        });

        post("/play/load", (req,res) -> {
            Map<String, Object> requestBody = GSON.fromJson(req.body(), HashMap.class);
            int gameNumber = Integer.parseInt((String) requestBody.get("index"));

            webChessService.loadBoard(gameNumber);
            Map<String, Object> submitData = new HashMap<>();

            submitData.put("turn", webChessService.getTurn(gameNumber));
            submitData.put("chessBoard", submitLoadBoard(webChessService, gameNumber));
            return GSON.toJson(submitData);
        });

        post("/play/initial", (req, res) -> {
            Map<String, Object> requestBody = GSON.fromJson(req.body(), HashMap.class);
            int gameNumber = Integer.parseInt((String) requestBody.get("index"));

            webChessService.initiateGame(gameNumber);
            Map<String, Object> submitData = new HashMap<>();

            submitData.put("turn", webChessService.getTurn(gameNumber));
            submitData.put("chessBoard", submitLoadBoard(webChessService, gameNumber));
            return GSON.toJson(submitData);
        });

        post("/play/move", (req, res) -> {
            Map<String, Object> requestBody = GSON.fromJson(req.body(), HashMap.class);
            int gameNumber = Integer.parseInt((String) requestBody.get("index"));
            String moveRawCommand = (String) requestBody.get("move");

            Map<String, String> submitData = webChessService.move(moveRawCommand, gameNumber);
            return GSON.toJson(submitData);
        });

        post("/play/grade", (req, res) -> {
            Map<String, Object> requestBody = GSON.fromJson(req.body(), HashMap.class);
            int gameNumber = Integer.parseInt((String) requestBody.get("index"));

            Map<String, String> submitData = webChessService.scores(gameNumber);
            return GSON.toJson(submitData);
        });

        post("/play/end", (req, res) -> {
            Map<String, Object> requestBody = GSON.fromJson(req.body(), HashMap.class);
            int gameNumber = Integer.parseInt((String) requestBody.get("index"));

            Map<String, Object> submitData = new HashMap<>();
            webChessService.end(gameNumber);
            return GSON.toJson(submitData);
        });

        post("/addPlayer", (req, res)-> {
            Map<String, Object> submitData = new HashMap<>();
            Map<String, Object> requestBody = GSON.fromJson(req.body(), HashMap.class);
            String rawPlayerName = (String) requestBody.get("name");
            submitData.put("result", webChessService.addPlayer(rawPlayerName));
            return GSON.toJson(submitData);
        });
    }

    private Map<String, String> submitLoadBoard(WebChessService webChessService, int index) {
        Map<String, String> submitBoard = new HashMap<>();
        Map<Position, Piece> loadBoard = webChessService.loadBoard(index);
        for (Map.Entry<Position, Piece> elem : loadBoard.entrySet()) {
            Position position = elem.getKey();
            Piece piece = elem.getValue();
            submitBoard.put(position.symbol(), piece.symbol());
        }
        return submitBoard;
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
