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

        get("/chessGame", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("cells", this.chessGameService.getCells());
            model.put("currentTeam", this.chessGameService.getCurrentTeam());
            model.put("blackScore", this.chessGameService.getBlackPieceScore());
            model.put("whiteScore", this.chessGameService.getWhitePieceScore());
            return render(model, "index.html");
        });

        get("/newChessGame", (req, res) -> {
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

            try {
                String source = req.queryParams("source");
                String target = req.queryParams("target");

                this.chessGameService.movePiece(source, target);

                if (this.chessGameService.isGameOver()) {
                    model.put("winner", this.chessGameService.getWinner());
                    model.put("loser", this.chessGameService.getLoser());
                    model.put("blackScore", this.chessGameService.getBlackPieceScore());
                    model.put("whiteScore", this.chessGameService.getWhitePieceScore());
                    this.chessGameService.endGame();

                    return render(model, "winner.html");
                }
            } catch (Exception e) {
                model.put("error", e.getMessage());
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
