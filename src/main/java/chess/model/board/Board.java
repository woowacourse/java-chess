package chess.model.board;

import chess.model.material.Color;
import chess.model.piece.None;
import chess.model.piece.Piece;
import chess.model.position.Position;
import chess.model.position.Route;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> pieces;
    private Color turn;

    public Board(Map<Position, Piece> pieces, Color turn) {
        this.pieces = pieces;
        this.turn = turn;
    }

    public void move(Position source, Position target) {
        validatePosition(source, target);
        validateMovement(source, target);
        applyMove(source, target);
    }

    private void applyMove(Position source, Position target) {
        pieces.put(target, findPiece(source));
        pieces.put(source, None.of());
        turn = turn.rotate();
    }

    private void validatePosition(Position source, Position target) {
        Piece sourcePiece = findPiece(source);
        if (sourcePiece.isNone()) {
            throw new IllegalArgumentException("source 위치에 기물이 존재하지 않습니다.");
        }
        if (sourcePiece.isDifferentColor(turn)) {
            throw new IllegalArgumentException("지금은 " + turn.name() + " 차례입니다.");
        }
        if (findPiece(target).isAllyWith(sourcePiece)) {
            throw new IllegalArgumentException("target 위치에 내 기물이 존재합니다.");
        }
    }

    private void validateMovement(Position source, Position target) {
        Piece sourcePiece = findPiece(source);
        Piece targetPiece = findPiece(target);
        if (sourcePiece.isEnemyWith(targetPiece) && sourcePiece.canAttack(source, target)) {
            return;
        }
        Route route = sourcePiece.findRoute(source, target);
        if (route.isBlocked(pieces)) {
            throw new IllegalArgumentException("경로 상에 다른 기물이 존재합니다.");
        }
    }

    public Piece findPiece(Position position) {
        return pieces.get(position);
    }
}
