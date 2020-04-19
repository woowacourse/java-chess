package chess.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Objects;

import chess.dao.ChessGameDao;
import chess.dao.ChessHistoryDao;
import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.ChessBoardInitializer;
import chess.domain.chessGame.ChessCommand;
import chess.domain.chessGame.ChessGame;
import chess.entity.ChessGameEntity;
import chess.entity.ChessHistoryEntity;
import chess.service.dto.ChessGameDto;

public class ChessService {

	private static final String MOVE_COMMAND = "move";

	private final ChessGameDao chessGameDao;
	private final ChessHistoryDao chessHistoryDao;

	public ChessService(final ChessGameDao chessGameDao, final ChessHistoryDao chessHistoryDao) {
		Objects.requireNonNull(chessGameDao, "ChessGameDao가 null입니다.");
		Objects.requireNonNull(chessHistoryDao, "ChessHistoryDao가 null입니다.");
		this.chessGameDao = chessGameDao;
		this.chessHistoryDao = chessHistoryDao;
		checkChessGameIsEmpty(chessGameDao);
	}

	private void checkChessGameIsEmpty(final ChessGameDao chessGameDao) {
		if (chessGameDao.isEmpty()) {
			final ChessGameEntity entity = ChessGameEntity.of(LocalDateTime.now(ZoneId.of("Asia/Seoul")));

			chessGameDao.add(entity);
		}
	}

	public ChessGameDto loadChessGame() {
		final long gameId = chessGameDao.findMaxGameId();
		return ChessGameDto.of(initChessGameOf(gameId));
	}

	private ChessGame initChessGameOf(final long gameId) {
		final ChessBoard chessBoard = new ChessBoard(ChessBoardInitializer.create());
		final ChessGame chessGame = ChessGame.from(chessBoard);

		chessHistoryDao.findAllByGameId(gameId).stream()
			.sorted(ChessHistoryEntity::compareTo)
			.map(ChessHistoryEntity::generateMoveCommand)
			.forEach(chessGame::move);
		return chessGame;
	}

	public ChessGameDto playChessGame(final String sourcePosition, final String targetPosition) {
		Objects.requireNonNull(sourcePosition, "소스 위치가 null입니다.");
		Objects.requireNonNull(targetPosition, "타겟 위치가 null입니다.");
		return moveChessPiece(sourcePosition, targetPosition);
	}

	private ChessGameDto moveChessPiece(final String sourcePosition, final String targetPosition) {
		final long gameId = chessGameDao.findMaxGameId();
		final ChessGame chessGame = initChessGameOf(gameId);
		final ChessCommand chessCommand = ChessCommand.of(Arrays.asList(MOVE_COMMAND, sourcePosition, targetPosition));
		final ChessHistoryEntity chessHistoryEntity =
			ChessHistoryEntity.of(gameId, sourcePosition, targetPosition, LocalDateTime.now(ZoneId.of("Asia/Seoul")));

		chessGame.move(chessCommand);
		chessHistoryDao.add(chessHistoryEntity);
		return ChessGameDto.of(chessGame);
	}

	public ChessGameDto createChessGame() {
		final ChessGameEntity entity = ChessGameEntity.of(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
		final long gameId = chessGameDao.add(entity);

		return ChessGameDto.of(initChessGameOf(gameId));
	}

	public ChessGameDto endChessGame() {
		final long gameId = chessGameDao.findMaxGameId();
		final ChessGame chessGame = initChessGameOf(gameId);

		chessGame.end();
		return ChessGameDto.of(chessGame);
	}

}
