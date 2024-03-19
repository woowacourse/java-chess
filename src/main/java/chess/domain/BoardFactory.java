package chess.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardFactory {

    public static final List<PieceName> PIECE_NAMES = List.of(PieceName.ROOK, PieceName.KNIGHT, PieceName.BISHOP,
            PieceName.QUEEN, PieceName.KING, PieceName.BISHOP, PieceName.KNIGHT, PieceName.ROOK);

    public static Map<Position, Piece> createBoard() {
        Map<Position, Piece> board = new HashMap<>();

        board.putAll(createPiecesWithoutPawn(new Rank(8), PieceColor.BLACK));
        board.putAll(createPawns(new Rank(7), PieceColor.BLACK));
        board.putAll(createPawns(new Rank(2), PieceColor.WHITE));
        board.putAll(createPiecesWithoutPawn(new Rank(1), PieceColor.WHITE));

        return board;
    }

    // TODO: 필드 및 메서드명 재고
    private static Map<Position, Piece> createPiecesWithoutPawn(Rank rank, PieceColor pieceColor) {
        return IntStream.range(0, PIECE_NAMES.size())
                .boxed()
                .collect(Collectors.toMap(
                        index -> new Position(rank, File.values()[index]),
                        index -> new Piece(PIECE_NAMES.get(index), pieceColor)
                ));
    }

    private static Map<Position, Piece> createPawns(Rank rank, PieceColor pieceColor) {
        return Arrays.stream(File.values())
                .collect(Collectors.toMap(
                        file -> new Position(rank, file),
                        file -> new Piece(PieceName.PAWN, pieceColor)
                ));
    }
}
