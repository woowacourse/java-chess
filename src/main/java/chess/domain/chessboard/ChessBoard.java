package chess.domain.chessboard;

import chess.domain.Position;
import chess.domain.chesspiece.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ChessBoard {
    private static final int FIRST_COLUMN = 0;
    private static final int LAST_COLUMN = 7;
    private static final int ONE_LINE = 1;
    private static final double INLINE_PAWN_SCORE = 0.5;
    private static final double ZERO = 0.0;

    private Map<Position, ChessPiece> chessBoard = new HashMap<>();
    private boolean gameOver = false;

    public ChessBoard(ChessBoardGenerator chessBoardGenerator) {
        chessBoard = chessBoardGenerator.getInitializedChessBoard();
    }

    public Map<Position, ChessPiece> getChessBoard() {
        return chessBoard;
    }

    public boolean movePiece(Position source, Position target) {
        if (!canMove(source, target)) return false;

        if (!chessBoard.get(target).isSameTeam(chessBoard.get(source))) {
            checkGameOver(target);
        }

        chessBoard.put(target, chessBoard.get(source));
        chessBoard.put(source, new Blank(Team.BLANK));

        return true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public double calculateScore(Team team) {
        double score = ZERO;


        for (int i = FIRST_COLUMN; i <= LAST_COLUMN; i++) {
            score += calculateColumn(team, i);
        }

        return score;
    }

    public boolean isNextTeam(Position source, Team team) {
        return chessBoard.get(source).isSameTeam(team);
    }

    boolean canMove(Position source, Position target) {
        ChessPiece sourceChessPiece = chessBoard.get(source);
        List<Position> route;
        try {
            route = sourceChessPiece.getRouteOfPiece(source, target);
        } catch (IllegalArgumentException e) {
            return false;
        }

        if (validExist(source)) {
            return false;
        }
        if (isSameChessPiece(sourceChessPiece, Pawn.class)) {
            return canMovePawns(source, target, route);
        }

        return (!chessBoard.get(target).isSameTeam(sourceChessPiece))
                && route.stream()
                .filter(position -> position != target)
                .allMatch(position -> isSameChessPiece(chessBoard.get(position), Blank.class));
    }

    private double calculateColumn(Team team, int column) {
        long inLinePawnCount = getInLinePawnCount(team, column);

        double scoreSum = IntStream.rangeClosed(FIRST_COLUMN, LAST_COLUMN)
                .mapToObj(i -> chessBoard.get(Position.of(i, column)))
                .filter(chessPiece -> chessPiece.isSameTeam(team))
                .mapToDouble(ChessPiece::getScore)
                .reduce(Double::sum).getAsDouble();

        return inLinePawnCount > ONE_LINE ? scoreSum - inLinePawnCount * INLINE_PAWN_SCORE : scoreSum;
    }

    private long getInLinePawnCount(Team team, int j) {
        return IntStream.rangeClosed(FIRST_COLUMN, LAST_COLUMN)
                .mapToObj(i -> chessBoard.get(Position.of(i, j)))
                .filter(chessPiece -> chessPiece.getClass() == Pawn.class && chessPiece.isSameTeam(team))
                .count();
    }

    private void checkGameOver(Position target) {
        if (chessBoard.get(target).getClass() == King.class) {
            gameOver = true;
        }
    }

    private Boolean canMovePawns(Position source, Position target, List<Position> route) {
        if (!source.isSameColumn(target)) {
            return !chessBoard.get(source).isSameTeam(chessBoard.get(target))
                    && !chessBoard.get(target).isSameTeam(Team.BLANK);
        }

        return route.stream()
                .allMatch(position -> isSameChessPiece(chessBoard.get(position), Blank.class));
    }

    private boolean validExist(Position source) {
        return !chessBoard.containsKey(source) || isSameChessPiece(chessBoard.get(source), Blank.class);
    }

    private boolean isSameChessPiece(ChessPiece chessPiece, Type type) {
        return chessPiece.getClass().getTypeName().equals(type.getTypeName());
    }
}
