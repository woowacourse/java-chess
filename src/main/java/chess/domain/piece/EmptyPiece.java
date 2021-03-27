package chess.domain.piece;

import chess.domain.board.Path;
import chess.domain.piece.strategy.Direction;
import chess.domain.position.Position;
import java.util.List;

public class EmptyPiece implements Piece {

    private static final EmptyPiece EMPTY_PIECE = new EmptyPiece();

    private EmptyPiece() {
    }

    public static EmptyPiece getInstance() {
        return EMPTY_PIECE;
    }

    @Override
    public List<Direction> directions() {
        throw new IllegalArgumentException("존재하지 않는 체스말은 이동 방향을 구할 수 없습니다.");
    }

    @Override
    public Path pathFrom(Direction direction, Position position) {
        throw new IllegalArgumentException("존재하지 않는 체스말은 경로를 구할 수 없습니다.");
    }

    @Override
    public boolean isColor(PieceColor color) {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }
}
