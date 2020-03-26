package chess.board;

import chess.board.piece.Blank;
import chess.board.piece.Direction;
import chess.board.piece.Team;

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
            for (Rank rank : Rank.values()) {
                Coordinate coordinate = Coordinate.of(file, rank);
                emptyBoard.put(coordinate, new Tile(coordinate, new Blank()));
            }
        }
        return new ChessBoard(emptyBoard);
    }

    public Map<Coordinate, Tile> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }

    public boolean move(String sourceKey, String targetKey) {
        Tile sourceTile = chessBoard.get(Coordinate.of(sourceKey));
        Tile targetTile = chessBoard.get(Coordinate.of(targetKey));

        if (sourceTile.isAlliance(targetTile)) {
            return false;
        }

        if (sourceTile.canNotReach(targetTile)) {
            return false;
        }

        Directions directions = sourceTile.findPath(targetTile);

        Coordinate next = Coordinate.of(sourceKey);
        boolean notExist = true;
        while (directions.isNotEmpty() && notExist) {
            Direction direction = directions.poll();
            next = next.move(direction);
            notExist = chessBoard.get(next).isBlank();
        }
        if (!notExist) {
            return false;
        }

        targetTile.replacePiece(sourceTile);
        return true;
    }

    public double calculateScore(Team team) {
        Score sum = Score.zero();
        for (File file : File.values()) {
            for (Rank rank : Rank.values()) {
                Coordinate coordinate = Coordinate.of(file, rank);
                sum = sum.add(chessBoard.get(coordinate));
            }
            sum = sum.subtractPawnScore();
        }
        return sum.getSum();
    }

    public void put(final Tile tile) {
        this.chessBoard.put(tile.getCoordinate(), tile);
    }

    public boolean isNotSameTeam(final String source, final Team currentTeam) {
        Tile tile = this.chessBoard.get(Coordinate.of(source));
        return !tile.isSameTeam(currentTeam);
    }
}
