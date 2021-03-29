package chess.db.controller;


import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.staticFiles;

import chess.beforedb.controller.dto.request.MoveRequestDTO;
import chess.beforedb.controller.dto.response.MoveResponse;
import chess.db.controller.dto.request.MoveRequestDTOForDB;
import chess.db.controller.dto.response.BoardResponseDTOForDB;
import chess.db.controller.dto.response.MoveResponseDTOForDB;
import chess.db.controller.dto.response.ResponseDTOForDB;
import chess.db.domain.game.ChessGameResponseDTO;
import chess.db.service.ChessServiceForDB;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class WebUIDBController {
    private static final String ROOT = "/";
    private static final String CREATE_CHESS_ROOM = "create-chess-room";
    private static final String CHESS_BOARD = "chess-board";
    private static final String MOVE = "move";
    private static final String END = "end";
    private static final String CHESS_BOARD_VIEW = "chess-board.html";
    private static final String HOME_VIEW = "index.html";
    private static final String RESPONSE_DTO = "responseDTO";
    private static final String APPLICATION_JSON = "application/json";

    private final ChessServiceForDB chessServiceForDB;
    private final Gson gson;

    public WebUIDBController(ChessServiceForDB chessServiceForDB) {
        this.chessServiceForDB = chessServiceForDB;
        this.gson = new Gson();
    }

    public void run() {
        staticFiles.location("/public");
        handleHomeRequest();
        handleCreateChessRoomRequest();
        handleEnterChessRoomRequest();
        handleGetBoardRequest();
        handleMoveRequest();
        handleEndRequest();
    }

    private void handleHomeRequest() {
        get(ROOT, (req, res) -> {
            List<ChessGameResponseDTO> allRoomsIdAndTitle
                = chessServiceForDB.getAllRoomsIdAndTitle();
            Map<String, Object> model = new HashMap<>();
            model.put("allChessGameRooms", allRoomsIdAndTitle);
            return render(model, HOME_VIEW);
        });
    }

    private void handleCreateChessRoomRequest() {
        post(ROOT + CREATE_CHESS_ROOM, (req, res) -> {
            chessServiceForDB.createNewChessGame(req.queryParams("room-title"));
            res.redirect(ROOT + CHESS_BOARD);
            return null;
        });
    }

    private void handleEnterChessRoomRequest() {
        get(ROOT + CHESS_BOARD, (req, res) -> {
            String roomIdToEnter = req.queryParams("id");
            res.redirect(ROOT + CHESS_BOARD + "?id=" + roomIdToEnter);
            return null;
        });
    }

    private void handleGetBoardRequest() {
        get(ROOT + CHESS_BOARD, (req, res) -> {
            Long roomId = Long.valueOf(req.queryParams("id"));
            ResponseDTOForDB responseDTO = chessServiceForDB.getGameStatus(roomId);
            Map<String, Object> model = new HashMap<>();
            model.put(RESPONSE_DTO, responseDTO);
            putBoardRanks(responseDTO.getBoardResponseDTO(), model);
            return render(model, CHESS_BOARD_VIEW);
        });
    }

    private void handleMoveRequest() {
        post(ROOT + MOVE, APPLICATION_JSON, (req, res) -> {
            MoveRequestDTOForDB moveRequestDTO = gson.fromJson(req.body(), MoveRequestDTOForDB.class);
            MoveResponseDTOForDB moveResponse = chessServiceForDB.requestMove(moveRequestDTO);
            res.type(APPLICATION_JSON);
            return gson.toJson(moveResponse);
        });
    }

    private void handleEndRequest() {
        get(ROOT + END, (req, res) -> {
            Long roomId = Long.valueOf(req.queryParams("id"));
            chessServiceForDB.endGame(roomId);
            res.redirect(ROOT);
            return null;
        });
    }

    private void putBoardRanks(BoardResponseDTOForDB boardResponseDTO, Map<String, Object> model) {
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
