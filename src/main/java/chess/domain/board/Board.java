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
        List<Entry<Position, Piece>> entries = findSameTeamPieces(turn.change());
        Position kingPosition = findKingPosition(turn);

        for (Entry<Position, Piece> entry : entries) {
            if (entry.getValue().isMovablePath(entry.getKey(), kingPosition)) {
                return true;
            }
        }
        return false;
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

    public void move(Position from, Position to) {
        Piece piece = board.get(from);
        Direction direction = piece.findDirection(from, to);
        validNowTurn(piece);
        piece.movable(from, to);
        piece.validArrive(board.get(to), direction);
        validPath(from, to, direction);

        board.put(to, piece);
        board.remove(from);
        turn = turn.change();
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

    public Score createResult() {
        return new Score(board);
    }
}
