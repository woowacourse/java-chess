package chess.controller;


import static spark.Spark.get;
import static spark.Spark.post;

import chess.controller.dto.request.MoveRequestDTO;
import chess.controller.dto.response.BoardResponseDTO;
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
        handleEndRequest();
    }

    private void handleHomeRequest() {
        get(ROOT, (req, res) -> {
            OutputView.printGameStartMessage();
            Map<String, Object> model = new HashMap<>();
            return render(model, HOME_VIEW);
        });
    }

    private void handleStartRequest() {
        get(ROOT + START, (req, res) -> {
            ResponseDTO responseDTO = getResponseWhenRequestStart();
            Map<String, Object> model = new HashMap<>();
            model.put(RESPONSE_DTO, responseDTO);
            putBoardRanks(responseDTO.getBoardResponseDTO(), model);
            return render(model, CHESS_BOARD_VIEW);
        });
    }

    private void putBoardRanks(BoardResponseDTO boardResponseDTO, Map<String, Object> model) {
        model.put("rank8", boardResponseDTO.getRank8());
        model.put("rank7", boardResponseDTO.getRank7());
        model.put("rank6", boardResponseDTO.getRank6());
        model.put("rank5", boardResponseDTO.getRank5());
        model.put("rank4", boardResponseDTO.getRank4());
        model.put("rank3", boardResponseDTO.getRank3());
        model.put("rank2", boardResponseDTO.getRank2());
        model.put("rank1", boardResponseDTO.getRank1());
    }

    private ResponseDTO getResponseWhenRequestStart() {
        ResponseDTO responseDTO = chessService.getResponseWhenRequestStart();
        OutputView.printBoard(responseDTO);
        return responseDTO;
    }

    private void handleMoveRequest() {
        post(ROOT + MOVE, (req, res) -> {
            MoveRequestDTO moveRequestDTO = new MoveRequestDTO(req.queryParams("startPosition"),
                req.queryParams("destination"));
            ResponseDTO responseDTO = getResponseWhenRequestMove(moveRequestDTO);
            Map<String, Object> model = new HashMap<>();
            model.put(RESPONSE_DTO, responseDTO);
            putBoardRanks(responseDTO.getBoardResponseDTO(), model);
            return render(model, CHESS_BOARD_VIEW);
        });
    }

    private ResponseDTO getResponseWhenRequestMove(MoveRequestDTO moveRequestDTO) {
        ResponseDTO responseDTO = handleMoveError(moveRequestDTO);
        OutputView.printBoard(responseDTO);
        OutputView.printScores(responseDTO);
        return responseDTO;
    }

    private ResponseDTO handleMoveError(MoveRequestDTO moveRequestDTO) {
        ResponseDTO responseDTO;
        try {
            responseDTO = chessService.getResponseWhenRequestMove(moveRequestDTO);
        } catch (Exception e) {
            responseDTO = chessService.getCurrentBoard();
            responseDTO.setCannotMove(true);
            responseDTO.setCannotMoveErrorMessage(e.getMessage());
        }
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
