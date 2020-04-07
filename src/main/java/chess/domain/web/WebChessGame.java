package chess.domain.web;

import chess.domain.game.ScoreResult;
import chess.domain.game.Turn;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.pieces.Pieces;
import chess.domain.piece.pieces.PiecesInitializer;
import chess.domain.position.Position;
import chess.domain.position.positions.Positions;

public class WebChessGame {
	private Pieces pieces;
	private Turn turn;

	public WebChessGame() {
		pieces = PiecesInitializer.operate();
		turn = new Turn(Color.WHITE);
	}

	public void reset() {
		pieces = PiecesInitializer.reset();
		turn = new Turn(Color.WHITE);
	}

	public void move(Position start, Position end) {
		pieces.move(start, end, turn.getColor());
		turn = turn.change();
	}

	public ScoreResult calculateScore() {
		return new ScoreResult(pieces);
	}

	public boolean isKingDead() {
		return pieces.isKingDead();
	}

	public Color getAliveKingColor() {
		return pieces.getAliveKingColor();
	}

	public BoardDto createBoardDto() {
		return new BoardDto(pieces);
	}

	public Positions findMovablePositions(Position position) {
		Piece piece = pieces.findBy(position, turn.getColor());
		return piece.createMovablePositions(pieces.getPieces());
	}

	public Turn getTurn() {
		return turn;
	}

	public Pieces getPieces() {
		return pieces;
	}
}
