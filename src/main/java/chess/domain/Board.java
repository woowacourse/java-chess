package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.FullPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.Type;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;
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
                Stream.of(File.values()).map(file -> new Position(file, rank)))
            .collect(Collectors.toList());

        for (Position position : positions) {
            board.put(position, new EmptyPiece());
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
        board.put(new Position(File.A, rank), new FullPiece(color, Type.ROOK));
        board.put(new Position(File.B, rank), new FullPiece(color, Type.KNIGHT));
        board.put(new Position(File.C, rank), new FullPiece(color, Type.BISHOP));
        board.put(new Position(File.D, rank), new FullPiece(color, Type.QUEEN));
        board.put(new Position(File.E, rank), new FullPiece(color, Type.KING));
        board.put(new Position(File.F, rank), new FullPiece(color, Type.BISHOP));
        board.put(new Position(File.G, rank), new FullPiece(color, Type.KNIGHT));
        board.put(new Position(File.H, rank), new FullPiece(color, Type.ROOK));
    }

    private static void initialPawns(Map<Position, Piece> board, final Rank rank,
        final Color color) {
        for (File file : File.values()) {
            board.put(new Position(file, rank), new FullPiece(color, Type.PAWN));
        }
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(new LinkedHashMap<>(board));
    }
}
