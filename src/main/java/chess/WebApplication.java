package chess;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

public class WebApplication {
    public static void main(String[] args) {
        port(8081);
        staticFileLocation("/static");

        Board board = new Board(new BoardInitializer());

        get("/", (req, res) -> {
            return boardRender(board, "index.html");
        });

        post("/move", (req, res) -> {
            String positions = req.body().split("=")[1];
            String[] commands = positions.split("\\+");
            String from = commands[1];
            String to = commands[2];
            board.move(Position.from(from), Position.from(to));
            res.redirect("/");
            return null;
        });
    }

    private static String boardRender(final Board board, final String templatePath) {
        BoardDto boardDto = BoardDto.from(board.toMap());
        return new HandlebarsTemplateEngine()
                .render(new ModelAndView(boardDto, templatePath));
    }
}
