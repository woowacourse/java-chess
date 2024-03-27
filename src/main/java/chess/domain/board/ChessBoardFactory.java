package chess.domain.board;

import chess.domain.pieces.Bishop;
import chess.domain.pieces.King;
import chess.domain.pieces.Knight;
import chess.domain.pieces.Queen;
import chess.domain.pieces.Rook;
import chess.domain.pieces.pawn.Pawn;
import chess.domain.pieces.piece.Color;
import chess.domain.pieces.piece.Piece;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ChessBoardFactory implements BoardFactory {

    @Override
    public Board createBoard() {
        Map<Square, Piece> initialArrangement = new HashMap<>();
        initialArrangement.putAll(createLine(Rank.ONE, createPieceLine(Color.WHITE)));
        initialArrangement.putAll(createLine(Rank.TWO, createPawnLine(Color.WHITE)));
        initialArrangement.putAll(createLine(Rank.SEVEN, createPawnLine(Color.BLACK)));
        initialArrangement.putAll(createLine(Rank.EIGHT, createPieceLine(Color.BLACK)));
        return new Board(initialArrangement);
    }

    private Map<Square, Piece> createLine(final Rank rank, final Map<File, Piece> line) {
        return line.entrySet().stream()
                .collect(Collectors.toMap(entry -> Square.of(entry.getKey(), rank), Map.Entry::getValue));
    }

    private Map<File, Piece> createPieceLine(final Color color) {
        return new HashMap<>() {{
            put(File.A, new Rook(color));
            put(File.B, new Knight(color));
            put(File.C, new Bishop(color));
            put(File.D, new Queen(color));
            put(File.E, new King(color));
            put(File.F, new Bishop(color));
            put(File.G, new Knight(color));
            put(File.H, new Rook(color));
        }};
    }

    private Map<File, Piece> createPawnLine(final Color color) {
        return Arrays.stream(File.values())
                .collect(Collectors.toMap(Function.identity(), file -> Pawn.of(color)));
    }
}
