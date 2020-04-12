package dao;

import dto.StateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class StateDaoTest {
	private StateDao stateDao;

	@BeforeEach
	void setUp() {
		stateDao = StateDao.getInstance();
	}

	@Test
	void getConnection() {
		final Connection connection = stateDao.getConnection();
		assertThat(connection).isNotNull();
	}

	@Test
	void closeConnection() throws SQLException {
		final Connection connection = stateDao.getConnection();
		stateDao.closeConnection(connection);
		assertThat(connection.isClosed()).isTrue();
	}

	@Test
	void addState() throws SQLException {
		final String roomName = "그니";
		final String state = "ended";
		final int resultNum = stateDao.addState(state, 1);
		assertThat(resultNum).isEqualTo(1);
		assertThat(stateDao.findStateByRoomName(roomName).getState()).isEqualTo(state);
	}

	@Test
	void findStateByRoomName() throws SQLException {
		final String roomName = "그니";
		final String state = "ended";
		final StateDto stateDtoBean = stateDao.findStateByRoomName(roomName);
		assertThat(stateDtoBean.getState()).isEqualTo(state);
	}

	@Test
	void setState() throws SQLException {
		final int roomId = 1;
		final String state = "started";
		int resultNum = stateDao.setStateByRoomId(roomId, state);
		final StateDto stateDtoBean = stateDao.findStateByRoomName("그니");
		assertThat(resultNum).isEqualTo(1);
		assertThat(stateDtoBean.getState()).isEqualTo("started");
	}
}