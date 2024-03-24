package domain.board;

import domain.piece.Piece;
import domain.piece.PieceColor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> piecePositions;

    public Board(final Map<Position, Piece> piecePositions) {
        this.piecePositions = piecePositions;
    }

    public void movePiece(final PieceColor pieceColor, final Position source, final Position destination) {
        validatePosition(pieceColor, source, destination);

        Piece targetPiece = piecePositions.get(source);
        targetPiece.checkMovable(source, destination, parsePiecePositionsIgnoreTargetPiece(source));

        piecePositions.put(destination, targetPiece);
        piecePositions.remove(source);
    }

    private void validatePosition(final PieceColor pieceColor, final Position source, final Position destination) {
        if (source.equals(destination)) {
            throw new IllegalArgumentException("출발지와 목적지가 같을 수 없습니다.");
        }

        if (!piecePositions.containsKey(source)) {
            throw new IllegalArgumentException("출발지에 기물이 존재하지 않습니다.");
        }

        if (piecePositions.get(source).isEnemy(pieceColor)) {
            throw new IllegalArgumentException("상대방의 기물을 이동시킬 수 없습니다.");
        }
    }

    private Map<Position, Piece> parsePiecePositionsIgnoreTargetPiece(final Position targetPiecePosition) {
        Map<Position, Piece> piecePositionsIgnoreTargetPiece = new HashMap<>(piecePositions);
        piecePositionsIgnoreTargetPiece.remove(targetPiecePosition);

        return piecePositionsIgnoreTargetPiece;
    }

    public Map<Position, Piece> piecePositions() {
        return Collections.unmodifiableMap(piecePositions);
    }
}
