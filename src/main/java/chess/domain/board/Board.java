package chess.domain.board;

import java.util.HashMap;
import java.util.Map;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;

public class Board {

    private static final String NOT_EXIST_PIECE = "[ERROR] 입력한 위치에 말이 존재하지 않습니다.";
    private static final String NON_MOVABLE_POSITION = "[ERROR] 해당 위치는 말이 움직일 수 없습니다.";
    private static final String NON_MOVABLE_ROUTE = "[ERROR] 해당 위치로 말이 도달할 수 없습니다.";
    private static final String NON_CATCHABLE_PIECE = "[ERROR] 잡을 수 없는 말 입니다.";
    private static final int TOTAL_KING_COUNT = 2;

    private final Map<Position, Piece> board;

    public Board() {
        this.board = new HashMap<>();
    }

    public Board(final Map<Position, Piece> board) {
        this.board = new HashMap<>(board);
    }

    public void movePiece(Position fromPosition, Position toPosition) {
        validateExistPiecePosition(fromPosition);
        Piece pieceToMove = board.get(fromPosition);
        validateMovablePosition(pieceToMove, fromPosition, toPosition);
        if (!pieceToMove.isKnight()) {
            validateNotExistOtherPieceInRoute(fromPosition, toPosition);
        }
        board.remove(fromPosition);
        board.put(toPosition, pieceToMove);
    }

    private void validateExistPiecePosition(Position position) {
        if (!board.containsKey(position)) {
            throw new IllegalArgumentException(NOT_EXIST_PIECE);
        }
    }

    private void validateMovablePosition(Piece piece, Position fromPosition, Position toPosition) {
        if (!piece.isMovable(fromPosition, toPosition) && !isCatchable(piece, fromPosition, toPosition)) {
            throw new IllegalArgumentException(NON_MOVABLE_POSITION);
        }
        if (board.containsKey(toPosition) && piece.isSameTeam(board.get(toPosition))) {
            throw new IllegalArgumentException(NON_CATCHABLE_PIECE);
        }
    }

    private boolean isCatchable(Piece piece, Position fromPosition, Position toPosition) {
        return board.containsKey(toPosition) && piece.isCatchable(fromPosition, toPosition);
    }

    private void validateNotExistOtherPieceInRoute(Position fromPosition, Position toPosition) {
        Direction direction = Direction.judge(fromPosition, toPosition);
        Position nextPosition = Direction.getNextPosition(fromPosition, direction);
        while (nextPosition != toPosition) {
            validateNotExistPiecePosition(nextPosition);
            nextPosition = Direction.getNextPosition(nextPosition, direction);
        }
    }

    private void validateNotExistPiecePosition(Position nextPosition) {
        if (board.containsKey(nextPosition)) {
            throw new IllegalArgumentException(NON_MOVABLE_ROUTE);
        }
    }

    public boolean isAllKingExist() {
        return board.values().stream()
            .filter(Piece::isKing)
            .map(piece -> 1)
            .reduce(0, Integer::sum) == TOTAL_KING_COUNT;
    }

    public Color getWinnerTeamColor() {
        if (isAllKingExist()) {
            return Color.NONE;
        }
        return board.values().stream()
            .filter(Piece::isKing)
            .map(Piece::getColor)
            .findAny()
            .get();
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }
}
