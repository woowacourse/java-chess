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
    private static final BoardDao boardDao = new BoardDao();
    private static final RenderService service = new RenderService();
    private static final String GAME_EXIT_ERROR_MESSAGE = "이미 게임이 종료되었습니다";

    public void run() {
        staticFiles.location("/static");
        final Map<String, Object> model = new HashMap<>();
        get("/", (req, res) -> service.renderStart(model));
        post("/start", (req, res) -> {
            String name = req.queryParams("name");
            create(name);
            service.setInitWeb(model, name);
            service.setPlayWeb(model, findGameByDB(name));
            return service.renderGame(model);
        });
        post("/command", (req, res) -> {
            String name = req.queryParams("name");
            service.setInitWeb(model, name);
            save(name, go(model, findGameByDB(name), req.queryParams("command")));
            service.setPlayWeb(model, findGameByDB(name));
            return service.renderGame(model);
        });
        post("/end", (req, res) -> {
            String name = req.queryParams("name");
            service.setInitWeb(model, name);
            service.setEndWeb(model, findGameByDB(name));
            boardDao.delete(name);
            return service.renderEnd(model);
        });
    }

    private void create(String name) {
        boardDao.create(new ChessGameDto(name, new ChessGame(new InitBoardStrategy())));
    }

    private ChessGame findGameByDB(String name) {
        return boardDao.findByName(name).getChessGame();
    }

    private ChessGame go(Map<String, Object> model, ChessGame chessGame, String input) {
        try {
            validateFinished(chessGame);
            chessGame.execute(new CommandDto(input));
            model.put("result", new RenderService().getResult(chessGame));
            return chessGame;
        } catch (IllegalArgumentException e) {
            model.put("error", e.getMessage());
        }
        return chessGame;
    }

    private void validateFinished(ChessGame chessGame) {
        if (chessGame.isFinished()) {
            throw new IllegalArgumentException(GAME_EXIT_ERROR_MESSAGE);
        }
    }

    private void save(String name, ChessGame chessGame) {
        boardDao.save(new ChessGameDto(name, chessGame));
    }
}
