package chess;

import chess.controller.WebUIChessController;
import chess.domain.User;
import chess.domain.board.Position;
import chess.domain.feature.Color;
import chess.domain.game.ChessGame;
import chess.domain.gamestate.State;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("");

        WebUIChessController webUIChessController = new WebUIChessController();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/registration", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "registration.html");
        });

        post("/menu", (req, res) -> {
            User user = new User(req.queryParams("id"), req.queryParams("pwd"));
            // 검증
            Map<String, Object> model = new HashMap<>();
            model.put("user", user);
            return render(model, "menu.html");
        });

        get("/game", (req, res) -> {
            ChessGame chessGame = webUIChessController.chessGame();
            Map<Position, Piece> chessBoard = chessGame.getChessBoardAsMap();
            Color turn = chessGame.getTurn();
            State state = chessGame.getState();

            Map<String, Object> model = new HashMap<>();
            for (Map.Entry<Position, Piece> entry : chessBoard.entrySet()) {
                model.put(entry.getKey().getPosition(), entry.getValue());
            }
            model.put("turn", turn);
            model.put("state", state);

            return render(model, "game.html");
        });

        post("/game", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "game.html");
        });

        get("/save", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "save.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
