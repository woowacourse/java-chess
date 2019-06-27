package chess;

import chess.controller.ChessIndexController;
import chess.controller.ChessResultController;
import chess.controller.ChessRoundController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        externalStaticFileLocation("src/main/resources/templates");

        get(ChessIndexController.PATH_CHESS_INDEX, ChessIndexController.fetchIndex);
        post(ChessIndexController.PATH_CHESS_INDEX, ChessIndexController.handleGameType);

        get(ChessRoundController.PATH_CHESS_ROUND, ChessRoundController.fetchChessRound);
        post(ChessRoundController.PATH_CHESS_ROUND, ChessRoundController.handleChessMove);

        get(ChessResultController.PATH_CHESS_RESULT, ChessResultController.fetchChessResult);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
