package chess.service;

import chess.config.DataSource;
import chess.config.DbConnector;
import chess.config.TableCreator;
import chess.dao.RoomDao;
import chess.domain.Piece;
import chess.dto.RoomDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoomServiceTest {
	private RoomService roomService;

	@BeforeEach
	public void setUp() throws Exception {
		DataSource dataSource = DataSource.getInstance();
		DbConnector dbConnector = new DbConnector(dataSource);
		TableCreator tableCreator = new TableCreator(dbConnector);
		tableCreator.create();

		RoomDao roomDao = RoomDao.from(dbConnector);
		roomService = new RoomService(roomDao);
		roomService.deleteAll();
	}

	@Test
	public void Room이_없을때_예외발생_테스트() {
		assertThrows(IllegalArgumentException.class, () -> roomService.latestId());
	}

	@Test
	public void getLatestIdTest() {
		long expected = 1;
		roomService.openRoom();
		assertEquals(expected, roomService.latestId());
	}

	@Test
	void updateStatusTest() {
		long roomId = 1;
		String winner = Piece.Color.BLACK.getName();
		roomService.openRoom();
		roomService.updateStatus(roomId, winner);

		long expected = 0;
		assertEquals(expected, roomService.findAllByOngoing().size());
	}

	@Test
	void findAllByOngoing() {
		long roomId = 1;
		String winner = Piece.Color.BLACK.getName();
		roomService.openRoom();
		roomService.openRoom();
		roomService.updateStatus(roomId, winner);

		List<RoomDto> expected = new ArrayList<>();
		long expectedId = 2;
		boolean ongoing = false;

		RoomDto roomDto = new RoomDto();
		roomDto.setId(expectedId);
		roomDto.setWinner(null);
		roomDto.setStatus(ongoing);
		expected.add(roomDto);

		assertEquals(expected, roomService.findAllByOngoing());
	}

	@AfterEach
	public void tearDown() {
		roomService.deleteAll();
	}
}