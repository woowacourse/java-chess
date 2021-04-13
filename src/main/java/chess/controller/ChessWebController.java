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

            return render(submitData, "Chess.html");
        });

        post("/load", (req,res) -> {
            webChessService.loadBoard();
            Map<String, Object> submitData = new HashMap<>();

            submitData.put("turn", webChessService.getTurn());
            submitData.put("chessBoard", submitLoadBoard(webChessService));
            return GSON.toJson(submitData);
        });

        post("/initial", (req, res) -> {
            webChessService.initiateGame();
            Map<String, Object> submitData = new HashMap<>();

            submitData.put("turn", webChessService.getTurn());
            submitData.put("chessBoard", submitLoadBoard(webChessService));
            return GSON.toJson(submitData);
        });

        post("/move", (req, res) -> {
            Map<String, Object> requestBody = GSON.fromJson(req.body(), HashMap.class);
            String moveRawCommand = (String) requestBody.get("move");
            Map<String, String> submitData = webChessService.move(moveRawCommand);
            return GSON.toJson(submitData);
        });

        post("/grade", (req, res) -> {
            Map<String, String> submitData = webChessService.scores();
            return GSON.toJson(submitData);
        });

        post("/end", (req, res) -> {
            Map<String, Object> submitData = new HashMap<>();
            webChessService.end();
            return GSON.toJson(submitData);
        });
    }

    private Map<String, String> submitLoadBoard(WebChessService webChessService) {
        Map<String, String> submitBoard = new HashMap<>();
        Map<Position, Piece> loadBoard = webChessService.loadBoard();
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
