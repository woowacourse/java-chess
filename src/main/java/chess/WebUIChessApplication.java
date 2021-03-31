package chess;

import chess.domain.chessgame.ChessGame;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import chess.dto.RequestDto;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/public");
        ChessGame game = new ChessGame();
        final Gson gson = new Gson();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/drawBoard", (req, res) -> {
            BoardDto boardDto = new BoardDto(game.board());
            return gson.toJson(boardDto.getBoard());
        });

        put("/restart", (req, res) -> {
            game.restartGame();
            return 200;
        });

        post("/move", (req, res) -> {
            RequestDto dto = gson.fromJson(req.body(), RequestDto.class);
            try {
                game.move(new Position(dto.getSource()), new Position(dto.getTarget()));
                return "200";
            } catch (IllegalArgumentException | IllegalStateException e) {
                return "401";
            }
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
