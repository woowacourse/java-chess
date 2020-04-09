package chess.domain.service;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import chess.domain.BoardConverter;
import chess.domain.ChessGame;
import chess.domain.FinishFlag;
import chess.domain.Side;
import chess.domain.dao.RoomDao;

public class ChessGameService {
	public static final String BLANK_MARK = ".";

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

	public void load(ChessGame chessGame, String roomName) throws SQLException {
		String turn = roomDao.findByRoomName(roomName, "turn")
				.orElseThrow(NoSuchElementException::new);
		String board = roomDao.findByRoomName(roomName, "board")
				.orElseThrow(NoSuchElementException::new);

		chessGame.load(BoardConverter.convertToBoard(board), Side.valueOf(turn));
	}
}
