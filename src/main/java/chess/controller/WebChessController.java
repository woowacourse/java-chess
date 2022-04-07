package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.dao.ChessDAO;
import chess.dto.RankDTO;
import chess.service.ChessService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    private final ChessService chessService = new ChessService(new ChessDAO());

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public void run() {
        staticFiles.location("/static");

        displayHome();
        moveChessPiece();
        calculateEachTeamScore();
        endChessGame();
    }

    private void displayHome() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            List<RankDTO> board = chessService.getBoardStatus();
            model.put("board", board);
            model.put("team", chessService.getTeam());

            return render(model, "index.html");
        });
    }

    private void moveChessPiece() {
        post("/move", (req, res) -> {
            String[] positions = req.body().split("//");
            String srcPosition = positions[0];
            String dstPosition = positions[1];

            boolean isKingDead = chessService.move(srcPosition, dstPosition);
            if (isKingDead) {
                String winner = chessService.getWinnerTeam();
                chessService.restartChessGame();
                return winner;
            }

            return "kingAlive";
        });
    }

    private void calculateEachTeamScore() {
        get("/status", (req, res)
                -> chessService.getWhiteTeamScore() + "//" + chessService.getBlackTeamScore()
        );
    }

    private void endChessGame() {
        get("/end", (req, res) -> {
            chessService.restartChessGame();
            res.redirect("/");
            return null;
        });
    }
}
