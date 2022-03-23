package chess.view;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;

public class EmptySpace extends Piece {
	public EmptySpace() {
		super("blank", Color.BLACK);
	}
}
