package chess.service;

import chess.dao.UserDAO;
import chess.dto.UsersDTO;

public final class UserService {
    private final UserDAO userDAO;

    public UserService(final UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public UsersDTO usersParticipatedInGame(final String roomId) {
        return userDAO.findByRoomId(roomId);
    }

    public int userIdByNickname(final String nickname) {
        return userDAO.findUserIdByNickname(nickname);
    }
}
