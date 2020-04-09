package chess.board;

import chess.coordinate.Coordinate;
import chess.coordinate.File;
import chess.coordinate.Rank;
import chess.piece.Blank;
import chess.piece.Piece;
import chess.piece.Team;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChessBoard {
    private final Map<Coordinate, Tile> chessBoard;

    private ChessBoard(final Map<Coordinate, Tile> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static ChessBoard empty() {
        Map<Coordinate, Tile> emptyBoard = new LinkedHashMap<>();
        for (Rank rank : Rank.values()) {
            makeEmpty(emptyBoard, rank);
        }
        return new ChessBoard(emptyBoard);
    }

    private static void makeEmpty(Map<Coordinate, Tile> emptyBoard, Rank rank) {
        for (File file : File.values()) {
            Coordinate coordinate = Coordinate.of(file, rank);
            emptyBoard.put(coordinate, new Tile(coordinate, Blank.getInstance()));
        }
    }

    public Map<Coordinate, Tile> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }

    public Piece replace(String sourceKey, String targetKey) {
        Tile sourceTile = chessBoard.get(Coordinate.of(sourceKey));
        Tile targetTile = chessBoard.get(Coordinate.of(targetKey));

        if (sourceTile.canNotReach(targetTile)) {
            throw new IllegalArgumentException(String.format("%s 에 도달할 수 없습니다.", targetKey));
        }

        Directions directions = sourceTile.findPath(targetTile);
        if (directions.isExist(sourceKey, chessBoard::get)) {
            throw new IllegalArgumentException("이동 경로에 다른 체스말이 존재합니다.");
        }
        return targetTile.replacePiece(sourceTile);
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

    public Piece findByKey(String key) {
        Coordinate coordinate = Coordinate.of(key);
        return chessBoard.get(coordinate).getPiece();
    }
}