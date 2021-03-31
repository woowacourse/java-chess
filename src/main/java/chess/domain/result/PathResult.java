package chess.domain.result;

import chess.domain.board.Board;
import chess.domain.board.Path;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.List;
import java.util.Map;

public final class PathResult implements Result {

    private final Board board;
    private final Position source;

    public PathResult(final Board board, final Position source) {
        this.board = board;
        this.source = source;
    }

    @Override
    public String infoAsString() {
        throw new IllegalArgumentException("경로는 문자열로 활용할 수 없습니다.");
    }

    @Override
    public Map<Position, Piece> infoAsMap() {
        throw new IllegalArgumentException("경로는 맵으로 활용할 수 없습니다.");
    }

    @Override
    public List<Position> infoAsList() {
        final Path path = board.pathsOf(source);
        return path.positions();
    }
}
