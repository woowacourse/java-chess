package chess.domain;

import chess.domain.chesspiece.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ChessBoard {
    private static final int FIRST_BLANK_ROW = 2;
    private static final int LAST_BLANK_ROW = 5;
    private static final int FIRST_COLUMN = 0;
    private static final int LAST_COLUMN = 7;
    private static final int BLACK_TEAM_AREA = 0;
    private static final int BLACK_TEAM_PAWNS_AREA = 1;
    private static final int WHITE_TEAM_AREA = 7;
    private static final int WHITE_TEAM_PAWNS_AREA = 6;
    private static final double INLINE_PAWN_SCORE = 0.5;

    private Map<Position, ChessPiece> chessBoard = new HashMap<>();
    private boolean gameOver = false;

    public ChessBoard() {
        init();
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
        double score = 0.0;

        for (int i = FIRST_COLUMN; i <= LAST_COLUMN; i++) {
            score += calculateColumn(team, i);
        }

        return score;
    }

    private double calculateColumn(Team team, int column) {
        long inLinePawnCount = getInLinePawnCount(team, column);

        double scoreSum = IntStream.rangeClosed(FIRST_COLUMN, LAST_COLUMN)
                .mapToObj(i -> chessBoard.get(Position.of(i, column)))
                .filter(chessPiece -> chessPiece.isSameTeam(team))
                .mapToDouble(ChessPiece::getScore)
                .reduce(Double::sum).getAsDouble();

        return inLinePawnCount > 1 ? scoreSum - inLinePawnCount * INLINE_PAWN_SCORE : scoreSum;
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

    private boolean canMove(Position source, Position target) {
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

        return route.stream()
                .limit(route.size() - 1)
                .allMatch(position -> isSameChessPiece(chessBoard.get(position), Blank.class)
                        && !chessBoard.get(target).isSameTeam(sourceChessPiece));
    }

    private Boolean canMovePawns(Position source, Position target, List<Position> route) {
        if (!source.isSameColumn(target)
                && !chessBoard.get(source).isSameTeam(chessBoard.get(target))) {
            return true;
        }

        return route.stream()
                .allMatch(position -> isSameChessPiece(chessBoard.get(position), Blank.class));
    }

    private boolean validExist(Position source) {
        return !chessBoard.containsKey(source) || isSameChessPiece(chessBoard.get(source), Blank.class);
    }

    private void init() {
        setChessPieces(BLACK_TEAM_AREA, Team.BLACK);
        setPawns(BLACK_TEAM_PAWNS_AREA, Team.BLACK);
        setBlanks();
        setPawns(WHITE_TEAM_PAWNS_AREA, Team.WHITE);
        setChessPieces(WHITE_TEAM_AREA, Team.WHITE);
    }

    private void setChessPieces(int y, Team team) {
        chessBoard.put(Position.of(y, 0), new Rook(team));
        chessBoard.put(Position.of(y, 1), new Knight(team));
        chessBoard.put(Position.of(y, 2), new Bishop(team));
        chessBoard.put(Position.of(y, 3), new King(team));
        chessBoard.put(Position.of(y, 4), new Queen(team));
        chessBoard.put(Position.of(y, 5), new Bishop(team));
        chessBoard.put(Position.of(y, 6), new Knight(team));
        chessBoard.put(Position.of(y, 7), new Rook(team));
    }

    private void setPawns(int y, Team team) {
        for (int i = FIRST_COLUMN; i <= LAST_COLUMN; i++) {
            chessBoard.put(Position.of(y, i), new Pawn(team));
        }
    }

    private void setBlanks() {
        for (int i = FIRST_BLANK_ROW; i <= LAST_BLANK_ROW; i++) {
            putBlankRow(i);
        }
    }

    private void putBlankRow(int i) {
        for (int j = FIRST_COLUMN; j <= LAST_COLUMN; j++) {
            chessBoard.put(Position.of(i, j), new Blank(Team.BLANK));
        }
    }

    private boolean isSameChessPiece(ChessPiece chessPiece, Type type) {
        return chessPiece.getClass().getTypeName().equals(type.getTypeName());
    }

    public boolean isNextTeam(Position source, Team team) {
        return chessBoard.get(source).isSameTeam(team);
    }
}
