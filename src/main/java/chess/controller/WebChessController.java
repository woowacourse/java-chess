package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.dto.RankDTO;
import chess.service.ChessService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    private ChessService chessService = new ChessService();

    public void run() {
        staticFiles.location("/static");

        displayHome();
        moveChessPiece();
        calculateEachTeamScore();
    }

    private void calculateEachTeamScore() {
        get("/status", (req, res)
                -> chessService.getBlackTeamScore() + "//" + chessService.getWhiteTeamScore()
        );
    }

    private void moveChessPiece() {
        post("/move", (req, res) -> {
            String[] positions = req.body().split("//");
            String srcPosition = positions[0];
            String dstPosition = positions[1];

            if (chessService.move(srcPosition, dstPosition)) {
                String winner = chessService.getWinnerTeam();
                chessService = new ChessService();
                return winner;
            }

            return "kingAlive";
        });
    }

    private void displayHome() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            List<RankDTO> board = chessService.getBoardStatus();
            model.put("board", board);

            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
