package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Move;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Board {

    private final Map<Position, Piece> pieces;

    Board(Map<Position, Piece> pieces) {
        this.pieces = new HashMap<>(pieces);
    }

    public boolean checkTurn(Position position, Color turn) {
        validatePieceToMove(position);
        return pieces.get(position).isRightTurn(turn);
    }

    public void move(Position source, Position target) {
        validateMove(source, target);
        movePiece(source, target);
    }

    private void validateMove(Position source, Position target) {
        validatePieceToMove(source);
        validateRightTarget(source, target);
        validatePieceReachable(source, target);
        validateNotCrossOtherPiece(source, target);
    }

    private void validatePieceToMove(Position position) {
        Piece piece = pieces.get(position);
        if (piece == null) {
            throw new IllegalArgumentException("움직일 기물이 없습니다");
        }
    }

    private void validateRightTarget(Position source, Position target) {
        Piece sourcePiece = pieces.get(source);
        Piece targetPiece = pieces.get(target);
        if (!sourcePiece.isRightTarget(targetPiece)) {
            throw new IllegalArgumentException("목표 위치에 같은 색 말이 있습니다");
        }
    }

    private void validatePieceReachable(Position source, Position target) {
        Piece sourcePiece = pieces.get(source);
        Piece targetPiece = pieces.get(target);
        Move move = new Move(source, target);
        if (!sourcePiece.isValidMove(move, targetPiece)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 없는 수입니다");
        }
    }

    private void validateNotCrossOtherPiece(Position source, Position target) {
        Move move = new Move(source, target);
        Set<Position> route = move.findRoute();
        for (Position position : route) {
            validateEmpty(position);
        }
    }

    private void validateEmpty(Position position) {
        if (!isEmpty(position)) {
            throw new IllegalArgumentException("다른 기물을 지나칠 수 없습니다");
        }
    }

    private boolean isEmpty(Position position) {
        return pieces.get(position) == null;
    }

    private void movePiece(Position source, Position target) {
        Piece sourcePiece = pieces.get(source);
        pieces.remove(source);
        pieces.put(target, sourcePiece);
    }

    public Map<Position, Piece> getPieces() {
        return new HashMap<>(pieces);
    }
}
