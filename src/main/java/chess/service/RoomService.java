package chess.service;

import chess.repository.RoomDao;

public class RoomService {
    private final RoomDao roomDao;

    public RoomService(final RoomDao roomDao) {
        this.roomDao = roomDao;
    }
}
