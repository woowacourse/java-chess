package chess.controller;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import chess.domain.ChessBoard;
import chess.domain.ChessBoardPosition;
import chess.domain.Team;
import chess.domain.state.GameState;
import chess.domain.state.Ready;
import chess.dto.ChessStatusDto;
import chess.dto.web.ChessBoardDto;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {
    private static final int PORT_NUMBER = 8080;
    private static final int BAD_REQUEST = 400;
    private static final String VIEW = "index.html";
    private static final String SOURCE_POSITION_PARAMETER_KEY = "from";
    private static final String TARGET_POSITION_PARAMETER_KEY = "to";
    private static final String CHESS_BOARD_KEY = "chessboard";
    private static final String FINISH_MESSAGE_KEY = "finishMessage";
    private static final String STATUS_KEY = "status";

    private GameState gameState;

    public void run() {
        gameState = new Ready();

        port(PORT_NUMBER);
        staticFileLocation("/static");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put(CHESS_BOARD_KEY, getChessBoard());
            return new ModelAndView(model, VIEW);
        }, new HandlebarsTemplateEngine());

        get("/start", (req, res) -> {
            gameState = gameState.start();
            Map<String, Object> model = new HashMap<>();
            model.put(CHESS_BOARD_KEY, getChessBoard());
            return new ModelAndView(model, VIEW);
        }, new HandlebarsTemplateEngine());

        get("/end", (req, res) -> {
            gameState = new Ready();
            Map<String, Object> model = new HashMap<>();
            model.put(CHESS_BOARD_KEY, getChessBoard());
            return new ModelAndView(model, VIEW);
        }, new HandlebarsTemplateEngine());

        get("/move", (req, res) -> {
            ChessBoardPosition sourcePosition = ChessBoardPosition.from(req.queryParams(SOURCE_POSITION_PARAMETER_KEY));
            ChessBoardPosition targetPosition = ChessBoardPosition.from(req.queryParams(TARGET_POSITION_PARAMETER_KEY));
            gameState = gameState.move(sourcePosition, targetPosition);
            Map<String, Object> model = new HashMap<>();
            model.put(CHESS_BOARD_KEY, getChessBoard());
            if (gameState.isFinished()) {
                model.putAll(getFinishStatus());
                gameState = new Ready();
            }
            return new ModelAndView(model, VIEW);
        }, new HandlebarsTemplateEngine());

        get("/status", (req, res) -> {
            gameState = gameState.status();
            Map<String, Object> model = new HashMap<>();
            model.put(CHESS_BOARD_KEY, getChessBoard());
            model.put(STATUS_KEY, getStatus());
            return new ModelAndView(model, VIEW);
        }, new HandlebarsTemplateEngine());

        exception(Exception.class, (exception, request, response) -> {
            response.status(BAD_REQUEST);
            response.body(exception.getMessage());
        });
    }

    private ChessBoardDto getChessBoard() {
        ChessBoard chessBoard = gameState.getChessBoard();
        return ChessBoardDto.of(chessBoard);
    }

    private Map<String, Object> getFinishStatus() {
        Map<String, Object> finishStatus = new HashMap<>();
        finishStatus.put(STATUS_KEY, getStatus());
        finishStatus.put(FINISH_MESSAGE_KEY, true);
        return finishStatus;
    }

    private ChessStatusDto getStatus() {
        ChessBoard chessBoard = gameState.getChessBoard();
        Team winner = gameState.findWinner();
        return ChessStatusDto.of(chessBoard, winner);
    }
}
