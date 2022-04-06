package chess.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import chess.controller.PositionConvertor;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.dto.response.GameDto;
import chess.dto.response.GamesDto;
import chess.dto.response.StatusDto;
import chess.dto.response.WinnerDto;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessServiceTest {

	private ChessService chessService;

	@BeforeEach
	void setUp() {
		chessService = new ChessService(new FakeGameDao());
	}

	@Test
	void start() {
		GameDto gameDto = chessService.start("name");
		GameDto expectedGameDto = GameDto.of(1, new Board(BoardFactory.initiate()));

		assertThat(gameDto.getBoard()).isEqualTo(expectedGameDto.getBoard());
	}

	@Test
	void status() {
		chessService.start("name");
		StatusDto statusDto = chessService.status();

		assertAll(
				() -> assertThat(statusDto.getBlackScore()).isEqualTo(38),
				() -> assertThat(statusDto.getWhiteScore()).isEqualTo(38),
				() -> assertThat(statusDto.getWinner()).isEqualTo("neutrality")
		);
	}

	@Test
	void move() {
		chessService.start("name");
		String source = "a2";
		String target = "a4";
		GameDto gameDto = chessService.move(1, PositionConvertor.to(source), PositionConvertor.to(target));
		Map<String, String> board = gameDto.getBoard();

		assertThat(board.containsKey(target)).isTrue();
	}

	@Test
	void end() {
		chessService.start("name");
		WinnerDto winnerDto = chessService.end(1);

		assertThat(winnerDto.getWinner()).isEqualTo("neutrality");
	}

	@Test
	void load() {
		chessService.start("name");
		GameDto gameDto = chessService.load(1);

		assertThat(gameDto.getGameId()).isEqualTo(1);
	}

	@Test
	void findAll() {
		chessService.start("name");
		GamesDto gamesDto = chessService.findAll();

		assertThat(gamesDto.getGames().size()).isEqualTo(1);
	}

	@Test
	void delete() {
		chessService.start("name");
		chessService.delete(1);
		GamesDto gamesDto = chessService.findAll();

		assertThat(gamesDto.getGames().size()).isEqualTo(0);
	}
}
