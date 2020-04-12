package chess.domain.service;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import chess.domain.BoardConverter;
import chess.domain.ChessGame;
import chess.domain.FinishFlag;
import chess.domain.Side;
import chess.domain.dao.RoomDao;
import chess.domain.dto.RoomDto;
import chess.domain.position.Position;

public class ChessGameService {
	private final RoomDao roomDao;

	public ChessGameService(RoomDao roomDao) {
		this.roomDao = roomDao;
	}

	public ChessGame create(String roomName) throws SQLException {
		validateDuplicated(roomName);
		ChessGame chessGame = ChessGame.start();

		String board = BoardConverter.convertToString(chessGame.getBoard());
		String turn = chessGame.getTurn().name();
		String finishFlag = FinishFlag.of(chessGame.isEnd()).getSymbol();
		roomDao.addRoom(roomName, board, turn, finishFlag);
		return chessGame;
	}

	private void validateDuplicated(String roomName) throws SQLException {
		if (isPresentRoom(roomName)) {
			throw new IllegalArgumentException("입력한 방이 이미 존재합니다.");
		}
	}

	private boolean isPresentRoom(String roomName) throws SQLException {
		return roomDao.findByRoomName(roomName, "board").isPresent();
	}

	public ChessGame move(String roomName, String source, String target) throws SQLException {
		ChessGame chessGame = load(roomName);
		chessGame.move(new Position(source), new Position(target));

		String board = BoardConverter.convertToString(chessGame.getBoard());
		String turn = chessGame.getTurn().name();
		String finishFlag = FinishFlag.of(chessGame.isEnd()).getSymbol();
		roomDao.updateRoom(roomName, board, turn, finishFlag);
		return chessGame;
	}

	public List<RoomDto> findAllRooms() throws SQLException {
		List<String> rooms = roomDao.findAll();
		return rooms.stream()
				.map(RoomDto::new)
				.collect(Collectors.toList());
	}

	public ChessGame load(String roomName) throws SQLException {
		validatePresent(roomName);
		String finishFlag = roomDao.findByRoomName(roomName, "finish_flag")
				.orElseThrow(NoSuchElementException::new);
		validateFinish(finishFlag);

		String board = roomDao.findByRoomName(roomName, "board")
				.orElseThrow(NoSuchElementException::new);
		String turn = roomDao.findByRoomName(roomName, "turn")
				.orElseThrow(NoSuchElementException::new);
		return new ChessGame(BoardConverter.convertToBoard(board), Side.valueOf(turn));
	}

	private void validatePresent(String roomName) throws SQLException {
		if (!isPresentRoom(roomName)) {
			throw new IllegalArgumentException("입력한 방이 없습니다.");
		}
	}

	private void validateFinish(String finishFlag) {
		if (FinishFlag.FINISH.isFinish(finishFlag)) {
			throw new IllegalArgumentException("종료된 게임입니다.");
		}
	}

	public ChessGame restart(String roomName) throws SQLException {
		ChessGame chessGame = ChessGame.start();

		String board = BoardConverter.convertToString(chessGame.getBoard());
		String turn = chessGame.getTurn().name();
		String finishFlag = FinishFlag.of(chessGame.isEnd()).getSymbol();
		roomDao.updateRoom(roomName, board, turn, finishFlag);
		return chessGame;
	}
}
