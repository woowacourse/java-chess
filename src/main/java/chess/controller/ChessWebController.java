package chess.controller;

import chess.controller.command.Command;
import chess.controller.dto.ScoreDto;
import chess.database.ChessCommand;
import chess.database.ChessCommandDao;
import chess.domain.piece.Team;
import chess.web.ChessGameResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class ChessWebController {
    private static final String MOVE_ERROR_MESSAGE = "이동할 수 없는 곳입니다. 다시 입력해주세요";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final ChessCommandDao chessCommandDao = new ChessCommandDao();
    private ChessManager chessManager;
    private ChessGameResponse chessGameResponse;

    public void play() {
        //start
        get("/start", (req, res) -> {
            chessManager = new ChessManager();
            chessGameResponse = new ChessGameResponse(chessManager);
            Map<String, Object> model = new HashMap<>();
            if (!chessCommandDao.selectCommands().isEmpty()) {
                model.put("haveLastGameRecord", "true");
            }
            model.put("chessPieces", chessGameResponse.getTiles());
            model.put("currentTeam", chessGameResponse.getCurrentTeam());
            return render(model, "chessGameStart.html");
        });

        //play last game
        get("/playing/lastGame", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<String> commands = chessCommandDao.selectCommands();

            for (String command : commands) {
                Command.MOVE.apply(chessManager, command);
            }
            model.put("chessPieces", chessGameResponse.getTiles());
            model.put("currentTeam", chessGameResponse.getCurrentTeam());
            return render(model, "chessGame.html");
        });

        //play new game
        get("/playing/newGame", (req, res) -> {
            initializeDatabase();
            Map<String, Object> model = new HashMap<>();
            model.put("chessPieces", chessGameResponse.getTiles());
            model.put("currentTeam", chessGameResponse.getCurrentTeam());
            return render(model, "chessGame.html");
        });

        //move source target
        post("/playing/move", (req, res) -> {
            String source = req.headers("source");
            String target = req.headers("target");
            String command = String.join(" ", new String[]{"move", source, target});

            try {
                Command.MOVE.apply(chessManager, command);
                saveToDatabase(command);
            } catch (Exception e) {
                throw new IllegalArgumentException(MOVE_ERROR_MESSAGE);
            }
            Map<String, Object> model = new HashMap<>();
            model.put("chessPieces", chessGameResponse.getTiles());
            model.put("currentTeam", chessGameResponse.getCurrentTeam());

            if (!chessManager.isPlaying()) {
                Team winner = chessManager.getWinner();
                chessManager.end();
                model.put("winner", winner);
                initializeDatabase();
            } else {
                model.put("winner", null);
            }
            return GSON.toJson(model);
        });

        //TODO: tojson으로 변경
        post("/playing/status", (req, res) -> {
            ScoreDto scoreDto = new ScoreDto(chessManager.calculateScore());
            String score = scoreDto.getScore();
            Map<String, Object> model = new HashMap<>();
            model.put("chessPieces", chessGameResponse.getTiles());
            model.put("currentTeam", chessGameResponse.getCurrentTeam());
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

    private void initializeDatabase() throws SQLException {
        chessCommandDao.deleteCommands();
    }

    public void saveToDatabase(String command) throws SQLException {
        chessCommandDao.addCommand(new ChessCommand(command));
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
