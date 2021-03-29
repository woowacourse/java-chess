package chess;

import chess.controller.WebController;
import chess.service.ChessService;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

// localhost:4567
public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/public");

        WebController webController = new WebController(new ChessService());
        webController.play();
    }


}
