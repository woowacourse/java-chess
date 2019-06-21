package chess;

import chess.domain.*;
import chess.domain.utils.InputParser;
import chess.view.JsonTransformer;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static ChessBoard chessBoard;
    public static void main(String[] args) {
        staticFiles.location("/assets");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/game_play", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessBoard = new ChessBoard();
            return render(model, "game_play.html");
        });

        post("/game_play", (req, res) -> {
            String source = req.queryParams("source");
            String target = req.queryParams("target");
            boolean isKingDead = chessBoard.move(InputParser.position(source), InputParser.position(target));
            if (isKingDead) {
                Map<String, Object> model = new HashMap<>();
                Team winner = chessBoard.getWinner();
                model.put("message", "왕이 잡혔습니다.");
                model.put("winner", winner);
                return render(model, "result.html");
            }
            Map<String, Object> model = new HashMap<>();
            return render(model, "game_play.html");
        });

        post("/status", (req, res) -> chessBoard.getBoard2(), new JsonTransformer());

        get("/result", (req, res) -> {
            Double whiteScore = chessBoard.totalScore(Team.WHITE);
            Double blackScore = chessBoard.totalScore(Team.BLACK);
            HashMap<String, Object> model = new HashMap<>();
            model.put("whiteScore", whiteScore);
            model.put("blackScore", blackScore);
            return render(model, "result.html");
        });

//        exception(RuntimeException.class, (e, req, res) -> {
//            res.redirect("/game_play");
//        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
