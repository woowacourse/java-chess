package chess.domain.userAccess;

import chess.domain.userAccess.room.Room;
import chess.domain.userAccess.room.RoomDao;
import chess.domain.userAccess.user.User;
import chess.domain.userAccess.user.UserDao;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserAccessService {

    private final UserDao userDao;
    private final RoomDao roomDao;

    public UserAccessService(UserDao userDao, RoomDao roomDao) {
        this.userDao = userDao;
        this.roomDao = roomDao;
    }

    public User findUserById(String userId) {
        Optional<User> user = userDao.findUserById(userId);
        if (user.isPresent()) {
            return user.get();
        }
        User newUser = new User(userId);
        userDao.createUser(newUser);
        return newUser;
    }

    public List<Room> findRoomsByUser(User user) {
        List<Room> rooms = roomDao.findRoomsByUser(user);
        return Collections.unmodifiableList(rooms);
    }

    public Room makeRoomByUser(User user) {
        roomDao.createRoom(user);
        return roomDao.findRoomByUserIdAndCommands(user.userId(), "");
    }

    public void updateRoomById(int roomId, String commands) {
        if (commands.isEmpty()) {
            roomDao.deleteRoomByRoomId(roomId);
        }
        roomDao.updateRoomByRoomId(roomId, commands);
    }
}
