package chess.controller.web;

import chess.service.RequestHandler;
import chess.service.RoomService;
import chess.view.web.OutputView;

import java.sql.Connection;

import static spark.Spark.get;
import static spark.Spark.post;

public class RoomController {
    private final RoomService roomService;

    public RoomController(Connection connection) {
        this.roomService = new RoomService(connection);
    }

    public void mapping() {
        loadRoomList();
        deleteRoom();
        createRoom();
    }

    private void loadRoomList() {
        get("/main", (req, res) ->
                OutputView.printRoomList(roomService.loadList()));
    }

    private void createRoom() {
        get("/room/create/:roomName", (req, res) -> {
            final String roomName = RequestHandler.roomName(req);
            final Long roomId = roomService.save(roomName);

            res.redirect("/game/create/" + roomId);
            return null;
        });
    }

    private void deleteRoom() {
        post("/room/delete/:roomId", (req, res) -> {
            final String roomName = RequestHandler.roomName(req);
            final Long roomId = roomService.roomId(roomName);
            roomService.delete(roomId);
            res.redirect("/game/delete/"+roomId);
            return null;
        });
    }
}