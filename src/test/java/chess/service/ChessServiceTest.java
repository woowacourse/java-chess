package chess.service;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;

import chess.dao.ChessGameDao;
import chess.dao.ChessHistoryDao;
import chess.dao.InMemoryChessGameDao;
import chess.dao.InMemoryChessHistoryDao;
import chess.domain.chessBoard.ChessBoard;
import chess.domain.chessBoard.ChessBoardInitializer;
import chess.domain.chessGame.ChessCommand;
import chess.domain.chessGame.ChessGame;
import chess.entity.ChessGameEntity;
import chess.entity.ChessHistoryEntity;
import chess.service.dto.ChessGameDto;

class ChessServiceTest {

	private static final String MOVE_COMMAND = "move";

	private ChessGameDao chessGameDao;
	private ChessHistoryDao chessHistoryDao;

	@BeforeEach
	void setUp() {
		chessGameDao = new InMemoryChessGameDao();
		chessHistoryDao = new InMemoryChessHistoryDao();
	}

	@ParameterizedTest
	@NullSource
	void ChessService_NullChessGameDao_ExceptionThrown(final ChessGameDao chessGameDao) {
		assertThatThrownBy(() -> new ChessService(chessGameDao, chessHistoryDao))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("ChessGameDao가 null입니다.");
	}

	@ParameterizedTest
	@NullSource
	void ChessService_NullChessHistoryDao_ExceptionThrown(final ChessHistoryDao chessHistoryDao) {
		assertThatThrownBy(() -> new ChessService(chessGameDao, chessHistoryDao))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("ChessHistoryDao가 null입니다.");
	}

	@Test
	void ChessService_ChessHistoryDao_GenerateInstance() {
		assertThat(new ChessService(chessGameDao, chessHistoryDao)).isInstanceOf(ChessService.class);
	}

	@Test
	void checkChessGameIsEmpty_EmptyChessGame_AddChessGame() {
		new ChessService(chessGameDao, chessHistoryDao);

		assertThat(chessGameDao.findMaxGameId()).isEqualTo(1);
	}

	@Test
	void checkChessGameIsEmpty_ExistChessGame_NotAddChessGame() {
		chessGameDao.add(ChessGameEntity.of(LocalDateTime.now()));
		chessGameDao.add(ChessGameEntity.of(LocalDateTime.now()));
		new ChessService(chessGameDao, chessHistoryDao);

		assertThat(chessGameDao.findMaxGameId()).isEqualTo(2);
	}

	@Test
	void loadChessGame_RecentChessGame_ReturnChessGameDto() {
		final ChessService chessService = new ChessService(chessGameDao, chessHistoryDao);
		final long gameId = chessGameDao.findMaxGameId();
		final ChessGame chessGame = ChessGame.from(new ChessBoard(ChessBoardInitializer.create()));
		chessHistoryDao.add(ChessHistoryEntity.of(gameId, "b2", "b4", LocalDateTime.now()));
		chessHistoryDao.add(ChessHistoryEntity.of(gameId, "b7", "b5", LocalDateTime.now()));
		chessGame.move(ChessCommand.of(Arrays.asList(MOVE_COMMAND, "b2", "b4")));
		chessGame.move(ChessCommand.of(Arrays.asList(MOVE_COMMAND, "b7", "b5")));

		final ChessGameDto expected = ChessGameDto.of(chessGame);
		assertThat(chessService.loadChessGame()).isEqualTo(expected);
	}

	@ParameterizedTest
	@NullSource
	void playChessGame_NullSourcePosition_ExceptionThrown(final String sourcePosition) {
		final String targetPosition = "b2";
		final ChessService chessService = new ChessService(chessGameDao, chessHistoryDao);

		assertThatThrownBy(() -> chessService.playChessGame(sourcePosition, targetPosition))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("소스 위치가 null입니다.");
	}

	@ParameterizedTest
	@NullSource
	void playChessGame_NullTargetPosition_ExceptionThrown(final String targetPosition) {
		final String sourcePosition = "b2";
		final ChessService chessService = new ChessService(chessGameDao, chessHistoryDao);

		assertThatThrownBy(() -> chessService.playChessGame(sourcePosition, targetPosition))
			.isInstanceOf(NullPointerException.class)
			.hasMessage("타겟 위치가 null입니다.");
	}

	@Test
	void playChessGame_SourcePositionAndTargetPosition_ReturnChessGameDto() {
		final ChessService chessService = new ChessService(chessGameDao, chessHistoryDao);
		final ChessGame chessGame = ChessGame.from(new ChessBoard(ChessBoardInitializer.create()));
		chessGame.move(ChessCommand.of(Arrays.asList(MOVE_COMMAND, "b2", "b4")));

		final ChessGameDto expected = ChessGameDto.of(chessGame);
		assertThat(chessService.playChessGame("b2", "b4")).isEqualTo(expected);
	}

	@Test
	void createChessGame_RemoveAllChessHistory_ReturnChessGameDto() {
		final ChessService chessService = new ChessService(chessGameDao, chessHistoryDao);
		chessHistoryDao.add(ChessHistoryEntity.of("b2", "b4"));
		chessHistoryDao.add(ChessHistoryEntity.of("b7", "b5"));

		final ChessGame chessGame = ChessGame.from(new ChessBoard(ChessBoardInitializer.create()));
		final ChessGameDto expected = ChessGameDto.of(chessGame);
		assertThat(chessService.createChessGame()).isEqualTo(expected);
	}

	@Test
	void endChessGame_EndRecentChessGame() {
		final ChessService chessService = new ChessService(chessGameDao, chessHistoryDao);
		final ChessGame chessGame = ChessGame.from(new ChessBoard(ChessBoardInitializer.create()));
		chessGame.end();

		final ChessGameDto expected = ChessGameDto.of(chessGame);
		assertThat(chessService.endChessGame()).isEqualTo(expected);
	}

}