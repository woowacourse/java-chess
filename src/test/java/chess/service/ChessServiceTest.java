package chess.service;

import chess.config.DataSource;
import chess.config.DbConnector;
import chess.config.TableCreator;
import chess.dao.CommandDao;
import chess.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChessServiceTest {
	private ChessService chessService;
	private Board board;

	@BeforeEach
	public void setUp() throws Exception {
		DataSource dataSource = DataSource.getInstance();
		DbConnector dbConnector = new DbConnector(dataSource);
		TableCreator tableCreator = new TableCreator(dbConnector);
		tableCreator.create();

		CommandDao commandDao = CommandDao.from(dbConnector);

		chessService = new ChessService(commandDao);
		board = new Board(BoardGenerator.generate());
		chessService.deleteAll();
	}

	@Test
	public void initGameTest() {
		Board board = new Board(BoardGenerator.generate());
		Game expected = Game.from(board);
		Game actual = chessService.initGame();
		assertEquals(expected, actual);
	}

	@Test
	public void 옳바른_문자열을_입력_이동_테스트() {
		Game game = Game.from(board);
		assertTrue(chessService.action(game, "a2", "a3", 1));
	}

	@Test
	public void 틀린_문자열을_입력_이동_예외발생_테스트() {
		Game game = Game.from(board);
		assertThrows(RuntimeException.class, () ->
				chessService.action(game, "2a", "3a", 1)
		);
	}

	@Test
	public void 빈칸을_입력_이동_예외발생_테스트() {
		Game game = Game.from(board);
		assertThrows(RuntimeException.class, () ->
				chessService.action(game, "", "", 1)
		);
	}

	@Test
	public void 세글자를_입력_이동_예외발생_테스트() {
		Game game = Game.from(board);
		assertThrows(RuntimeException.class, () ->
				chessService.action(game, "a23", "a32", 1)
		);
	}

	@Test
	public void 한글자를_입력_이동_예외발생_테스트() {
		Game game = Game.from(board);
		assertThrows(RuntimeException.class, () ->
				chessService.action(game, "2", "3", 1)
		);
	}

	@Test
	public void loadTest() {
		final long roomId = 1;
		Game expected = Game.from(board);
		Position origin = Position.of("2", "b");
		Position target = Position.of("4", "b");
		expected.action(origin, target);

		Board newBoard = new Board(BoardGenerator.generate());
		Game game = Game.from(newBoard);
		chessService.action(game, "b2", "b4", roomId);
		Game actual = chessService.load(roomId);

		assertEquals(expected, actual);
	}

	@Test
	public void getSquaresTest() {
		Game game = Game.from(board);
		List<Square> expected = board.values();
		List<Square> actual = chessService.getSquares(game);

		assertEquals(expected, actual);
	}

	@AfterEach
	public void tearDown() {
		chessService.deleteAll();
	}
}