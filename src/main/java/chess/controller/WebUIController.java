package chess.controller;

import static spark.Spark.get;
import static spark.Spark.internalServerError;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.controller.command.Command;
import chess.controller.command.CommandReader;
import chess.domain.gamestatus.GameStatus;
import chess.domain.gamestatus.NothingHappened;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIController {

    private static GameStatus gameStatus;

    public static void run() {
        try {
            runWithoutException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void runWithoutException() {
        staticFiles.location("/static");

        get("/", (request, response) -> initAndRender());

        post("/", WebUIController::moveAndRedirect);

        internalServerError(renderErrorPage());
    }

    private static String initAndRender() {
        gameStatus = new NothingHappened();
        gameStatus = gameStatus.start();
        return renderRefreshedChessBoard();
    }

    private static String moveAndRedirect(Request request, Response response) {
        String requestCommand = "move "
            + request.queryParams("from-position")
            + " "
            + request.queryParams("to-position");

        Command command = CommandReader.of(requestCommand);
        gameStatus = command.execute(gameStatus);

        return renderRefreshedChessBoard();
    }

    private static String renderRefreshedChessBoard() {

        Map<Position, Piece> board = gameStatus.getBoard();
        Map<String, Object> model = new HashMap<>();

        for (Entry<Position, Piece> entry : board.entrySet()) {
            model.put(entry.getKey().toString(), entry.getValue());
        }
        model.put("empty-image", "image/peace/empty.png");

        return render(model, "chess.html");
    }

    private static String renderErrorPage() {
        return render(new HashMap<>(), "error.html");
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
