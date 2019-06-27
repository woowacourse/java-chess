package chess.service;

import chess.dao.UserDao;
import chess.dao.UserDaoImpl;
import chess.dto.UserDto;
import spark.Request;

import java.util.List;

public class UserService {
    private static UserDao userDao = UserDaoImpl.getInstance();

    private UserService() {}

    public static void addUsers(Request req, int gameId) {
        UserDto whiteUserDto = new UserDto(gameId, req.queryParams("white_user"), 2);
        UserDto blackUserDto = new UserDto(gameId, req.queryParams("black_user"), 1);
        userDao.addUser(whiteUserDto);
        userDao.addUser(blackUserDto);
    }

    public static List<UserDto> findByGameId(int gameId) {
        return userDao.findByGameId(gameId);
    }
}
