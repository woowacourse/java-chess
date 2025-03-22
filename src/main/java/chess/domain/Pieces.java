package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import java.util.List;
import java.util.Set;

public class Pieces {

    private final Set<Piece> pieces;

    public Pieces(final Set<Piece> pieces) {
        this.pieces = pieces;
    }

    public Set<Piece> getPieces() {
        return pieces;
    }

    public void validateMove(Piece piece, Position endPosition) {
        if (piece.getClass() == Knight.class) {
            return;
        }
        List<Position> route = piece.canMove(endPosition);
        route.removeLast();
        for (Position position : route) {
            if (findPieceByPosition(position).samePosition(endPosition)) {
                throw new IllegalArgumentException("경로에 기물이 존재합니다.");
            }
        }
    }

    public Piece findPieceByPosition(Position position) {
        for (Piece piece : pieces) {
            if (piece.samePosition(position)) {
                return piece;
            }
        }
        throw new IllegalArgumentException("기물이 해당 위치에 존재하지 않습니다.");
    }

    public Piece findPieceByPositionAndColor(Position position, Color color) {
        for (Piece piece : pieces) {
            if (piece.samePosition(position) && piece.isSameTeam(color)) {
                return piece;
            }
        }
        throw new IllegalArgumentException("기물이 해당 위치에 존재하지 않습니다.");
    }

    public boolean hasPieceInPosition(final Position position) {
        for (Piece piece : pieces) {
            if (piece.samePosition(position)) {
                return true;
            }
        }
        return false;
    }

    public void move(final Piece piece, final Position wantMovePosition) {
        if (hasPieceInPosition(wantMovePosition)) {
            Piece findPiece = findPieceByPosition(wantMovePosition);
            if (piece.isOtherTeam(findPiece)) {
                pieces.remove(findPiece);
            } else {
                throw new IllegalArgumentException("같은 팀의 말은 잡지 못합니다.");
            }
        }
        piece.move(wantMovePosition);
    }
}
