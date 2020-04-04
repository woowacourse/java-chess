package chess;

import chess.dao.ChessPieceDao;
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

        ChessWebService webService = new ChessWebService(new ChessPieceDao());
        Board board = new Board();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            boolean canContinue = webService.canContinue("guest");

            model.put("canContinue", canContinue);
            return render(model, "index.html");
        });

        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            webService.startNewGame(board);

            model.put("pieces", webService.convertPieces(board));
            model.put("turn", webService.turnMsg(board));
            model.put("white_score", webService.calculateScore(board, PieceColor.WHITE));
            model.put("black_score", webService.calculateScore(board, PieceColor.BLACK));
            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String source = req.queryParams("source");
            String target = req.queryParams("target");

            webService.move(board, source, target);
            boolean gameOver = webService.isGameOver(board);

            if (gameOver) {
                webService.deleteSaved();
                String winningMsg = webService.winningMsg(board);
                model.put("end", winningMsg);
                model.put("pieces", webService.convertPieces(board));
                return render(model, "index.html");
            }

            model.put("pieces", webService.convertPieces(board));
            model.put("turn", webService.turnMsg(board));
            model.put("white_score", webService.calculateScore(board, PieceColor.WHITE));
            model.put("black_score", webService.calculateScore(board, PieceColor.BLACK));
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
