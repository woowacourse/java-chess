package domain;

import domain.chessboard.ChessBoard;
import domain.chessboard.ColorScore;
import domain.chessboard.EmptyType;
import domain.chessboard.GameResult;
import domain.chessboard.Result;
import domain.chessboard.Square;
import domain.chessboard.StatusResult;
import domain.coordinate.MovePosition;
import domain.coordinate.Position;
import domain.coordinate.Route;
import domain.piece.Color;
import domain.piece.PieceType;

import javax.print.DocFlavor;
import java.lang.reflect.GenericArrayType;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChessGame {

    private final ChessBoard chessBoard;
    private Color colorTurn;

    public ChessGame(final ChessBoard chessBoard) {
        colorTurn = Color.WHITE;
        this.chessBoard = chessBoard;
    }

    public void move(final MovePosition movePosition) {
        final Square startPoint = chessBoard.findSquare(movePosition.getSource());
        final Square endPoint = chessBoard.findSquare(movePosition.getTarget());
        validateTurn(startPoint);

        checkCondition(movePosition, startPoint, endPoint);

        movePiece(startPoint, endPoint);

        colorTurn = colorTurn.reverse();
    }

    private void validateTurn(final Square square) {
        final Color reverse = colorTurn.reverse();

        if (square.isSameColor(reverse)) {
            throw new IllegalStateException(String.format("%s의 턴이 아닙니다.", reverse));
        }
    }

    private void checkCondition(final MovePosition movePosition, final Square startPoint, final Square endPoint) {
        checkRoute(movePosition, startPoint);
        checkPawn(movePosition, startPoint, endPoint);
        checkTarget(endPoint);
    }

    private void checkRoute(final MovePosition movePosition, final Square startPoint) {
        Route route = startPoint.findRoute(movePosition);

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

    private void checkPawn(final MovePosition movePosition, final Square startPoint, final Square endPoint) {
        if (startPoint.getType().equals(PieceType.PAWN)) {
            validatePawnDestination(movePosition, endPoint);
        }
    }

    private void validatePawnDestination(final MovePosition movePosition, final Square endPoint) {
        if (isDiagonallyMovable(movePosition, endPoint) || isStraightMovable(movePosition, endPoint)) { // 대각인 경우, 상대방의 기물만 존재하면 됨
            return;
        }

        throw new IllegalStateException("잘못된 도착 지점입니다.");
    }

    private boolean isStraightMovable(final MovePosition movePosition, final Square endPoint) {

        return endPoint.isSameType(EmptyType.EMPTY) && movePosition.isStraight();
    }

    private boolean isDiagonallyMovable(final MovePosition movePosition, final Square endPoint) {
        return endPoint.isSameType(EmptyType.EMPTY)|| (endPoint.isSameColor(colorTurn.reverse()) && movePosition.isDiagonally());
    }

    private void checkTarget(final Square endPoint) {
        if ((endPoint.isNotSameType(EmptyType.EMPTY) && endPoint.isSameColor(colorTurn))) {
            throw new IllegalStateException("잘못된 도착 지점입니다.");
        }
    }

    private static void movePiece(final Square startPoint, final Square endPoint) {
        endPoint.bePiece(startPoint);
        startPoint.beEmpty();
    }

    public StatusResult getStatusResult() {
        ColorScore blackScore = new ColorScore(Color.BLACK, chessBoard.calculateColorScore(Color.BLACK));
        ColorScore whiteScore = new ColorScore(Color.WHITE, chessBoard.calculateColorScore(Color.WHITE));
        return StatusResult.of(blackScore, whiteScore);
    }

    public Result getCheckMateResult() {
        boolean isExistKingOfBlack = chessBoard.isExistKingThisColor(Color.BLACK);
        boolean isExistKingOfWhite = chessBoard.isExistKingThisColor(Color.WHITE);
        return Result.createByCheckMate(isExistKingOfBlack, isExistKingOfWhite);
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

}
