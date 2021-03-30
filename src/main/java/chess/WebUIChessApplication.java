package chess;

import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.dto.PieceDto;
import chess.dto.WebBoardDto;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    public static void main(String[] args) {
        staticFiles.location("/templates");
        ChessGame chessGame = new ChessGame(new Board());
        chessGame.start();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "init.html");
        });

        post("/main", (req, res) -> {
            WebBoardDto webBoardDto = new WebBoardDto(chessGame.board());
            Map<String, Object> model = webBoardDto.getBoardDto();
            return render(model, "main.html");
        });

        post("/restart", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "init.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
