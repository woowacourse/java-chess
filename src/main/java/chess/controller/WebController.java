package chess.controller;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.piece.Piece;
import chess.domain.position.Square;
import chess.dto.SquareAndPiece;
import chess.view.InputView;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController extends Controller {
    public WebController() {
        super();
    }

    @Override
    public void run() {
        port(8080);
        staticFileLocation("/templates");
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/command", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            inGame(req.queryParams("command"), model);
            return render(model, "index.html");
        });
    }

    protected void inGame(String input, Map<String, Object> model) {
        try {
            executeCommand(InputView.requireCommand(input));
        } catch (IllegalArgumentException e) {
            model.put("error", e);
        } finally {
            if (game == null) {
                return;
            }
            Map<Square, Piece> pieceMap = game.getBoard();
            List<SquareAndPiece> infos = new ArrayList<>();
            for (Square square : pieceMap.keySet()) {
                infos.add(SquareAndPiece.of(square, pieceMap.get(square)));
            }
            model.put("infos", infos);
        }
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
