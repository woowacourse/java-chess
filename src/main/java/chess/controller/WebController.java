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
import spark.ModelAndView;
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
        get("/", (req, res) -> renderOnlyHTML("login.html"));
        post("/login", this::login);
        post("/logout", this::logout);
        get("/games", this::games);
        get("/games/:id", this::eachGame);
        post("/start", this::startGame);
        post("/move/:id", this::move);
    }

    private Response login(spark.Request req, Response res) throws SQLException {
        String userName = req.queryParams("userName");
        userService.addUserIfNotExist(userName);
        res.cookie("user", encodeCookie(userName));
        res.redirect("/games");
        return res;
    }

    private Response logout(spark.Request req, Response res) {
        res.removeCookie("user");
        res.redirect("/games");
        return res;
    }

    private String games(spark.Request req, Response res) throws SQLException {
        String user = decodeCookie(req.cookie("user"));
        if (user == null || user.equals("")) {
            res.redirect("/");
            return "";
        }
        int currentUserId = userService.findUserIdByName(user);
        List<GameDto> runningGamesByUserId = chessService.findGamesByUserId(currentUserId);
        HashMap<String, Object> model = new HashMap<>();
        model.put("games", runningGamesByUserId);
        model.put("user", user);
        return render(model, "games.html");
    }

    private String startGame(spark.Request req, Response res) throws SQLException {
        String user = decodeCookie(req.cookie("user"));
        int currentUser = userService.findUserIdByName(user);
        int gameId = chessService.getAddedGameId(
            new Game(currentUser, false, LocalDateTime.now(ZoneId.of("Asia/Seoul"))));
        res.redirect("/games/" + gameId);
        return "";
    }

    private String eachGame(spark.Request req, Response res) throws SQLException {
        int currentGameId = Integer.parseInt(req.params("id"));
        String user = decodeCookie(req.cookie("user"));
        if (user == null || user.equals("")) {
            res.redirect("/");
            return "";
        }
        String error = req.cookie("em");
        res.removeCookie("/games/" + currentGameId, "em");
        WebBoardDto webBoardDto = chessService.reloadAllHistory(currentGameId)
            .getWebBoardDto();
        Map<String, Object> model = getCurrentGameMap(user, error, currentGameId, webBoardDto);
        return render(model, "chess.html");
    }

    private String move(spark.Request req, Response res) throws SQLException {
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

    private static String renderOnlyHTML(String templatePath) {
        return new HandlebarsTemplateEngine()
            .render(new ModelAndView(new HashMap<>(), templatePath));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private Map<String, Object> getCurrentGameMap(String user, String error, int currentGameId,
        WebBoardDto webBoardDto) {
        Map<String, Object> model = webBoardDto.getWebBoard();
        model.put("user", user);
        model.put("em", error);
        model.put("gameId", currentGameId);
        model.put("turn", webBoardDto.getTurn());
        model.put("isEnd", webBoardDto.isEnd());
        model.put("winner", webBoardDto.getWinner());
        return model;
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
}
