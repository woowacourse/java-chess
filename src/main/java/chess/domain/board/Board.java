package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private static final String INVALID_POSITION_INDEX = "잘못된 위치를 입력했습니다.";

    private final Map<Position, Piece> boards;

    public Board(Map<Position, Piece> boards) {
        this.boards = new HashMap<>(boards);
    }

    public Piece findPiece(Position position) {
        if (boards.containsKey(position)) {
            return boards.get(position);
        }
        throw new IllegalArgumentException(INVALID_POSITION_INDEX);
    }

    public void movePiece(Position sourcePosition, Position targetPosition, Team nowPlayingTeam) {
        validatePaths(sourcePosition.findStraightPaths(targetPosition));
        Piece sourcePiece = boards.get(sourcePosition);
        Piece movedPiece = sourcePiece.move(targetPosition, nowPlayingTeam, findPiece(targetPosition).getTeam());
        boards.put(targetPosition, movedPiece);
        boards.put(sourcePosition, Empty.create(sourcePosition));
    }

    private void validatePaths(List<Position> paths) {
        if (!isEmptyPosition(paths)) {
            throw new IllegalArgumentException(INVALID_POSITION_INDEX);
        }
    }

    boolean isEmptyPosition(List<Position> paths) {
        return paths.isEmpty() || paths.stream()
                .map(boards::get)
                .allMatch(Piece::isEmpty);
    }

    public Map<Position, Piece> getBoards() {
        return boards;
    }
}
