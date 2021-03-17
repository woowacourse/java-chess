package chess.domain.board;

import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.player.PlayerType;
import java.util.HashMap;
import java.util.Map;

public class Board {
    private static Board board = null;

    private final Map<Coordinate, Cell> cells = new HashMap<>();

    private Board() {
        createBoard();
    }

    public static Board getInstance() {
        if (board == null) {
            board = new Board();
        }
        return board;
    }

    private void createBoard() {
        for (File file : File.values()) {
            createCells(file);
        }
    }

    private void createCells(File file) {
        for (Rank rank : Rank.values()) {
            Coordinate coordinate = new Coordinate(file, rank);
            cells.put(coordinate, new Cell(coordinate));
        }
    }

    public Piece of(Coordinate coordinate) {
        Cell cell = cells.get(coordinate);
        return cell.getPiece();
    }

    public void initialize() {
        for (File file : File.values()) {
            Coordinate coordinate = new Coordinate(file, Rank.SEVEN);
            Cell cell = cells.get(coordinate);
            cell.put(new Pawn(PlayerType.BLACK));
        }

        for (File file : File.values()) {
            Coordinate coordinate = new Coordinate(file, Rank.TWO);
            Cell cell = cells.get(coordinate);
            cell.put(new Pawn(PlayerType.WHITE));
        }
    }
}
