package chess.domain.dao;

import static org.assertj.core.api.Assertions.*;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import chess.domain.BoardConverter;
import chess.domain.ChessGame;
import chess.domain.Side;
import chess.domain.dto.ChessGameDto;

public class RoomDaoTest {
	private RoomDao roomDao;

	@BeforeEach
	void setUp() throws SQLException {
		roomDao = new RoomDao();
		ChessGameDto.of("A", ChessGame.start());
		roomDao.addRoom(ChessGameDto.of("A", ChessGame.start()));
		roomDao.addRoom(ChessGameDto.of("B", ChessGame.start()));
	}

	@Test
	void findByRoomName() throws SQLException {
		String actualBoard = "RNBQKBNRPPPPPPPP................................pppppppprnbqkbnr";
		String expectedBoard = roomDao.findByRoomName("A", "board")
				.orElseThrow(NoSuchElementException::new);

		assertThat(actualBoard).isEqualTo(expectedBoard);
	}

	@Test
	void addRoom() throws SQLException {
		String roomName = "C";
		String board = "RNBQKBNRPPPPPPPP................................pppppppprnbqkbnr";
		String turn = "BLACK";
		ChessGame chessGame = new ChessGame(BoardConverter.convertToBoard(board), Side.valueOf(turn));

		roomDao.addRoom(ChessGameDto.of(roomName, chessGame));
	}

	@Test
	void deleteRoom() throws SQLException {
		String roomName = "A";

		roomDao.deleteRoom(roomName);
	}

	@Test
	void updateRoom() throws SQLException {
		String roomName = "B";
		String board = "RNBQKBNRPPPPPPPP................................pppppppprnbqkbnr";
		String turn = "BLACK";
		ChessGame chessGame = new ChessGame(BoardConverter.convertToBoard(board), Side.valueOf(turn));

		roomDao.updateRoom(ChessGameDto.of(roomName, chessGame));
	}
}
