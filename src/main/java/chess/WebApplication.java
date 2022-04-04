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
import static spark.Spark.staticFileLocation;

public class WebApplication {
    public static void main(String[] args) {
        port(8081);
        staticFileLocation("/static");

        get("/", (req, res) -> {
            Map<Position, Piece> board = new Board(new BoardInitializer()).toMap();
            return boardRender(board, "index.html");
        });
    }

    private static String boardRender(final Map<Position, Piece> board, final String templatePath) {
        BoardDto boardDto = BoardDto.from(board);
        return new HandlebarsTemplateEngine()
                .render(new ModelAndView(boardDto, templatePath));
    }
}
