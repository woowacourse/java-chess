package application.web;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.port;
import static spark.Spark.post;

import application.web.controller.GameController;
import application.web.service.GameService;
import chess.dao.mysql.GameDao;
import chess.dao.mysql.PlayerDao;
import chess.domain.game.repository.GameRepository;
import chess.domain.player.repository.PlayerRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WebApplication {

    public static void main(String[] args) {
        final WebApplication webApplication = new WebApplication();
        webApplication.run();
    }

    private void run() {
        final GameController gameController = initializeGameController();
        final Gson gson = new GsonBuilder().create();

        port(8081);

        get("/", gameController.index());

        path("/room", () -> {
            get("/start", gameController.createNewGame());
            get("/:gameId", gameController.loadGame());
            post("/:gameId/move", gameController.movePiece());
            post("/:gameId/promotion", gameController.promotion());
            get("/:gameId/status", gameController.calculatePlayerScores(), gson::toJson);
            get("/:gameId/end", gameController.endGame());
        });

        exception(RuntimeException.class, gameController.handleException());
    }

    private GameController initializeGameController() {
        final PlayerRepository playerRepository = new PlayerRepository(PlayerDao.getInstance());
        final GameRepository gameRepository = new GameRepository(playerRepository, GameDao.getInstance());
        final GameService gameService = new GameService(gameRepository);
        return new GameController(gameService);
    }
}
