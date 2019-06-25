package chess.domain;

import chess.domain.chesspiece.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {
    private static final int FIRST_BLANK_ROW = 2;
    private static final int LAST_BLANK_ROW = 5;
    private static final int FIRST_COLUMN = 0;
    private static final int LAST_COLUMN = 7;
    private static final int BLACK_TEAM_AREA = 0;
    private static final int BLACK_TEAM_PAWNS_AREA = 1;
    private static final int WHITE_TEAM_AREA = 7;
    private static final int WHITE_TEAM_PAWNS_AREA = 6;

    private Map<Position, ChessPiece> chessBoard = new HashMap<>();

    public ChessBoard() {
        init();
    }

    public boolean canMove(Position source, Position target) {
        ChessPiece sourceChessPiece = chessBoard.get(source);
        List<Position> route = sourceChessPiece.getRouteOfPiece(source, target);

        if (validExist(source)) {
            throw new IllegalArgumentException();
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
                .allMatch(position -> isSameChessPiece(chessBoard.get(source), Blank.class));
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
        for (int i = FIRST_BLANK_ROW; i < LAST_BLANK_ROW; i++) {
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
}
