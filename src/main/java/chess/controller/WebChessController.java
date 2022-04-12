package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.db.BoardDAO;
import chess.db.StateDAO;
import chess.domain.command.Command;
import chess.domain.command.CommandConverter;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.state.State;
import chess.web.BoardDTO;
import chess.web.RequestParser;
import java.util.Map;
import java.util.NoSuchElementException;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {

    private final BoardDTO boardDTO = BoardDTO.buildModel();
    private BoardDAO boardDAO;
    private StateDAO stateDAO;

    private State getState(StateDAO stateDAO, BoardDAO boardDAO) {
        Color color = stateDAO.findColor();
        Map<Position, Piece> pieces = boardDAO.findAllPieces();
        return State.create(pieces, color);
    }

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
        stateDAO = new StateDAO(roomId);
        boardDAO = new BoardDAO(roomId);
        if (stateDAO.isSaved()) {
            State now = getState(stateDAO, boardDAO);
            boardDTO.update(now.getBoard());
            return loadFromSave(now);
        }
        return new ModelAndView(Map.of(), "new_game.html");
    }

    private ModelAndView loadFromSave(State now) {
        if (now.isWhite()) {
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
        boardDTO.update(next.getBoard());
        if (next.isRunning()) {
            stateDAO.initializeID();
            stateDAO.initializeColor();
            boardDAO.initializePieces(next);
            return new ModelAndView(boardDTO.getData(), "white.html");
        }
        return new ModelAndView(Map.of(), "finished_before_start.html");
    }

    public void runTurn() {
        post("/move", (req, res) -> {
            State now = getState(stateDAO, boardDAO);
            try {
                Command command = CommandConverter.convertCommand(RequestParser.from(req.body()).getCommand());
                return executeOneTurn(res, command);
            } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException exception) {
                return handleRunningException(now, exception.getMessage());
            }
        }, new HandlebarsTemplateEngine());
    }

    private ModelAndView executeOneTurn(Response res, Command command) {
        State now = getState(stateDAO, boardDAO);
        State next = now.proceed(command);
        if (next.isFinished()) {
            boardDTO.update(next.getBoard());
            return killGame(res);
        }
        if (command.isStatus()) {
            return continueToStatus(next);
        }
        return endTurn(next, command);
    }

    private ModelAndView continueToStatus(State next) {
        boardDTO.updateWithScore(next.getBoard(), next.generateScore());
        return new ModelAndView(boardDTO.getData(), "status.html");
    }

    private ModelAndView endTurn(State next, Command command) {
        boardDTO.update(next.getBoard());
        movePieceIntoDB(next, command);
        if (!next.isWhite()) {
            stateDAO.convertColor(Color.BLACK);
            return new ModelAndView(boardDTO.getData(), "black.html");
        }
        stateDAO.convertColor(Color.WHITE);
        return new ModelAndView(boardDTO.getData(), "white.html");
    }

    private void movePieceIntoDB(State next, Command command) {
        Position from = command.getFromPosition();
        Position to = command.getToPosition();
        boardDAO.delete(from);
        boardDAO.delete(to);
        boardDAO.insert(to, next.getBoard().findPiece(to));
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
            State now = getState(stateDAO, boardDAO);
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
        post("/saved", (req, res) -> {
            return new ModelAndView(boardDTO.getData(), "saved.html");
        }, new HandlebarsTemplateEngine());
    }

    public void terminateGame() {
        get("/finished", (req, res) -> {
            return new ModelAndView(boardDTO.getData(), "finished.html");
        }, new HandlebarsTemplateEngine());
    }

    private ModelAndView killGame(Response res) {
        stateDAO.terminateDB();
        res.redirect("/finished");
        return null;
    }
}
