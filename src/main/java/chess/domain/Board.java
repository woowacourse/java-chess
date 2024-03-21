package chess.domain;

import chess.domain.piece.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    static final String ERROR_NOT_EXIST_PIECE = "해당 위치에 기물이 존재하지 않습니다.";
    static final String ERROR_MOVE_NOT_AVAILABLE = "해당 위치로 기물을 이동할 수 없습니다.";
    private final Map<Position, Piece> values;

    public Board(Map<Position, Piece> values) {
        this.values = new HashMap<>(values);
    }

    public void move(final Position source, final Position target) {
        Piece sourcePiece = values.get(source);

        if (isNotExistPiece(source)) {
            throw new IllegalArgumentException(ERROR_NOT_EXIST_PIECE);
        }

        if (!sourcePiece.canMove(source, target)) {
            throw new IllegalArgumentException(ERROR_MOVE_NOT_AVAILABLE);
        }

        if (sourcePiece.getType() != PieceType.KNIGHT && existObstacleOnPath(source, target)) {
            throw new IllegalArgumentException(ERROR_MOVE_NOT_AVAILABLE);
        }

        values.remove(source);
        values.put(target, sourcePiece);
    }

    private boolean existObstacleOnPath(final Position source, final Position target) {
        List<Position> path = generatePath(source, target);
        return path.stream().anyMatch(values::containsKey);
    }

    // TODO: 기물의 책임이 아님 ~> 다른 곳으로 책임 위임 ~> 차라리 board가 나은데 여기도 적절하지 않다. ~> 객체 분리 시점이다.
    private List<Position> generatePath(Position source, Position target) {
        List<Position> path = new ArrayList<>();

        int deltaFile = (int) Math.signum(target.file().get() - source.file().get());
        int deltaRank = (int) Math.signum(target.rank().get() - source.rank().get());

        File file = source.file();
        Rank rank = source.rank();
        while (target.file() != file || target.rank() != rank) {
            file = file.add(deltaFile);
            rank = rank.add(deltaRank);

            path.add(new Position(file, rank));
        }

        return path;
    }

    public boolean isNotExistPiece(Position position) {
        return !values.containsKey(position);
    }

    public Map<Position, Piece> get() {
        return values;
    }
}
