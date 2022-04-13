package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.db.StateDAO;
import chess.domain.command.Command;
import chess.domain.command.CommandConverter;
import chess.domain.piece.Color;
import chess.domain.state.State;
import chess.web.BoardDTO;
import chess.web.RequestParser;
import chess.web.WebChessGame;
import java.util.Map;
import java.util.NoSuchElementException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    private final BoardDTO boardDTO = BoardDTO.buildModel();
    private final WebChessGame webChessGame = new WebChessGame();

    public void inputGameID() {
        get("/", (req, res) ->
                        new ModelAndView(Map.of("users", new StateDAO().findAllUsers()), "index.html"),
                new HandlebarsTemplateEngine());
    }

    public void preprocess() {
        post("/preprocess", (req, res) -> {
            try {
                String roomId = RequestParser.from(req.body())
                        .getRoomID();
                return searchSavedGame(roomId);
            } catch (IllegalArgumentException exception) {
                return new ModelAndView(Map.of("exception", exception.getMessage()), "index_exception.html");
            }
        }, new HandlebarsTemplateEngine());
    }

    private ModelAndView searchSavedGame(String roomId) {
        webChessGame.searchSavedGame(roomId, boardDTO);
        if (webChessGame.isSaved()) {
            return loadFromSave(webChessGame.getColor());
        }
        return new ModelAndView(Map.of(), "new_game.html");
    }

    private ModelAndView loadFromSave(Color color) {
        if (color == Color.WHITE) {
            return new ModelAndView(boardDTO.getData(), "white.html");
        }
        return new ModelAndView(boardDTO.getData(), "black.html");
    }

    public void readyGame() {
        post("/ready", (req, res) -> {
            try {
                return initializeGame(req);
            } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException exception) {
                return new ModelAndView(Map.of("exception", exception.getMessage()), "ready_exception.html");
            }
        }, new HandlebarsTemplateEngine());
    }

    private ModelAndView initializeGame(Request req) {
        State now = State.create();
        State next = now.proceed(CommandConverter.convertCommand(RequestParser.from(req.body()).getCommand()));
        webChessGame.initializeGame(boardDTO, next);
        if (webChessGame.isSaved()) {
            return new ModelAndView(boardDTO.getData(), "white.html");
        }
        return new ModelAndView(Map.of(), "finished_before_start.html");
    }

    public void runTurn() {
        post("/move", (req, res) -> {
            State now = webChessGame.getState();
            try {
                Command command = CommandConverter.convertCommand(RequestParser.from(req.body()).getCommand());
                State next = webChessGame.executeOneTurn(command, boardDTO);
                return executeOneTurn(res, command, next);
            } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException exception) {
                return handleRunningException(now, exception.getMessage());
            }
        }, new HandlebarsTemplateEngine());
    }

    private ModelAndView executeOneTurn(Response res, Command command, State next) {
        if (next.isFinished()) {
            res.redirect("/finished");
            return null;
        }
        if (command.isStatus()) {
            return continueToStatus();
        }
        return endTurn(next);
    }

    private ModelAndView continueToStatus() {
        return new ModelAndView(boardDTO.getData(), "status.html");
    }

    private ModelAndView endTurn(State next) {
        if (!next.isWhite()) {
            return new ModelAndView(boardDTO.getData(), "black.html");
        }
        return new ModelAndView(boardDTO.getData(), "white.html");
    }

    private ModelAndView handleRunningException(State now, String message) {
        boardDTO.updateWithMessage(now.getBoard(), message);
        if (now.isWhite()) {
            return new ModelAndView(boardDTO.getData(), "white_exception.html");
        }
        return new ModelAndView(boardDTO.getData(), "black_exception.html");
    }

    public void backwardToMove() {
        post("/backwardToMove", (req, res) -> {
            State now = webChessGame.getState();
            if (now.isWhite()) {
                return new ModelAndView(boardDTO.getData(), "white.html");
            }
            return new ModelAndView(boardDTO.getData(), "black.html");
        }, new HandlebarsTemplateEngine());
    }

    public void backwardToReady() {
        post("/backwardToReady", (req, res) ->
                        new ModelAndView(boardDTO.getData(), "new_game.html"),
                new HandlebarsTemplateEngine());
    }

    public void backwardToPreprocess() {
        post("/backwardToPreprocess", (req, res) ->
                        new ModelAndView(Map.of(), "index.html"),
                new HandlebarsTemplateEngine());
    }

    public void saveGame() {
        post("/saved", (req, res) -> new ModelAndView(boardDTO.getData(), "saved.html"), new HandlebarsTemplateEngine());
    }

    public void terminateGame() {
        get("/finished", (req, res) -> new ModelAndView(boardDTO.getData(), "finished.html"), new HandlebarsTemplateEngine());
    }
}
