package chess.controller;

import chess.domain.User;
import chess.domain.board.ChessBoard;
import chess.domain.board.ChessBoardFactory;
import chess.service.ChessService;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class ChessWebController {
    private final ChessService chessService;

    public ChessWebController(final ChessService chessService) {
        this.chessService = chessService;
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }


    public void run() {
        get("/", (req, res) -> {
            ChessBoard chessBoard = ChessBoardFactory.initializeBoard();
            System.out.println("체크체크체킃");
            //TilesDto tilesDto = new TilesDto(chessBoard);
            System.out.println("gdkjgldajflakdf");
            Map<String, Object> model = new HashMap<>();
           // model.put("tilesDto", toJson(tilesDto));
            return render(model, "board.html");
        });

    }
}
