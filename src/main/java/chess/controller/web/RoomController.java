package chess.controller.web;

import chess.service.RequestHandler;
import chess.service.RoomService;
import chess.view.web.OutputView;

import java.sql.Connection;

import static spark.Spark.get;

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
        get("/main", (req, res) -> {
            res.status(200);
            return OutputView.printRoomList(roomService.loadList());
        });
    }

    private void createRoom() {
        get("/room/create/:roomName", (req, res) -> {
            final String roomName = RequestHandler.roomName(req);
            final Long roomId = roomService.save(roomName);

            res.status(200);
            res.redirect("/game/create/" + roomId);
            return null;
        });
    }

    private void deleteRoom() {
        get("/room/delete/:roomId", (req, res) -> {
            final Long roomId = RequestHandler.roomId(req);
            roomService.delete(roomId);

            res.status(200);
            res.redirect("/game/delete/" + roomId);
            return null;
        });
    }
}