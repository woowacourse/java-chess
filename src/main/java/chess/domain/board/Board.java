package chess.domain.board;

import java.util.HashMap;
import java.util.Map;

import chess.domain.game.Team;
import chess.domain.move.Move;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public class Board {

    private static final EmptyPiece EMPTY_PIECE = EmptyPiece.create();

    private final Map<Position, Piece> pieces;

    protected Board(Map<Position, Piece> pieces) {
        this.pieces = new HashMap<>(pieces);
    }

    public void move(Position source, Position target) {
        validateNotSameTeam(source, target);
        validateMove(source, target);
        validateNoObstacle(source, target);
        makeMove(source, target);
    }

    private void validateNotSameTeam(Position source, Position target) {
        if (getPieceAt(source).isSameTeamWith(getPieceAt(target))) {
            throw new IllegalArgumentException("목표 위치에 같은 색 말이 있습니다");
        }
    }

    private void validateMove(Position source, Position target) {
        if (!hasMove(source, target)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 없는 수입니다");
        }
    }

    private boolean hasMove(Position source, Position target) {
        Move move = Move.of(source, target);
        if (hasPieceAt(target)) {
            return getPieceAt(source).hasAttackMove(move);
        }
        return getPieceAt(source).hasMove(move);
    }

    private void validateNoObstacle(Position source, Position target) {
        Move unitMove = Move.of(source, target).getUnitMove();
        Position current = unitMove.move(source);
        while (!current.equals(target)) {
            validateNoPieceAt(current);
            current = unitMove.move(current);
        }
    }

    private void validateNoPieceAt(Position position) {
        if (hasPieceAt(position)) {
            throw new IllegalArgumentException("다른 기물을 지나칠 수 없습니다");
        }
    }

    private void makeMove(Position source, Position target) {
        pieces.put(target, getPieceAt(source).touch());
        pieces.remove(source);
    }

    public boolean hasPositionTeamOf(Position position, Team team) {
        return getPieceAt(position).hasTeam(team);
    }

    private boolean hasPieceAt(Position target) {
        return !getPieceAt(target).isEmpty();
    }

    private Piece getPieceAt(Position position) {
        return pieces.getOrDefault(position, EMPTY_PIECE);
    }

    public Map<Position, Piece> getPieces() {
        return new HashMap<>(pieces);
    }
}
