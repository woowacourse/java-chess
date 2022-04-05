package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.dto.Request;
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
        move(chessGame);
        status(chessGame);
        end(chessGame);
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }

    private static void game(ChessGame chessGame) {
        post("/game", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            model.put("pieces", StringPieceMapByPiecesByPositions(chessGame));
            return render(model, "game.html");
        });
    }

    private static void move(ChessGame chessGame) {
        post("/move", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            try {
                Request request = Request.toPlay(
                    "move" + " " + req.queryParams("source") + " " + req.queryParams("target"));
                chessGame.move(Position.of(request.getSource()), Position.of(request.getTarget()));
                model.put("pieces", StringPieceMapByPiecesByPositions(chessGame));
                if (chessGame.isFinished()) {
                    return finish(chessGame, model);
                }

                return render(model, "game.html");

            } catch (RuntimeException e) {
                model.put("pieces", StringPieceMapByPiecesByPositions(chessGame));
                model.put("error", e.getMessage());
                return render(model, "game.html");
            }
        });
    }

    private static String finish(ChessGame chessGame, Map<String, Object> model) {
        model.put("pieces", StringPieceMapByPiecesByPositions(chessGame));
        return render(model, "finish.html");
    }

    private static void status(ChessGame chessGame) {
        get("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("pieces", StringPieceMapByPiecesByPositions(chessGame));
            model.put("status", chessGame.getScore());
            return render(model, "game.html");
        });
    }

    private static void end(ChessGame chessGame) {
        get("/end", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            chessGame.init(new DefaultArrangement());
            return render(model, "index.html");
        });
    }

    private static Map<String, String> StringPieceMapByPiecesByPositions(ChessGame chessGame) {
        Map<Position, Piece> boardValue = chessGame.getBoardValue();
        Map<String, String> stringBoardPieces = new HashMap<>();
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                stringBoardPieces.put(file.getValue() + rank.getValue(),
                    EmblemMapper.fullNameFrom(boardValue.get(Position.of(file, rank))));
            }
        }
        return stringBoardPieces;
    }
}
