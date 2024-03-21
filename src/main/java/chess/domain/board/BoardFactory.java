package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.square.File;
import chess.domain.square.Rank;
import chess.domain.square.Square;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardFactory {

    public static final List<Function<PieceColor, Piece>> PIECES_ARRANGEMENT = List.of(
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

    private static Map<Square, Piece> createPiecesWithoutPawn(Rank rank, PieceColor pieceColor) {
        return IntStream.range(0, PIECES_ARRANGEMENT.size())
                .boxed()
                .collect(Collectors.toMap(
                        index -> new Square(File.values()[index], rank),
                        index -> PIECES_ARRANGEMENT.get(index).apply(pieceColor)
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
