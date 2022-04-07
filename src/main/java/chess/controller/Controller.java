package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.dao.BoardDao;
import chess.domain.boardstrategy.InitBoardStrategy;
import chess.domain.game.ChessGame;
import chess.dto.ChessGameDto;
import chess.dto.CommandDto;
import java.util.HashMap;
import java.util.Map;

public class Controller {
    private static final String EMPTY = "";

    private final BoardDao boardDao = new BoardDao();

    public void run() {
        staticFiles.location("/static");
        final Map<String, Object> model = new HashMap<>();
        RenderService service = new RenderService();
        get("/", (req, res) -> service.renderStart(model));
        post("/start", (req, res) -> {
            String name = req.queryParams("name");
            service.initWeb(model, name);
            return service.renderGame(model, createGameFromDB(name));
        });
        post("/command", (req, res) -> {
            String name = req.queryParams("name");
            save(name, go(model, findGameByDB(name), req.queryParams("command")));
            return service.renderGame(model, findGameByDB(name));
        });
        post("/end", (req, res) -> {
            String name = req.queryParams("name");
            return service.renderEnd(model, findGameByDB(name));
        });
    }

    private ChessGame createGameFromDB(String name) {
        boardDao.create(new ChessGameDto(name, new ChessGame(new InitBoardStrategy())));
        return findGameByDB(name);
    }

    private ChessGame findGameByDB(String name) {
        return boardDao.findByName(name).getChessGame();
    }

    private ChessGame go(Map<String, Object> model, ChessGame chessGame, String input) {
        try {
            chessGame.execute(new CommandDto(input));
            model.put("error", EMPTY);
            return chessGame;
        } catch (IllegalArgumentException e) {
            model.put("error", e.getMessage());
        }
        return chessGame;
    }

    private void save(String name, ChessGame chessGame) {
        boardDao.save(new ChessGameDto(name, chessGame));
    }
}
