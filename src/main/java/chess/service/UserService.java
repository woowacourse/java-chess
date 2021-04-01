package chess.service;

import chess.dao.UserDAO;
import chess.dto.UsersDTO;

import java.sql.SQLException;

public class UserService {
    private final UserDAO userDAO;

    public UserService(final UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UsersDTO usersParticipatedInGame(final String roomId) throws SQLException {
        return userDAO.findByRoomId(roomId);
    }

    public int userIdByNickname(final String nickname) throws SQLException {
        return userDAO.findUserIdByNickname(nickname);
    }
}
