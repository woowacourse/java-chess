package chess;

import static spark.Spark.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.domain.board.factory.RegularBoardFactory;
import chess.domain.board.position.Position;
import chess.domain.piece.Piece;
import chess.dto.response.InitBoardResponse;
import com.google.gson.Gson;
import lecture.pobi.User;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {

    private static final Gson gson = new Gson();

    public static void main(String[] args) {
        staticFiles.location("/static");

        get("/", (req, res) -> {
            final Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        Map<Position, Piece> initBoard = RegularBoardFactory.getInstance().create();
        InitBoardResponse initBoardResponse = InitBoardResponse.from(initBoard);

        get("/board", "application/json", (req, res) -> {
            return initBoardResponse;
        }, gson::toJson);

/*
        Map<String, String> routes = new HashMap<>();
        post("/move", (req, res) -> {

            Position from = Position.of(req.queryParams("from"));
            Position to = Position.of(req.queryParams("to"));

            return null;
        });
*/
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
