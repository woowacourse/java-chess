package chess.domain.board;

public class ChessBoardMaker {
//
//    private static final int EMPTY_START = 3;
//    private static final int EMPTY_END = 6;
//    private static final int RANK_SIZE = 8;
//    private static final int FILE_SIZE = 8;
//    private static final List<Type> KING_RANK = List.of(
//            Type.ROOK, Type.KNIGHT, Type.BISHOP, Type.QUEEN,
//            Type.KING, Type.BISHOP, Type.KNIGHT, Type.ROOK);
//    private static final List<Type> PAWN_RANK = Stream.generate(() -> Type.PAWN)
//            .limit(RANK_SIZE)
//            .toList();
//    private static final List<Empty> EMPTY_RANK = Stream.generate(Empty::getInstance)
//            .limit(FILE_SIZE)
//            .toList();
//
//    public ChessBoard make() {
//        Queue<Square> orderedSquares = makeOrderedSquares();
//        return new ChessBoard(makeInitialSquares(orderedSquares));
//    }
//
//    private Queue<Square> makeOrderedSquares() {
//        Queue<Square> orderedSquares = new ArrayDeque<>();
//        orderedSquares.addAll(makePieces(KING_RANK, Color.BLACK));
//        orderedSquares.addAll(makePieces(PAWN_RANK, Color.BLACK));
//        orderedSquares.addAll(makeEmptyRanks());
//        orderedSquares.addAll(makePieces(PAWN_RANK, Color.WHITE));
//        orderedSquares.addAll(makePieces(KING_RANK, Color.WHITE));
//
//        return orderedSquares;
//    }
//
//    private List<Square> makeEmptyRanks() {
//        List<Square> squares = new ArrayList<>();
//        for (int rank = EMPTY_START; rank <= EMPTY_END; rank++) {
//            squares.addAll(EMPTY_RANK);
//        }
//
//        return squares;
//    }
//
//    private List<Piece> makePieces(List<Type> squares, Color color) {
//        return squares.stream()
//                .map(type -> new Piece(type, color))
//                .toList();
//    }
//
//    private Map<Position, Square> makeInitialSquares(Queue<Square> squareQueue) {
//        Map<Position, Square> squares = new LinkedHashMap<>();
//        for (Rank rank : Rank.values()) {
//            squares.putAll(makeRank(rank, squareQueue));
//        }
//        return squares;
//    }
//
//    private Map<Position, Square> makeRank(Rank rank, Queue<Square> squareQueue) {
//        Map<Position, Square> squares = new LinkedHashMap<>();
//        for (File file : File.values()) {
//            squares.put(new Position(rank, file), squareQueue.poll());
//        }
//
//        return squares;
//    }
}
