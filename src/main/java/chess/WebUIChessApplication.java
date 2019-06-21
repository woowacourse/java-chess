package chess;

import java.util.HashMap;
import java.util.Map;

import chess.domain.ChessBoard;
import chess.domain.Team;
import chess.domain.Turn;
import chess.domain.utils.InputParser;
import chess.view.JsonTransformer;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class WebUIChessApplication {
    private static ChessBoard chessBoard;

    public static void main(String[] args) {
        staticFiles.location("/assets");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        // 게임을 처음 시작할 때 진입점
        get("/game_play", (req, res) -> {
            chessBoard = new ChessBoard();
            Map<String, Object> model = new HashMap<>();
            model.put("turn", chessBoard.getTurn());
            return render(model, "game_play.html");
        });

        post("/game_play", (req, res) -> {
            String source = req.queryParams("source");
            String target = req.queryParams("target");
            boolean isKingDead = chessBoard.move(InputParser.position(source), InputParser.position(target));
            if (isKingDead) {
                Map<String, Object> model = new HashMap<>();
                Turn winner = chessBoard.getTurn();
                model.put("message", "왕이 잡혔습니다.");
                model.put("winner", winner);
                return render(model, "result.html");
            }
            Map<String, Object> model = new HashMap<>();
            model.put("turn", chessBoard.getTurn());
            return render(model, "game_play.html");
        });

        post("/status", (req, res) -> {
            return chessBoard.getBoard();
        }, new JsonTransformer());

        get("/result", (req, res) -> {
            Double whiteScore = chessBoard.totalScore(Team.WHITE);
            Double blackScore = chessBoard.totalScore(Team.BLACK);
            HashMap<String, Object> model = new HashMap<>();
            model.put("whiteScore", whiteScore);
            model.put("blackScore", blackScore);
            return render(model, "result.html");
        });


        get("/end", (req, res) -> {
            HashMap<String, Object> model = new HashMap<>();
            return render(model, "end.html");
        });

        exception(RuntimeException.class, (e, req, res) -> {

            res.status(404);
            res.body(
                 "<h1> 에러 발생 </h1>" +
                 "<div>" + e.getMessage() + "</div>" +
                 "<button onclick=\"window.history.back()\">되돌아가기</button>"
            );
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
