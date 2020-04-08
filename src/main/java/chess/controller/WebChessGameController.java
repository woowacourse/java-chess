package chess.controller;

import static spark.Spark.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import chess.dao.ChessGameDao;
import chess.domain.piece.Position;
import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessGameController {
    private static final Gson GSON = new GsonBuilder().create();
    private static final ChessGameDao chessGameDao = new ChessGameDao();
    private ChessService chessService;

    public WebChessGameController(ChessService chessService) {
        this.chessService = chessService;
        route();
    }

    public void route() {
        Spark.staticFileLocation("assets");

        get("/games", this::getGameList);
        get("/", this::renderIndexPage);
        get("/game/:id", this::renderGamePage);
        get("/board/:id", this::getChessGameById);
        post("/move/:id", this::movePiece);
        post("/restart/:id", this::restartGame);
        post("/create", this::createChessRoom);
        notFound("<script>location.replace('/')</script>");
    }

    private String renderGamePage(Request req, Response res) throws SQLException {
        if (chessGameDao.selectAll().contains(Integer.parseInt(req.params(":id")))) {
            Map<String, Object> model = new HashMap<>();
            model.put("id", req.params(":id"));
            return render(model, "game.html");
        }
        return "<script>location.replace('/')</script>";
    }

    private String renderIndexPage(Request req, Response res) {
        return render(new HashMap<>(), "index.html");
    }

    private String createChessRoom(Request req, Response res) throws SQLException {
        return GSON.toJson(chessService.createChessRoom());
    }

    private String restartGame(Request req, Response res) throws SQLException {
        return GSON.toJson(chessService.restartGame(Integer.parseInt(req.params(":id"))));
    }

    private String movePiece(Request req, Response res) throws SQLException {
        int pieceId = Integer.parseInt(req.params(":id"));
        Map<String, Double> data = GSON.fromJson(req.body(), Map.class);
        Position source = Position.of(data.get("sx").intValue(), data.get("sy").intValue());
        Position target = Position.of(data.get("tx").intValue(), data.get("ty").intValue());
        return GSON.toJson(chessService.movePiece(pieceId, source, target));
    }

    private String getChessGameById(Request req, Response res) throws SQLException {
        return GSON.toJson(chessService.getChessGameById(Integer.parseInt(req.params(":id"))));
    }

    private String getGameList(Request req, Response res) throws SQLException {
        return GSON.toJson(chessService.getGameList());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
