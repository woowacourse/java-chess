package chess;

import chess.controller.ChessGameController;
import chess.dao.ChessGameDao;
import chess.dao.ChessPieceDao;
import chess.dao.DBManager;
import chess.domain.ChessBoard;
import chess.domain.pieces.Type;
import chess.dto.ChessBoardDto;
import chess.dto.PieceDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;
import static spark.Spark.externalStaticFileLocation;

public class WebUIChessApplication {
    public static void main(String[] args) {
        port(8080);

        staticFiles.location("/templates");

        get("/playgame", ChessGameController.newGame);
        get("/continuegame", ChessGameController.continueGame);
        get("/initialize", ChessGameController.initialize);

        post("/move", ChessGameController.move);
    }


    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
