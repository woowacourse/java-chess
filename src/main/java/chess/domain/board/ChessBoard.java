package chess.domain.board;

import chess.domain.coordinate.Coordinate;
import chess.domain.coordinate.File;
import chess.domain.coordinate.Rank;
import chess.domain.piece.Blank;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChessBoard {
    private final Map<Coordinate, Tile> chessBoard;

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

    public Piece move(String sourceKey, String targetKey) {
        Tile sourceTile = chessBoard.get(Coordinate.of(sourceKey));
        Tile targetTile = chessBoard.get(Coordinate.of(targetKey));

        if (sourceTile.canNotReach(targetTile)) {
            throw new IllegalArgumentException("That piece can not move to target.");
        }

        Directions directions = sourceTile.findPath(targetTile);

        if (directions.hasObstacle(sourceKey, chessBoard::get)) {
            throw new IllegalArgumentException("There is a obstacle between source and target.");
        }

        return targetTile.replacePiece(sourceTile);
    }

    public double calculateScore(Team team) {
        return Score.calculateScore(team, chessBoard::get);
    }

    public void put(final Tile tile) {
        this.chessBoard.put(tile.getCoordinate(), tile);
    }

    public boolean isNotSameTeam(final String source, final Team currentTeam) {
        Tile tile = this.chessBoard.get(Coordinate.of(source));
        return !tile.isSameTeam(currentTeam);
    }
}