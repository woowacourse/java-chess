package chess.domain.piece.state;

import java.util.List;

import chess.domain.ChessBoard;
import chess.domain.piece.position.Position;

public class Dead implements State {
    @Override
    public List<Position> getMovablePositions(Position source, ChessBoard board) {
        throw new IllegalArgumentException("죽은 체스말 입니다.");
    }

    @Override
    public State killed() {
        throw new IllegalArgumentException("죽은 체스말 입니다.");
    }

    @Override
    public State updateState() {
        throw new IllegalArgumentException("죽은 체스말 입니다.");
    }
}
