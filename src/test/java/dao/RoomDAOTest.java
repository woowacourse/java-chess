package dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RoomDAOTest {
	private RoomDAO roomDAO;

	@BeforeEach
	void setUp() throws SQLException {
		roomDAO = new RoomDAO();
		roomDAO.deleteAll();
	}

	@DisplayName("모든 방을 탐색")
	@Test
	void findAll() throws SQLException {
		roomDAO.createRoom(1);
		roomDAO.createRoom(2);
		roomDAO.createRoom(3);

		Map<String, Object> all = roomDAO.findAll();

		HashMap<String, List<String>> expect = new HashMap<>();
		expect.put("rooms", Arrays.asList("1", "2", "3"));
		assertThat(all).isEqualTo(expect);
	}

	@DisplayName("새로운 방을 만든 후 삭제")
	@Test
	void createRoom() throws SQLException {
		String room = roomDAO.createRoom();
		roomDAO.delete(room);
	}
}