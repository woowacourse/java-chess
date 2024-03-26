package chess.domain.chessGame;

import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.position.File;
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
        validateIsMovableOrCatchable(targetSpace);
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
            validateRouteHasPieceInSpaces(route, spaces);
        }
    }

    private void validateRouteHasPieceInSpaces(Position route, List<Space> spaces) {
        for (Space space : spaces) {
            validateRouteHasPieceInSpace(route, space);
        }
    }

    private void validateRouteHasPieceInSpace(Position route, Space space) {
        if (space.position.equals(route) && space.hasAnyPiece()) {
            throw new IllegalArgumentException("루트에 피스가 있습니다.");
        }
    }

    private void validateIsMovableOrCatchable(Space targetSpace) {
        if (piece.isMovable(position, targetSpace.position) || piece.isCatchable(position, targetSpace.position)) {
            return;
        }
        throw new IllegalArgumentException("이동 규칙을 위반한 움직임입니다.");
    }

    private void validateSameColorOnSpace(Space targetSpace) {
        if (piece.isSameColor(targetSpace.piece)) {
            throw new IllegalArgumentException("해당 위치에 피스가 이미 있습니다.");
        }
    }

    public boolean isSamePosition(Position otherPosition) {
        return position.equals(otherPosition);
    }

    public boolean isSameFile(File file) {
        return position.isSameFile(file);
    }

    private boolean hasAnyPiece() {
        return !piece.isSameColor(new EmptyPiece());
    }

    public boolean hasKing() {
        return piece.isKing();
    }

    public boolean doesNotHavePiece() {
        return piece.isSameColor(new EmptyPiece());
    }

    public boolean hasColor(Color color) {
        return piece.isSameColor(color);
    }

    public boolean isValidTurn(Turn turn) {
        return turn.isValidTurn(piece);
    }

    public double calculateScore() {
        return PieceScore.findScore(piece);
    }

    public Score score() {
        return new Score(piece);
    }
}
