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
        Piece piece = findPieceToMove(position);
        return piece.isRightTurn(turn);
    }

    public void move(Position source, Position target) {
        Piece sourcePiece = findPieceToMove(source);
        Piece targetPiece = pieces.get(target);

        checkRightTarget(sourcePiece, targetPiece);
        checkPieceReachable(source, target);
        checkNotCrossOtherPiece(source, target);

        movePiece(source, target);
    }

    private Piece findPieceToMove(Position position) {
        Piece piece = pieces.get(position);
        if (piece == null) {
            throw new IllegalArgumentException("움직일 기물이 없습니다");
        }
        return piece;
    }

    private void checkPieceReachable(Position source, Position target) {
        Piece sourcePiece = pieces.get(source);
        Piece targetPiece = pieces.get(target);
        Move move = new Move(source, target);
        if (!sourcePiece.isValidMove(move, targetPiece)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 없는 수입니다");
        }
    }

    private void checkRightTarget(Piece sourcePiece, Piece targetPiece) {
        if (!sourcePiece.isRightTarget(targetPiece)) {
            throw new IllegalArgumentException("목표 위치에 같은 색 말이 있습니다");
        }
    }

    private void checkNotCrossOtherPiece(Position source, Position target) {
        Move move = new Move(source, target);
        Set<Position> route = move.findRoute();
        for (Position position : route) {
            checkEmpty(position);
        }
    }

    private void checkEmpty(Position position) {
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
        pieces.put(target, sourcePiece.touch());
    }

    public Map<Position, Piece> getPieces() {
        return new HashMap<>(pieces);
    }
}
