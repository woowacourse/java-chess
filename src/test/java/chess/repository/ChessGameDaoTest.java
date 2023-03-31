package chess.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import chess.domain.game.state.MovingState;
import chess.domain.game.state.StartState;
import chess.mysql.JdbcTemplate;
import chess.mysql.TestConnectionPool;
import chess.repository.chess.ChessGameDao;
import chess.repository.user.UserDao;
import chess.repository.user.UserDto;
import java.util.Optional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@SuppressWarnings({"NonAsciiCharacters", "SpellCheckingInspection"})
@DisplayNameGeneration(ReplaceUnderscores.class)
@TestMethodOrder(OrderAnnotation.class)
class ChessGameDaoTest {

    private static final UserDao userDao = new UserDao(new JdbcTemplate(new TestConnectionPool()));

    private static int userId;
    private final ChessGameDao chessGameDao = new ChessGameDao(new JdbcTemplate(new TestConnectionPool()));

    @BeforeAll
    static void 사용자_생성() {
        userDao.deleteByUserName("ChessGameDaoTest");
        userDao.save("ChessGameDaoTest");

        Optional<UserDto> userDto = userDao.findUserIdIfExist("ChessGameDaoTest");
        if (userDto.isEmpty()) {
            throw new IllegalArgumentException("사용자가 존재하지 않습니다.");
        }
        userId = userDto.get().getUserId();
    }

    @Test
    @Order(1)
    void save를_통해_새로운_보드를_생성할_수_있다() {
        //expect
        assertDoesNotThrow(() -> chessGameDao.save(userId, StartState.getInstance()));
    }

    @Test
    @Order(2)
    void findBoardIdsByUserId() {
        //expect
        assertThat(chessGameDao.findBoardIdsByUserId(userId)).isNotEmpty();
    }


    @Test
    @Order(3)
    void delete() {
        //given
        int boardId = chessGameDao.findBoardIdsByUserId(userId).get(0);

        //expect
        assertDoesNotThrow(() -> chessGameDao.delete(boardId));
    }

    @Test
    @Order(4)
    void update() {
        //given
        int boardId = chessGameDao.save(userId, StartState.getInstance());
        chessGameDao.update(boardId, MovingState.getInstance());

        //expect
        assertThat(chessGameDao.findStatusByBoardId(boardId)).contains(MovingState.getInstance().getStateName());
    }
}
