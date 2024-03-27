package chess.db;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.character.Team;
import chess.exception.InvalidGameRoomException;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {
    public static final String ROOM_NAME = "roomName";
    private ChessGameDao chessGameDao;

    @BeforeEach
    void setUp() throws SQLException {
        chessGameDao = new ChessGameDao(new TestConnectionGenerator());
        chessGameDao.delete(ROOM_NAME);
    }

    @DisplayName("필드 추가")
    @Test
    void add() {
        assertThatCode(() -> chessGameDao.add(ROOM_NAME, Team.WHITE))
                .doesNotThrowAnyException();
    }

    @DisplayName("입력된 방 이름이 16자를 넘어가면 예외가 발생한다.")
    @Test
    void invalidAddIfNameOver16() {
        assertThatThrownBy(() -> chessGameDao.add("soLongRoomNameInput", Team.WHITE))
                .isInstanceOf(InvalidGameRoomException.class)
                .hasMessage("중복된 방 이름이 존재하거나, 방이름이 17자 이상입니다.");
    }

    @DisplayName("입력된 방 이름이 중복되면 예외가 발생한다.")
    @Test
    void invalidAddIfNameDuplicated() {
        chessGameDao.add(ROOM_NAME, Team.WHITE);
        assertThatThrownBy(() -> chessGameDao.add(ROOM_NAME, Team.WHITE))
                .isInstanceOf(InvalidGameRoomException.class)
                .hasMessage("중복된 방 이름이 존재하거나, 방이름이 17자 이상입니다.");
    }

    @DisplayName("방 이름으로 현재 팀을 찾는다.")
    @Test
    void findCurrentTeamByRoomName() {
        chessGameDao.add(ROOM_NAME, Team.WHITE);
        assertThat(chessGameDao.findCurrentTeamByRoomName(ROOM_NAME)).isEqualTo(Team.WHITE);
    }

    @DisplayName("방 이름이 존재하지 않으면, 예외가 발생한다.")
    @Test
    void findCurrentTeamByInvalidRoomName() {
        chessGameDao.add(ROOM_NAME, Team.WHITE);
        assertThatThrownBy(() -> chessGameDao.findCurrentTeamByRoomName("noName"))
                .isInstanceOf(InvalidGameRoomException.class)
                .hasMessage("존재하지 않는 방 이름입니다.");
    }

    @DisplayName("입력된 팀으로 입력된 방이름의 현재 팀을 바꾼다.")
    @Test
    void update() throws SQLException {
        chessGameDao.add(ROOM_NAME, Team.WHITE);
        chessGameDao.update(Team.BLACK, ROOM_NAME);

        assertThat(chessGameDao.findCurrentTeamByRoomName(ROOM_NAME))
                .isEqualTo(Team.BLACK);
    }

    @DisplayName("입력된 방 이름을 삭제한다.")
    @Test
    void delete() {
        chessGameDao.add(ROOM_NAME, Team.WHITE);
        assertThatCode(() -> chessGameDao.delete(ROOM_NAME))
                .doesNotThrowAnyException();
        assertThatThrownBy(() -> chessGameDao.findCurrentTeamByRoomName(ROOM_NAME))
                .isInstanceOf(InvalidGameRoomException.class)
                .hasMessage("존재하지 않는 방 이름입니다.");
    }
}
