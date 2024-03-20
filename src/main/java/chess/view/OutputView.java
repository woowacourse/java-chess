package chess.view;

import chess.domain.position.Position;
import chess.domain.position.Rank;
import chess.domain.square.Square;

import java.util.Map;

public class OutputView {
    private static final OutputView INSTANCE = new OutputView();
//    private static final Map<Type, String> TYPE_VIEWS = new EnumMap<>(Type.class);
//    private static final Map<Color, Function<String, String>> COLOR_FORMATTERS = new EnumMap<>(Color.class);
//    private static final Map<Square, String> SQUARE_VIEWS = new HashMap<>();
//
//    static {
//        initTypeView();
//        initColorFormatter();
//        initSquareView();
//    }
//
//    private static void initColorFormatter() {
//        COLOR_FORMATTERS.putAll(Map.of(
//                Color.BLACK, String::toUpperCase,
//                Color.WHITE, String::toLowerCase));
//    }
//
//    private static void initTypeView() {
//        TYPE_VIEWS.putAll(Map.of(
//                Type.PAWN, "P",
//                Type.KNIGHT, "N",
//                Type.BISHOP, "B",
//                Type.ROOK, "R",
//                Type.QUEEN, "Q",
//                Type.KING, "K"));
//    }
//
//    private static void initSquareView() {
//        SQUARE_VIEWS.put(Empty.getInstance(), ".");
//        for (Type type : Type.values()) {
//            initByType(type);
//        }
//    }
//
//    private static void initByType(Type type) {
//        for (Color color : Color.values()) {
//            Function<String, String> colorFormatter = COLOR_FORMATTERS.get(color);
//            String typeView = TYPE_VIEWS.get(type);
//            String pieceView = colorFormatter.apply(typeView);
//
//            SQUARE_VIEWS.put(new Piece(type, color), pieceView);
//        }
//    }

    private OutputView() {
    }

    public static OutputView getInstance() {
        return INSTANCE;
    }

    public void printChessBoard(Map<Position, Square> squares) {
//        for (Rank rank : Rank.values()) {
//            printRank(squares, rank);
//            System.out.println();
//        }
//        System.out.println();
    }

    private void printRank(Map<Position, Square> squares, Rank rank) {
//        for (File file : File.values()) {
//            Square square = squares.get(new Position(rank, file));
//            System.out.print(SQUARE_VIEWS.get(square));
//        }
    }
}
