package chess;

import chess.controller.WebChessController;
import chess.domain.game.ScoreResult;
import chess.domain.piece.Color;
import chess.domain.position.Position;
import chess.domain.position.PositionFactory;
import chess.domain.util.WrongOperationException;
import chess.domain.util.WrongPositionException;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static chess.JsonTransformer.json;
import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        WebChessController chessController = new WebChessController();

        staticFiles.location("/public");

        get("/", (req, res) -> {
            return render(chessController.init(), "index.html");
        });

        get("/new", (req, res) -> {
            return render(chessController.newGame(), "chess.html");
        });

        get("/loading", (req, res) -> {
            return render(chessController.load(), "chess.html");
        });

        get("/board", (req, res) -> {
            return chessController.board();
        }, json());

        post("/board", (req, res) -> {
            // TODO: 2020/04/07 isKingDead 체크될 경우 render를 result에 해줘야됨!!
            return render(chessController.move(req.queryParams("start"),req.queryParams("end")),"chess.html");
        });

        post("/start", (req, res) -> {
            return chessController.chooseFirstPosition(req.queryParams("start"));
        }, json());

        post("/end", (req, res) -> {
            return chessController.chooseSecondPosition(req.queryParams("end"));
        }, json());
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}