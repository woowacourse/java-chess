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
import chess.dto.response.GameDto;
import chess.dto.response.GamesDto;
import chess.dto.response.StatusDto;
import chess.dto.response.WinnerDto;
import java.util.List;

public class ChessService {

	private final GameDao gameDao;
	private State state;

	public ChessService(final GameDao gameDao) {
		this.gameDao = gameDao;
	}

	public GameDto start(String name) {
		state = new Ready();
		Board board = new Board(BoardFactory.initiate());
		state = state.start(board);
		Game game = new Game(name, Command.START.getCommand());
		int gameId = gameDao.save(game);
		return GameDto.of(gameId, state.getBoard());
	}

	public StatusDto status() {
		ScoreResult scoreResult = state.createStatus();
		return StatusDto.of(scoreResult);
	}

	public GameDto move(int gameId, Position source, Position target) {
		state = state.move(source, target);
		List<String> commands = List.of(Command.MOVE.getCommand(), source.convertPositionToString(),
				target.convertPositionToString());
		Game game = new Game(gameId, commands);
		gameDao.update(game);
		return GameDto.of(gameId, state.getBoard());
	}

	public WinnerDto end(int gameId) {
		state = state.finish();
		Game game = new Game(gameId, List.of(Command.END.getCommand()));
		gameDao.update(game);
		Team winner = state.judgeWinner();
		return WinnerDto.of(winner.getValue());
	}

	public GameDto load(int gameId) {
		state = new Ready();
		Game game = gameDao.findById(gameId);
		for (String commandLog : game.parseLog()) {
			List<String> inputCommand = Game.parseCommand(commandLog);
			Command command = Command.of(inputCommand);
			state = command.apply(state, inputCommand);
		}
		return GameDto.of(gameId, state.getBoard());
	}

	public GamesDto findAll() {
		List<Game> games = gameDao.findAll();
		return GamesDto.of(games);
	}

	public void delete(int gameId) {
		gameDao.delete(gameId);
	}
}
