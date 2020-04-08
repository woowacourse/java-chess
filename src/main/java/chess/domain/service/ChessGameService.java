package chess.domain.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import chess.domain.BoardConverter;
import chess.domain.ChessGame;
import chess.domain.FinishFlag;
import chess.domain.dao.RoomDao;

public class ChessGameService {
	private static final String BLANK_MARK = ".";

	private final RoomDao roomDao;

	public ChessGameService(RoomDao roomDao) {
		this.roomDao = roomDao;
	}

	public void create(ChessGame chessGame, String roomName) throws SQLException {
		validateDuplicatedRoom(roomName);
		String boardInfo = BoardConverter.convert(chessGame.getBoard(), BLANK_MARK);
		String turn = chessGame.getTurn().name();
		String finishFlag = FinishFlag.of(chessGame.isEnd()).getSymbol();
		roomDao.addRoom(roomName, boardInfo, turn, finishFlag);
	}

	private void validateDuplicatedRoom(String roomName) throws SQLException {
		Optional<String> result = roomDao.findByRoomName(roomName);
		if (!result.isPresent()) {
			throw new IllegalArgumentException("같은 이름의 방이 존재합니다.");
		}
	}

	public void save(ChessGame chessGame, String roomName) throws SQLException {
		String boardInfo = BoardConverter.convert(chessGame.getBoard(), BLANK_MARK);
		roomDao.updateBoard(roomName, boardInfo);
	}

	public List<String> findAllRooms() throws SQLException {
		return roomDao.findAll();
	}
}
