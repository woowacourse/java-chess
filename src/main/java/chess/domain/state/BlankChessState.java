package chess.domain.state;

import chess.domain.color.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import java.util.Map;

public final class BlankChessState extends ChessState {

    public BlankChessState(Map<Position, Piece> board) {
        super(board);
    }

    @Override
    public void move(Color turnColor, Positions positions) {
        throw new IllegalArgumentException("이동할 수 있는 말이 없습니다.");
    }
}
