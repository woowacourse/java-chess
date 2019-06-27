package chess.dao;

import chess.dto.UserDto;

import java.util.List;

public interface UserDao {
    List<UserDto> findByGameId(int gameId);

    int addUser(UserDto userDto);

    int deleteUserByGameId(int gameId);
}
