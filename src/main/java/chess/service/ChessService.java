package chess.service;

import chess.controller.Command;
import chess.dao.GameDao;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.board.Position;
import chess.domain.game.Game;
import chess.domain.piece.Team;
import chess.domain.score.ScoreResult;
import chess.domain.state.Ready;
import chess.domain.state.State;
import chess.dto.GameDto;
import chess.dto.GamesDto;
import chess.dto.StatusDto;
import chess.dto.WinnerDto;
import java.util.Arrays;
import java.util.List;

public class ChessService {

	private static final String COMMAND_DELIMITER = " ";
	private static final String LOG_DELIMITER = "\n";

	private final GameDao gameDao;
	private State state;

	public ChessService(final GameDao gameDao) {
		this.gameDao = gameDao;
	}

	public GameDto start(String name) {
		state = new Ready();
		Board board = new Board(BoardFactory.initiate());
		state = state.start(board);
		int gameId = gameDao.save(name, Command.START.getCommand());
		return GameDto.of(gameId, state.getBoard());
	}

	public StatusDto status() {
		ScoreResult scoreResult = state.createStatus();
		return StatusDto.of(scoreResult);
	}

	public GameDto move(int gameId, Position source, Position target) {
		state = state.play(source, target);
		String command = String.join(COMMAND_DELIMITER,
				List.of(Command.MOVE.getCommand(), source.convertPositionToString(), target.convertPositionToString())
		);
		gameDao.update(gameId, command);
		return GameDto.of(gameId, state.getBoard());
	}

	public WinnerDto end(int gameId) {
		state = state.finish();
		gameDao.update(gameId, Command.END.getCommand());
		Team winner = state.judgeWinner();
		return WinnerDto.of(winner.getValue());
	}

	public GameDto load(int gameId) {
		state = new Ready();
		Game game = gameDao.findById(gameId);
		String[] commandLogs = game.getCommandLog().split(LOG_DELIMITER);
		for (String log : commandLogs) {
			List<String> inputCommand = Arrays.asList(log.split(COMMAND_DELIMITER));
			Command command = Command.of(inputCommand);
			state = command.apply(state, inputCommand);
		}
		return GameDto.of(gameId, state.getBoard());
	}

	public GamesDto findAll() {
		List<Game> games = gameDao.findAll();
		return GamesDto.of(games);
	}
}
