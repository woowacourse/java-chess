package chess.domain.board;

import chess.domain.piece.Direction;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board = new HashMap<>();
    private Team turn = Team.WHITE;

    public void initBoard(BoardGenerator boardGenerator) {
        board.putAll(boardGenerator.create());
    }

    public Map<Position, Piece> getBoard() {
        return board;
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
