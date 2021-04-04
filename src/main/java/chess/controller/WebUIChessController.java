package chess.controller;

import chess.database.room.Room;
import chess.database.room.RoomDAO;
import chess.domain.board.ChessBoard;
import chess.domain.board.Position;
import chess.domain.feature.Color;
import chess.domain.feature.Type;
import chess.domain.game.ChessGame;
import chess.domain.gamestate.Ready;
import chess.domain.gamestate.Running;
import chess.domain.piece.Piece;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Collections;
import java.util.List;

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
            e.printStackTrace();
            Response response = new Response(409);
            response.add("alert", roomId + "는 이미 존재하는 방입니다.");
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
        String state = roomJson.get("state").getAsString();
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
        Color turn = Color.convert(room.getTurn());
        String state = room.getState();
        JsonObject stateJson = gson.fromJson(state, JsonObject.class);
        for (String position : stateJson.keySet()) {
            Piece piece = getPiece(stateJson, position);
            chessBoard.replace(Position.of(position), piece);
        }
        chessGame = new ChessGame(chessBoard, turn, new Running());
    }

    private Piece getPiece(JsonObject stateJson, String position) {
        JsonObject pieceJson = gson.fromJson(stateJson.get(position), JsonObject.class);
        String type = pieceJson.get("type").getAsString();
        String color = pieceJson.get("color").getAsString();

        Type pieceType = Type.convert(type);
        return pieceType.createPiece(Position.of(position), Color.convert(color));
    }

    public Response getAllSavedRooms() {
        try {
            Response response = new Response();
            response.add("rooms", roomDAO.getAllRoom());
            return response;
        } catch (Exception e) {
            return new Response(502);
        }
    }

    public Response resetGameAsReadyState() {
        initializeChessBoard();
        return new Response();
    }
}
