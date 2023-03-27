package chess;

import java.util.List;
import java.util.Map;

import chess.dao.ChessGameDao;
import chess.domain.ChessGame;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.piece.Piece;
import chess.domain.result.TempResult;
import chess.dto.MoveDto;

public class ChessService {

	private final ChessGame game;

	public ChessService() {
		game = new ChessGame();
	}

	public boolean checkLastGameExists() {
		ChessGameDao chessGameDao = new ChessGameDao();
		if (chessGameDao.checkConnection()) {
			return chessGameDao.isLastGameExists();
		}
		return false;
	}

	public void loadLastGame() {
		List<MoveDto> moves = new ChessGameDao().loadMoves();
		game.initialize();
		for (MoveDto move : moves) {
			Position source = new Position(move.getSourceColumn(), move.getSourceRow());
			Position target = new Position(move.getTargetColumn(), move.getTargetRow());
			game.movePiece(source, target);
		}
	}

	public ChessGame initGame() {
		game.initialize();
		return game;
	}

	public void movePiece(final Position source, final Position target) {
		ChessGameDao chessGameDao = new ChessGameDao();
		MoveDto moveDto = new MoveDto(source.getColumn(), source.getRow(), target.getColumn(), target.getRow());
		chessGameDao.saveMove(moveDto);
		game.movePiece(source, target);
	}

	public TempResult getTempResult() {
		return game.getTempResult();
	}

	public Team getFinalWinner() {
		return game.getFinalWinner();
	}

	public boolean isGameDone() {
		ChessGameDao chessGameDao = new ChessGameDao();
		if (game.isGameDone()) {
			chessGameDao.deleteMoves();
			return true;
		}
		return false;
	}

	public Map<Position, Piece> getBoard() {
		return game.getBoard();
	}
}
