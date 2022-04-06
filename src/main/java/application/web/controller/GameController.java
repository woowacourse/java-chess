package application.web.controller;

import java.util.HashMap;
import java.util.Map;

import application.web.service.GameService;
import chess.domain.Color;
import chess.domain.game.ChessGame;
import chess.dto.GameDto;
import chess.dto.PlayerScoresDto;
import spark.ExceptionHandler;
import spark.ModelAndView;
import spark.Route;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class GameController {

    private static final int HTTP_STATUS_ERROR = 400;

    private final GameService gameService;

    public GameController(final GameService gameService) {
        this.gameService = gameService;
    }

    public Route index() {
        return (request, response) -> {
            final Map<Long, Boolean> games = gameService.listGames();
            final Map<String, Object> model = new HashMap<>();
            model.put("games", games);
            return render(model, "index.html");
        };
    }

    public Route createNewGame() {
        return (request, response) -> {
            final ChessGame chessGame = gameService.createNewGame();
            response.redirect("/room/" + chessGame.getId());
            return "";
        };
    }

    public Route loadGame() {
        return (request, response) -> {
            final long gameId = parseGameId(request.params(":gameId"));
            return renderBoard(gameService.loadGame(gameId));
        };
    }

    public Route movePiece() {
        return (request, response) -> {
            final long gameId = parseGameId(request.params(":gameId"));
            final String source = request.queryParams("source");
            final String target = request.queryParams("target");
            return renderBoard(gameService.movePiece(gameId, source, target));
        };
    }

    public Route promotion() {
        return (request, response) -> {
            final long gameId = parseGameId(request.params(":gameId"));
            final String pieceName = request.queryParams("pieceName");
            return renderBoard(gameService.promotion(gameId, pieceName));
        };
    }

    public Route calculatePlayerScores() {
        return (request, response) -> {
            final long gameId = parseGameId(request.params(":gameId"));
            final Map<Color, Double> playerScores = gameService.calculatePlayerScores(gameId);
            return PlayerScoresDto.toDto(playerScores);
        };
    }

    public Route endGame() {
        return (request, response) -> {
            final long gameId = parseGameId(request.params(":gameId"));
            return renderBoard(gameService.endGame(gameId));
        };
    }

    private long parseGameId(final String gameId) {
        try {
            return Integer.parseInt(gameId);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("gameId는 숫자여야 합니다.");
        }
    }

    private String renderBoard(final ChessGame chessGame) {
        final Map<String, Object> model = new HashMap<>();
        model.put("game", GameDto.toDto(chessGame));
        if (chessGame.isRunning()) {
            model.put("promotable", chessGame.isPromotable());
        }
        return render(model, "board.html");
    }

    private String render(final Map<String, Object> model, final String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public ExceptionHandler<RuntimeException> handleException() {
        return (exception, request, response) -> {
            response.type("application/json; charset=utf-8");
            response.status(HTTP_STATUS_ERROR);
            response.body(exception.getMessage());
        };
    }
}
