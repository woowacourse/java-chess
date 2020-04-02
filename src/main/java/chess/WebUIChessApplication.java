package chess;

import chess.controller.ChessManager;
import chess.controller.command.Command;
import chess.controller.dto.ScoreDto;
import chess.controller.dto.Tile;
import chess.domain.piece.Team;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/public");

        ChessManager chessManager = new ChessManager();
        List<Tile> tiles = chessManager.getTileDto().getTiles();
        Team currentTeam = chessManager.getCurrentTeam();

        //start
        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "chessGameStart.html");
        });

        post("/playing", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("chessPieces", tiles);
            model.put("currentTeam", currentTeam);
            return render(model, "chessGame.html");
        });

        //move source target
        post("/move", (req, res) -> {
            String cmd = req.queryParams("move_cmd");
            Command.MOVE.apply(chessManager, cmd);
            List<Tile> tiles2 = chessManager.getTileDto().getTiles();
            Team currentTeam2 = chessManager.getCurrentTeam();
            Map<String, Object> model = new HashMap<>();
            model.put("chessPieces", tiles2);
            model.put("currentTeam", currentTeam2);
            model.put("cmd", cmd);

            if (!chessManager.isPlaying()) {
                Team winner = chessManager.getWinner();
                model.put("winner", winner);
            }

            return render(model, "chessGame.html");
        });

        //status
        post("/status", (req, res) -> {
            List<Tile> tiles3 = chessManager.getTileDto().getTiles();
            Team currentTeam3 = chessManager.getCurrentTeam();
            ScoreDto scoreDto = new ScoreDto(chessManager.calculateScore());
            String score = scoreDto.getScore();
            Map<String, Object> model = new HashMap<>();
            model.put("chessPieces", tiles3);
            model.put("currentTeam", currentTeam3);
            model.put("score", score);
            return render(model, "chessGame.html");
        });

        //end
        post("/end", (req, res) -> {
            chessManager.end();
            Map<String, Object> model = new HashMap<>();
            return render(model, "chessGameEnd.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
