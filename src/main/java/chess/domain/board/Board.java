package chess.domain.board;

import chess.domain.pieces.Empty;
import chess.domain.pieces.Piece;
import chess.domain.pieces.Team;
import chess.domain.strategy.Route;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> board;

    public Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public Map<Position, Piece> getBoard() {
        return board;
    }

    public Piece findPiece(final Position position) {
        return board.get(position);
    }

    public void switchPosition(final Position source, final Position destination) {
        validateMove(source, destination);
        board.replace(destination, findPiece(source));
        board.replace(source, new Empty(Team.EMPTY));
    }

    private void validateMove(final Position source, final Position destination) {
        Piece piece = findPiece(source);
        validateMoveSamePosition(source, destination);
        piece.canMove(source, destination, isAttackMove(source, destination));
        validateObstacle(piece, source, destination);
        validateMoveMyTeam(source, destination);
    }

    private boolean isAttackMove(final Position source, final Position destination) {
        Piece sourcePiece = findPiece(source);
        Piece destinationPiece = findPiece(destination);
        return (sourcePiece.isWhiteTeam() && destinationPiece.isBlackTeam())
            || (sourcePiece.isBlackTeam() && destinationPiece.isWhiteTeam());
    }

    private void validateMoveSamePosition(final Position source, final Position destination) {
        if (source.equals(destination)) {
            throw new IllegalArgumentException("같은 위치로 움직일 수 없습니다.");
        }
    }

    private void validateObstacle(final Piece piece, final Position source, final Position destination) {
        Route route = piece.generateRoute(source, destination);

        boolean isObstacleExist = route.getRoute().stream()
            .map(this::findPiece)
            .anyMatch(p -> !(p instanceof Empty));

        if (isObstacleExist) {
            throw new IllegalArgumentException("장애물이 존재합니다.");
        }
    }

    private void validateMoveMyTeam(final Position start, final Position end) {
        Piece selectedPiece = findPiece(start);
        Piece destinationPiece = findPiece(end);
        if (isSameTeam(selectedPiece, destinationPiece) && !destinationPiece.isEmpty()) {
            throw new IllegalArgumentException("우리팀 말에게 이동할 수 없습니다.");
        }
    }

    private boolean isSameTeam(final Piece selectedPiece, final Piece destinationPiece) {
        return selectedPiece.isWhiteTeam() == destinationPiece.isWhiteTeam()
            || selectedPiece.isBlackTeam() == destinationPiece.isBlackTeam();
    }
}
