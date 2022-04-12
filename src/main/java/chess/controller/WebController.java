package chess.controller;

import chess.db.ChessGameRepository;
import chess.domain.ChessGame;
import chess.web.Response;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class WebController {

    private static final int PORT = 8081;
    private static final String REQ_BODY_EQUALS = ":|=";
    private static final String EMPTY = "";
    private static final int VALUE = 1;
    private static final String CHESS_TEMPLATE_PATH = "chessGame.html";

    private final ChessGameRepository repository = new ChessGameRepository();

    public void run() {
        config();

        get("/", (req, res) -> {
            int chessGameId = repository.save(start());
            res.redirect("/chess-game/" + chessGameId);
            return null;
        });

        get("/chess-game", (req, res) -> {
            res.redirect("/");
            return null;
        });

        get("/chess-game/:id", (req, res) -> {
            int chessGameId = Integer.parseInt(req.params("id"));
            Response response = Response.init(chessGameId, getChessGame(chessGameId));
            return render(response, CHESS_TEMPLATE_PATH);
        });

        post("/chess-game/:id/move", (req, res) -> {
            int chessGameId = Integer.parseInt(req.params("id"));
            Response response = move(req.body(), chessGameId);
            return render(response, CHESS_TEMPLATE_PATH);
        });

        get("/chess-game/:id/end", (req, res) -> {
            int chessGameId = Integer.parseInt(req.params("id"));
            Response response = end(chessGameId);
            return render(response, CHESS_TEMPLATE_PATH);
        });
    }

    private void config() {
        port(PORT);
        staticFileLocation("/static");
    }

    private ChessGame start() {
        ChessGame chessGame = new ChessGame();
        Command.start(chessGame);
        return chessGame;
    }

    private Response move(final String body, final int chessGameId) {
        ChessGame chessGame = getChessGame(chessGameId);
        try {
            Command.move(getCommands(body), chessGame);
            repository.move(chessGameId, chessGame);
            return Response.init(chessGameId, chessGame);
        } catch (Exception exception) {
            return Response.exception(chessGameId, chessGame, exception.getMessage());
        }
    }

    private Response end(final int chessGameId) {
        ChessGame chessGame = getChessGame(chessGameId);
        try {
            Command.end(chessGame);
            repository.end(chessGameId, chessGame);
            return Response.init(chessGameId, chessGame);
        } catch (Exception exception) {
            return Response.exception(chessGameId, chessGame, exception.getMessage());
        }
    }

    private ChessGame getChessGame(final int chessGameId) {
        return repository.find(chessGameId);
    }

    private static String getCommands(final String body) {
        if (body == null) {
            return EMPTY;
        }
        String[] commands = convert(body).split(REQ_BODY_EQUALS);
        if (commands.length < 2) {
            return EMPTY;
        }
        return commands[VALUE];
    }

    private static String convert(final String string) {
        return string.replace("\"", EMPTY)
                .replace("{", EMPTY)
                .replace("}", EMPTY)
                .replace("+", " ");
    }

    private static String render(final Response response, final String templatePath) {
        return new HandlebarsTemplateEngine()
                .render(new ModelAndView(response, templatePath));
    }
}
