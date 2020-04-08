package chess.service;

import chess.dao.ChessGameDao;
import chess.domain.game.ChessGame;
import chess.domain.game.exception.InvalidTurnException;
import chess.domain.game.state.Ready;
import chess.domain.piece.Position;
import chess.domain.piece.exception.NotMovableException;
import chess.dto.BoardDto;
import chess.dto.ChessGameDto;
import chess.dto.ResponseDto;
import chess.dto.StatusDto;
import chess.dto.TurnDto;
import chess.service.exception.InvalidGameException;

public class ChessGameService {
	private final ChessGameDao chessGameDao;

	public ChessGameService(ChessGameDao chessGameDao) {
		this.chessGameDao = chessGameDao;
	}

	public ResponseDto games() throws Exception {
		return new ResponseDto(ResponseDto.SUCCESS, chessGameDao.findAll());
	}

	public ResponseDto find(int id) throws Exception {
		return chessGameDao.findById(id)
				.map(game -> new ResponseDto(ResponseDto.SUCCESS, convertToChessGameDto(game)))
				.orElseGet(() -> new ResponseDto(ResponseDto.FAIL, null));
	}

	public ResponseDto move(int id, Position source, Position target) throws Exception {
		ChessGame chessGame = chessGameDao.findById(id)
				.orElseThrow(InvalidGameException::new);
		try {
			chessGame.move(source, target);
			chessGameDao.updateById(id, chessGame);
		} catch (NotMovableException | IllegalArgumentException e) {
			return new ResponseDto(ResponseDto.FAIL, "이동할 수 없는 위치입니다.");
		} catch (InvalidTurnException e) {
			return new ResponseDto(ResponseDto.FAIL, chessGame.turn().getColor() + "의 턴입니다.");
		}
		return new ResponseDto(ResponseDto.SUCCESS, convertToChessGameDto(chessGame));
	}

	public ResponseDto restart(int id) throws Exception {
		chessGameDao.findById(id)
				.orElseThrow(InvalidGameException::new);
		ChessGame chessGame = new ChessGame(new Ready());
		chessGame.start();
		chessGameDao.updateById(id, chessGame);
		return new ResponseDto(ResponseDto.SUCCESS, null);
	}

	public ResponseDto create() throws Exception {
		int chessGameId = chessGameDao.create();
		if (chessGameId == 0) {
			return new ResponseDto(ResponseDto.FAIL, null);
		}
		ChessGame chessGame = chessGameDao.findById(chessGameId)
				.orElseThrow(InvalidGameException::new);
		chessGame.start();
		chessGameDao.updateById(chessGameId, chessGame);
		return new ResponseDto(ResponseDto.SUCCESS, chessGameId);
	}

	public ResponseDto delete(int id) throws Exception {
		chessGameDao.deleteById(id);
		return new ResponseDto(ResponseDto.SUCCESS, null);
	}

	private ChessGameDto convertToChessGameDto(ChessGame chessGame) {
		return new ChessGameDto(new BoardDto(chessGame.board()), new TurnDto(chessGame.turn()),
				new StatusDto(chessGame.status().getWhiteScore(), chessGame.status().getBlackScore(),
						chessGame.status().getWinner()), chessGame.isFinished());
	}
}
