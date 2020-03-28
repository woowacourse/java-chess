package chess.board;

import chess.coordinate.Coordinate;
import chess.coordinate.File;
import chess.coordinate.Rank;
import chess.piece.Blank;
import chess.piece.Team;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ChessBoard {
    private final Map<Coordinate, Tile> chessBoard;

    private ChessBoard(final Map<Coordinate, Tile> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static ChessBoard empty() {
        Map<Coordinate, Tile> emptyBoard = new HashMap<>();
        for (File file : File.values()) {
            makeEmpty(emptyBoard, file);
        }
        return new ChessBoard(emptyBoard);
    }

    private static void makeEmpty(Map<Coordinate, Tile> emptyBoard, File file) {
        for (Rank rank : Rank.values()) {
            Coordinate coordinate = Coordinate.of(file, rank);
            emptyBoard.put(coordinate, new Tile(coordinate, Blank.getInstance()));
        }
    }

    public Map<Coordinate, Tile> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }

    public boolean move(String sourceKey, String targetKey, Consumer<Object> pushEvent) {
        Tile sourceTile = chessBoard.get(Coordinate.of(sourceKey));
        Tile targetTile = chessBoard.get(Coordinate.of(targetKey));

        if (sourceTile.canNotReach(targetTile)) {
            return false;
        }

        Directions directions = sourceTile.findPath(targetTile);
        if (directions.isExist(sourceKey, chessBoard::get)) {
            return false;
        }

        pushEvent.accept(targetTile.replacePiece(sourceTile));
        return true;
    }

    public double calculateScore(Team team) {
        return Score.calculateScore(team, chessBoard::get).getSum();
    }

    public void put(final Tile tile) {
        this.chessBoard.put(tile.getCoordinate(), tile);
    }

    public boolean isNotSameTeam(final String source, final Team currentTeam) {
        Tile tile = this.chessBoard.get(Coordinate.of(source));
        return tile.isNotSameTeam(currentTeam);
    }
}