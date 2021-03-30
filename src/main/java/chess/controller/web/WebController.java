package chess.controller.web;


import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.controller.dto.request.MoveRequestDTO;
import chess.controller.dto.response.BoardResponseDTO;
import chess.controller.dto.response.ChessGameResponseDTO;
import chess.controller.dto.response.MoveResponseDTO;
import chess.controller.dto.response.ResponseDTO;
import chess.service.ChessWebService;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebController {
    private static final String ROOT = "/";
    private static final String CREATE_CHESS_ROOM = "create-chess-room";
    private static final String CHESS_BOARD = "chess-board";
    private static final String MOVE = "move";
    private static final String DELETE = "delete";
    private static final String CHESS_BOARD_VIEW = "chess-board.html";
    private static final String HOME_VIEW = "index.html";
    private static final String RESPONSE_DTO = "responseDTO";
    private static final String APPLICATION_JSON = "application/json";

    private final ChessWebService chessWebService;
    private final Gson gson;

    public WebController(ChessWebService chessWebService) {
        this.chessWebService = chessWebService;
        this.gson = new Gson();
    }

    public void run() {
        staticFiles.location("/public");
        handleHomeRequest();
        handleCreateChessRoomRequest();
        handleGetChessBoardRequest();
        handleMoveRequest();
        handleDeleteRequest();
    }

    private void handleHomeRequest() {
        get(ROOT, (req, res) -> {
            List<ChessGameResponseDTO> allRoomsIdAndTitle = chessWebService.getAllRoomsIdAndTitle();
            Map<String, Object> model = new HashMap<>();
            model.put("allChessGameRooms", allRoomsIdAndTitle);
            return render(model, HOME_VIEW);
        });
    }

    private void handleCreateChessRoomRequest() {
        post(ROOT + CREATE_CHESS_ROOM, (req, res) -> {
            Long createdChessGameId
                = chessWebService.createNewChessGame(req.queryParams("room-title"));
            res.redirect(ROOT + CHESS_BOARD + "?id=" + createdChessGameId);
            return null;
        });
    }

    private void handleGetChessBoardRequest() {
        get(ROOT + CHESS_BOARD, (req, res) -> {
            Long gameId = Long.valueOf(req.queryParams("id"));
            ResponseDTO responseDTO = chessWebService.getGameStatus(gameId);
            Map<String, Object> model = new HashMap<>();
            model.put(RESPONSE_DTO, responseDTO);
            putBoardRanksToModel(model, responseDTO.getBoardResponseDTO());
            return render(model, CHESS_BOARD_VIEW);
        });
    }

    private void putBoardRanksToModel(Map<String, Object> model,
        BoardResponseDTO boardResponseDTO) {

        model.put("rank8", boardResponseDTO.getRank8());
        model.put("rank7", boardResponseDTO.getRank7());
        model.put("rank6", boardResponseDTO.getRank6());
        model.put("rank5", boardResponseDTO.getRank5());
        model.put("rank4", boardResponseDTO.getRank4());
        model.put("rank3", boardResponseDTO.getRank3());
        model.put("rank2", boardResponseDTO.getRank2());
        model.put("rank1", boardResponseDTO.getRank1());
    }

    private void handleMoveRequest() {
        post(ROOT + MOVE, APPLICATION_JSON, (req, res) -> {
            MoveRequestDTO moveRequestDTO = gson.fromJson(req.body(), MoveRequestDTO.class);
            MoveResponseDTO moveResponse = chessWebService.requestMove(moveRequestDTO);
            res.type(APPLICATION_JSON);
            return gson.toJson(moveResponse);
        });
    }

    private void handleDeleteRequest() {
        get(ROOT + DELETE, (req, res) -> {
            Long gameId = Long.valueOf(req.queryParams("id"));
            chessWebService.deleteGame(gameId);
            res.redirect(ROOT);
            return null;
        });
    }

    private static String render(Map<String, Object> model, String templatePath) {
        return new HandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
