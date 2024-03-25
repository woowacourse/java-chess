package chess.domain.board;

public class ChessBoardMakerTest {

//    @DisplayName("체스판을 만든다.")
//    @Test
//    void createChessBoard() {
//        // given
//        ChessBoardMaker chessBoardMaker = new ChessBoardMaker();
//
//        Map<Position, Piece> pieces = new LinkedHashMap<>();
//        pieces.put(new Position(Rank.FIRST, File.A), new Piece(Type.ROOK, Color.BLACK));
//        pieces.put(new Position(Rank.FIRST, File.B), new Piece(Type.KNIGHT, Color.BLACK));
//        pieces.put(new Position(Rank.FIRST, File.C), new Piece(Type.BISHOP, Color.BLACK));
//        pieces.put(new Position(Rank.FIRST, File.D), new Piece(Type.QUEEN, Color.BLACK));
//        pieces.put(new Position(Rank.FIRST, File.E), new Piece(Type.KING, Color.BLACK));
//        pieces.put(new Position(Rank.FIRST, File.F), new Piece(Type.BISHOP, Color.BLACK));
//        pieces.put(new Position(Rank.FIRST, File.G), new Piece(Type.KNIGHT, Color.BLACK));
//        pieces.put(new Position(Rank.FIRST, File.H), new Piece(Type.ROOK, Color.BLACK));
//
//        for (File file : File.values()) {
//            pieces.put(new Position(Rank.SECOND, file), new Piece(Type.PAWN, Color.BLACK));
//            pieces.put(new Position(Rank.SEVENTH, file), new Piece(Type.PAWN, Color.WHITE));
//        }
//
//        pieces.put(new Position(Rank.EIGHTH, File.A), new Piece(Type.ROOK, Color.WHITE));
//        pieces.put(new Position(Rank.EIGHTH, File.B), new Piece(Type.KNIGHT, Color.WHITE));
//        pieces.put(new Position(Rank.EIGHTH, File.C), new Piece(Type.BISHOP, Color.WHITE));
//        pieces.put(new Position(Rank.EIGHTH, File.D), new Piece(Type.QUEEN, Color.WHITE));
//        pieces.put(new Position(Rank.EIGHTH, File.E), new Piece(Type.KING, Color.WHITE));
//        pieces.put(new Position(Rank.EIGHTH, File.F), new Piece(Type.BISHOP, Color.WHITE));
//        pieces.put(new Position(Rank.EIGHTH, File.G), new Piece(Type.KNIGHT, Color.WHITE));
//        pieces.put(new Position(Rank.EIGHTH, File.H), new Piece(Type.ROOK, Color.WHITE));
//
//        for (File file : File.values()) {
//            pieces.put(new Position(Rank.THIRD, file), Empty.getInstance());
//            pieces.put(new Position(Rank.FOURTH, file), Empty.getInstance());
//            pieces.put(new Position(Rank.FIFTH, file), Empty.getInstance());
//            pieces.put(new Position(Rank.SIXTH, file), Empty.getInstance());
//        }
//        ChessBoard expected = new ChessBoard(pieces);
//
//        // when
//        ChessBoard board = chessBoardMaker.make();
//
//        // then
//        assertThat(board)
//                .usingRecursiveComparison()
//                .isEqualTo(expected);
//    }
}
