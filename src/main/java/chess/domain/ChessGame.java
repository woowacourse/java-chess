package chess.domain;

import java.util.List;
import java.util.Optional;

import chess.exception.GameOverException;
import chess.exception.UnmovableException;
import chess.domain.piece.Piece;

public class ChessGame {
	private ChessBoard chessBoard;
	private Player currentPlayer;

	public ChessGame(ChessBoard chessBoard) {
		currentPlayer = Player.WHITE;
		this.chessBoard = chessBoard;
	}

	public ChessGame(Player player, ChessBoard chessBoard) {
		currentPlayer = player;
		this.chessBoard = chessBoard;
	}

	public void move(Position start, Position end) {
		Optional<Piece> startPiece = chessBoard.findPiece(start);
		if(!startPiece.isPresent() || !startPiece.get().isMine(currentPlayer)) {
			throw new UnmovableException();
		}

		Optional<Piece> endPiece = chessBoard.findPiece(end);

		Path path = getPath(end, startPiece, endPiece);
		if (!chessBoard.isMovable(path)) {
			throw new UnmovableException();
		}

		if (endPiece.isPresent() && !endPiece.get().isMine(currentPlayer)) {
			chessBoard.remove(endPiece.get());
			checkGameOver(endPiece.get());
		}

		startPiece.get().changePosition(end);
		currentPlayer = currentPlayer.changePlayer();
	}

	private void checkGameOver(Piece endPiece) {
		if (endPiece.isKing()) {
			throw new GameOverException("게임종료! " + currentPlayer.name() + " 승리");
		}
	}

	private Path getPath(Position end, Optional<Piece> startPiece, Optional<Piece> endPiece) {
		if (!endPiece.isPresent()) {
			return startPiece.get().getMovablePath(end);
		}
		if (!endPiece.get().isMine(currentPlayer)) {
			return startPiece.get().getAttackablePath(end);
		}
		throw new UnmovableException();
	}

	public double getPlayerScore(Player player) {
		return chessBoard.getXScore(player).getScore();
	}

	public Result findWinner() {
		return compareResult(getPlayerScore(Player.BLACK), getPlayerScore(Player.WHITE));
	}

	private Result compareResult(double blackScore, double whiteScore) {
		return (blackScore == whiteScore)? Result.DRAW :
				(blackScore > whiteScore)? Result.BLACK_WIN : Result.WHITE_WIN;
	}

	public List<Piece> getPieces() {
		return chessBoard.getPieces();
	}

	public String getCurrentPlayerName() {
		return this.currentPlayer.name();
	}
}