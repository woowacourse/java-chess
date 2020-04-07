package chess.domain.board;

import chess.domain.coordinate.Coordinate;
import chess.domain.coordinate.File;
import chess.domain.coordinate.Rank;
import chess.domain.observer.Observable;
import chess.domain.observer.Publishable;
import chess.domain.piece.Blank;
import chess.domain.piece.Team;

import java.util.*;

public class ChessBoard implements Publishable {
    private final Map<Coordinate, Tile> chessBoard;
    private Observable observable;

    private ChessBoard(final Map<Coordinate, Tile> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static ChessBoard empty() {
        Map<Coordinate, Tile> emptyBoard = new HashMap<>();
        for (File file : File.values()) {
            putEmptyTileInFile(emptyBoard, file);
        }
        return new ChessBoard(emptyBoard);
    }

    private static void putEmptyTileInFile(Map<Coordinate, Tile> emptyBoard, File file) {
        for (Rank rank : Rank.values()) {
            Coordinate coordinate = Coordinate.of(file, rank);
            emptyBoard.put(coordinate, new Tile(coordinate, new Blank()));
        }
    }

    public Map<Coordinate, Tile> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }

    public void move(Coordinate source, Coordinate target) {

        if (!canMove(source, target)) {
            throw new IllegalArgumentException("That piece can not move to target.");
        }

        Tile sourceTile = chessBoard.get(source);
        Tile targetTile = chessBoard.get(target);

        push(targetTile.replacePiece(sourceTile));
    }

    public boolean canMove(Coordinate source, Coordinate target) {
        Tile sourceTile = chessBoard.get(source);
        Tile targetTile = chessBoard.get(target);

        if (sourceTile.canNotReach(targetTile)) {
            return false;
        }

        Directions directions = sourceTile.findPath(targetTile);
        if (directions.hasObstacle(source, chessBoard::get)) {
            return false;
        }
        return true;
    }

    public List<String> getMovableWay(Coordinate sourceCoordinate) {
        List<String> movableCoordinate = new ArrayList<>();
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                Coordinate targetCoordinate = Coordinate.of(file, rank);
                if (canMove(sourceCoordinate, targetCoordinate)) {
                    movableCoordinate.add(targetCoordinate.toString());
                }
            }
        }
        return movableCoordinate;
    }

    public double calculateScore(Team team) {
        return Score.calculateScore(team, chessBoard::get);
    }

    public void put(final Tile tile) {
        this.chessBoard.put(tile.getCoordinate(), tile);
    }

    public boolean isNotSameTeam(final Coordinate source, final Team currentTeam) {
        Tile tile = this.chessBoard.get(source);
        return !tile.isSameTeam(currentTeam);
    }

    @Override
    public void subscribe(final Observable observable) {
        this.observable = observable;
    }

    @Override
    public void push(final Object object) {
        observable.update(object);
    }
}