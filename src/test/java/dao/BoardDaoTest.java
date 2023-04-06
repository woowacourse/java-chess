package dao;

import domain.point.File;
import domain.point.Point;
import domain.point.Rank;
import domain.util.ExceptionMessages;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class BoardDaoTest {
    public static final String ID_GAME_007 = "Game007";
    private final BoardDao boardDao = new BoardDao();

    @AfterEach
    public void setDown() {
        try {
            boardDao.deleteById(ID_GAME_007);
        } catch (IllegalArgumentException ignored) {}
        // 삭제 테스트의 경우
    }

    @Test
    public void connection() {
        try (final var connection = boardDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("ID를 입력하여 새 게임방을 만들 수 있다.")
    public void save() {
        assertDoesNotThrow(() -> boardDao.save(ID_GAME_007));
    }

    @Test
    @DisplayName("이미 존재하는 게임방의 ID를 입력하면 예외가 발생한다.")
    public void alreadySaved() {
        // given
        boardDao.save(ID_GAME_007);

        // when & then
        assertThatThrownBy(() -> boardDao.save(ID_GAME_007))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessages.ALREADY_EXISTING_ID);
    }

    @Test
    @DisplayName("ID를 입력하여 게임 진행상황을 찾을 수 있다.")
    public void findStatusById() {
        // given
        boardDao.save(ID_GAME_007);

        // when
        List<Movement> movements = boardDao.findStatusById(ID_GAME_007);

        // then
        assertThat(movements).isNotNull();
    }

    @Test
    @DisplayName("존재하지 않는 게임방의 ID를 입력하면 예외가 발생한다.")
    public void cannotFindStatusById() {
        assertThatThrownBy(() -> boardDao.findStatusById("Game009"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessages.NOT_EXISTING_GAME_ID);
    }


    @Test
    @DisplayName("주어진 ID로 저장된 게임방에 기물의 이동을 기록할 수 있다.")
    public void updateMovement() {
        // given
        boardDao.save(ID_GAME_007);

        // when
        Movement movement = new Movement(new Point(File.D, Rank.TWO), new Point(File.D, Rank.THREE));
        assertDoesNotThrow(() ->
                boardDao.updateMovement(ID_GAME_007, movement)
        );

        // then
        assertThat(boardDao.findStatusById(ID_GAME_007).contains(movement)).isTrue();
    }

    @Test
    @Disabled("테스트 시간이 너무 길어진다.")
    @DisplayName("한 게임방에 기물의 이동을 2500번 초과하여 기록할 수 없다.")
    public void updateTooMuchMovement() {
        // given
        boardDao.save(ID_GAME_007);

        // when & then
        Movement movement = new Movement(new Point(File.D, Rank.TWO), new Point(File.D, Rank.THREE));
        assertThatThrownBy(() -> IntStream.range(0, 2501)
                .forEach(i -> boardDao.updateMovement(ID_GAME_007, movement)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessages.MAX_MOVEMENT_COUNT);
    }

    @Test
    @DisplayName("주어진 ID로 게임방을 제거할 수 있다.")
    public void delete() {
        // given
        boardDao.save(ID_GAME_007);

        // when
        boardDao.deleteById(ID_GAME_007);

        // then
        assertThatThrownBy(() -> boardDao.findStatusById(ID_GAME_007))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ExceptionMessages.NOT_EXISTING_GAME_ID);
    }

    @Test
    @DisplayName("존재하지 않는 ID로 게임방을 제거하려고 시도하면 예외가 발생한다.")
    public void cannotDelete() {
        // given
        String id = "Game009";

        // when & then
        assertThatThrownBy(() -> boardDao.deleteById(id))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 삭제되었거나 존재하지 않는 ID입니다.");
    }
}
