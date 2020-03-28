package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.position.Position;

import java.util.List;

public class Blank extends Piece {
    public Blank(char representation, Team team, Position position) {
        super(representation, team, position);
    }

    @Override
    public List<Position> getPossiblePositions(Board board) {
        throw new UnsupportedOperationException("빈 칸은 이동할 수 없습니다.");
    }

    @Override
    public Piece movePiecePosition(Position toPosition) {
        throw new UnsupportedOperationException("빈 칸은 이동할 수 없습니다.");
    }
}
