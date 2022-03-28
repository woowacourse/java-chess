package chess.domain.board;

import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class Board {

    private final Map<Position, Piece> board = new HashMap<>();
    private Team turn = Team.WHITE;

    public void initBoard(BoardGenerator boardGenerator) {
        board.putAll(boardGenerator.create());
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public boolean check() {
        Position to = findKingPosition(turn);
        return checkAnyMatch(to);
    }

    public boolean checkmate() {
        Position kingPosition = findKingPosition(turn);
        List<Position> kingPaths = board.get(kingPosition).findMovablePosition(kingPosition);

        for (Position to : kingPaths) {
            if (!checkAnyMatch(to)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkAnyMatch(Position to) {
        return findSameTeamPieces(turn.change())
                .stream()
                .anyMatch(entry -> validCheck(entry.getKey(), to, entry.getValue()));
    }

    private List<Entry<Position, Piece>> findSameTeamPieces(Team team) {
        return board.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSameTeam(team))
                .collect(Collectors.toUnmodifiableList());
    }

    private Position findKingPosition(Team team) {
        return board.entrySet()
                .stream()
                .filter(entry -> entry.getValue().isSameTeam(team) && entry.getValue().isKing())
                .map(Entry::getKey)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("King 이 존재하지 않습니다."));
    }

    private boolean validCheck(Position from, Position to, Piece fromPiece) {
        try {
            fromPiece.movable(from, to);
            validPath(from, to, fromPiece.findDirection(from, to));
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public void move(Position from, Position to) {
        validCanMove(from, to);
        validCheckAfterMove(from, to);
        turn = turn.change();
    }

    private void validCanMove(Position from, Position to) {
        Piece fromPiece = board.get(from);
        Piece toPiece = board.get(to);
        Direction direction = fromPiece.findDirection(from, to);

        validNowTurn(fromPiece);
        fromPiece.movable(from, to);
        fromPiece.validArrive(toPiece, direction);
        validPath(from, to, direction);
    }

    private void validCheckAfterMove(Position from, Position to) {
        Piece fromPiece = board.get(from);
        Piece toPiece = board.get(to);
        board.put(to, fromPiece);
        board.remove(from);
        if (check()) {
            board.put(to, toPiece);
            board.put(from, fromPiece);
            throw new IllegalArgumentException("체크 상황을 벗어나야 합니다.");
        }
    }

    private void validNowTurn(Piece piece) {
        if (!piece.isSameTeam(turn)) {
            throw new IllegalArgumentException("현재 차례는 " + turn + "입니다.");
        }
    }

    private void validPath(Position from, Position to, Direction direction) {
        Position current = from.move(direction);

        while (!current.equals(to)) {
            if (board.get(current) != null) {
                throw new IllegalArgumentException("이동 경로에 말이 있습니다.");
            }
            current = current.move(direction);
        }
    }

    public boolean isEmpty() {
        return board.isEmpty();
    }

    public Result createResult() {
        return new Result(board);
    }
}
