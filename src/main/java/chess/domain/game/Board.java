package chess.domain.game;

import chess.domain.location.Position;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private final Map<Position, Piece> pieceByPosition;
    private boolean isKingDead = false;

    public Board(Map<Position, Piece> maps) {
        this.pieceByPosition = new HashMap<>(maps);
    }

    public void move(Color color, Position from, Position to) {
        validateFromPiece(color, from);
        validateToPiece(color, to);
        validateCanMove(from, to);
        updateIsKingDead(to);
        movePiece(from, to);
    }

    private void validateFromPiece(Color color, Position from) {
        if (!pieceByPosition.get(from)
                            .isSame(color)) {
            throw new IllegalArgumentException("같은 색깔의 말을 골라야 합니다.");
        }
    }

    private void validateToPiece(Color color, Position to) {
        if (pieceByPosition.get(to)
                           .isSame(color)) {
            throw new IllegalArgumentException("같은 색깔의 말을 잡을 수 없습니다.");
        }
    }

    private void validateCanMove(Position from, Position to) {
        if (!movablePositions(from).contains(to)) {
            throw new IllegalArgumentException("이동할 수 없는 위치로의 이동입니다.");
        }
    }

    private List<Position> movablePositions(Position from) {
        return pieceByPosition.get(from)
                              .movablePositions(from, allPieces());
    }

    private void updateIsKingDead(Position to) {
        if (pieceByPosition.get(to)
                           .isKing()) {
            isKingDead = true;
        }
    }

    private void movePiece(Position from, Position to) {
        pieceByPosition.put(to, pieceByPosition.get(from));
        pieceByPosition.put(from, new Empty());
    }

    public boolean isKingDead() {
        return isKingDead;
    }

    public Map<Position, Piece> allPieces() {
        return Collections.unmodifiableMap(pieceByPosition);
    }
}