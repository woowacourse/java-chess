package chess;

import chess.dao.ChessPieceDao;
import chess.dao.MoveHistoryDao;
import chess.domains.board.Board;
import chess.domains.piece.PieceColor;
import chess.service.ChessWebService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/");

        ChessWebService webService = new ChessWebService(new ChessPieceDao(), new MoveHistoryDao());
        Board board = new Board();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/enter", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String user_id = req.queryParams("user_id");
            boolean canResume = webService.canResume(user_id);

            model.put("user_id", user_id);
            model.put("canResume", canResume);
            return render(model, "game_room.html");
        });

        post("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String user_id = req.queryParams("user_id");

            webService.startNewGame(board, user_id);

            model.put("user_id", user_id);
            model.put("pieces", webService.convertPieces(board));
            model.put("turn", webService.turnMsg(board));
            model.put("white_score", webService.calculateScore(board, PieceColor.WHITE));
            model.put("black_score", webService.calculateScore(board, PieceColor.BLACK));
            return render(model, "game_room.html");
        });

        post("/resume", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String user_id = req.queryParams("user_id");

            webService.resumeGame(board, user_id);

            model.put("user_id", user_id);
            model.put("pieces", webService.convertPieces(board));
            model.put("turn", webService.turnMsg(board));
            model.put("white_score", webService.calculateScore(board, PieceColor.WHITE));
            model.put("black_score", webService.calculateScore(board, PieceColor.BLACK));
            return render(model, "game_room.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String user_id = req.queryParams("user_id");
            String source = req.queryParams("source");
            String target = req.queryParams("target");

            webService.move(board, user_id, source, target);

            boolean gameOver = webService.isGameOver(board);
            if (gameOver) {
                webService.deleteSaved(user_id);
                String winningMsg = webService.winningMsg(board);
                model.put("user_id", user_id);
                model.put("end", winningMsg);
                model.put("pieces", webService.convertPieces(board));
                return render(model, "game_room.html");
            }

            model.put("user_id", user_id);
            model.put("pieces", webService.convertPieces(board));
            model.put("turn", webService.turnMsg(board));
            model.put("white_score", webService.calculateScore(board, PieceColor.WHITE));
            model.put("black_score", webService.calculateScore(board, PieceColor.BLACK));
            return render(model, "game_room.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
