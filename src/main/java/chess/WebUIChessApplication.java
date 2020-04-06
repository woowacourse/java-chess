package chess;

import chess.controller.ChessManager;
import chess.controller.command.Command;
import chess.controller.dto.ScoreDto;
import chess.controller.dto.Tile;
import chess.database.ChessCommandDao;
import chess.domain.piece.Team;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {

    private static ChessCommandDao chessCommandDao = new ChessCommandDao();
    private static ChessManager chessManager;

    public static void main(String[] args) {
        staticFiles.location("/public");

        //start
        get("/start", (req, res) -> {
            chessManager = new ChessManager();
            Map<String, Object> model = new HashMap<>();
            if (chessCommandDao.selectCommands() != null) {
                model.put("haveLastGameRecord", "true");
            }
            return render(model, "chessGameStart.html");
        });

        //play last game
        get("/playing:lastGame", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<String> commands = chessCommandDao.selectCommands();

            for (String command : commands) {
                Command.MOVE.apply(chessManager, command);
            }
            List<Tile> tiles = chessManager.getTileDto().getTiles();
            Team currentTeam = chessManager.getCurrentTeam();
            model.put("chessPieces", tiles);
            model.put("currentTeam", currentTeam);
            return render(model, "chessGame.html");
        });

        //play new game
        get("/playing:newGame", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            initializeDatabase();
            List<Tile> tiles = chessManager.getTileDto().getTiles();
            Team currentTeam = chessManager.getCurrentTeam();
            model.put("chessPieces", tiles);
            model.put("currentTeam", currentTeam);
            return render(model, "chessGame.html");
        });

        //move source target
        post("/move", (req, res) -> {
            String cmd = req.queryParams("move_cmd");
            String errorMessage = null;
            try {
                Command.MOVE.apply(chessManager, cmd);
                saveToDatabase(cmd);
            } catch (Exception e) {
                errorMessage = "이동할 수 없는 곳입니다. 다시 입력해주세요.";
            }
            List<Tile> tiles = chessManager.getTileDto().getTiles();
            Team currentTeam = chessManager.getCurrentTeam();
            Map<String, Object> model = new HashMap<>();
            model.put("chessPieces", tiles);
            model.put("currentTeam", currentTeam);
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
            List<Tile> tiles = chessManager.getTileDto().getTiles();
            Team currentTeam = chessManager.getCurrentTeam();
            ScoreDto scoreDto = new ScoreDto(chessManager.calculateScore());
            String score = scoreDto.getScore();
            Map<String, Object> model = new HashMap<>();
            model.put("chessPieces", tiles);
            model.put("currentTeam", currentTeam);
            model.put("score", score);
            return render(model, "chessGame.html");
        });

        //end
        get("/end", (req, res) -> {
            chessManager.end();
            Map<String, Object> model = new HashMap<>();
            return render(model, "chessGameEnd.html");
        });
    }

    private static void initializeDatabase() throws SQLException {
        chessCommandDao.deleteCommands();
    }

    public static void saveToDatabase(String command) throws SQLException {
        chessCommandDao.addCommand(command);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
