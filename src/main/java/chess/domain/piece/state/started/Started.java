package chess.domain.piece.state.started;

import java.util.Map;

import chess.domain.game.state.position.Direction;
import chess.domain.game.state.position.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.state.Dead;
import chess.domain.piece.state.PieceState;

public abstract class Started implements PieceState {
    @Override
    public PieceState die() {
        return new Dead();
    }

    public boolean canKill(Map<Position, Piece> board, Position source, Position target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);

        return isFilled(board, target) && !sourcePiece.isSameColor(targetPiece);
    }

    public boolean isFilled(Map<Position, Piece> board, Position target) {
        return board.containsKey(target);
    }

    public boolean canMoveOrKillByOneStep(Map<Position, Piece> board, Position source, Direction direction) {
        Position target = source.findNext(direction);
        if (source.isBlocked(direction)) {
            return false;
        }

        return !isFilled(board, target) || canKill(board, source, target);
    }

    public boolean canOnlyMoveByOneStep(Map<Position, Piece> board, Position source, Direction direction) {
        Position target = source.findNext(direction);
        if (source.isBlocked(direction)) {
            return false;
        }

        return !isFilled(board, target);
    }
}
