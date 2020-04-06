package chess;

import chess.controller.ChessManager;
import chess.controller.command.Command;
import chess.controller.dto.ScoreDto;
import chess.controller.dto.Tile;
import chess.database.ChessBoardDao;
import chess.domain.piece.Team;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {

    private static ChessBoardDao chessBoardDao;

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

//        post("/playing", (req, res) -> {
//            Map<String, Object> model = new HashMap<>();
//            return render(model, "chessGame.html");
//        });

        //play new game
        get("/playing:false", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("chessPieces", tiles);
            model.put("currentTeam", currentTeam);
            return render(model, "chessGame.html");
        });

        //play last game
        get("/playing:true", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("chessPieces", tiles);
            model.put("playLastGame", "이전게임");
            model.put("currentTeam", currentTeam);
            return render(model, "chessGame.html");
        });

        //move source target
        post("/move", (req, res) -> {
            String cmd = req.queryParams("move_cmd");
            String errorMessage = null;
            try {
                Command.MOVE.apply(chessManager, cmd);
            } catch (Exception e) {
                errorMessage = "이동할 수 없는 곳입니다. 다시 입력해주세요.";
            }
            List<Tile> tiles2 = chessManager.getTileDto().getTiles();
            Team currentTeam2 = chessManager.getCurrentTeam();
            Map<String, Object> model = new HashMap<>();
            model.put("chessPieces", tiles2);
            model.put("currentTeam", currentTeam2);
            model.put("cmd", cmd);
            model.put("error", errorMessage);

            if (!chessManager.isPlaying()) {
                Team winner = chessManager.getWinner();
                chessManager.end();
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
            List<Tile> tiles4 = chessManager.getTileDto().getTiles();
            saveToDatabase(tiles4);
            Map<String, Object> model = new HashMap<>();
            return render(model, "chessGameEnd.html");
        });
    }

    public static void saveToDatabase(List<Tile> tiles) throws SQLException {
        chessBoardDao.getConnection();
        chessBoardDao = new ChessBoardDao();
        for (Tile tile : tiles) {
            chessBoardDao.addBoard(tile.getPosition().toString(), tile.getPiece().toString());
        }
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
