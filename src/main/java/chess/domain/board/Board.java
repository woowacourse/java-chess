package chess.domain.board;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chess.domain.piece.Color;
import chess.domain.piece.None;
import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Square;

public final class Board {
    private static final String ERROR_MESSAGE_POSITION_INCAPABLE = "[ERROR] 허걱... 거긴 못가... 미안..";
    private static final String ERROR_MESSAGE_DIRECTION_INCAPABLE = "[ERROR] 길이 막혔다...!";

    private final Map<Square, Piece> board;

    public Board(BoardGenerator boardGenerator) {
        this(boardGenerator.generate());
    }

    private Board(Map<Square, Piece> board) {
        this.board = new LinkedHashMap<>(board);
    }

    public boolean isRightTurn(Square source, Color turn) {
        return board.get(source).isSameColor(turn);
    }

    public void checkCanMove(Square source, Square target) {
        Piece sourcePiece = board.get(source);
        Piece targetPiece = board.get(target);
        Direction direction = source.getGap(target);

        checkCapablePosition(direction, sourcePiece, targetPiece);
        checkCapableRoute(source, target, direction);
        sourcePiece.checkSameTeam(targetPiece);
    }

    private void checkCapablePosition(Direction direction, Piece sourcePiece, Piece targetPiece) {
        if (!sourcePiece.canMove(direction, targetPiece)) {
            throw new IllegalArgumentException(ERROR_MESSAGE_POSITION_INCAPABLE);
        }
    }

    private void checkCapableRoute(Square source, Square target, Direction direction) {
        Direction unitDirection = direction.getUnitDirection();
        Square road = source.add(unitDirection);

        while (!road.equals(target)) {
            checkNone(board.get(road));
            road = road.add(unitDirection);
        }
    }

    private void checkNone(Piece roadPiece) {
        if (!roadPiece.isNone()) {
            throw new IllegalArgumentException(ERROR_MESSAGE_DIRECTION_INCAPABLE);
        }
    }

    public boolean isTargetKing(Square target) {
        return board.get(target).isKing();
    }

    public Board move(Square source, Square target) {
        Piece sourcePiece = board.get(source);
        board.put(target, sourcePiece);
        board.put(source, new None(Color.NONE));
        return new Board(board);
    }

    public List<Map.Entry<Square, Piece>> filterBy(Color color) {
        return board.entrySet().stream()
                .filter(entry -> entry.getValue().isSameColor(color))
                .collect(Collectors.toList());
    }

    public Map<Square, Piece> getBoard() {
        return new LinkedHashMap<>(board);
    }
}
