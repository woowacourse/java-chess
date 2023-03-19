package domain.chessgame;

import domain.chessboard.ChessBoard;
import domain.chessboard.Square;
import domain.coordinate.Position;
import domain.coordinate.Route;
import domain.piece.Color;
import domain.type.EmptyType;
import domain.type.PieceType;

import java.util.function.Predicate;

public final class ChessGame {

    private final ChessBoard chessBoard;
    private final Turn turn;

    public ChessGame(final ChessBoard chessBoard) {
        turn = new Turn(Color.WHITE);
        this.chessBoard = chessBoard;
    }

    public void move(final Position source, final Position target) {
        final Square startPoint = chessBoard.findSquare(source);
        final Square endPoint = chessBoard.findSquare(target);

        validateTurn(startPoint);
        checkCondition(source, target, startPoint, endPoint);
        movePiece(startPoint, endPoint);

        turn.nextTurn();
    }

    private void validateTurn(final Square square) {
        final Color turnColor = turn.getColor();

        if (square.isDifferentColor(turnColor)) {
            throw new IllegalStateException(String.format("지금은 %s의 턴입니다.", turnColor));
        }
    }

    private void checkCondition(final Position source, final Position target, final Square startPoint, final Square endPoint) {
        checkRoute(source, target, startPoint);
        checkPawn(source, target, startPoint, endPoint);
        checkTarget(endPoint);
    }

    private void checkRoute(final Position source, final Position target, final Square startPoint) {
        Route route = startPoint.findRoute(source, target);

        final boolean hasHurdle = route.getRoute().stream()
                .map(chessBoard::findSquare)
                .map(Square::getType)
                .anyMatch(Predicate.not(EmptyType::isEmpty));

        validateRoute(hasHurdle);
    }

    private void validateRoute(final boolean hasHurdle) {
        if (hasHurdle) {
            throw new IllegalStateException("장애물이 있어 이동할 수 없습니다.");
        }
    }

    private void checkPawn(final Position source, final Position target, final Square startPoint, final Square endPoint) {
        if (startPoint.isEqualType(PieceType.PAWN)) {
            validatePawnDestination(source, target, endPoint);
        }
    }

    private void validatePawnDestination(final Position source, final Position target, final Square endPoint) {
        if (isDiagonallyMovable(source, target, endPoint) || isStraightMovable(source, target, endPoint)) {
            return;
        }

        throw new IllegalStateException("잘못된 도착 지점입니다.");
    }

    private boolean isStraightMovable(final Position source, final Position target, final Square endPoint) {
        return endPoint.isEqualType(EmptyType.EMPTY) && source.isStraight(target);
    }

    private boolean isDiagonallyMovable(final Position source, final Position target, final Square endPoint) {
        return endPoint.isEqualType(EmptyType.EMPTY) || (endPoint.isDifferentColor(turn.getColor()) && source.isDiagonally(target));
    }

    private void checkTarget(final Square endPoint) {
        if ((endPoint.isDifferentType(EmptyType.EMPTY) && endPoint.isSameColor(turn.getColor()))) {
            throw new IllegalStateException("잘못된 도착 지점입니다.");
        }
    }

    private static void movePiece(final Square startPoint, final Square endPoint) {
        endPoint.bePiece(startPoint);
        startPoint.beEmpty();
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

}
