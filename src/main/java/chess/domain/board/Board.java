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

    public Piece find(Coordinate coordinate) {
        Cell cell = cells.get(coordinate);
        return cell.getPiece();
    }

    public void initialize() {
        putPawns(TeamType.BLACK, Rank.SEVEN);
        putPiecesNotPawn(TeamType.BLACK, Rank.EIGHT);

        putPawns(TeamType.WHITE, Rank.TWO);
        putPiecesNotPawn(TeamType.WHITE, Rank.ONE);
    }

    private void putPawns(TeamType teamType, Rank rank) {
        for (File file : File.values()) {
            Coordinate coordinate = new Coordinate(file, rank);
            Cell cell = cells.get(coordinate);
            cell.put(new Pawn(teamType));
        }
    }

    private void putPiecesNotPawn(TeamType teamType, Rank rank) {
        List<File> files = Arrays.asList(File.values());
        List<Piece> pieces = createPiecesNotPawn(teamType);

        for (int i = 0; i < files.size(); i++) {
            Coordinate coordinate = new Coordinate(files.get(i), rank);
            Cell cell = cells.get(coordinate);
            cell.put(pieces.get(i));
        }
    }

    private List<Piece> createPiecesNotPawn(TeamType teamType) {
        return Arrays.asList(
            new Rook(teamType),
            new Knight(teamType),
            new Bishop(teamType),
            new Queen(teamType),
            new King(teamType),
            new Bishop(teamType),
            new Knight(teamType),
            new Rook(teamType)
        );
    }
}
