package chess.controller;

import static spark.Spark.get;
import static spark.Spark.internalServerError;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.controller.command.Command;
import chess.controller.command.CommandReader;
import chess.dao.CommandLogDao;
import chess.dbconnect.JDBCConnector;
import chess.domain.gamestatus.Finished;
import chess.domain.gamestatus.GameStatus;
import chess.domain.gamestatus.NothingHappened;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.score.Score;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIController {

    private static GameStatus gameStatus;
    private static CommandLogDao commandLogDao = new CommandLogDao(new JDBCConnector());

    public static void run() {
        try {
            runWithoutException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void runWithoutException() {
        staticFiles.location("/static");

        get("/", (request, response) -> loadRecords());

        post("/", WebUIController::moveAndRender);

        internalServerError(renderErrorPage());
    }

    private static String loadRecords() throws SQLException {
        gameStatus = new NothingHappened();
        gameStatus = gameStatus.start();

        executePastRequests();

        return refreshAndRender();
    }

    private static void executePastRequests() throws SQLException {
        List<String> commandLogs = commandLogDao.getAllByOldOneFirst();

        for (String commandLog : commandLogs) {
            Command command = CommandReader.of(commandLog);
            gameStatus = command.execute(gameStatus);
        }
    }

    private static String moveAndRender(Request request, Response response) throws SQLException {
        String requestCommand = "move " + request.queryParams("from-position")
            + " " + request.queryParams("to-position");
        Command command = CommandReader.of(requestCommand);
        gameStatus = command.execute(gameStatus);
        commandLogDao.add(requestCommand);

        return refreshAndRender();
    }

    private static String refreshAndRender() {
        if (gameStatus instanceof Finished) {
            return renderFinishedStatus();
        }
        return renderRunningStatus();
    }

    private static String renderRunningStatus() {
        Map<String, Object> model = getBoardModel();
        return render(model, "chess.html");
    }

    private static String renderFinishedStatus() {
        Map<String, Object> model = getBoardModel();
        Score score = gameStatus.scoring();

        model.put("winner", gameStatus.scoring().getWinner());

        return render(model, "concluded.html");
    }

    private static Map<String, Object> getBoardModel() {
        Map<Position, Piece> board = gameStatus.getBoard();
        Map<String, Object> model = new HashMap<>();

        for (Entry<Position, Piece> entry : board.entrySet()) {
            model.put(entry.getKey().toString(), entry.getValue());
        }
        model.put("empty-image", "image/peace/empty.png");

        return model;
    }

    private static String renderErrorPage() {
        return render(new HashMap<>(), "error.html");
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
