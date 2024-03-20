//package domain.piece;
//
//import domain.chessboard.Square;
//import domain.game.PieceMover;
//import domain.position.File;
//import domain.position.Position;
//import domain.position.Rank;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.IntStream;
//
//public class PieceGenerator {
//    private static final List<PieceRole> BACK = List.of(ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK);
//    private static final List<PieceRole> FRONT = List.of(PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN, PAWN);
//
//    private PieceGenerator(){
//    }
//
//    public static void generate(final PieceMover mover) {
//        for (int row = 0; row < 8; row++) {
//            List<Piece> pieces = generateRankPieces(row);
//            for (int column = 0; column < pieces.size(); column++) {
//                Square square = new Square(new Position(new File((char) ('a' + column)), new Rank(row)));
//                Piece piece = pieces.get(column);
//                mover.add(square, piece);
//            }
//        }
//    }
//
//    public static List<Piece> generateRankPieces(final int row) {
//        if (row == 0) {
//            return generateListPiece(BACK, Color.BLACK, row);
//        }
//        if (row == 1) {
//            return generateListPiece(FRONT, Color.BLACK, row);
//        }
//        if (row == 6) {
//            return generateListPiece(FRONT, Color.WHITE, row);
//        }
//        if (row == 7) {
//            return generateListPiece(BACK, Color.WHITE, row);
//        }
//        return new ArrayList<>();
//    }
//
//    private static List<Piece> generateListPiece(final List<PieceRole> pieceRoles, final Color color, final int row) {
//        return IntStream.range(0, pieceRoles.size())
//                .mapToObj(column ->
//                        new Piece(new PieceType(pieceRoles.get(column), color),
//                                new Position(
//                                        new File((char) ('a' + column)), new Rank(row)))).toList();
//
//    }
//}
