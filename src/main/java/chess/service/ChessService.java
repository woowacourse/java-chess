package chess.service;

import java.util.List;

import chess.dao.ChessDAO;
import chess.dao.TurnDAO;
import chess.domain.Result;
import chess.domain.Status;
import chess.domain.Team;
import chess.domain.Turn;
import chess.domain.chessboard.ChessBoard;
import chess.domain.chesspiece.ChessPiece;
import chess.domain.factory.BoardFactory;
import chess.domain.position.Position;
import chess.dto.ChessDTO;

public class ChessService {
	private final ChessDAO chessDAO;
	private final TurnDAO turnDAO;

	public ChessService() {
		chessDAO = new ChessDAO();
		turnDAO = new TurnDAO();
	}

	public ChessBoard move(Position startPosition, Position targetPosition) {
		ChessBoard chessBoard = find();
		ChessPiece startPiece = chessBoard.findByPosition(startPosition);
		chessBoard.move(startPosition, targetPosition);
		chessDAO.update(targetPosition, startPiece.getName());
		chessDAO.update(startPosition, ".");
		turnDAO.changeTurn(chessBoard.isWhiteTurn());
		return chessBoard;
	}

	public ChessBoard find() {
		List<ChessDTO> chessDTOS = chessDAO.findAll();
		Turn turn = turnDAO.find();
		if (chessDTOS.isEmpty()) {
			return createBoard(BoardFactory.createBoard());
		}
		ChessBoard chessBoard = BoardFactory.createBoard(chessDTOS, turn);
		return chessBoard;
	}

	private ChessBoard createBoard(ChessBoard chessBoard) {
		List<ChessPiece> chessPieces = chessBoard.findAll();
		for (ChessPiece chessPiece : chessPieces) {
			String position = chessPiece.getPositionName();
			String name = chessPiece.getName();
			chessDAO.addPiece(new ChessDTO(position, name));
		}
		return chessBoard;
	}

	public boolean restart() {
		chessDAO.removeAll();
		turnDAO.removeAll();
		return true;
	}

	public boolean isEnd() {
		ChessBoard chessBoard = find();
		return chessBoard.isLiveBothKing() == false;
	}

	public boolean isWinWhiteTeam() {
		ChessBoard chessBoard = find();
		return chessBoard.isLiveKing(Team.WHITE);
	}

	public Result status() {
		ChessBoard chessBoard = find();
		Status status = chessBoard.createStatus();
		return status.getResult();
	}
}
