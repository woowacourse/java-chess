package chess;

import chess.controller.GameController;
import chess.controller.MainController;
import chess.model.dao.ChessDAO;
import chess.service.GameService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.get;

public class WebUIChessApplication {
    public static void main(String[] args) {
        ChessDAO chessDAO = ChessDAO.getInstance();
        GameService gameService = new GameService(chessDAO);
        GameController gameController = new GameController(gameService);
        MainController mainController = new MainController();

        get("/", mainController::main);

        get("/newgame.html", gameController::start);

        get("/show", gameController::show);

        get("/game.html", gameController::play);

        get("continueGame", gameController::continueGame);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}

