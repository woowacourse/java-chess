package domain;

import domain.chessboard.ChessBoard;
import domain.chessboard.EmptyType;
import domain.chessboard.Square;
import domain.coordinate.Position;
import domain.coordinate.Route;
import domain.piece.Color;
import domain.piece.PieceType;

public class ChessGame {

    private final ChessBoard chessBoard;
    private Color colorTurn;

    public ChessGame(final ChessBoard chessBoard) {
        colorTurn = Color.WHITE;
        this.chessBoard = chessBoard;
    }

    public void move(final Position source, final Position target) {
        final Square startPoint = chessBoard.findSquare(source);
        final Square endPoint = chessBoard.findSquare(target);
        validateTurn(startPoint);

        checkCondition(source, target, startPoint, endPoint);

        movePiece(startPoint, endPoint);

        colorTurn = colorTurn.reverse();
    }

    private void validateTurn(final Square square) {
        final Color reverse = colorTurn.reverse();

        if (square.isSameColor(reverse)) {
            throw new IllegalStateException(String.format("%s의 턴이 아닙니다.", reverse));
        }
    }

    private void checkCondition(final Position source, final Position target, final Square startPoint, final Square endPoint) {
        checkRoute(source, target, startPoint);
        checkPawn(source, target, startPoint, endPoint);
        checkTarget(endPoint);
    }

    private void checkRoute(final Position source, final Position target, final Square startPoint) {
        Route route = startPoint.findRoute(source, target);

        final boolean isCorrectRoute = !route.getRoute().stream()
                .map(chessBoard::findSquare)
                .map(Square::getType)
                .allMatch(type -> EmptyType.EMPTY == type);

        validateRoute(isCorrectRoute);
    }

    private void validateRoute(final boolean isCorrectRoute) {
        if (isCorrectRoute) {
            throw new IllegalStateException("장애물이 있어 이동할 수 없습니다.");
        }
    }

    private void checkPawn(final Position source, final Position target, final Square startPoint, final Square endPoint) {
        if (startPoint.getType().equals(PieceType.PAWN)) {
            validatePawnDestination(source, target, endPoint);
        }
    }

    private void validatePawnDestination(final Position source, final Position target, final Square endPoint) {
        if (isDiagonallyMovable(source, target, endPoint) || isStraightMovable(source, target, endPoint)) { // 대각인 경우, 상대방의 기물만 존재하면 됨
            return;
        }

        throw new IllegalStateException("잘못된 도착 지점입니다.");
    }

    private boolean isStraightMovable(final Position source, final Position target, final Square endPoint) {
        return endPoint.isSameType(EmptyType.EMPTY) && source.isStraight(target);
    }

    private boolean isDiagonallyMovable(final Position source, final Position target, final Square endPoint) {
        return endPoint.isSameType(EmptyType.EMPTY)|| (endPoint.isSameColor(colorTurn.reverse()) && source.isDiagonally(target));
    }

    private void checkTarget(final Square endPoint) {
        if ((endPoint.isNotSameType(EmptyType.EMPTY) && endPoint.isSameColor(colorTurn))) { // 우리팀 기물이 목표지점에 있을 떄
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
