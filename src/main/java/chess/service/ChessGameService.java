package chess.service;

import java.util.Optional;

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

public class ChessGameService {
	private final ChessGameDao chessGameDao;

	public ChessGameService(ChessGameDao chessGameDao) {
		this.chessGameDao = chessGameDao;
	}

	public ResponseDto games() throws Exception {
		return new ResponseDto(ResponseDto.SUCCESS, chessGameDao.findAll());
	}

	public ResponseDto find(int id) throws Exception {
		Optional<ChessGame> chessGame = chessGameDao.findById(id);
		return chessGame.map(game -> new ResponseDto(ResponseDto.SUCCESS, convertToChessGameDto(game)))
				.orElseGet(() -> new ResponseDto(ResponseDto.FAIL, null));
	}

	public ResponseDto move(int id, Position source, Position target) throws Exception {
		Optional<ChessGame> optionalChessGame = chessGameDao.findById(id);
		try {
			optionalChessGame.ifPresent(chessGame -> {
				chessGame.move(source, target);
				try {
					chessGameDao.updateById(id, chessGame);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			});
		} catch (NotMovableException | IllegalArgumentException e) {
			return new ResponseDto(ResponseDto.FAIL, "이동할 수 없는 위치입니다.");
		} catch (InvalidTurnException e) {
			return new ResponseDto(ResponseDto.FAIL, optionalChessGame.get().turn().getColor() + "의 턴입니다.");
		}
		return new ResponseDto(ResponseDto.SUCCESS, convertToChessGameDto(optionalChessGame.get()));
	}

	public ResponseDto restart(int id) throws Exception {
		Optional<ChessGame> chessGame = chessGameDao.findById(id);
		if (!chessGame.isPresent()) {
			return new ResponseDto(ResponseDto.FAIL, null);
		}
		ChessGame newChessGame = new ChessGame(new Ready());
		newChessGame.start();
		chessGameDao.updateById(id, newChessGame);
		return new ResponseDto(ResponseDto.SUCCESS, convertToChessGameDto(chessGame.get()));
	}

	public ResponseDto create() throws Exception {
		int chessGameId = chessGameDao.create();
		if (chessGameId == 0) {
			return new ResponseDto(ResponseDto.FAIL, null);
		}
		Optional<ChessGame> chessGame = chessGameDao.findById(chessGameId);
		if (chessGame.isPresent()) {
			chessGame.get().start();
			chessGameDao.updateById(chessGameId, chessGame.get());
			return new ResponseDto(ResponseDto.SUCCESS, chessGameId);
		}
		return new ResponseDto(ResponseDto.FAIL, null);
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
