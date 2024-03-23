package chess.domain.chessBoard;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.view.PieceSign;
import java.util.List;

public class Space {

    private Piece piece;
    private final Position position;

    public Space(Piece piece, Position position) {
        this.piece = piece;
        this.position = position;
    }

    public String pieceCharacter() {
        return PieceSign.findSign(piece);
    }

    public void movePiece(Space targetSpace, List<Space> spaces) {
        validateIsMovableSpace(targetSpace);
        validateClearRoute(targetSpace, spaces);
        validateSameColorOnSpace(targetSpace);
        if (piece.isCatchable(position, targetSpace.position) || targetSpace.doesNotHavePiece()) {
            targetSpace.piece = piece;
            piece = new EmptyPiece();
            return;
        }
        throw new IllegalArgumentException("해당 위치의 상대 말을 잡을 수 없습니다.");
    }

    private void validateClearRoute(Space targetSpace, List<Space> spaces) {
        List<Position> routes = targetSpace.position.findRoute(position);
        for (Position route : routes) {
            for (Space space : spaces) {
                validateRouteHasPiece(route, space);
            }
        }
    }

    private void validateRouteHasPiece(Position route, Space space) {
        if (space.position.equals(route) && space.hasPiece()) {
            throw new IllegalArgumentException("루트에 피스가 있습니다.");
        }
    }

    private void validateIsMovableSpace(Space targetSpace) {
        if (!piece.isMovable(position, targetSpace.position)) {
            throw new IllegalArgumentException("이동 규칙을 위반한 움직임입니다.");
        }
    }

    private void validateSameColorOnSpace(Space targetSpace) {
        if (piece.isSameColor(targetSpace.piece)) {
            throw new IllegalArgumentException("해당 위치에 피스가 이미 있습니다.");
        }
    }

    public boolean isSamePosition(Position otherPosition) {
        return position.equals(otherPosition);
    }

    private boolean hasPiece() {
        return !piece.isSameColor(new EmptyPiece());
    }

    public boolean doesNotHavePiece() {
        return piece.isSameColor(new EmptyPiece());
    }

    public boolean isValidTurn(Turn turn) {
        return turn.isValidTurn(piece);
    }
}
