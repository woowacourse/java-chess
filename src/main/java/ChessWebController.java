import chess.game.ChessGame;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

public class ChessWebController {
    private static final HandlebarsTemplateEngine handlebarsTemplateEngine = new HandlebarsTemplateEngine();

    private final ChessService chessService;

    ChessWebController(ChessService chessService) {
        this.chessService = chessService;
    }

    void run() {
        staticFiles.location("templates");

        ChessGame chessGame = new ChessGame();

        get("/main", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "start.html");
        });

        get("/start", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/start/boards", (req, res) -> chessService.findAllBoards());

        get("/start/board", (req, res) -> chessService.findBoard(req));

        post("/start/move", (req, res) -> chessService.move(req, chessGame));

        get("/start/winner", (req, res) -> chessService.findWinner(chessGame));

        get("/end", (req, res) -> {
            chessService.insertChessBoard(chessGame);
            Map<String, Object> model = new HashMap<>();
            return render(model, "start.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return handlebarsTemplateEngine.render(new ModelAndView(model, templatePath));
    }
}
