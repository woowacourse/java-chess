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

    private final Map<Coordinate, Piece> cells = new HashMap<>();

    private Board() {
    }

    public static Board getInstance() {
        if (board == null) {
            board = new Board();
        }
        return board;
    }

    public Piece find(Coordinate coordinate) {
        return cells.get(coordinate);
    }

    public void initialize() {
        putPawns(TeamType.BLACK, Rank.SEVEN);
        putPiecesNotPawn(TeamType.BLACK, Rank.EIGHT);

        putPawns(TeamType.WHITE, Rank.TWO);
        putPiecesNotPawn(TeamType.WHITE, Rank.ONE);
    }

    private void putPawns(TeamType teamType, Rank rank) {
        for (File file : File.values()) {
            cells.put(new Coordinate(file, rank), new Pawn(teamType));
        }
    }

    private void putPiecesNotPawn(TeamType teamType, Rank rank) {
        List<File> files = Arrays.asList(File.values());
        List<Piece> pieces = createPiecesNotPawn(teamType);

        for (int i = 0; i < files.size(); i++) {
            Coordinate coordinate = new Coordinate(files.get(i), rank);
            cells.put(coordinate, pieces.get(i));
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

    public Piece find(String currentCoordinateInput, TeamType teamType) {
        Coordinate currentCoordinate = Coordinate.from(currentCoordinateInput);
        Piece piece = cells.get(currentCoordinate);
        if (piece == null) {
            throw new IllegalArgumentException("말이 존재하지 않습니다.");
        }
        if (!piece.isTeamOf(teamType)) {
            throw new IllegalArgumentException("자신의 말이 아닙니다.");
        }
        return piece;
    }

    public Map<Coordinate, Piece> getCells() {
        return cells;
    }

    public void remove(Coordinate coordinate) {
        cells.remove(coordinate);
    }

    public void put(Piece piece, Coordinate coordinate) {
        cells.put(coordinate, piece);
    }
}

