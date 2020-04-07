package dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StateDaoTest {
	private StateDao stateDao;

	@BeforeEach
	void setUp() {
		stateDao = new StateDao();
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
		final State stateBean = stateDao.findStateByRoomName(roomName);
		assertThat(stateBean.getState()).isEqualTo(state);
	}

	@Test
	void setState() throws SQLException {
		final int stateId = 1;
		final String state = "started";
		int resultNum = stateDao.setState(stateId, state);
		final State stateBean = stateDao.findStateByRoomName("그니");
		assertThat(resultNum).isEqualTo(1);
		assertThat(stateBean.getState()).isEqualTo("started");
	}
}