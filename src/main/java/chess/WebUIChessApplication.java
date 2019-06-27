package chess;

import chess.domain.*;
import chess.service.GameService;
import chess.service.PieceService;
import chess.service.UserService;
import com.google.gson.Gson;
import dao.*;
import dto.GameDto;
import dto.NavigatorDto;
import dto.PieceDto;
import dto.UserDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/");

        get("/", (req, res) -> render(WebUIChessController.getNotEndGames(), "intro.html"));

        post("/", (req, res) -> WebUIChessController.generateGameAndUsers(req, res));

        get("/:gameId", (req, res) -> render(WebUIChessController.getGameElements(req), "chess.html"));

        post("/:gameId", (req, res) -> WebUIChessController.movePiece(req, res));

        exception(Exception.class, (exception, req, res) -> {
            res.body(String.format("<script>alert('%s'); history.back();</script>", exception.getMessage(), req.pathInfo()));
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
