package chess.controller;

import static chess.MappingUtil.*;
import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.Game;
import chess.dao.GameDaoImpl;
import chess.dto.Request;
import chess.model.ChessGame;
import chess.model.PieceArrangement.DefaultArrangement;
import chess.model.PieceColor;
import chess.model.Position;
import chess.model.Turn;
import chess.service.ChessGameService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    private Game game;
    private ChessGame chessGame;

    public void run(ChessGameService service) {
        path("/", () -> {
            index();
            game(service);
            save(service);
            init(service);
            move();
            status();
            end();
        });
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
            int id = new GameDaoImpl().findIdByPlayers(req.queryParams("idPlayerWhite"), req.queryParams("idPlayerBlack"));
            game = new Game(req.queryParams("idPlayerWhite"), req.queryParams("idPlayerBlack"),
                new Turn(PieceColor.valueOf(new GameDaoImpl().findTurnById(id))), id);

            chessGame = service.init(game.getId(), new Turn(game.getTurn()), new DefaultArrangement());
            model.put("pieces", StringPieceMapByPiecesByPositions(chessGame.getBoardValue()));
            model.put("color", chessGame.getTurnColor());
            return render(model, "game.html");
        });
    }

    private void move() {
        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                Request request = Request.toPlay(
                    "move" + " " + req.queryParams("source") + " " + req.queryParams("target"));
                chessGame.move(Position.of(request.getSource()), Position.of(request.getTarget()));
                game.nextTurn();
                model.put("pieces", StringPieceMapByPiecesByPositions(chessGame.getBoardValue()));
                model.put("color", chessGame.getTurnColor());
                if (chessGame.isFinished()) {
                    return finish(model);
                }

                return render(model, "game.html");

            } catch (RuntimeException e) {
                model.put("pieces", StringPieceMapByPiecesByPositions(chessGame.getBoardValue()));
                model.put("color", chessGame.getTurnColor());
                model.put("error", "[ERROR] 적절하지 않은 입력입니다.");
                return render(model, "game.html");
            }
        });
    }

    private void status() {
        get("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("pieces", StringPieceMapByPiecesByPositions(chessGame.getBoardValue()));
            model.put("color", chessGame.getTurnColor());
            model.put("score", chessGame.getScore());
            return render(model, "game.html");
        });
    }

    private void save(ChessGameService service) {
        post("/save", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            service.save(game, chessGame);
            model.put("pieces", StringPieceMapByPiecesByPositions(chessGame.getBoardValue()));
            model.put("color", chessGame.getTurnColor());
            return render(model, "game.html");
        });
    }

    private void init(ChessGameService service) {
        post("/init", (req, res) -> {
            service.deleteById(game.getId());
            chessGame = service.init(game.getId(), new Turn(), new DefaultArrangement());
            return render(Map.of(), "index.html");
        });
    }

    private String finish(Map<String, Object> model) {
        model.put("score", chessGame.getScore());
        model.put("color", chessGame.getTurnColor());
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
