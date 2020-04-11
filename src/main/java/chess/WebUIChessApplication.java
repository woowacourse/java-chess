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

        post("/ready", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String gameId = req.queryParams("game_id");
            boolean canResume = webService.canResume(gameId);

            model.put("game_id", gameId);
            model.put("canResume", canResume);
            return render(model, "game_room.html");
        });

        post("/play", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String gameId = req.queryParams("game_id");

            webService.startNewGame(board, gameId);

            model.put("game_id", gameId);
            model.put("pieces", webService.convertPieces(board));
            model.put("turn", webService.turnMsg(board));
            model.put("white_score", webService.calculateScore(board, PieceColor.WHITE));
            model.put("black_score", webService.calculateScore(board, PieceColor.BLACK));
            return render(model, "game_room.html");
        });

        post("/resume", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String gameId = req.queryParams("game_id");

            webService.resumeGame(board, gameId);

            model.put("game_id", gameId);
            model.put("pieces", webService.convertPieces(board));
            model.put("turn", webService.turnMsg(board));
            model.put("white_score", webService.calculateScore(board, PieceColor.WHITE));
            model.put("black_score", webService.calculateScore(board, PieceColor.BLACK));
            return render(model, "game_room.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String gameId = req.queryParams("game_id");
            String source = req.queryParams("source");
            String target = req.queryParams("target");

            webService.move(board, gameId, source, target);

            boolean gameOver = webService.isGameOver(board);
            if (gameOver) {
                webService.deleteSaved(gameId);
                String winningMsg = webService.winningMsg(board);
                model.put("game_id", gameId);
                model.put("end", winningMsg);
                model.put("pieces", webService.convertPieces(board));
                return render(model, "game_room.html");
            }

            model.put("game_id", gameId);
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
