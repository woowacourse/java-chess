package chess.controller;

import chess.dao.BoardDAO;
import chess.domain.board.Board;
import chess.domain.move.MovingInfo;
import chess.factory.BoardFactory;
import chess.generator.JSONGenerator;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ChessGameController {
    private static Board board;
    private static BoardDAO boardDAO = new BoardDAO();

    public static String index(Request request, Response response) {
        Map<String, Object> model = new HashMap<>();

        return render(model, "index.html");
    }

    public static String newGame(Request request, Response response) throws SQLException {
        boardDAO.initialize();
        board = BoardFactory.createBoard();
        boardDAO.updateDB(board);
        return JSONGenerator.generateJSON(board);
    }

    public static String move(Request request, Response response) throws SQLException {
        MovingInfo movingInfo = MovingInfo.of(request);

        try {
            board.move(movingInfo);
        } catch (IllegalArgumentException | ArrayIndexOutOfBoundsException error) {
            return error.getMessage();
        }
        boardDAO.updateDB(board);
        return JSONGenerator.generateJSON(board);
    }

    public static String continueGame(Request request, Response response) throws SQLException {
        board = boardDAO.loadBoard();
        return JSONGenerator.generateJSON(board);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
