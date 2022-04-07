package chess.controller;

import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

import chess.domain.ChessBoard;
import chess.domain.state.GameState;
import chess.domain.state.Ready;
import chess.dto.web.ChessBoardDto;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {
    private static final int PORT_NUMBER = 8080;
    private static final String VIEW = "index.html";
    private static final String CHESS_BOARD_KEY = "chessboard";

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
    }

    private ChessBoardDto getChessBoard() {
        ChessBoard chessBoard = gameState.getChessBoard();
        return ChessBoardDto.of(chessBoard);
    }
}
