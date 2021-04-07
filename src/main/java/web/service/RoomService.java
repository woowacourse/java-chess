package web.service;

import web.dao.RoomDao;

import java.sql.SQLException;
import java.util.List;

public class RoomService {
    private static final RoomDao roomDao = new RoomDao();

    public List<Integer> roomIdS() throws SQLException {
        return roomDao.findRoomIds();
    }

    public int newRoomId() throws SQLException {
        return roomDao.newRoomId();
    }
}
