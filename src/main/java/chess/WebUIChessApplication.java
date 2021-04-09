package chess;

import chess.dao.ChessDao;
import chess.domain.ChessResult;
import chess.domain.chessgame.ChessGame;
import chess.domain.position.Position;
import chess.dto.BoardDto;
import chess.dto.GameIdDTO;
import chess.dto.RequestDto;
import chess.dto.StatusDto;
import com.google.gson.Gson;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

enum HttpStatus {
    SUCCESS(200, true),
    FAIL(400, false);

    private final int code;
    private final boolean succeeded;

    HttpStatus(int code, boolean succeeded) {
        this.code = code;
        this.succeeded = succeeded;
    }

    public boolean succeeded() {
        return succeeded;
    }
}

public class WebUIChessApplication {
    private static final Gson GSON = new Gson();
    private static final ChessDao DAO = new ChessDao();

    public static void main(String[] args) {
        staticFiles.location("/public");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "main.html");
        });

        post("/create", (req, res) -> {
            try {
                GameIdDTO id = GSON.fromJson(req.body(), GameIdDTO.class);
                DAO.addGame(id.getGameId(), new ChessGame());
                return HttpStatus.SUCCESS.succeeded();
            } catch (IllegalArgumentException e) {
                return HttpStatus.FAIL.succeeded();
            }
        });

        post("/enter", (req, res) -> {
            String gameId = req.queryParams("gameId");
            if (DAO.isExistingGame(gameId)) {
                return HttpStatus.SUCCESS.succeeded();
            }
            return HttpStatus.FAIL.succeeded();
        });

        get("/game", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, "index.html");
        });

        get("/drawBoard", (req, res) -> {
            ChessGame game = DAO.findGameById(req.queryParams("gameId"));
            BoardDto boardDto = new BoardDto(game.board());
            return GSON.toJson(boardDto.board());
        });

        post("/move", (req, res) -> {
            String id = req.queryParams("gameId");
            ChessGame game = DAO.findGameById(id);
            RequestDto dto = GSON.fromJson(req.body(), RequestDto.class);
            try {
                game.move(new Position(dto.getSource()), new Position(dto.getTarget()));
                DAO.updateGame(id, game);
                return HttpStatus.SUCCESS.succeeded();
            } catch (IllegalArgumentException | IllegalStateException e) {
                return HttpStatus.FAIL.succeeded();
            }
        });

        get("/status", (req, res) -> {
            ChessGame game = DAO.findGameById(req.queryParams("gameId"));
            StatusDto statusDto = new StatusDto(new ChessResult(game.board()), game.currentTurn());
            return GSON.toJson(statusDto.status());
        });

        get("/checkStatus", (req, res) -> {
            ChessGame game = DAO.findGameById(req.queryParams("gameId"));
            return game.isRunning();
        });

        put("/restart", (req, res) -> {
            String id = req.queryParams("gameId");
            ChessGame game = DAO.findGameById(id);
            game.restartGame();
            DAO.updateGame(id, game);
            return HttpStatus.SUCCESS.succeeded();
        });

        get("/result", (req, res) -> {
            ChessGame game = DAO.findGameById(req.queryParams("gameId"));
            ChessResult result = new ChessResult(game.board());
            return result.winner().teamName();
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
