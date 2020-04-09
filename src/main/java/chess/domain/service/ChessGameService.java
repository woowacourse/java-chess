package chess.domain.service;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import chess.domain.BoardConverter;
import chess.domain.ChessGame;
import chess.domain.FinishFlag;
import chess.domain.Side;
import chess.domain.dao.RoomDao;
import chess.domain.position.Position;

public class ChessGameService {
	public static final String BLANK_MARK = ".";

	private final RoomDao roomDao;

	public ChessGameService(RoomDao roomDao) {
		this.roomDao = roomDao;
	}

	public ChessGame create(String roomName) throws SQLException {
		ChessGame chessGame = ChessGame.start();

		String board = BoardConverter.convertToString(chessGame.getBoard(), BLANK_MARK);
		String turn = chessGame.getTurn().name();
		String finishFlag = FinishFlag.of(chessGame.isEnd()).getSymbol();
		roomDao.addRoom(roomName, board, turn, finishFlag);
		return chessGame;
	}

	public ChessGame move(String roomName, String source, String target) throws SQLException {
		ChessGame chessGame = load(roomName);
		chessGame.move(new Position(source), new Position(target));

		String board = BoardConverter.convertToString(chessGame.getBoard(), BLANK_MARK);
		String turn = chessGame.getTurn().name();
		String finishFlag = FinishFlag.of(chessGame.isEnd()).getSymbol();
		roomDao.updateRoom(roomName, board, turn, finishFlag);
		return chessGame;
	}

	public List<String> findAllRooms() throws SQLException {
		return roomDao.findAll();
	}

	public ChessGame load(String roomName) throws SQLException {
		String finishFlag = roomDao.findByRoomName(roomName, "finish_flag")
				.orElseThrow(NoSuchElementException::new);
		validateFinish(finishFlag);

		String board = roomDao.findByRoomName(roomName, "board")
				.orElseThrow(NoSuchElementException::new);
		String turn = roomDao.findByRoomName(roomName, "turn")
				.orElseThrow(NoSuchElementException::new);
		return new ChessGame(BoardConverter.convertToBoard(board), Side.valueOf(turn));
	}

	private void validateFinish(String finishFlag) {
		if ("Y".equals(finishFlag)) {
			throw new IllegalArgumentException("종료된 게임입니다.");
		}
	}

	public ChessGame restart(String roomName) throws SQLException {
		ChessGame chessGame = ChessGame.start();

		String board = BoardConverter.convertToString(chessGame.getBoard(), BLANK_MARK);
		String turn = chessGame.getTurn().name();
		String finishFlag = FinishFlag.of(chessGame.isEnd()).getSymbol();
		roomDao.updateRoom(roomName, board, turn, finishFlag);
		return chessGame;
	}
}
