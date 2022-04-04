package chess;

import chess.controller.Command;
import chess.domain.ChessGame;
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

        ChessGame chessGame = new ChessGame();
        Command.execute("start", chessGame);

        get("/", (req, res) -> {
            return boardRender(chessGame.getBoard(), "index.html");
        });

        post("/move", (req, res) -> {
            String commands = req.body().split("=")[1].replace("+", " ");
            Command.execute(commands, chessGame);
            res.redirect("/");
            return null;
        });
    }

    private static String boardRender(final Map<Position, Piece> board, final String templatePath) {
        BoardDto boardDto = BoardDto.from(board);
        return new HandlebarsTemplateEngine()
                .render(new ModelAndView(boardDto, templatePath));
    }
}
