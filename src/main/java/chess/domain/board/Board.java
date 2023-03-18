package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Move;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Move move = new Move(source, target);

        MoveType moveType = checkType(sourcePiece, target);
        checkPieceReachable(sourcePiece, moveType, move);
        checkNotCrossOtherPiece(source, move);

        movePiece(source, target, sourcePiece);
    }

    private Piece findPieceToMove(Position position) {
        Piece piece = pieces.get(position);
        if (piece == null) {
            throw new IllegalArgumentException("움직일 기물이 없습니다");
        }
        return piece;
    }

    private MoveType checkType(Piece sourcePiece, Position target) {
        Piece targetPiece = pieces.get(target);
        if (isEmpty(target)) {
            return MoveType.MOVE;
        }
        if (!sourcePiece.isRightTarget(targetPiece)) {
            throw new IllegalArgumentException("목표 위치에 같은 색 말이 있습니다");
        }
        return MoveType.ATTACK;
    }

    private void checkPieceReachable(Piece sourcePiece, MoveType moveType, Move move) {
        if (!sourcePiece.isValidMove(move, moveType)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 없는 수입니다");
        }
    }

    private void checkNotCrossOtherPiece(Position position, Move move) {
        List<Move> moves = move.findRoute();
        for (Move route : moves) {
            checkEmpty(position.move(route));
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

    private void movePiece(Position source, Position target, Piece sourcePiece) {
        pieces.remove(source);
        pieces.put(target, sourcePiece.touch());
    }

    public Map<Position, Piece> getPieces() {
        return new HashMap<>(pieces);
    }
}
