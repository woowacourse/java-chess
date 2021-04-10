package chess.controller;

import static spark.Spark.*;

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
    private UserDto userDto;

    public WebChessController() {
        chessService = new ChessService();
    }

    public void run() {
        staticFiles.location("/templates");
        port(8080);

        get("/", (req, res) -> render(new HashMap<>(), "start.html"));
        get("/chess", (req, res) -> startWithUser());
        get("/restart", (req, res) -> restart());
        post("/user", (req, res) -> login(req));
        post("/signup", (req, res) -> signup(req));
        get("/adduser", (req, res) -> render(new HashMap<>(), "signup.html"));
        post("/board", (req, res) -> makeBoard(req));
        post("/save", (req, res) -> exit(req));
        put("/piece", (req, res) -> GSON.toJson(chessService.matchPieceName(userDto, req.body())));
        put("/move", (req, res) -> chessService.move(userDto, GSON.fromJson(req.body(), RequestDto.class)));
        post("/color", (req, res) -> chessService.makeCurrentColor(userDto, req.body()));
        post("/turn", (req, res) -> chessService.makeNextColor(userDto));
        post("/score", (req, res) -> chessService.score(userDto, req.body()));
    }

    private String startWithUser() {
        Map<String, Object> model = new HashMap<>();
        model.put("user", userDto);
        return render(model, "chess.html");
    }

    private String restart() {
        chessService.restartChess(userDto);
        return render(new HashMap<>(), "chess.html");
    }

    private String login(Request req) {
        UserDto loginUser = chessService.requestLoginUser(req.queryParams("name"), req.queryParams("password"));
        Map<String, Object> model = new HashMap<>();
        loginUser = chessService.findUser(loginUser);
        if (loginUser == null) {
            return render(model, "start.html");
        }
        userDto = loginUser;
        model.put("user", userDto);

        //chess/1 했을 때 1을 넣는 것
        // String userId = req.params(":name");
        // model.put("userId", userId);
        return render(model, "chess.html");
    }

    private String signup(Request req) {
        chessService.addUser(req.queryParams("name"), req.queryParams("password"));
        return render(new HashMap<>(), "start.html");
    }

    private String exit(Request req) {
        chessService.addBoard(userDto, req.body());
        return render(new HashMap<>(), "start.html");
    }

    private String makeBoard(Request req) {
        return GSON.toJson(chessService.matchBoardImageSource(userDto, req.body()));
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
