package chess.domain.service;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.domain.room.Room;
import chess.service.RoomService;

public class RoomServiceTest {
	RoomService roomService = RoomService.getInstance();

	@BeforeEach
	void setUp() throws SQLException {
		roomService.removeRoom("hello world");
	}

	@DisplayName("방을 추가하는 메서드 테스트")
	@Test
	void addRoomTest() throws SQLException {
		roomService.addRoom("hello world");
		Room room = roomService.findRoom("hello world");
		assertThat(room.getRoomName()).isEqualTo("hello world");
	}

	@DisplayName("모든 방을 찾는 메서드 테스트")
	@Test
	void findAllRoomTest() throws SQLException {
		roomService.addRoom("hello world");
		List<Room> rooms = roomService.findAllRoom();
		assertThat(rooms.size()).isEqualTo(1);
	}

	@DisplayName("방을 삭제하는 메서드 테스트")
	@Test
	void deleteRoomTest() throws SQLException {
		roomService.removeRoom("hello world");
		Room room = roomService.findRoom("hello world");
		assertThat(room).isNull();
	}
}
