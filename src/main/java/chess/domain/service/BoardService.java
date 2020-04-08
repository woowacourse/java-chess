package chess.domain.service;

import java.sql.SQLException;

import chess.domain.BoardConverter;
import chess.domain.ChessGame;
import chess.domain.FinishFlag;
import chess.domain.dao.RoomDao;

public class BoardService {
	private static final String BLANK_MARK = ".";

	private final RoomDao roomDao;

	public BoardService(RoomDao roomDao) {
		this.roomDao = roomDao;
	}

	public void create(ChessGame chessGame, String roomName) throws SQLException {
		String boardInfo = BoardConverter.convert(chessGame.getBoard(), BLANK_MARK);
		String turn = chessGame.getTurn().name();
		String finishFlag = FinishFlag.of(chessGame.isEnd()).getSymbol();
		roomDao.addRoom(roomName, boardInfo, turn, finishFlag);
	}

	public void save(ChessGame chessGame, String roomName) throws SQLException {
		String boardInfo = BoardConverter.convert(chessGame.getBoard(), BLANK_MARK);
		roomDao.updateBoard(roomName, boardInfo);
	}
}
