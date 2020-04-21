package chess.controller;

import static spark.Spark.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.ChessGame;
import chess.dao.GamesDao;
import chess.dao.MoveDao;
import chess.domain.position.Position;
import chess.dto.ScoreDto;
import chess.dto.TurnDto;
import chess.dto.UnitsDto;
import chess.service.BoardService;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class BoardController {
    private final BoardService boardService = new BoardService();
    private final Gson gson = new GsonBuilder().create();
    private final MoveDao moveDao;
    private final GamesDao gamesDao;

    public BoardController(MoveDao moveDao, GamesDao gamesDao) {
        this.moveDao = moveDao;
        this.gamesDao = gamesDao;
    }

    public void route() {
        Spark.staticFileLocation("/templates");
        get("/", this::index);
        get("/init", this::init);
        get("/new_game/:id", this::createNewGame);
        get("/games", this::games);
        get("/turn/:id", this::turn);
        get("/board/:id", this::board);
        get("/score/:id", this::score);
        post("/move/:id", this::move);
    }

    private String index(Request request, Response response) {
        try {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        } catch (Exception e) {
            response.status(500);
            return gson.toJson(e.getMessage());
        }
    }

    private String init(Request request, Response response) {
        try {
            return render(new HashMap<>(), "userNames.html");
        } catch (Exception e) {
            response.status(500);
            return gson.toJson(e.getMessage());
        }
    }

    private String createNewGame(Request request, Response response) {
        try {
            HashMap<String, Object> model = new HashMap<>();
            model.put("id", request.params(":id"));
            return render(model, "playingGame.html");
        } catch (Exception e) {
            response.status(500);
            return gson.toJson(e.getMessage());
        }

    }

    private String games(Request request, Response response) {
        try {
            return gson.toJson(boardService.everyGames());
        } catch (Exception e) {
            response.status(500);
            return gson.toJson(e.getMessage());
        }
    }

    private String turn(Request request, Response response) {
        try {
            int id = Integer.parseInt(request.params(":id"));
            HashMap<String, Object> model = new HashMap<>();
            model.put("turn", boardService.turn(id));
            return gson.toJson(model);
        } catch (Exception e) {
            response.status(500);
            return gson.toJson(e.getMessage());
        }

    }

    private String board(Request request, Response response) {
        try {
            int id = Integer.parseInt(request.params(":id"));
            return gson.toJson(boardService.board(id));
        } catch (Exception e) {
            response.status(500);
            return gson.toJson(e.getMessage());
        }

    }

    private String score(Request request, Response response) {
        try {
            int id = Integer.parseInt(request.params(":id"));
            HashMap<String, Object> model = new HashMap<>();
            model.put("score", boardService.score(id));
            return gson.toJson(model);
        } catch (Exception e) {
            response.status(500);
            return gson.toJson(e.getMessage());
        }

    }

    private String move(Request request, Response response) {
        try {
            int id = Integer.parseInt(request.params(":id"));
            Map<String, Integer> map = gson.fromJson(request.body(), new TypeToken<Map<String, Integer>>() {}.getType());
            Position source = Position.of(map.get("sourceX"), map.get("sourceY"));
            Position target = Position.of(map.get("targetX"), map.get("targetY"));

            return gson.toJson(boardService.move(id, source, target));
        } catch (Exception e) {
            response.status(500);
            return gson.toJson(e.getMessage());
        }

    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}

