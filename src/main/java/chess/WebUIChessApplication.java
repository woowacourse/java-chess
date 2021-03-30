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
    private static final Gson GSON = new Gson();

    public static void main(String[] args) {
        staticFiles.location("/templates");

        ChessGame chessGame = new ChessGame();
        ChessService chessService = new ChessService(chessGame);

        get("/chess", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        post("/board", (req, res) -> {
            return GSON.toJson(chessGame.getBoard().get(Point.of(req.body())).getImage());
        });

        post("/move", (req, res) -> {
            RequestDto requestDto = GSON.fromJson(req.body(), RequestDto.class);
             return chessService.move(requestDto);
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}