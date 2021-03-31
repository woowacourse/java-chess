package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.feature.Color;
import chess.domain.feature.Type;
import chess.domain.game.ChessGame;
import chess.domain.game.Result;
import chess.domain.gamestate.Ready;
import chess.domain.piece.Piece;
import chess.domain.room.Room;
import chess.domain.room.RoomDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebUIChessController {
    private final Gson gson = new Gson();
    private final RoomDAO roomDAO = new RoomDAO();
    private ChessGame chessGame;

    public Map<String, Object> chessBoard() {
        if (chessGame == null) {
            initializeChessBoard();
        }
        return getRoomToRender(chessGame);
    }

    public Map<String, Object> movePiece(List<String> input) {
        try {
            chessGame.play(input);
            return getRoomToRender(chessGame);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private Map<String, Object> getRoomToRender(ChessGame chessGame) {
        Map<String, Object> model = new HashMap<>();
        Map<Position, Piece> chessBoard = chessGame.getChessBoardAsMap();
        Color turn = chessGame.getTurn();

        for (Map.Entry<Position, Piece> entry : chessBoard.entrySet()) {
            model.put(entry.getKey().getPosition(), entry.getValue());
        }
        model.put("turn", turn);

        if (chessGame.isOngoing()) {
            model.put("result", null);
            return model;
        }
        Result result = chessGame.result();
        model.put("result", result);
        initializeChessBoard();
        return model;
    }

    private void initializeChessBoard() {
        ChessBoard chessBoard = new ChessBoard();
        chessGame = new ChessGame(chessBoard, Color.WHITE, new Ready());
        chessGame.start(Collections.singletonList("start"));
    }

    public boolean saveRoom(String request) {
        try {
            Room room = createRoomToSave(request);
            roomDAO.addRoom(room);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private Room createRoomToSave(String request) {
        JsonObject roomJson = gson.fromJson(request, JsonObject.class);
        String id = roomJson.get("room_id").getAsString();
        JsonObject state = roomJson.get("state").getAsJsonObject();
        return new Room(id, state);
    }

    public Map<String, Object> loadRoom(String request) {
        try {
            JsonObject roomJson = gson.fromJson(request, JsonObject.class);
            String id = roomJson.get("room_id").getAsString();
            Room room = roomDAO.findByRoomId(id);
            verifyRoom(room);
            return getSavedRoomToRender(room);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private void verifyRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException();
        }
    }

    private Map<String, Object> getSavedRoomToRender(Room room) {
        Map<String, Object> model = new HashMap<>();
        model.put("room_id", room.getRoomId());
        JsonObject stateJson = room.getState();
        for (String key : stateJson.keySet()) {
            if ("turn".equals(key)) {
                model.put(key, stateJson.get(key));
                continue;
            }
            Piece piece = getPiece(stateJson, key);
            model.put(key, piece);
        }
        return model;
    }

    private Piece getPiece(JsonObject stateJson, String key) {
        JsonObject pieceJson = gson.fromJson(stateJson.get(key), JsonObject.class);
        String type = pieceJson.get("type").getAsString();
        String color = pieceJson.get("color").getAsString();

        Type pieceType = Type.convert(type);
        return pieceType.createPiece(Position.of(key), Color.convert(color));
    }

    public Map<String, Object> getSavedRooms() {
        try {
            Map<String, Object> model = new HashMap<>();
            model.put("rooms", roomDAO.getAllRoom());
            return model;
        } catch (Exception e) {
            return null;
        }
    }
}
