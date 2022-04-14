package chess.controller;

import chess.domain.ChessGame;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Positions;
import chess.dto.ChessDTO;
import chess.dto.GameIdDTO;
import chess.service.ChessService;
import spark.Request;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.*;

public class ChessWebController {

    private static final String VIEW = "game.html";
    private static final String GAME_ID = ":id";
    private static final String INIT_STATE = "init";
    private static final String STATE = "state";
    private static final String ERROR_ANNOUNCE_MESSAGE = "<br> 뒤로 가기를 눌러주세요. ";

    private final ChessService chessService;

    public ChessWebController(ChessService chessService) {
        this.chessService = chessService;
    }

    public void run() {
        ChessGame chessGame = new ChessGame();

        get("/", (req, res) -> modelAndView(new LinkedHashMap<>(), VIEW), new HandlebarsTemplateEngine());

        post("/input-user", (req, res) -> {
            String whiteName = req.queryParams(Color.WHITE.name());
            String blackName = req.queryParams(Color.BLACK.name());
            chessService.saveGame(whiteName, blackName, chessGame.getTurn());
            int gameId = chessService.findGameIdByUser(whiteName, blackName).getId();

            res.redirect("/start/" + gameId + "/init");
            return null;
        });

        get("/start/:id/:state", (req, res) -> {
            int gameId = Integer.parseInt(req.params(GAME_ID));
            if (!chessGame.isRunning() || req.params(STATE).equals(INIT_STATE)) {
                chessService.initGame(chessGame, gameId);
            }
            chessGame.loadBoard(chessService.findPieces(new GameIdDTO(gameId)));
            Map<String, Object> model = new LinkedHashMap<>(chessService.findPieces(new GameIdDTO(gameId)));
            model.put("id", gameId);

            return modelAndView(model, VIEW);
        }, new HandlebarsTemplateEngine());

        post("/move/:id", (req, res) -> {
            int gameId = Integer.parseInt(req.params(GAME_ID));
            movePiece(chessGame, req, gameId);
            if (chessGame.isFinished()) {
                res.redirect("/end/" + gameId);
            }

            res.redirect("/start/" + gameId + "/running");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/status/:id", (req, res) -> {
            int gameId = Integer.parseInt(req.params(GAME_ID));
            Map<String, Object> model = new HashMap<>(chessGame.toBoardModel());;
            model.put("id", gameId);

            return modelAndView(chessGame.createScore(model), VIEW);
        }, new HandlebarsTemplateEngine());

        get("/end/:id", (req, res) -> {
            int gameId = Integer.parseInt(req.params(GAME_ID));
            if (chessGame.isRunning()) {
                chessGame.end();
            }
            chessService.deleteGame(new GameIdDTO(gameId));

            res.redirect("/status/" + gameId);
            return null;
        }, new HandlebarsTemplateEngine());

        exception(Exception.class, (exception, request, response) -> {
            response.status(400);
            response.body(exception.getMessage() + ERROR_ANNOUNCE_MESSAGE);
        });
    }

    private void movePiece(ChessGame chessGame, Request req, int gameId) {
        String from = req.queryParams("from");
        String to = req.queryParams("to");

        Piece piece = chessGame.findPiece(from);
        chessGame.move(Positions.findPosition(from), Positions.findPosition(to));
        chessService.savePieces(List.of(new ChessDTO(piece.getColor(), piece.getPiece(), to)), new GameIdDTO(gameId));
        chessService.deletePiece(from, new GameIdDTO(gameId));
        chessService.updateTurn(new GameIdDTO(gameId), chessGame.getTurn());
    }
}
