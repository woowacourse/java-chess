package chess;

import chess.controller.ChessController;
import chess.domain.BoardDTO;
import chess.domain.MoveInfoDTO;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class WebUIChessApplication {
    public static void main(String[] args) {
        Spark.staticFileLocation("/static");
        new ChessController().run();
    }
}
