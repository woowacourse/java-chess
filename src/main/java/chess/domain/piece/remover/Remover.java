package chess.domain.piece.remover;

import chess.domain.board.position.Position;
import chess.domain.piece.Piece;

import java.util.List;
import java.util.Set;

public interface Remover {
	Set<Position> findRemovablePositions(Set<Position> positions, List<Piece> pieces);
}
