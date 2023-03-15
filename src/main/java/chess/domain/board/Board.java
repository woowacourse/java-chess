package chess.domain.board;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Board {

    private final Map<Position, Piece> board;

    private Board(final Map<Position, Piece> board) {
        this.board = board;
    }

    public static Board initialize() {
        final Map<Position, Piece> result = new HashMap<>();
        result.putAll(initializePiece(Color.WHITE, File.ONE));
        result.putAll(initializePawn(Color.WHITE, File.TWO));
        result.putAll(initializeEmptyPiece(List.of(File.THREE, File.FOUR, File.FIVE, File.SIX)));
        result.putAll(initializePawn(Color.BLACK, File.SEVEN));
        result.putAll(initializePiece(Color.BLACK, File.EIGHT));
        return new Board(result);
    }

    private static Map<Position, Piece> initializePiece(final Color color, final File file) {
        final List<Piece> pieces = List.of(Rook.from(color), Knight.from(color), Bishop.from(color), Queen.from(color),
                King.from(color), Bishop.from(color), Knight.from(color), Rook.from(color));
        final List<Rank> ranks = Arrays.stream(Rank.values()).collect(toList());

        return IntStream.range(0, pieces.size())
                .boxed()
                .collect(toMap(index -> Position.of(ranks.get(index), file), pieces::get));

    }

    private static Map<Position, Piece> initializePawn(final Color color, final File file) {
        return Arrays.stream(Rank.values())
                .map(rank -> Position.of(rank, file))
                .collect(toMap(Function.identity(), ignore -> Pawn.from(color)));
    }

    private static Map<Position, Piece> initializeEmptyPiece(final List<File> files) {
        return files.stream()
                .flatMap(file -> Arrays.stream(Rank.values()).map(rank -> Position.of(rank, file)))
                .collect(toMap(Function.identity(), ignore -> Empty.create()));
    }

    public Map<Position, Piece> getBoard() {
        return Collections.unmodifiableMap(board);
    }
}
