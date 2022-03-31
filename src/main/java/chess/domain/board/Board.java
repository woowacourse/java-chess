package chess.domain.board;

import static chess.domain.piece.Direction.NORTH;
import static chess.domain.piece.Direction.SOUTH;
import static chess.domain.piece.Direction.pullDiagonalDirections;

import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

public class Board {

    private final Map<Position, Piece> board = new HashMap<>();
    private Team turn = Team.WHITE;

    public void initBoard(BoardGenerationStrategy boardGenerator) {
        board.putAll(boardGenerator.create());
    }

    public void move(Position from, Position to) {
        Piece fromPiece = board.get(from);
        Direction direction = fromPiece.findDirection(from, to);

        fromPiece.movable(from, to);
        validatePath(from, to, direction);
        validateMoveByPieceType(fromPiece, board.get(to), direction);

        moveOrRollBack(from, to);
        turn = turn.change();
    }


    private void validateMoveByPieceType(Piece from, Piece to, Direction direction) {
        validateNowTurn(from);

        if (from.isPawn()) {
            checkStraightCondition(to, direction);
            checkDiagonalCondition(from, to, direction);
        }
        checkDifferentTeam(from, to);
    }

    private void validateNowTurn(Piece piece) {
        if (!piece.isSameTeam(turn)) {
            throw new IllegalArgumentException("현재 차례는 " + turn + "입니다.");
        }
    }

    private void validatePath(Position from, Position to, Direction direction) {
        Position current = from.move(direction);

        while (!current.equals(to)) {
            if (board.get(current) != null) {
                throw new IllegalArgumentException("이동 경로에 말이 있습니다.");
            }
            current = current.move(direction);
        }
    }

    private void moveOrRollBack(Position from, Position to) {
        Piece fromPiece = board.get(from);
        Piece toPiece = board.get(to);
        board.put(to, fromPiece);
        board.remove(from);
        if (isCheck()) {
            board.put(to, toPiece);
            board.put(from, fromPiece);
            throw new IllegalArgumentException("체크 상황을 벗어나야 합니다.");
        }
    }

    public boolean isCheck() {
        if (board.isEmpty()) {
            return false;
        }
        Position to = findKingPosition(turn);
        return hasCheckKing(to);
    }

    private Position findKingPosition(Team team) {
        return board.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSameTeam(team) && entry.getValue().isKing())
                .map(Entry::getKey)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("King 이 존재하지 않습니다."));
    }

    private boolean hasCheckKing(Position to) {
        return findSameTeamPieces(turn.change())
                .stream()
                .anyMatch(entry -> canKillKing(entry.getKey(), to, entry.getValue()));
    }

    private List<Entry<Position, Piece>> findSameTeamPieces(Team team) {
        return board.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSameTeam(team))
                .collect(Collectors.toUnmodifiableList());
    }

    private boolean canKillKing(Position from, Position to, Piece fromPiece) {
        try {
            fromPiece.movable(from, to);
            validatePath(from, to, fromPiece.findDirection(from, to));
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    private void checkStraightCondition(Piece to, Direction direction) {
        if ((direction == NORTH || direction == SOUTH) && Objects.nonNull(to)) {
            throw new IllegalArgumentException("직진은 도착 지점에 말이 없을 때만 가능합니다.");
        }
    }

    private void checkDiagonalCondition(Piece from, Piece to, Direction direction) {
        if (pullDiagonalDirections().contains(direction)
                && (Objects.isNull(to) || from.isSameTeam(to))) {
            throw new IllegalArgumentException("대각선 이동은 상대편의 말을 잡을 때만 가능합니다.");
        }
    }

    private void checkDifferentTeam(Piece from, Piece to) {
        if (Objects.nonNull(to) && from.isSameTeam(to)) {
            throw new IllegalArgumentException("도착 지점에 아군 말이 있어 이동이 불가능합니다.");
        }
    }

    public boolean isCheckmate() {
        if (board.isEmpty()) {
            return false;
        }
        try {
            Position kingPosition = findKingPosition(turn);
            List<Position> kingPaths = board.get(kingPosition).findMovablePosition(kingPosition);
            return cannotMoveKing(kingPaths);
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    private boolean cannotMoveKing(List<Position> kingPaths) {
        for (Position to : kingPaths) {
            if (!hasCheckKing(to)) {
                return false;
            }
        }
        return true;
    }

    public boolean isEmpty() {
        return board.isEmpty();
    }

    public Result createResult() {
        return new Result(board);
    }

    public Map<Position, Piece> getBoard() {
        return Map.copyOf(board);
    }
}
