package chess.controller;

import chess.dao.ChessDataBase;
import chess.domain.piece.position.Position;
import chess.service.ChessService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class ChessController {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final HandlebarsTemplateEngine handlebarsTemplateEngine = new HandlebarsTemplateEngine();
    private static final ChessService chessService = new ChessService(new ChessDataBase());

    private static String render(Map<String, Object> model, String templatePath) {
        return handlebarsTemplateEngine.render(new ModelAndView(model, templatePath));
    }

    public void run() {
        get("/", this::rendChessBoard);
        post("/move", this::move);
        get("/init", this::initChessBoard);
        get("/continue", this::restartChessBoard);
    }

    private String rendChessBoard(Request req, Response res) {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
    }

    private String initChessBoard(Request req, Response res) throws Exception {
        return gson.toJson(chessService.initChessBoard());
    }

    private String restartChessBoard(Request req, Response res) throws Exception {
        return gson.toJson(chessService.board());
    }

    private String move(Request req, Response res) throws Exception {
            String source = req.queryParams("sourcePosition");
            String target = req.queryParams("targetPosition");
            Position sourcePosition = Position.of(source);
            Position targetPosition = Position.of(target);

            return gson.toJson(chessService.move(sourcePosition, targetPosition));
        }
    }

