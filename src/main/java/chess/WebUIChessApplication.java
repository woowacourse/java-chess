package chess;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;

import chess.domain.game.ChessGame;
import chess.domain.game.state.Ready;
import chess.domain.piece.Position;
import chess.dto.BoardDto;
import chess.dto.ChessGameDto;
import chess.dto.ScoreDto;
import chess.dto.StatusDto;
import chess.dto.TurnDto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ModelAndView;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIChessApplication {
    private static final Map<String, ChessGame> CHESS_GAMES = new HashMap<>();
    private static final Gson GSON = new GsonBuilder().create();

    public static void main(String[] args) {
        Spark.staticFileLocation("templates");

        get("/game/:id", (req, res) -> {
            ChessGame chessGame = new ChessGame(new Ready());
            chessGame.start();
            CHESS_GAMES.putIfAbsent(req.params(":id"), chessGame);
            Map<String, Object> model = new HashMap<>();
            model.put("id", req.params(":id"));
            return render(model, "index.html");
        });

        get("/board/:id", (req, res) -> {
            ChessGame chessGame = CHESS_GAMES.get(req.params(":id"));
            return responseChessGame(chessGame);
        });

        post("/move/:id", (req, res) -> {
            ChessGame chessGame = CHESS_GAMES.get(req.params(":id"));
            Map<String, Double> data = GSON.fromJson(req.body(), Map.class);
            Position source = Position.of(data.get("sx").intValue(), data.get("sy").intValue());
            Position target = Position.of(data.get("tx").intValue(), data.get("ty").intValue());
            chessGame.move(source, target);
            return responseChessGame(chessGame);
        });
    }

    private static String responseChessGame(ChessGame chessGame) {
        return GSON.toJson(new ChessGameDto(new BoardDto(chessGame.board()), new TurnDto(chessGame.turn()),
            new StatusDto(new ScoreDto(chessGame.status().getWhiteScore()),
                new ScoreDto(chessGame.status().getBlackScore())), chessGame.isFinished()));
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
