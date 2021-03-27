package chess;

import chess.domain.board.Board;
import chess.domain.board.Team;
import chess.domain.chessgame.ChessGame;
import chess.domain.chessgame.Turn;
import chess.dto.BoardDto;
import chess.dto.BoardDtoWeb;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;

public class WebUIChessApplication {
    public static void main(String[] args) {
        Spark.staticFileLocation("/public");
        get("/", (req, res) -> {
            Board board = new Board();
            ChessGame chessGame = new ChessGame(board, new Turn(Team.WHITE));
            chessGame.start();
            BoardDtoWeb boardDtoWeb = new BoardDtoWeb(board);
            Map<String, Object> model = new HashMap<>();
            model.put("board", boardDtoWeb);
            return render(model, "index.html");
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
