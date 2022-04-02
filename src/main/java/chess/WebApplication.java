package chess;

import chess.controller.ChessController;
import chess.controller.WebController;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static spark.Spark.*;

public class WebApplication {

    public static void main(String[] args) {
        WebController webController = new WebController();
        webController.run();
    }
}
