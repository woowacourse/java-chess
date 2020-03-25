package chess.domain;

import chess.domain.chesspieces.*;
import chess.domain.moverules.Direction;
import chess.domain.position.Position;
import chess.domain.position.Positions;
import chess.domain.position.component.Column;
import chess.domain.position.component.Row;

import java.util.*;
import java.util.stream.Collectors;

public class ChessBoard {
    private final Map<Position, Square> chessBoard = new LinkedHashMap<>();

    public ChessBoard() {
        Positions.getValues().forEach(position -> chessBoard.put(position, new Empty()));

        Map<Piece, List<Position>> locationInfo = new LinkedHashMap<>();

        Player blackPlayer = Player.BLACK;
        locationInfo.put(new Rook(blackPlayer), Arrays.asList(Positions.of(Row.A, Column.EIGHT), Positions.of(Row.H, Column.EIGHT)));
        locationInfo.put(new Knight(blackPlayer), Arrays.asList(Positions.of(Row.B, Column.EIGHT), Positions.of(Row.G, Column.EIGHT)));
        locationInfo.put(new Bishop(blackPlayer), Arrays.asList(Positions.of(Row.C, Column.EIGHT), Positions.of(Row.F, Column.EIGHT)));
        locationInfo.put(new Queen(blackPlayer), Arrays.asList(Positions.of(Row.D, Column.EIGHT)));
        locationInfo.put(new King(blackPlayer), Arrays.asList(Positions.of(Row.E, Column.EIGHT)));
        locationInfo.put(new Pawn(blackPlayer), Arrays.stream(Row.values()).map(row -> Positions.of(row, Column.SEVEN)).collect(Collectors.toList()));

        Player whitePlayer = Player.WHITE;
        locationInfo.put(new Rook(whitePlayer), Arrays.asList(Positions.of(Row.A, Column.ONE), Positions.of(Row.H, Column.ONE)));
        locationInfo.put(new Knight(whitePlayer), Arrays.asList(Positions.of(Row.B, Column.ONE), Positions.of(Row.G, Column.ONE)));
        locationInfo.put(new Bishop(whitePlayer), Arrays.asList(Positions.of(Row.C, Column.ONE), Positions.of(Row.F, Column.ONE)));
        locationInfo.put(new Queen(whitePlayer), Arrays.asList(Positions.of(Row.D, Column.ONE)));
        locationInfo.put(new Queen(whitePlayer), Arrays.asList(Positions.of(Row.E, Column.ONE)));
        locationInfo.put(new Pawn(whitePlayer), Arrays.stream(Row.values()).map(row -> Positions.of(row, Column.TWO)).collect(Collectors.toList()));

        for (Map.Entry<Piece, List<Position>> entry : locationInfo.entrySet()) {
            entry.getValue().forEach(position -> chessBoard.put(position, entry.getKey()));
        }
    }

    public void move(Position source, Position target) {
        if (((Piece) chessBoard.get(source)).movable(source, target)
                && validateObstacles(getRoutes(source, target))
                && !isSamePlayer(source, target)) {
            chessBoard.put(target, chessBoard.get(source));
            chessBoard.put(source, new Empty());
        }
    }

    private boolean isSamePlayer(Position source, Position target) {
        if (chessBoard.get(source).getClass() == (Empty.class)
                || chessBoard.get(target).getClass() == (Empty.class)){
            return false;
        }
        return ((Piece) chessBoard.get(source)).getPlayer() != ((Piece) chessBoard.get(target)).getPlayer();
    }

    public Map<Position, Square> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }

    public List<Position> getRoutes(Position source, Position target) {
        Direction direction = Direction.getMoveRule(source, target);
        List<Position> routes = direction.getPositionsBetween(source, target);
        return routes;
    }

    public boolean validateObstacles(List<Position> routes) {
        for (Position position : routes) {
            if (!chessBoard.get(position).getClass().isInstance(Empty.class)) {
                return false;
            }
        }
        return true;
    }
}
