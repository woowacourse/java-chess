package chess.controller;

import chess.domain.ChessGame;
import chess.domain.dto.PiecesDto;
import com.google.gson.*;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFiles;

public class WebChessController {

    public void run() {
        staticFiles.location("/static");

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/data", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ChessGame chessGame = new ChessGame();
            PiecesDto piecesDto = new PiecesDto(chessGame.getPiecesByAllPosition());
            String piecesDtoJson = gson.toJson(piecesDto);
            return piecesDtoJson;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
