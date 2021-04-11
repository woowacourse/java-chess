package chess.controller;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import chess.dto.RequestDto;
import chess.dto.UserDto;
import chess.service.ChessService;
import spark.ModelAndView;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {
    private static final Gson GSON = new Gson();

    private final ChessService chessService;

    public WebChessController() {
        chessService = new ChessService();
    }

    public void run() {
        staticFiles.location("/templates");
        port(8080);

        get("/", (req, res) -> render(new HashMap<>(), "start.html"));
        post("/chess", (req, res) -> startWithUser(req.body()));
        post("/restart", (req, res) -> restart(req));
        post("/user", (req, res) -> login(req));
        post("/signup", (req, res) -> signup(req));
        get("/adduser", (req, res) -> render(new HashMap<>(), "signup.html"));
        post("/board", (req, res) -> makeBoard(req));
        post("/save", (req, res) -> exit(req));
        put("/piece", (req, res) -> GSON.toJson(chessService.matchPieceName(GSON.fromJson(req.body(), RequestDto.class))));
        put("/move", (req, res) -> chessService.move(GSON.fromJson(req.body(), RequestDto.class)));
        post("/color", (req, res) -> chessService.makeCurrentColor(GSON.fromJson(req.body(), RequestDto.class)));
        post("/turn", (req, res) -> chessService.makeNextColor(req.body()));
        post("/score", (req, res) -> chessService.score(GSON.fromJson(req.body(), RequestDto.class)));
        post("/userId", (req, res) -> chessService.makeUserID(req.body()));
    }

    private String startWithUser(String userId) {
        Map<String, Object> model = new HashMap<>();
        UserDto userWithId = chessService.findUserWithId(userId);
        model.put("user", userWithId);
        return render(model, "chess.html");
    }

    private String restart(Request req) {
        chessService.restartChess(req.body());
        return render(new HashMap<>(), "chess.html");
    }

    private String login(Request req) {
        UserDto loginUser = chessService.requestLoginUser(req.queryParams("name"), req.queryParams("password"));
        Map<String, Object> model = new HashMap<>();
        if (loginUser == null) {
            return render(model, "start.html");
        }
        model.put("user", loginUser);
        return render(model, "chess.html");
    }

    private String signup(Request req) {
        chessService.addUser(req.queryParams("name"), req.queryParams("password"));
        return render(new HashMap<>(), "start.html");
    }

    private String exit(Request req) {
        RequestDto requestDto = GSON.fromJson(req.body(), RequestDto.class);
        chessService.addBoard(requestDto.getSecondInfo(), requestDto.getFirstInfo());
        return render(new HashMap<>(), "start.html");
    }

    private String makeBoard(Request req) {
        RequestDto requestDto = GSON.fromJson(req.body(), RequestDto.class);
        return GSON.toJson(chessService.matchBoardImageSource(requestDto.getSecondInfo(), requestDto.getFirstInfo()));
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
