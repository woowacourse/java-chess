package chess;

import static chess.EmblemMapper.*;
import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.dao.BoardDaoImpl;
import chess.dao.GameDaoImpl;
import chess.dto.Request;
import chess.model.PieceArrangement.DefaultArrangement;
import chess.model.Position;
import chess.model.Turn;
import chess.service.ChessGameService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        staticFiles.location("/");
        ChessGameService service = new ChessGameService(new BoardDaoImpl());

        index();
        game(service);
        move(service);
        status(service);
        save(service);
        end();
    }

    private static void index() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }

    private static void game(ChessGameService service) {
        post("/game", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Game game = new Game(req.queryParams("idPlayerWhite"), req.queryParams("idPlayerBlack"));
            service.setGameDao(new GameDaoImpl(game));
            service.init(new Turn(), new DefaultArrangement());
            model.put("pieces", StringPieceMapByPiecesByPositions(service.getPiecesByPositions()));
            model.put("color", service.getTurnColor());
            return render(model, "game.html");
        });
    }

    private static void move(ChessGameService service) {
        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                Request request = Request.toPlay(
                    "move" + " " + req.queryParams("source") + " " + req.queryParams("target"));
                service.move(Position.of(request.getSource()), Position.of(request.getTarget()));
                model.put("pieces", StringPieceMapByPiecesByPositions(service.getPiecesByPositions()));
                model.put("color", service.getTurnColor());
                if (service.isFinished()) {
                    return finish(service, model);
                }

                return render(model, "game.html");

            } catch (RuntimeException e) {
                model.put("pieces", StringPieceMapByPiecesByPositions(service.getPiecesByPositions()));
                model.put("color", service.getTurnColor());
                model.put("error", e.getMessage());
                return render(model, "game.html");
            }
        });
    }

    private static void status(ChessGameService service) {
        get("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("pieces", StringPieceMapByPiecesByPositions(service.getPiecesByPositions()));
            model.put("color", service.getTurnColor());
            model.put("score", service.getScore());
            return render(model, "game.html");
        });
    }

    private static void save(ChessGameService service) {
        post("/save", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            service.save();
            model.put("pieces", StringPieceMapByPiecesByPositions(service.getPiecesByPositions()));
            return render(model, "game.html");
        });
    }

    private static String finish(ChessGameService service, Map<String, Object> model) {
        model.put("score", service.getScore());
        model.put("color", service.getTurnColor());
        return render(model, "finish.html");
    }

    private static void end() {
        get("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
