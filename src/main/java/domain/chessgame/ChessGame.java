package domain.chessgame;

import domain.chessboard.ChessBoard;
import domain.piece.Color;
import domain.position.Position;
import domain.position.Route;
import domain.squarestatus.SquareStatus;
import domain.type.EmptyType;
import domain.type.PieceType;

import java.util.List;
import java.util.function.Predicate;

public final class ChessGame {

    private final ChessBoard chessBoard;
    private Turn turn;

    public ChessGame(final ChessBoard chessBoard) {
        turn = new Turn(Color.WHITE);
        this.chessBoard = chessBoard;
    }

    public void move(final Position source, final Position target) {
        validateTurn(source);
        checkCondition(source, target);
        movePiece(source, target);

        turn = turn.nextTurn();
    }

    public boolean isKingAlive() {
        return chessBoard.isKingAlive();
    }

    public double calculateScore(final Color color) {
        final List<Long> columnPawnCount = chessBoard.findColumnPawnCounts(color);
        final List<SquareStatus> pieces = chessBoard.findPieces(color);
        return ScoreCalculator.calculateScore(pieces, columnPawnCount);
    }

    private void validateTurn(final Position source) {
        final Color turnColor = turn.getColor();

        if (chessBoard.isDifferentColor(source, turnColor)) {
            throw new IllegalStateException(String.format("지금은 %s의 턴입니다.", turnColor));
        }
    }

    private void checkCondition(final Position source, final Position target) {
        checkRoute(source, target);
        checkPawn(source, target);
        checkTarget(target);
    }

    private void checkRoute(final Position source, final Position target) {
        Route route = chessBoard.findRoute(source, target);
        validateRoute(hasHurdle(route));
    }

    private boolean hasHurdle(final Route route) {
        return route.getRoute().stream()
                .map(chessBoard::findPosition)
                .map(SquareStatus::getType)
                .anyMatch(Predicate.not(EmptyType::isEmpty));
    }

    private void validateRoute(final boolean hasHurdle) {
        if (hasHurdle) {
            throw new IllegalStateException("장애물이 있어 이동할 수 없습니다.");
        }
    }

    private void checkPawn(final Position source, final Position target) {
        if (chessBoard.isSameType(source, PieceType.PAWN)) {
            validatePawnDestination(source, target);
        }
    }

    private void validatePawnDestination(final Position source, final Position target) {
        if (isDiagonallyMovable(source, target) || isStraightMovable(source, target)) {
            return;
        }

        throw new IllegalStateException("잘못된 도착 지점입니다.");
    }

    private boolean isStraightMovable(final Position source, final Position target) {
        return chessBoard.isSameType(target, EmptyType.EMPTY) && source.isStraight(target);
    }

    private boolean isDiagonallyMovable(final Position source, final Position target) {
        return chessBoard.isSameType(target, EmptyType.EMPTY) || (chessBoard.isDifferentColor(target, turn.getColor()) && source.isDiagonally(target));
    }

    private void checkTarget(final Position target) {
        if ((chessBoard.isDifferentType(target, EmptyType.EMPTY) && chessBoard.isSameColor(target, turn.getColor()))) {
            throw new IllegalStateException("잘못된 도착 지점입니다.");
        }
    }

    private void movePiece(final Position source, final Position target) {
        chessBoard.move(source, target);
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

}
