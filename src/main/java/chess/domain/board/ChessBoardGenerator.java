package chess.domain.board;

import chess.domain.piece.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChessBoardGenerator {

    private ChessBoardGenerator() {
    }

    public static Map<Coordinate, Cell> generateDefaultChessBoard() {
        Map<Coordinate, Cell> cells = Arrays.stream(File.values())
                .flatMap(ChessBoardGenerator::generateDefaultCoordinate)
                .collect(Collectors.toMap(coordinate -> coordinate, value -> new Cell()));
        initializeDefaultPieces(cells);
        return cells;
    }

    private static Stream<Coordinate> generateDefaultCoordinate(File file) {
        return Arrays.stream(Rank.values())
                .map(rank -> new Coordinate(file, rank));
    }

    private static void initializeDefaultPieces(Map<Coordinate, Cell> cells) {
        putPawns(cells, TeamType.BLACK, Rank.SEVEN);
        putPiecesExceptPawn(cells, TeamType.BLACK, Rank.EIGHT);
        putPawns(cells, TeamType.WHITE, Rank.TWO);
        putPiecesExceptPawn(cells, TeamType.WHITE, Rank.ONE);
    }

    private static void putPawns(Map<Coordinate, Cell> cells, TeamType teamType, Rank pawnDefaultRank) {
        for (File file : File.values()) {
            Coordinate coordinate = new Coordinate(file, pawnDefaultRank);
            Cell emptyCell = cells.get(coordinate);
            emptyCell.put(new Pawn(teamType));
        }
    }

    private static void putPiecesExceptPawn(Map<Coordinate, Cell> cells, TeamType teamType, Rank piecesDefaultRank) {
        List<Piece> pieces = generatePiecesExceptPawn(teamType);
        File[] files = File.values();
        for (int i = 0; i < files.length; i++) {
            Coordinate coordinate = new Coordinate(files[i], piecesDefaultRank);
            Cell emptyCell = cells.get(coordinate);
            emptyCell.put(pieces.get(i));
        }
    }

    private static List<Piece> generatePiecesExceptPawn(TeamType teamType) {
        return Arrays.asList(new Rook(teamType), new Knight(teamType), new Bishop(teamType), new Queen(teamType),
                new King(teamType), new Bishop(teamType), new Knight(teamType), new Rook(teamType));
    }
}
