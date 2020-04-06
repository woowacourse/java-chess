package chess.service;

import java.sql.SQLException;

import chess.dao.ChessGameDao;
import chess.domain.game.Board;
import chess.domain.game.ChessGame;
import chess.domain.game.Turn;
import chess.domain.game.exception.InvalidTurnException;
import chess.domain.game.state.Playing;
import chess.domain.piece.Position;
import chess.domain.piece.exception.NotMovableException;
import chess.dto.BoardDto;
import chess.dto.ChessGameDto;
import chess.dto.ResponseDto;
import chess.dto.StatusDto;
import chess.dto.TurnDto;

public class ChessGameService {
	private final ChessGameDao chessGameDao;

	public ChessGameService() {
		this.chessGameDao = new ChessGameDao();
	}

	public ResponseDto games() throws SQLException {
		return new ResponseDto(ResponseDto.SUCCESS, chessGameDao.findAll());
	}

	public ResponseDto findById(int id) throws SQLException {
		ChessGame chessGame = chessGameDao.findById(id);
		if (chessGame == null) {
			return new ResponseDto(ResponseDto.FAIL, null);
		}
		return new ResponseDto(ResponseDto.SUCCESS, convertToChessGameDto(chessGame));
	}

	public ResponseDto move(int id, Position source, Position target) throws SQLException {
		ChessGame chessGame = chessGameDao.findById(id);
		try {
			chessGame.move(source, target);
			chessGameDao.update(id, chessGame);
		} catch (NotMovableException | IllegalArgumentException e) {
			return new ResponseDto(ResponseDto.FAIL, "이동할 수 없는 위치입니다.");
		} catch (InvalidTurnException e) {
			return new ResponseDto(ResponseDto.FAIL, chessGame.turn().getColor() + "의 턴입니다.");
		}
		return new ResponseDto(ResponseDto.SUCCESS, convertToChessGameDto(chessGame));
	}

	public ResponseDto restart(int id) throws SQLException {
		ChessGame chessGame = chessGameDao.findById(id);
		if (chessGame == null) {
			return new ResponseDto(ResponseDto.FAIL, null);
		}
		ChessGame newChessGame = new ChessGame(new Playing(Board.create(), Turn.WHITE));
		chessGameDao.update(id, newChessGame);
		return new ResponseDto(ResponseDto.SUCCESS, convertToChessGameDto(chessGame));
	}

	public ResponseDto create() throws SQLException {
		int chessGameId = chessGameDao.create();
		ChessGame chessGame = chessGameDao.findById(chessGameId);
		chessGame.start();
		chessGameDao.update(chessGameId, chessGame);
		return new ResponseDto(ResponseDto.SUCCESS, chessGameId);
	}

	private ChessGameDto convertToChessGameDto(ChessGame chessGame) {
		return new ChessGameDto(new BoardDto(chessGame.board()), new TurnDto(chessGame.turn()),
				new StatusDto(chessGame.status().getWhiteScore(), chessGame.status().getBlackScore(),
						chessGame.status().getWinner()), chessGame.isFinished());
	}
}
