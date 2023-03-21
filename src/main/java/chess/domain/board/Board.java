package chess.domain.board;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

import java.util.List;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> boards;

    public Board(Map<Position, Piece> boards) {
        this.boards = boards;
    }

    public Piece findPiece(Position position) {
        if (boards.containsKey(position)) {
            return boards.get(position);
        }
        throw new IllegalArgumentException("잘못된 위치를 입력했습니다");
    }

    public void movePiece(Position sourcePosition, Position targetPosition, Team nowPlayingTeam) {
        validate(sourcePosition, targetPosition, nowPlayingTeam);
        Piece sourcePiece = boards.get(sourcePosition);
        Piece movedPiece = sourcePiece.move();
        boards.put(targetPosition, movedPiece);
        boards.put(sourcePosition, Empty.create());
    }

    private void validate(Position sourcePosition, Position targetPosition, Team nowPlayingTeam) {
        validateSourceTeam(sourcePosition, nowPlayingTeam);
        validateCanMove(sourcePosition, targetPosition);
        List<Position> path = sourcePosition.findPath(targetPosition);
        validatePath(path);
    }

    private void validateSourceTeam(Position sourcePosition, Team nowPlayingTeam) {
        Piece sourcePiece = findPiece(sourcePosition);
        sourcePiece.validateTeam(nowPlayingTeam);
    }

    private void validateCanMove(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = findPiece(sourcePosition);
        Piece targetPiece = findPiece(targetPosition);
        sourcePiece.validateCanMove(sourcePosition, targetPosition, targetPiece.getTeam());
    }

    private void validatePath(List<Position> paths) {
        if (!isEmptyPosition(paths)) {
            throw new IllegalArgumentException("경로가 없습니다.");
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
