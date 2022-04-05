package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.dao.BoardDao;
import chess.dao.BoardDaoImpl;
import chess.dao.PieceDao;
import chess.dao.PieceDaoImpl;
import chess.domain.game.ChessGame;
import chess.service.ChessService;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessController {

    private static final String INDEX_PAGE = "index.html";
    private static final String BOARD_PAGE = "board.html";
    private static final String INDEX_URL = "/";
    private static final String BOARD_URL = "/board";

    private final ChessService chessService = new ChessService();
    private final PieceDao pieceDao;
    private final BoardDao boardDao;

    public ChessController(Connection connection) {
        this.pieceDao = new PieceDaoImpl(connection);
        this.boardDao = new BoardDaoImpl(connection);
    }

    public void run() {
        ChessGame chessGame = new ChessGame();

        get(INDEX_URL, (request, response) -> render(new HashMap<>(), INDEX_PAGE));

        get(BOARD_URL, (request, response) -> {
            int id = Integer.parseInt(request.queryParams("id"));
            return render(chessService.findBoardModel(chessGame, pieceDao, id), BOARD_PAGE);
        });

        post("/new", (request, response) -> {
            int id = chessService.createNewBoard(boardDao, pieceDao, chessGame);
            response.redirect(BOARD_URL + "?id=" + id);
            return null;
        });

        get("/move", (request, response) -> {
            List<String> inputs = new ArrayList<>();
            inputs.add("move");
            inputs.add(request.queryParams("from"));
            inputs.add(request.queryParams("to"));

            return render(chessService.move(chessGame, pieceDao, inputs), BOARD_PAGE);
        });

        get("/status", (request, response) -> render(chessService.calculateScore(chessGame), BOARD_PAGE));

        get("/end", (request, response) -> {
            chessService.end(chessGame);
            response.redirect(INDEX_URL);
            return null;
        });
    }

    public String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
