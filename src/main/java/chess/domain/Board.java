package chess.domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Board {

    private final Map<Position, Piece> board;

    private Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board create() {
        final Map<Position, Piece> board = new LinkedHashMap<>(64);

        initialPositions(board);
        initialPieces(board);

        return new Board(board);
    }

    private static void initialPositions(Map<Position, Piece> board) {
        List<Position> positions = Stream.of(Rank.values())
            .flatMap(rank ->
                Stream.of(File.values()).map(file -> new Position(rank, file)))
            .collect(Collectors.toList());

        for (Position position : positions) {
            board.put(position, null);
        }
    }

    private static void initialPieces(Map<Position, Piece> board) {
        initialPiecesWithoutPawn(board, Rank.EIGHT, Color.BLACK);
        initialPawns(board, Rank.SEVEN, Color.BLACK);

        initialPiecesWithoutPawn(board, Rank.ONE, Color.WHITE);
        initialPawns(board, Rank.TWO, Color.WHITE);
    }

    private static void initialPiecesWithoutPawn(Map<Position, Piece> board, final Rank rank,
        final Color color) {
        board.put(new Position(rank, File.A), new Piece(color, Type.ROOK));
        board.put(new Position(rank, File.B), new Piece(color, Type.KNIGHT));
        board.put(new Position(rank, File.C), new Piece(color, Type.BISHOP));
        board.put(new Position(rank, File.D), new Piece(color, Type.QUEEN));
        board.put(new Position(rank, File.E), new Piece(color, Type.KING));
        board.put(new Position(rank, File.F), new Piece(color, Type.BISHOP));
        board.put(new Position(rank, File.G), new Piece(color, Type.KNIGHT));
        board.put(new Position(rank, File.H), new Piece(color, Type.ROOK));
    }

    private static void initialPawns(Map<Position, Piece> board, final Rank rank,
        final Color color) {
        for (File file : File.values()) {
            board.put(new Position(rank, file), new Piece(color, Type.PAWN));
        }
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(board));
    }
}
