package chess;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.controller.ChessWebController;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {

    private final static Gson gson = new Gson();

    public static void main(String[] args) {
        staticFiles.location("/static");
        ChessWebController webController = new ChessWebController();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "Chess.html");
        });

        post("/initial", (req, res) -> {
            Map<String, Object> submitData = new HashMap<>();
            Map<String, Object> initialChessBoard = new HashMap<>();
            Map<Position, Piece> startedBoard= webController.startedBoard();
            insertInitialBoard(initialChessBoard, startedBoard);

            submitData.put("turn", webController.getTurn());
            submitData.put("chessBoard", initialChessBoard);
            return gson.toJson(submitData);
        });

        post("/move", (req, res) -> {
            Map<String, Object> submitData = new HashMap<>();
            Map<String, Object> requestBody = gson.fromJson(req.body(), HashMap.class);
            String moveRawCommand = (String) requestBody.get("move");
            String moveResult = webController.move(moveRawCommand);
            submitData.put("isSuccess", moveResult);

            judgeEnd(webController, submitData);
            submitData.put("turn", webController.getTurn());
            return gson.toJson(submitData);
        });

        post("/grade", (req, res) -> {
            Map<String, Object> submitData = new HashMap<>();
            submitData.put("whiteScore", webController.whiteScore());
            submitData.put("blackScore", webController.blackScore());
            return gson.toJson(submitData);
        });

        post("/end", (req, res) -> {
            Map<String, Object> submitData = new HashMap<>();
            webController.end();
            return gson.toJson(submitData);
        });
    }

    private static void judgeEnd(ChessWebController webController,
        Map<String, Object> submitData) {
        if (webController.isEnd()) {
            submitData.put("winner", webController.winnerColor());
        }
    }

    private static void insertInitialBoard(Map<String, Object> initialChessBoard,
        Map<Position, Piece> startedBoard) {
        for (Map.Entry<Position, Piece> elem : startedBoard.entrySet()) {
            Position position = elem.getKey();
            Piece piece = elem.getValue();
            initialChessBoard.put(position.symbol(), piece.symbol());
        }
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}