package chess;

import static spark.Spark.*;

import chess.domain.ChessGame;
import chess.domain.RequestDto;
import chess.domain.board.Point;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class WebUIChessApplication {

    public static void main(String[] args) {
        staticFiles.location("/templates");
        port(8080);

        ChessService chessService = new ChessService();
    }
}