package chess;

import java.util.List;

import chess.dao.ChessGameDao;
import chess.dao.JdbcConnector;
import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.result.TempResult;
import chess.dto.BoardTurnDto;
import chess.dto.MoveDto;

public class ChessService {

	private final ChessGame game;
	private boolean isDbConnected;

	public ChessService() {
		this.game = new ChessGame();
		this.isDbConnected = false;
	}

	public boolean checkLastGameExists() {
		if (isDBNotConnected()) {
			return false;
		}
		isDbConnected = true;
		ChessGameDao chessGameDao = ChessGameDao.create();
		return chessGameDao.isLastGameExists();
	}

	private boolean isDBNotConnected() {
		JdbcConnector jdbcConnector = new JdbcConnector();
		return jdbcConnector.isDBNotConnected();
	}

	public void loadLastGame() {
		ChessGameDao chessGameDao = ChessGameDao.create();
		List<MoveDto> moves = chessGameDao.loadMoves();
		game.initialize();
		for (MoveDto move : moves) {
			Position source = new Position(move.getSourceColumn(), move.getSourceRow());
			Position target = new Position(move.getTargetColumn(), move.getTargetRow());
			game.movePiece(source, target);
		}
	}

	public void initGame() {
		game.initialize();
	}

	public void movePiece(final Position source, final Position target) {
		saveIfDbConnected(source, target);
		game.movePiece(source, target);
	}

	private void saveIfDbConnected(final Position source, final Position target) {
		if (isDbConnected) {
			ChessGameDao chessGameDao = ChessGameDao.create();
			MoveDto moveDto = new MoveDto(source.getColumn(), source.getRow(), target.getColumn(), target.getRow());
			chessGameDao.saveMove(moveDto);
		}
	}

	public TempResult getTempResult() {
		return game.getTempResult();
	}

	public Team getFinalWinner() {
		return game.getFinalWinner();
	}

	public boolean isGameDone() {
		if (game.isGameDone()) {
			deleteMovesIfDbConnected();
			return true;
		}
		return false;
	}

	private void deleteMovesIfDbConnected() {
		if (isDbConnected) {
			ChessGameDao chessGameDao = ChessGameDao.create();
			chessGameDao.deleteMoves();
		}
	}

	public BoardTurnDto getBoardAndTurn() {
		return new BoardTurnDto(game.getBoard(), game.getTurn());
	}
}
