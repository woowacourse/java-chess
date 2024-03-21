package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardFactory {

    public static final List<Function<PieceColor, Piece>> PIECE_GENERATORS = List.of(
            Rook::new, Knight::new, Bishop::new, Queen::new,
            King::new, Bishop::new, Knight::new, Rook::new);

    private BoardFactory() {
    }

    public static Board createBoard() {
        Map<Square, Piece> board = new HashMap<>();

        board.putAll(createPiecesWithoutPawn(Rank.EIGHT, PieceColor.BLACK));
        board.putAll(createPawns(Rank.SEVEN, PieceColor.BLACK));
        board.putAll(createPawns(Rank.TWO, PieceColor.WHITE));
        board.putAll(createPiecesWithoutPawn(Rank.ONE, PieceColor.WHITE));

        return new Board(board);
    }

    // TODO: 필드 및 메서드명 재고
    private static Map<Square, Piece> createPiecesWithoutPawn(Rank rank, PieceColor pieceColor) {
        return IntStream.range(0, PIECE_GENERATORS.size())
                .boxed()
                .collect(Collectors.toMap(
                        index -> new Square(File.values()[index], rank),
                        index -> PIECE_GENERATORS.get(index).apply(pieceColor)
                ));
    }

    private static Map<Square, Piece> createPawns(Rank rank, PieceColor pieceColor) {
        return Arrays.stream(File.values())
                .collect(Collectors.toMap(
                        file -> new Square(file, rank),
                        file -> new Pawn(pieceColor)
                ));
    }
}
