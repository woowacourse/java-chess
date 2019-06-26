package dao;

import chess.domain.DBConnector;
import dto.UserDto;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserDaoTest {

    @Test
    void UserCRDTest() {
        DBConnector.setDATABASE("chess_test_db");
        GameDao gameDao = GameDaoImpl.getInstance();
        UserDao userDao = UserDaoImpl.getInstance();

        int gameId = gameDao.addGame();

        UserDto userDto = new UserDto(gameId,"지토",1);
        UserDto userDto2 = new UserDto(gameId,"로비",2);
        assertThat(userDao.addUser(userDto)).isEqualTo(1);
        assertThat(userDao.addUser(userDto2)).isEqualTo(1);

        assertThat(userDao.findByGameId(gameId)).isEqualTo(Arrays.asList(userDto,userDto2));

        assertThat(userDao.deleteUserByGameId(gameId)).isEqualTo(2);

        gameDao.deleteGameByid(gameId);
        DBConnector.setDATABASE("chess_db");
    }
}
