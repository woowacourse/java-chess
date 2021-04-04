package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.feature.Color;
import chess.domain.feature.Type;
import chess.domain.game.ChessGame;
import chess.domain.gamestate.Ready;
import chess.domain.gamestate.Running;
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
    public static final Gson gson = new Gson();

    private final RoomDAO roomDAO = new RoomDAO();
    private ChessGame chessGame;

    public Response createRoom(String roomId) {
        try {
            roomDAO.validateRoomExistence(roomId);
            initializeChessBoard();
            return new Response(chessGame, roomId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Response response = new Response(409);
            response.add("alert", "는 이미 존재하는 방입니다.");
            return response;
        }
    }

    public Response movePiece(List<String> input) {
        try {
            chessGame.play(input);
            return new Response(chessGame);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Response(400);
        }
    }

    private void initializeChessBoard() {
        ChessBoard chessBoard = new ChessBoard();
        chessGame = new ChessGame(chessBoard, Color.WHITE, new Ready());
        chessGame.start(Collections.singletonList("start"));
    }

    public Response saveRoom(String request) {
        try {
            Room room = createRoomToSave(request);
            roomDAO.addRoom(room);
            return new Response(200);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Response(500);
        }
    }

    private Room createRoomToSave(String request) {
        JsonObject roomJson = gson.fromJson(request, JsonObject.class);
        String id = roomJson.get("room_id").getAsString();
        String turn = roomJson.get("turn").getAsString();
        JsonObject state = roomJson.get("state").getAsJsonObject();
        return new Room(id, turn, state);
    }

    public Response loadRoom(String request) {
        try {
            JsonObject roomJson = gson.fromJson(request, JsonObject.class);
            String roomId = roomJson.get("room_id").getAsString();
            Room room = roomDAO.findByRoomId(roomId);
            setChessGame(room);
            return new Response(chessGame, roomId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Response(400);
        }
    }

    private void setChessGame(Room room) {
        ChessBoard chessBoard = new ChessBoard();
        JsonObject stateJson = room.getState();
        Color turn = Color.convert(room.getTurn());
        for (String key : stateJson.keySet()) {
            Piece piece = getPiece(stateJson, key);
            chessBoard.replace(Position.of(key), piece);
        }
        chessGame = new ChessGame(chessBoard, turn, new Running());
    }

    private Piece getPiece(JsonObject stateJson, String key) {
        JsonObject pieceJson = gson.fromJson(stateJson.get(key), JsonObject.class);
        String type = pieceJson.get("type").getAsString();
        String color = pieceJson.get("color").getAsString();

        Type pieceType = Type.convert(type);
        return pieceType.createPiece(Position.of(key), Color.convert(color));
    }

    public Response getSavedRooms() {
        try {
            Map<String, Object> model = new HashMap<>();
            model.put("rooms", roomDAO.getAllRoom());
            Response response = new Response();
            response.add("rooms", roomDAO.getAllRoom());
            return response;
        } catch (Exception e) {
            return new Response();
        }
    }

    public Response resetGameState() {
        initializeChessBoard();
        return new Response();
    }
}
