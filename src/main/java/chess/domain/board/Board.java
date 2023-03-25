package chess.domain.board;

import chess.domain.game.GameResult;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import chess.domain.position.Move;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Board {

    private static final long INITIAL_KING_COUNT = 2;

    private final Map<Position, Piece> pieces;

    Board(Map<Position, Piece> pieces) {
        this.pieces = new HashMap<>(pieces);
    }

    public boolean checkTurn(Position position, Color turn) {
        Piece piece = pieces.get(position);
        validatePieceToMove(piece);
        return piece.isRightTurn(turn);
    }

    public void move(Position source, Position target) {
        Piece sourcePiece = pieces.get(source);
        validatePieceToMove(sourcePiece);
        Piece targetPiece = pieces.get(target);
        validateRightTarget(sourcePiece, targetPiece);

        Move move = new Move(source, target);
        validateMove(sourcePiece, targetPiece, move);

        pieces.remove(source);
        pieces.put(target, sourcePiece);
    }

    private void validatePieceToMove(Piece piece) {
        if (piece == null) {
            throw new IllegalArgumentException("움직일 기물이 없습니다");
        }
    }

    private void validateRightTarget(Piece sourcePiece, Piece targetPiece) {
        if (sourcePiece.isSameColor(targetPiece)) {
            throw new IllegalArgumentException("목표 위치에 같은 색 말이 있습니다");
        }
    }

    private void validateMove(Piece sourcePiece, Piece targetPiece, Move move) {
        validatePieceMove(sourcePiece, targetPiece, move);
        validateNotCrossOtherPiece(move);
    }

    private void validatePieceMove(Piece sourcePiece, Piece targetPiece, Move move) {
        if (!sourcePiece.canMove(move, targetPiece)) {
            throw new IllegalArgumentException("해당 기물이 이동할 수 없는 수입니다");
        }
    }

    private void validateNotCrossOtherPiece(Move move) {
        Set<Position> route = move.findRoute();
        for (Position position : route) {
            validateEmpty(position);
        }
    }

    private void validateEmpty(Position position) {
        if (pieces.get(position) != null) {
            throw new IllegalArgumentException("다른 기물을 지나칠 수 없습니다");
        }
    }

    public boolean isGameOver() {
        long kingCount = pieces.values().stream()
                .filter(piece -> piece.isSameType(PieceType.KING))
                .count();
        return kingCount != INITIAL_KING_COUNT;
    }

    public GameResult getResult() {
        return new GameResult(pieces);
    }

    public Map<Position, Piece> getPieces() {
        return new HashMap<>(pieces);
    }
}
