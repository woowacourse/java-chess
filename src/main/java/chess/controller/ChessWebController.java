package chess.controller;

import chess.controller.command.Command;
import chess.database.ChessDao;
import chess.database.MySqlChessDao;
import chess.web.ChessCommand;
import chess.web.MoveResponse;
import chess.web.StartResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class ChessWebController {
    private static final String MOVE_ERROR_MESSAGE = "이동할 수 없는 곳입니다. 다시 입력해주세요";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private ChessManager chessManager;
    private ChessDao chessDao;
    private MoveResponse moveResponse;

    public void play() {
        //start
        get("/start", (req, res) -> {
            chessManager = new ChessManager();
            chessDao = new MySqlChessDao();
            moveResponse = new MoveResponse(chessManager);

            return render(new StartResponse(chessManager, chessDao).getModel(), "chessGameStart.html");
        });

        //play last game
        get("/playing/lastGame", (req, res) -> {
            List<ChessCommand> commands = chessDao.selectCommands();
            for (ChessCommand command : commands) {
                Command.MOVE.apply(chessManager, command.get());
            }
            return render(moveResponse.getModel(), "chessGame.html");
        });

        //play new game
        get("/playing/newGame", (req, res) -> {
            initializeDatabase();
            return render(moveResponse.getModel(), "chessGame.html");
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

            if (!chessManager.isPlaying()) {
                initializeDatabase();
            }
            return GSON.toJson(moveResponse.getModel());
        });

        //end
        get("/end", (req, res) -> {
            chessManager.end();
            Map<String, Object> model = new HashMap<>();
            return render(model, "chessGameEnd.html");
        });
    }

    private void initializeDatabase() throws SQLException {
        chessDao.clearCommands();
    }

    public void saveToDatabase(String command) throws SQLException {
        chessDao.addCommand(new ChessCommand(command));
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
