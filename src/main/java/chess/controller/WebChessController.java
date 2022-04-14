package chess.controller;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFileLocation;

import chess.controller.request.MoveRequest;
import chess.domain.ChessBoardPosition;
import chess.dto.web.ChessBoardDto;
import chess.service.ChessService;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebChessController {
    private static final int PORT_NUMBER = 8080;
    private static final int BAD_REQUEST = 400;
    private static final String VIEW_PAGE = "index.html";
    private static final String SOURCE_POSITION_PARAMETER_KEY = "from";
    private static final String TARGET_POSITION_PARAMETER_KEY = "to";
    private static final String CHESS_BOARD_KEY = "chessboard";

    private final ChessService chessService;

    public WebChessController() {
        this.chessService = new ChessService();
    }

    public void run() {
        port(PORT_NUMBER);
        staticFileLocation("/static");

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            ChessBoardDto chessBoardDto = chessService.getChessBoard();
            model.put(CHESS_BOARD_KEY, chessBoardDto);
            return render(model);
        });

        get("/start", (req, res) -> {
            ChessBoardDto chessBoardDto = chessService.createChessGame();

            Map<String, Object> model = new HashMap<>();
            model.put(CHESS_BOARD_KEY, chessBoardDto);
            return render(model);
        });

        get("/end", (req, res) -> {
            ChessBoardDto chessBoardDto = chessService.deleteChessGame();

            Map<String, Object> model = new HashMap<>();
            model.put(CHESS_BOARD_KEY, chessBoardDto);
            return render(model);
        });

        post("/move", (req, res) -> {
            MoveRequest moveRequest = MoveRequest.of(req.body());
            ChessBoardPosition sourcePosition = ChessBoardPosition.from(moveRequest.get(SOURCE_POSITION_PARAMETER_KEY));
            ChessBoardPosition targetPosition = ChessBoardPosition.from(moveRequest.get(TARGET_POSITION_PARAMETER_KEY));

            Map<String, Object> model = new HashMap<>(chessService.updateChessBoard(sourcePosition, targetPosition));
            return render(model);
        });

        get("/status", (req, res) -> {
            Map<String, Object> model = new HashMap<>(chessService.getStatus());
            return render(model);
        });

        exception(Exception.class, (exception, request, response) -> {
            response.status(BAD_REQUEST);
            response.body(exception.getMessage());
        });
    }

    private String render(Map<String, Object> model) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, VIEW_PAGE));
    }
}
