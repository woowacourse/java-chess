package chess.controller;

import chess.service.ChessGameService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebChessController {
    private ChessGameService chessGameService;

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void run() {
        port(8080);
        staticFiles.location("/static");

        this.chessGameService = new ChessGameService();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/chess-game", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("cells", this.chessGameService.getCells());
            model.put("currentTeam", this.chessGameService.getCurrentTeam());
            model.put("blackScore", this.chessGameService.getBlackPieceScore());
            model.put("whiteScore", this.chessGameService.getWhitePieceScore());
            return render(model, "index.html");
        });

        get("/new-chess-game", (req, res) -> {
            this.chessGameService.setNewChessGame();

            Map<String, Object> model = new HashMap<>();
            model.put("cells", this.chessGameService.getCells());
            model.put("currentTeam", this.chessGameService.getCurrentTeam());
            model.put("blackScore", this.chessGameService.getBlackPieceScore());
            model.put("whiteScore", this.chessGameService.getWhitePieceScore());
            return render(model, "index.html");
        });

        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String source = req.queryParams("source");
            String target = req.queryParams("target");

            try {
                this.chessGameService.movePiece(source, target);
            } catch (IllegalArgumentException e) {
                model.put("error", e.getMessage());
            }

            if (this.chessGameService.isGameOver()) {
                model.put("winner", this.chessGameService.getWinner());
                model.put("loser", this.chessGameService.getLoser());
                model.put("blackScore", this.chessGameService.getBlackPieceScore());
                model.put("whiteScore", this.chessGameService.getWhitePieceScore());
                this.chessGameService.endGame();

                return render(model, "winner.html");
            }

            model.put("cells", this.chessGameService.getCells());
            model.put("currentTeam", this.chessGameService.getCurrentTeam());
            model.put("blackScore", this.chessGameService.getBlackPieceScore());
            model.put("whiteScore", this.chessGameService.getWhitePieceScore());

            this.chessGameService.proceedGame();
            return render(model, "index.html");
        });

    }
}
