package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.domain.ChessGame;
import chess.domain.dto.GameDto;
import chess.domain.dto.WebBoardDto;
import chess.domain.web.Game;
import chess.domain.web.GameHistory;
import chess.service.ChessService;
import chess.service.UserService;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {
    private final ChessService chessService;
    private final UserService userService;

    public WebController(ChessService chessService, UserService userService) {
        this.chessService = chessService;
        this.userService = userService;
    }

    public void run() {
        get("/", this::renderLogin);
        get("/games", this::renderGames);
        get("/games/:id", this::renderGame);
        post("/login", this::login);
        post("/logout", this::logoutAndRedirect);
        post("/start", this::start);
        post("/move/:id", this::move);
    }

    private String renderLogin(Request req, Response res) {
        return new HandlebarsTemplateEngine()
            .render(new ModelAndView(new HashMap<>(), "login.html"));
    }

    private String renderGame(Request req, Response res) throws SQLException {
        Optional<Integer> currentUserId = checkLogin(req);
        return currentUserId.map(id -> {
            int gameId = Integer.parseInt(req.params("id"));
            return eachGame(gameId, getErrorMessage(req, res, gameId));
        }).orElseGet(() -> logoutAndRedirect(req, res));
    }

    private String renderGames(Request req, Response res) {
        Optional<Integer> currentUserId = checkLogin(req);
        return currentUserId.map(this::games)
            .orElseGet(() -> logoutAndRedirect(req, res));
    }

    private String login(Request req, Response res) {
        String userName = req.queryParams("userName");
        int userId = userService.addUserIfNotExist(userName);
        res.cookie("user", encodeCookie(String.valueOf(userId)));
        res.redirect("/games");
        return "";
    }

    private String logoutAndRedirect(Request req, Response res) {
        res.removeCookie("user");
        res.redirect("/");
        return "";
    }

    private String start(Request req, Response res) {
        Optional<Integer> currentUserId = checkLogin(req);
        return currentUserId.map(id -> startGame(res, id))
            .orElseGet(() -> logoutAndRedirect(req, res));
    }

    private Optional<Integer> checkLogin(Request req) {
        String userIdStringFormat = decodeCookie(req.cookie("user"));
        if (userIdStringFormat == null) {
            return Optional.empty();
        }
        int userId = Integer.parseInt(userIdStringFormat);
        if (userService.isUserExist(userId)) {
            return Optional.of(userId);
        }
        return Optional.empty();
    }

    private String games(int currentUserId) {
        List<GameDto> runningGamesByUserId = chessService.findGamesByUserId(currentUserId);
        HashMap<String, Object> model = new HashMap<>();
        model.put("games", runningGamesByUserId);
        model.put("user", currentUserId);
        return render(model, "games.html");
    }

    private String startGame(Response res, int currentUserId) {
        int gameId = chessService.getAddedGameId(
            new Game(currentUserId, false, LocalDateTime.now(ZoneId.of("Asia/Seoul"))));
        res.redirect("/games/" + gameId);
        return "";
    }

    private String getErrorMessage(Request req, Response res, int gameId) {
        String error = req.cookie("em");
        res.removeCookie("/games/" + gameId, "em");
        return error;
    }

    private String eachGame(int currentGameId, String error) {
        WebBoardDto webBoardDto = chessService.reloadAllHistory(currentGameId).getWebBoardDto();
        Map<String, Object> model = getCurrentGameMap(currentGameId, error, currentGameId,
            webBoardDto);
        return render(model, "chess.html");
    }

    private String move(Request req, Response res) throws SQLException {
        int gameId = Integer.parseInt(req.params("id"));
        String command = req.queryParams("command");
        ChessGame chessGame = chessService.reloadAllHistory(gameId);
        try {
            chessGame.move(command);
            if (chessGame.isEnd()) {
                chessService.updateGameIsEnd(gameId);
            }
        } catch (IllegalArgumentException e) {
            res.cookie("/games/" + gameId, "em", encodeCookie(e.getMessage()), 3000, false);
            res.redirect("/games/" + gameId);
            return "";
        }

        chessService.addGameHistory(
            new GameHistory(gameId, command, LocalDateTime.now(ZoneId.of("Asia/Seoul"))));
        res.redirect("/games/" + gameId);
        return "";
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private String encodeCookie(String cookie) {
        if (cookie == null) {
            return null;
        }
        return new String(Base64.getUrlEncoder().withoutPadding()
            .encode(cookie.getBytes(StandardCharsets.UTF_8)));
    }

    private String decodeCookie(String cookie) {
        if (cookie == null) {
            return null;
        }
        return new String(Base64.getUrlDecoder().decode(cookie));
    }

    private Map<String, Object> getCurrentGameMap(int currentUserId, String error,
        int currentGameId,
        WebBoardDto webBoardDto) {
        Map<String, Object> model = webBoardDto.getWebBoard();
        model.put("user", currentUserId);
        model.put("em", error);
        model.put("gameId", currentGameId);
        model.put("turn", webBoardDto.getTurn());
        model.put("isEnd", webBoardDto.isEnd());
        model.put("winner", webBoardDto.getWinner());
        return model;
    }
}
