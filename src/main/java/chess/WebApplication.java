package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.model.ChessGame;
import chess.model.File;
import chess.model.PieceArrangement.DefaultArrangement;
import chess.model.Position;
import chess.model.Rank;
import chess.model.Turn;
import chess.model.piece.Piece;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebApplication {
    public static void main(String[] args) {
        staticFiles.location("/");
        ChessGame chessGame = new ChessGame(new Turn(), new DefaultArrangement());

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        game(chessGame);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static void game(ChessGame chessGame) {
        post("/game", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Map<Position, Piece> boardValue = chessGame.getBoardValue();
            Map<String, String> stringBoardPieces = new HashMap<>();
            for (File file : File.values()) {
                for (Rank rank : Rank.values()) {
                    stringBoardPieces.put(file.getValue() + rank.getValue(),
                        EmblemMapper.fullNameFrom(boardValue.get(Position.of(file, rank))));
                }
            }

            return render(model, "game.html");
        });
    }
}
