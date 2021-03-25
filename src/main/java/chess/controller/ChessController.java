package chess.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import chess.controller.dto.request.MoveRequestDTO;
import chess.controller.dto.response.ResponseDTO;
import chess.service.ChessService;
import chess.view.OutputView;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class ChessController {
    private static final String ROOT = "/";
    private static final String START = "start";
    private static final String MOVE = "move";
    private static final String STATUS = "status";
    private static final String END_COMMAND_INPUT = "end";
    private static final String CHESS_BOARD_VIEW = "chess-board.html";
    private static final String HOME_VIEW = "index.html";
    private static final String RESPONSE_DTO = "responseDTO";

    private final ChessService chessService;

    public ChessController(ChessService chessService) {
        this.chessService = chessService;
    }

    public void run() {
        handleHomeRequest();
        handleStartRequest();
        handleMoveRequest();
        handleStatusRequest();
        handleEndRequest();
    }

    private void handleHomeRequest() {
        get(ROOT, (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return render(model, HOME_VIEW);
        });
    }

    private void handleStartRequest() {
        get(ROOT + START, (req, res) -> {
            OutputView.printGameStartMessage();
            ResponseDTO responseDTO = getResponseWhenRequestStart();
            Map<String, Object> model = new HashMap<>();
            model.put(RESPONSE_DTO, responseDTO);
            return render(model, CHESS_BOARD_VIEW);
        });
    }

    private ResponseDTO getResponseWhenRequestStart() {
        ResponseDTO responseDTO = chessService.getResponseWhenRequestStart();
        OutputView.printBoard(responseDTO);
        return responseDTO;
    }

    private void handleMoveRequest() {
        post(ROOT + MOVE, (req, res) -> {
            MoveRequestDTO moveRequestDTO = new MoveRequestDTO(
                req.queryParams("startPosition"),
                req.queryParams("destination")
            );
            ResponseDTO responseDTO = getResponseWhenRequestMove(moveRequestDTO);
            Map<String, Object> model = new HashMap<>();
            model.put(RESPONSE_DTO, responseDTO);
            return render(model, CHESS_BOARD_VIEW);
        });
    }

    private ResponseDTO getResponseWhenRequestMove(MoveRequestDTO moveRequestDTO) {
        ResponseDTO responseDTO = chessService.getResponseWhenRequestMove(moveRequestDTO);
        OutputView.printBoard(responseDTO);
        return responseDTO;
    }

    private void handleStatusRequest() {
        get(ROOT + STATUS, (req, res) -> {
            ResponseDTO responseDTO = getResponseWhenRequestStatus();
            Map<String, Object> model = new HashMap<>();
            model.put(RESPONSE_DTO, responseDTO);
            return render(model, CHESS_BOARD_VIEW);
        });
    }

    private ResponseDTO getResponseWhenRequestStatus() {
        ResponseDTO responseDTO = chessService.getScores();
        OutputView.printScores(responseDTO);
        return responseDTO;
    }

    private void handleEndRequest() {
        get(ROOT + END_COMMAND_INPUT, (req, res) -> {
            chessService.endGame();
            Map<String, Object> model = new HashMap<>();
            return render(model, HOME_VIEW);
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
