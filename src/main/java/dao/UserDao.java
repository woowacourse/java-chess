package dao;

import dto.UserDto;

public interface UserDao {
    UserDto findByGameId(int gameId);
    int addUser(String userName,int teamId, int gameId);
    int deleteUserByGameId(int gameId);
}
