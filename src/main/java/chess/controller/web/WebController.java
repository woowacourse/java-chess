package chess.controller.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class WebController {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
/*

    public Route move = (req, res) -> {
        MoveResult moveResult;
        res.type("application/json");
        try {
            Square beginSquare = Square.of(req.queryMap("source").value());
            Square endSquare = Square.of(req.queryMap("target").value());
            return service.canMove(beginSquare, endSquare).toJson();
        } catch (Exception e) {
            moveResult = new MoveResult();
            moveResult.setCanMove(false);
            res.status(INTERNAL_SERVER_ERROR_CODE);
            return moveResult.toJson();
        }
    };
*/

    public static void start() {


        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/game/move", (request, response) -> {

          //  gson.fromJson(request.body(),);
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }
/*
    public static void run() {
        ChessGameManager chessGameManager = ChessGameManagerFactory.createNotStartedGameManager();

        do {
            Command command = Repeater.repeatOnError(() -> CommandRouter.findByInputCommand(InputView.getCommand()));
            chessGameManager = executeCommandOrPassOnError(chessGameManager, command);
        } while (chessGameManager.isNotEnd());

        if (chessGameManager.isStart()) {
            OutputView.printResult(chessGameManager.getStatistics());
        }
    }

    private static ChessGameManager executeCommandOrPassOnError(ChessGameManager chessGameManager, Command command) {
        try {
            return command.execute(chessGameManager);
        } catch (RuntimeException e) {
            OutputView.printMessage(e.getMessage());
            return chessGameManager;
        }
    }*/
}
