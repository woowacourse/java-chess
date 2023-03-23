package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.Team;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Board {

    private static final String INVALID_POSITION_INDEX = "잘못된 위치를 입력했습니다";
    private static final String INVALID_PATH_MESSAGE = "경로가 없습니다.";

    private final Map<Position, Piece> boards;

    public Board(Map<Position, Piece> boards) {
        this.boards = boards;
    }

    public Piece findPiece(Position position) {
        if (boards.containsKey(position)) {
            return boards.get(position);
        }
        throw new IllegalArgumentException(INVALID_POSITION_INDEX);
    }

    public void movePiece(Position sourcePosition, Position targetPosition, Team nowPlayingTeam) {
        validate(sourcePosition, targetPosition, nowPlayingTeam);
        Piece sourcePiece = boards.get(sourcePosition);
        Piece movedPiece = sourcePiece.move();
        boards.put(targetPosition, movedPiece);
        boards.put(sourcePosition, null);
    }

    private void validate(Position sourcePosition, Position targetPosition, Team nowPlayingTeam) {
        validateSourceTeam(sourcePosition, nowPlayingTeam);
        validateCanMove(sourcePosition, targetPosition);
        List<Position> paths = sourcePosition.findStraightPaths(targetPosition);
        validatePath(paths);
    }

    private void validateSourceTeam(Position sourcePosition, Team nowPlayingTeam) {
        Piece sourcePiece = findPiece(sourcePosition);
        if (sourcePiece == null) {
            throw new IllegalArgumentException("본인 말만 움직일 수 있습니다.");
        }
        sourcePiece.validateTeam(nowPlayingTeam);
    }

    private void validateCanMove(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = findPiece(sourcePosition);
        Piece targetPiece = findPiece(targetPosition);
        if (sourcePiece == null) {
            throw new IllegalArgumentException("본인 말만 움직일 수 있습니다.");
        }
        if (targetPiece == null) {
            sourcePiece.validateCanMove(sourcePosition, targetPosition, Team.EMPTY);
            return;
        }
        sourcePiece.validateCanMove(sourcePosition, targetPosition, targetPiece.getTeam());
    }

    private void validatePath(List<Position> paths) {
        if (!isEmptyPosition(paths)) {
            throw new IllegalArgumentException(INVALID_PATH_MESSAGE);
        }
    }

    boolean isEmptyPosition(List<Position> paths) {
        return paths.isEmpty() || paths.stream()
                .map(boards::get)
                .allMatch(Objects::isNull);
    }

    public Map<Position, Piece> getBoards() {
        return boards;
    }
}
