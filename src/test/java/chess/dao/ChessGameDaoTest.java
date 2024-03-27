package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.character.Team;
import chess.exception.InvalidGameRoomException;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {
    private static final String ROOM_NAME = "roomName";
    private ChessGameDao chessGameDao;
    private static Connection connection;

    @BeforeAll
    static void openConnection() {
        try {
            ConnectionGenerator connectionGenerator = new TestConnectionGenerator();
            connection = connectionGenerator.getConnection();
            connection.setAutoCommit(false);
        } catch (SQLException ignored) {
        }
    }

    @AfterAll
    static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ignored) {
        }
    }

    @BeforeEach
    void setUp() {
        chessGameDao = new ChessGameDao();
    }

    @AfterEach
    void tearDown() {
        try {
            connection.rollback();
        } catch (SQLException ignored) {
        }
    }

    @DisplayName("필드 추가")
    @Test
    void add() {
        assertThatCode(() -> chessGameDao.add(Team.WHITE, ROOM_NAME, connection))
                .doesNotThrowAnyException();
    }

    @DisplayName("입력된 방 이름이 16자를 넘어가면 예외가 발생한다.")
    @Test
    void invalidAddIfNameOver16() {
        String name = "soLongRoomNameInput";

        assertThatThrownBy(() -> chessGameDao.add(Team.WHITE, name, connection))
                .isInstanceOf(InvalidGameRoomException.class)
                .hasMessage("중복된 방 이름이 존재하거나, 방이름이 17자 이상입니다.");
    }

    @DisplayName("입력된 방 이름이 중복되면 예외가 발생한다.")
    @Test
    void invalidAddIfNameDuplicated() {
        chessGameDao.add(Team.WHITE, ROOM_NAME, connection);

        assertThatThrownBy(() -> chessGameDao.add(Team.WHITE, ROOM_NAME, connection))
                .isInstanceOf(InvalidGameRoomException.class)
                .hasMessage("중복된 방 이름이 존재하거나, 방이름이 17자 이상입니다.");
    }

    @DisplayName("방 이름으로 현재 팀을 찾는다.")
    @Test
    void findCurrentTeamByRoomName() {
        chessGameDao.add(Team.WHITE, ROOM_NAME, connection);

        Team currentTeam = chessGameDao.findCurrentTeamByRoomName(ROOM_NAME, connection);

        assertThat(currentTeam).isEqualTo(Team.WHITE);
    }

    @DisplayName("방 이름이 존재하지 않으면, 예외가 발생한다.")
    @Test
    void findCurrentTeamByInvalidRoomName() {
        String name = "noname";

        chessGameDao.add(Team.WHITE, ROOM_NAME, connection);

        assertThatThrownBy(() -> chessGameDao.findCurrentTeamByRoomName(name, connection))
                .isInstanceOf(InvalidGameRoomException.class)
                .hasMessage("존재하지 않는 방 이름입니다.");
    }

    @DisplayName("입력된 팀으로 입력된 방이름의 현재 팀을 바꾼다.")
    @Test
    void update() {
        chessGameDao.add(Team.WHITE, ROOM_NAME, connection);
        chessGameDao.update(Team.BLACK, ROOM_NAME, connection);
        Team currentTeam = chessGameDao.findCurrentTeamByRoomName(ROOM_NAME, connection);

        assertThat(currentTeam)
                .isEqualTo(Team.BLACK);
    }

    @DisplayName("입력된 방 이름을 삭제한다.")
    @Test
    void delete() {
        chessGameDao.add(Team.WHITE, ROOM_NAME, connection);
        chessGameDao.delete(ROOM_NAME, connection);

        assertThatThrownBy(() -> chessGameDao.findCurrentTeamByRoomName(ROOM_NAME, connection))
                .isInstanceOf(InvalidGameRoomException.class)
                .hasMessage("존재하지 않는 방 이름입니다.");
    }
}
