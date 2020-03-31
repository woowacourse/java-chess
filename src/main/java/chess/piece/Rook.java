package chess.piece;

import java.util.List;
import java.util.stream.Collectors;

import chess.position.File;
import chess.position.Position;
import chess.position.Rank;
import chess.validator.RookMoveValidator;

public class Rook extends Piece {
	public Rook(Team team) {
		super(team, "R", new RookMoveValidator());
	}
}
