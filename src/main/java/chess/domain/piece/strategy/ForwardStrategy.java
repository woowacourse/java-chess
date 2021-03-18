package chess.domain.piece.strategy;

import chess.domain.Board;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.Optional;

public class ForwardStrategy implements MoveStrategy {

    @Override
    public boolean isMovable(Piece piece, Board board) {
        Position position = board.getCoordinates().get(piece);
        Position possiblePosition = position.moveTo(Direction.UP);
        Optional<Piece> targetPiece = board.findPieceBy(possiblePosition);
        return !targetPiece.isPresent() || piece.isEnemy(targetPiece.get());
    }

    @Override
    public List<Direction> getDirections() {

    }
}
