package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.domain.GameCommand;
import chess.domain.piece.Color;
import chess.domain.piece.generator.NormalPiecesGenerator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {

    public void run() {
         final ChessGame chessGame = new ChessGame(new ChessBoard(new NormalPiecesGenerator()));

         renderMain(chessGame);
         renderStart(chessGame);
         renderMove(chessGame);
         renderStatus(chessGame);
         renderEnd(chessGame);
    }

    private void renderMain(ChessGame chessGame) {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.putAll(chessGame.chessBoardToMap());

            if (chessGame.isEndGameByPiece()) {
                model.put("winner", chessGame.getWinner() + " 승리!!");
                return render(model, "index.html");
            }

            return render(model, "index.html");
        });
    }

    private void renderStart(ChessGame chessGame) {
        get("/start", (req, res) -> {
            chessGame.init(new ChessBoard(new NormalPiecesGenerator()));
            Map<String, Object> model = new HashMap<>();
            model.putAll(chessGame.chessBoardToMap());


            try {
                chessGame.playGameByCommand(new GameCommand("start"));
            } catch (Exception e) {
                return renderErrorMessage(e.getMessage(), chessGame);
            }

            return render(model, "index.html");
        });
    }

    private void renderMove(ChessGame chessGame) {
        post("/move", (req, res) -> {
            final String from = req.queryParams("from").toLowerCase(Locale.ROOT);
            final String to = req.queryParams("to").toLowerCase(Locale.ROOT);

            try {
                chessGame.playGameByCommand(new GameCommand("move", from, to));
            } catch (Exception e) {
                return renderErrorMessage(e.getMessage(), chessGame);
            }

            res.redirect("/");
            return null;
        });
    }

    private void renderStatus(ChessGame chessGame) {
        get("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.putAll(chessGame.chessBoardToMap());

            try {
                chessGame.playGameByCommand(new GameCommand("status"));
            } catch (Exception e) {
                return renderErrorMessage(e.getMessage(), chessGame);
            }

            double whiteScore = chessGame.calculateScore(Color.WHITE);
            double blackScore = chessGame.calculateScore(Color.BLACK);
            model.put("white", "WHITE : " + whiteScore);
            model.put("black", "BLACK : " + blackScore);

            return render(model, "index.html");
        });
    }

    private void renderEnd(ChessGame chessGame) {
        get("/end", (req, res) -> {
            try {
                chessGame.playGameByCommand(new GameCommand("end"));
            } catch (Exception e) {
                return renderErrorMessage(e.getMessage(), chessGame);
            }
            res.redirect("/");
            return null;
        });
    }

    private static String renderErrorMessage(String errorMessage, ChessGame chessGame) {
        Map<String, Object> model = new HashMap<>();
        model.putAll(chessGame.chessBoardToMap());
        model.put("error", errorMessage);
        return render(model, "index.html");
    }


    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

}
