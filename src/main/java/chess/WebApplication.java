package chess;

import chess.controller.Command;
import chess.db.ChessGameRepository;
import chess.domain.ChessGame;
import chess.web.Response;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class WebApplication {
    public static void main(String[] args) {
        port(8081);
        staticFileLocation("/static");

        ChessGameRepository repository = new ChessGameRepository();

        get("/", (req, res) -> {
            ChessGame chessGame = new ChessGame();
            Command.execute("start", chessGame);

            int chessGameId = repository.save(chessGame);

            Response response = Response.init(chessGameId, chessGame);
            return render(response, "index.html");
        });

//        get("/temp", (req, res) -> {
//            return render(response, "index.html");
//        });
//
        get("/:id", (req, res) -> {
            int chessGameId = Integer.parseInt(req.params("id"));
            Response response = Response.init(chessGameId, repository.find(chessGameId));
            return render(response, "index.html");
        });

        post("/move", (req, res) -> {
            Response response;
            String[] request = req.body().split("&");
            int chessGameId = getChessGameId(request[0]);
            ChessGame chessGame = getChessGame(chessGameId, repository);
            try {
                String commands = getCommands(request[1]);
                Command.execute(commands, chessGame);
                repository.move(chessGameId, chessGame);
                response = Response.init(chessGameId, chessGame);
            } catch (Exception exception) {
                response = Response.exception(chessGameId, chessGame, exception.getMessage());
            }
            return render(response, "index.html");
        });
    }

    private static String render(final Response response, final String templatePath) {
        return new HandlebarsTemplateEngine()
                .render(new ModelAndView(response, templatePath));
    }

    private static int getChessGameId(final String body) {
        if (body == null) {
            return 0;
        }
        String[] chessGameId = convert(body).split(":|=");
        return Integer.parseInt(chessGameId[1]);
    }

    private static ChessGame getChessGame(final int chessGameId, final ChessGameRepository repository) {
        return repository.find(chessGameId);
    }

    private static String getCommands(final String body) {
        if (body == null) {
            return "";
        }
        String[] commands = convert(body).split(":|=");
        if (commands.length < 2) {
            return "";
        }
        return commands[1];
    }

    private static String convert(final String string) {
        return string.replace("\"", "")
                .replace("{", "")
                .replace("}", "")
                .replace("+", " ");
    }
}
