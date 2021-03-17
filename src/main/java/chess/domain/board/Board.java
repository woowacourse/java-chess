package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.player.TeamType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
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
            cell.put(new Pawn(TeamType.BLACK));
        }

        for (File file : File.values()) {
            Coordinate coordinate = new Coordinate(file, Rank.TWO);
            Cell cell = cells.get(coordinate);
            cell.put(new Pawn(TeamType.WHITE));
        }

        List<File> files = Arrays.asList(File.values());
        List<Piece> pieces = Arrays.asList(
            new Rook(TeamType.BLACK),
            new Knight(TeamType.BLACK),
            new Bishop(TeamType.BLACK),
            new Queen(TeamType.BLACK),
            new King(TeamType.BLACK),
            new Bishop(TeamType.BLACK),
            new Knight(TeamType.BLACK),
            new Rook(TeamType.BLACK)
        );

        for (int i = 0; i < files.size(); i++) {
            Coordinate coordinate
                = new Coordinate(files.get(i), Rank.EIGHT);
            Cell cell = cells.get(coordinate);
            cell.put(pieces.get(i));
        }
    }
}
