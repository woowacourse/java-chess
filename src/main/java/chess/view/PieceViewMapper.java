package chess.view;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class PieceViewMapper {
    private static final Map<Piece, String> PIECE_STRING_MAPPER = new HashMap<>();
    private static final String INITIAL_PIECE_STRING = "RNBQKBNRPPPPPPPPpppppppprnbqkbnr";
    private static final String STRING_FOR_NO_PIECE = ".";
    private static final int PAWN_COUNT_END = 8;
    private static final int PAWN_COUNT_START = 1;

    static {
        final List<Piece> pieces = new ArrayList<>();
        pieces.addAll(piecesByColor(Color.BLACK));
        pieces.addAll(pawnsByColor(Color.BLACK));
        pieces.addAll(pawnsByColor(Color.WHITE));
        pieces.addAll(piecesByColor(Color.WHITE));

        final String[] split = INITIAL_PIECE_STRING.split("");
        for (int i = 0; i < split.length; i++) {
            PIECE_STRING_MAPPER.put(pieces.get(i), split[i]);
        }
    }

    private static List<Piece> piecesByColor(Color color) {
        return List.of(new Rook(color), new Knight(color), new Bishop(color),
                new Queen(color), new King(color), new Bishop(color), new Knight(color),
                new Rook(color));
    }

    private static List<Piece> pawnsByColor(Color color) {
        return IntStream.rangeClosed(PAWN_COUNT_START, PAWN_COUNT_END)
                .mapToObj(number -> new Pawn(color))
                .collect(Collectors.toList());
    }

    public PieceViewMapper() {
    }

    public static String parse(Piece piece) {
        return PIECE_STRING_MAPPER.getOrDefault(piece, STRING_FOR_NO_PIECE);
    }
}
