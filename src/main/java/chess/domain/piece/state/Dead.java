package chess.domain.piece.state;

import java.util.List;

import chess.domain.game.state.ChessBoard;
import chess.domain.game.state.position.Position;

public class Dead implements PieceState {
    @Override
    public List<Position> findMovablePositions(Position source, ChessBoard board) {
        throw new IllegalArgumentException("죽은 체스말 입니다.");
    }

    @Override
    public PieceState killed() {
        throw new IllegalArgumentException("죽은 체스말 입니다.");
    }

    @Override
    public PieceState updateState() {
        throw new IllegalArgumentException("죽은 체스말 입니다.");
    }
}
