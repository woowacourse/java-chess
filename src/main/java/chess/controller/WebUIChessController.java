package chess.controller;

import chess.domain.board.ChessBoard;
import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.feature.Color;
import chess.domain.feature.Type;
import chess.domain.game.ChessGame;
import chess.domain.game.Result;
import chess.domain.gamestate.Ready;
import chess.domain.piece.Piece;
import chess.domain.room.Room;
import chess.domain.room.RoomDAO;
import chess.view.OutputView;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebUIChessController {
    private final RoomDAO roomDAO = new RoomDAO();
    private final Gson gson = new Gson();
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
//            Room room = gson.fromJson(request, Room.class);
            JsonObject roomJson = gson.fromJson(request, JsonObject.class);
            String id = roomJson.get("room_id").getAsString();
            JsonObject state = roomJson.get("state").getAsJsonObject();
            Room room = new Room(id, state);
            roomDAO.addRoom(room);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public Map<String, Object> loadRoom(String request) {
        try {
            JsonObject roomJson = gson.fromJson(request, JsonObject.class);
            String id = roomJson.get("roomId").getAsString();
            Room room = roomDAO.findByRoomId(id);
            verifyRoom(room);
            //TODO
            String state = roomJson.get("state").getAsString();
            return getSavedRoomToRender(state);
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

    private Map<String, Object> getSavedRoomToRender(String state) {
        Map<String, Object> model = new HashMap<>();
        JsonObject stateJson = gson.fromJson(state, JsonObject.class);
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                String position = "" + column.getColumn() + row.getRow();
                JsonObject pieceJson = gson.fromJson(stateJson.get(position), JsonObject.class);
                String type = pieceJson.get("type").getAsString();
                String color = pieceJson.get("color").getAsString();

                Type pieceType = Type.convert(type);
                Piece piece = pieceType.createPiece(Position.of(position), Color.convert(color));
                model.put(position, piece);
            }
        }
        model.put("turn", stateJson.get("turn"));
        model.put("result", stateJson.get("result"));
        return model;
    }

    public void gameResult() {
        try {
            Result result = chessGame.calculateResult();
            OutputView.printResult(result);
            chessBoard();
        } catch (UnsupportedOperationException ignored) {
        }
    }
}
