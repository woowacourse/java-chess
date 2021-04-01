package chess;

import chess.dao.ChessDAO;
import chess.domain.ChessResult;
import chess.domain.chessgame.ChessGame;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import chess.dto.RequestDto;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class WebUIChessApplication {
    private static final String DEFAULT_GAME_ID = "1";


    public static void main(String[] args) throws SQLException {
        staticFiles.location("/public");
        ChessDAO dao = new ChessDAO();
        ChessGame game = dao.findGameById(DEFAULT_GAME_ID);
        final Gson gson = new Gson();

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/drawBoard", (req, res) -> {
            BoardDto boardDto = new BoardDto(game.board());
            return gson.toJson(boardDto.getBoard());
        });

        post("/move", (req, res) -> {
            RequestDto dto = gson.fromJson(req.body(), RequestDto.class);
            try {
                game.move(new Position(dto.getSource()), new Position(dto.getTarget()));
                return 200;
            } catch (IllegalArgumentException | IllegalStateException e) {
                return 401;
            }
        });

        get("/checkStatus", (req, res) -> game.isRunning());

        put("/restart", (req, res) -> {
            game.restartGame();
            return 200;
        });

        get("/finish", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ChessResult result = new ChessResult(game.board());
            model.put("team", result.winner().teamName());

            return render(model, "test.html");
        });

        put("/save", (res, req) -> {
            dao.updateGame(DEFAULT_GAME_ID, game);
            return 200;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
