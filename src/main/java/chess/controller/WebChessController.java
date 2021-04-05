package chess.controller;

import static spark.Spark.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import chess.domain.ChessGame;
import chess.dto.RequestDto;
import chess.dto.UserDto;
import chess.service.ChessService;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {
    private static final Gson GSON = new Gson();

    private final ChessService chessService;
    private ChessGame chessGame;
    private UserDto userDto;

    public WebChessController() {
        chessService = new ChessService();
        chessGame = new ChessGame();
    }

    public void run() {
        staticFiles.location("/templates");
        port(8080);

        get("/", (req, res) -> render(new HashMap<>(), "start.html"));
        get("/chess", (req, res) -> startWithUser());
        get("/restart", (req, res) -> restart());
        post("/user", (req, res) -> login(req));
        get("/signup", (req, res) -> signup(req));
        get("/adduser", (req, res) -> render(new HashMap<>(), "signup.html"));
        post("/board", (req, res) -> makeBoard(req));
        post("/save", (req, res) -> exit(req));
        put("/piece", (req, res) -> GSON.toJson(chessService.matchPieceName(chessGame, req.body())));
        put("/move", (req, res) -> chessService.move(chessGame, GSON.fromJson(req.body(), RequestDto.class)));
        post("/color", (req, res) -> chessService.makeCurrentColor(chessGame, req.body()));
        post("/turn", (req, res) -> chessService.makeNextColor(chessGame));
        post("/score", (req, res) -> chessService.score(chessGame, req.body()));
    }

    private String startWithUser() {
        Map<String, Object> model = new HashMap<>();
        model.put("user", userDto);
        return render(model, "chess.html");
    }

    private String restart() throws SQLException {
        chessGame = chessService.restartChess(userDto);
        return render(new HashMap<>(), "chess.html");
    }

    private String login(Request req) throws SQLException {
        UserDto loginUser = chessService.requestLoginUser(req.queryParams("name"), req.queryParams("password"));
        Map<String, Object> model = new HashMap<>();
        loginUser = chessService.findUser(loginUser);
        if (loginUser == null) {
            return render(model, "start.html");
        }
        userDto = loginUser;
        model.put("user", userDto);
        return render(model, "chess.html");
    }

    private String signup(Request req) throws SQLException {
        chessService.addUser(req.queryParams("name"), req.queryParams("password"));
        return render(new HashMap<>(), "start.html");
    }

    private String exit(Request req) throws SQLException {
        chessService.addBoard(chessGame, userDto, req.body());
        return render(new HashMap<>(), "start.html");
    }

    private String makeBoard(spark.Request req) throws SQLException {
        chessGame = chessService.matchBoard(userDto);
        return GSON.toJson(chessService.matchBoardImageSource(chessGame, req.body()));
    }

    public String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
