package chess.beforedb.controller.web;


import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.beforedb.controller.dto.request.MoveRequestDTO;
import chess.beforedb.controller.dto.response.BoardResponseDTO;
import chess.beforedb.controller.dto.response.MoveResponse;
import chess.beforedb.controller.dto.response.ResponseDTO;
import chess.beforedb.service.ChessService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIController {
    private static final String ROOT = "/";
    private static final String START = "start";
    private static final String CHESS_BOARD = "chess-board";
    private static final String MOVE = "move";
    private static final String END_COMMAND_INPUT = "end";
    private static final String CHESS_BOARD_VIEW = "chess-board.html";
    private static final String HOME_VIEW = "index.html";
    private static final String RESPONSE_DTO = "responseDTO";
    private static final String APPLICATION_JSON = "application/json";

    private final ChessService chessService;
    private final Gson gson;

    public WebUIController(ChessService chessService) {
        this.chessService = chessService;
        this.gson = new Gson();
    }

    public void run() {
        staticFiles.location("/public");
        handleHomeRequest();
        handleStartRequest();
        handleBoardRequest();
        handleMoveRequest();
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
            chessService.start();
            res.redirect(ROOT + CHESS_BOARD);
            return null;
        });
    }

    private void handleBoardRequest() {
        get(ROOT + CHESS_BOARD, (req, res) -> {
            ResponseDTO responseDTO = chessService.getCurrentBoard();
            Map<String, Object> model = new HashMap<>();
            model.put(RESPONSE_DTO, responseDTO);
            putBoardRanks(responseDTO.getBoardResponseDTO(), model);
            return render(model, CHESS_BOARD_VIEW);
        });
    }

    private void handleMoveRequest() {
        post(ROOT + MOVE, APPLICATION_JSON, (req, res) -> {
            MoveRequestDTO moveRequestDTO = gson.fromJson(req.body(), MoveRequestDTO.class);
            MoveResponse moveResponse = chessService.requestMove(moveRequestDTO);
            res.type(APPLICATION_JSON);
            return gson.toJson(moveResponse);
        });
    }

    private void handleEndRequest() {
        get(ROOT + END_COMMAND_INPUT, (req, res) -> {
            chessService.endGame();
            res.redirect(ROOT);
            return null;
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

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
