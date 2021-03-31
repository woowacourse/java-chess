package chess.domain.dao;

import chess.dao.GridDAO;
import chess.dto.GridDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class GridDAOTest {
    private GridDAO gridDao = new GridDAO();
    private long gridId;
    private long roomId = 1L;

    @BeforeEach
    @DisplayName("DB의 모든 테이블 TRUNCATE 하기")
    public void setup() throws SQLException {
        Fixture.truncateDB();
    }

    @BeforeEach
    @DisplayName("find와 update를 테스트 하기 위해 Grid 데이터 1개 삽입")
    public void insertData() throws SQLException {
        gridId = gridDao.createGrid(roomId);
    }

    @Test
    @DisplayName("gridId로 해당 grid를 찾는 지 테스트")
    public void findGridByGridId() throws SQLException {
        GridDto gridDto = gridDao.findGridByGridId(gridId);
        assertThat(gridDto.getGridId()).isEqualTo(gridId);
    }

    @Test
    @DisplayName("Grid를 생성하면 초기 값이 잘 셋팅되는 지 테스트")
    public void findGridByGridId_CheckInitValues() throws SQLException {
        GridDto gridDto = gridDao.findGridByGridId(gridId);
        assertThat(gridDto.getGridId()).isEqualTo(gridId);
        assertThat(gridDto.getRoomId()).isEqualTo(roomId);
        assertThat(gridDto.isBlackTurn()).isEqualTo(false);
        assertThat(gridDto.isFinished()).isEqualTo(false);
        assertThat(gridDto.isStarted()).isEqualTo(false);
    }

    @Test
    @DisplayName("roomId로 해당 grid를 찾는 지 테스트")
    public void findRecentGridByRoomId() throws SQLException {
        GridDto gridDto = gridDao.findGridByGridId(roomId);
        assertThat(gridDto.getGridId()).isEqualTo(gridId);
    }

    @Test
    @DisplayName("Grid의 상태를 Starting으로 바꾸는 지 테스트")
    public void changeToStarting() throws SQLException {
        gridDao.changeToStarting(gridId);
        GridDto gridDto = gridDao.findGridByGridId(gridId);
        assertThat(gridDto.isStarted()).isTrue();
    }

    @Test
    @DisplayName("Grid의 차례를 정상적으로 바꾸는 지 테스트")
    public void changeTurn() throws SQLException {
        gridDao.changeTurn(gridId, false);
        GridDto gridDto = gridDao.findGridByGridId(gridId);
        assertThat(gridDto.isBlackTurn()).isFalse();

        gridDao.changeTurn(gridId, true);
        GridDto gridDto2 = gridDao.findGridByGridId(gridId);
        assertThat(gridDto2.isBlackTurn()).isTrue();
    }

    @Test
    @DisplayName("Grid를 끝난 상태로 정상적으로 바꾸는 지 테스트")
    public void changeToFinished() throws SQLException {
        gridDao.changeToFinished(gridId);
        GridDto gridDto = gridDao.findGridByGridId(gridId);
        assertThat(gridDto.isFinished()).isTrue();
    }
}
