package chess.domain.service;

import java.sql.SQLException;
import java.util.List;

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
		String board = BoardConverter.convert(chessGame.getBoard(), BLANK_MARK);
		String turn = chessGame.getTurn().name();
		String finishFlag = FinishFlag.of(chessGame.isEnd()).getSymbol();
		roomDao.addRoom(roomName, board, turn, finishFlag);
	}

	public void save(ChessGame chessGame, String roomName) throws SQLException {
		String board = BoardConverter.convert(chessGame.getBoard(), BLANK_MARK);
		String turn = chessGame.getTurn().name();
		String finishFlag = FinishFlag.of(chessGame.isEnd()).getSymbol();
		roomDao.updateRoom(roomName, board, turn, finishFlag);
	}

	public List<String> findAllRooms() throws SQLException {
		return roomDao.findAll();
	}
}
