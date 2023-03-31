package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.RoleType;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private static final int INIT_KING_COUNT = 2;

    private final Map<Position, Piece> boards;

    public Board(Map<Position, Piece> boards) {
        this.boards = boards;
    }

    public Board() {
        this(new HashMap<>());
    }

    public Piece findPiece(Position position) {
        if (boards.containsKey(position)) {
            return boards.get(position);
        }
        throw new IllegalArgumentException("잘못된 위치를 입력했습니다");
    }

    public void movePiece(Position sourcePosition, Position targetPosition) {
        Piece sourcePiece = boards.get(sourcePosition);
        validateMove(sourcePosition, targetPosition, sourcePiece);
        boards.put(targetPosition, sourcePiece);
        boards.put(sourcePosition, Empty.create());
    }

    private void validateMove(Position sourcePosition, Position targetPosition, Piece sourcePiece) {
        validateEmptyPiece(sourcePiece);
        validateSamePosition(sourcePosition, targetPosition);
        validatePieceMove(sourcePosition, targetPosition, sourcePiece);
        validatePath(sourcePosition, targetPosition);
    }

    private void validateSamePosition(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException("자신의 위치로 이동할 수 없습니다.");
        }
    }

    private void validateEmptyPiece(Piece sourcePiece) {
        if (sourcePiece.isSameRoleType(RoleType.EMPTY)) {
            throw new IllegalArgumentException("해당 위치에 말이 없습니다.");
        }
    }

    private void validatePieceMove(Position sourcePosition, Position targetPosition, Piece sourcePiece) {
        Color tagetColor = boards.get(targetPosition).getColor();
        if (!sourcePiece.canMove(sourcePosition, targetPosition, tagetColor)) {
            throw new IllegalArgumentException("잘못된 위치를 지정하셨습니다.");
        }
    }

    private void validatePath(Position sourcePosition, Position targetPosition) {
        if (isBlockBetween(sourcePosition, targetPosition)) {
            throw new IllegalArgumentException("경로에 다른 말이 있습니다.");
        }
    }

    private boolean isBlockBetween(Position sourcePosition, Position targetPosition) {
        return !sourcePosition.findPathWithoutSourceAndTarget(targetPosition).stream()
                .map(boards::get)
                .allMatch(piece -> piece.isSameRoleType(RoleType.EMPTY));
    }

    public boolean checkKingDead() {
        long kingCount = boards.values().stream()
                .filter(piece -> piece.isSameRoleType(RoleType.KING))
                .count();
        return kingCount < INIT_KING_COUNT;
    }

    public void initialize() {
        boards.putAll(BoardFactory.createBoard());
    }

    public boolean isNotInitialize() {
        return boards.isEmpty();
    }

    public Map<Position, Piece> getBoards() {
        return boards;
    }
}
