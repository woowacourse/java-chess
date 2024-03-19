import java.util.ArrayDeque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Stream;

public class ChessBoardMaker {

    public static final List<Piece> KING_LINE = List.of(
            Piece.ROOK, Piece.KNIGHT, Piece.BISHOP, Piece.QUEEN,
            Piece.KING, Piece.BISHOP, Piece.KNIGHT, Piece.ROOK);
    public static final List<Piece> PAWN_LINE = Stream.generate(() -> Piece.PAWN)
            .limit(8)
            .toList();
    public static final List<Empty> EMPTY_LINE = Stream.generate(() -> new Empty())
            .limit(8)
            .toList();
    
    public ChessBoard make() {
        Queue<Square> orderedSquares = makeOrderedSquares();
        return new ChessBoard(makeInitialSquares(orderedSquares));
    }

    private static Queue<Square> makeOrderedSquares() {
        Queue<Square> orderedSquares = new ArrayDeque<>();
        orderedSquares.addAll(KING_LINE);
        orderedSquares.addAll(PAWN_LINE);
        for (int rank = 3; rank <= 6; rank++) {
            orderedSquares.addAll(EMPTY_LINE);
        }
        orderedSquares.addAll(PAWN_LINE);
        orderedSquares.addAll(KING_LINE);
        return orderedSquares;
    }

    private static Map<Position, Square> makeInitialSquares(Queue<Square> squareQueue) {
        Map<Position, Square> squares = new LinkedHashMap<>();
        for (Rank rank : Rank.values()) {
            for (File file : File.values()) {
                squares.put(new Position(rank, file), squareQueue.poll());
            }
        }
        return squares;
    }
}
