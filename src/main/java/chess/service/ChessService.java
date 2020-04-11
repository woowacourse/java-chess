package chess.service;

import chess.database.dao.BoardDao;
import chess.database.dao.TurnDao;
import chess.domain.ChessGame;
import chess.domain.board.Board;
import chess.domain.board.BoardFactory;
import chess.domain.coordinates.Coordinates;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.dto.BoardResponseDto;
import chess.dto.MoveRequestDto;
import chess.dto.MoveResponseDto;
import chess.exception.PieceMoveFailedException;

public class ChessService {
	private final BoardDao boardDao;
	private final TurnDao turnDao;

	public ChessService(BoardDao boardDao, TurnDao turnDao) {
		this.boardDao = boardDao;
		this.turnDao = turnDao;
	}

	public BoardResponseDto resumeGame() {
		Board board = boardDao.getBoard();
		Color turn = turnDao.getTurn();

		ChessGame chessGame = new ChessGame(board, turn);
		return new BoardResponseDto(chessGame);
	}

	public MoveResponseDto move(MoveRequestDto moveRequestDto) throws PieceMoveFailedException {
		Board board = boardDao.getBoard();
		Color turn = turnDao.getTurn();
		ChessGame chessGame = new ChessGame(board, turn);

		move(moveRequestDto, chessGame);
		boolean isEndOfGame = chessGame.isEndOfGame();
		if (isEndOfGame) {
			initializeBoard();
		}
		return MoveResponseDto.ofSuccessToMove(true, isEndOfGame, chessGame.calculateScore());
	}

	private void move(MoveRequestDto moveRequestDto, ChessGame chessGame) {
		Coordinates from = Coordinates.of(moveRequestDto.getFrom());
		Coordinates to = Coordinates.of(moveRequestDto.getTo());
		Piece piece = chessGame.move(from, to);
		boardDao.insertOrUpdatePieceBy(to, piece);
		boardDao.deletePieceBy(from);
		turnDao.update(chessGame.getTurn());
	}

	public BoardResponseDto initializeBoard() {
		Board board = BoardFactory.createNewGame();
		ChessGame chessGame = new ChessGame(board);

		boardDao.deleteBoard();
		boardDao.insertBoard(board);
		turnDao.deleteTurn();
		turnDao.insert(chessGame.getTurn());
		return new BoardResponseDto(chessGame);
	}
}
