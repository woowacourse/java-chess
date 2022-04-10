package chess.controller;

import static chess.MappingUtil.*;
import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.Game;
import chess.dao.GameDaoImpl;
import chess.dto.Request;
import chess.model.PieceArrangement.DefaultArrangement;
import chess.model.Position;
import chess.model.Turn;
import chess.service.ChessGameService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    public void run(ChessGameService service) {
        index();
        game(service);
        move(service);
        status(service);
        save(service);
        init(service);
        end();
    }

    private void index() {
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }

    private void game(ChessGameService service) {
        post("/game", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int id = new GameDaoImpl().findByIds(req.queryParams("idPlayerWhite"), req.queryParams("idPlayerBlack"));
            Game game = new Game(req.queryParams("idPlayerWhite"), req.queryParams("idPlayerBlack"), id);
            service.setGameDao(new GameDaoImpl(game));
            service.init(new Turn(), new DefaultArrangement());
            model.put("pieces", StringPieceMapByPiecesByPositions(service.getPiecesByPositions()));
            model.put("color", service.loadTurnColor());
            return render(model, "game.html");
        });
    }

    private void move(ChessGameService service) {
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

    private void status(ChessGameService service) {
        get("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("pieces", StringPieceMapByPiecesByPositions(service.getPiecesByPositions()));
            model.put("color", service.getTurnColor());
            model.put("score", service.getScore());
            return render(model, "game.html");
        });
    }

    private void save(ChessGameService service) {
        post("/save", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            service.save();
            model.put("pieces", StringPieceMapByPiecesByPositions(service.getPiecesByPositions()));
            model.put("color", service.getTurnColor());
            return render(model, "game.html");
        });
    }

    private void init(ChessGameService service) {
        post("/init", (req, res) -> {
            service.delete();
            service.init(new Turn(), new DefaultArrangement());
            return render(Map.of(), "index.html");
        });
    }

    private String finish(ChessGameService service, Map<String, Object> model) {
        model.put("score", service.getScore());
        model.put("color", service.getTurnColor());
        return render(model, "finish.html");
    }

    private void end() {
        get("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });
    }

    private String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
