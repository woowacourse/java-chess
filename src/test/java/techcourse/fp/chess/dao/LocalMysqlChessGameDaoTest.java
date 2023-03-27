package techcourse.fp.chess.dao;


class LocalMysqlChessGameDaoTest {

    private LocalMysqlChessGameDao dao = new LocalMysqlChessGameDao();
//
//    @Test
//    void test1() {
//        final Map<Position, Piece> emptyBoard = createEmptyBoard();
//        emptyBoard.put(A1, BLACK_KING);
//        emptyBoard.put(A2, WHITE_KING);
//
//        final ChessGameRequest request = new ChessGameRequest(emptyBoard, "임시 게임", Color.BLACK.name());
//
//        dao.save(request);
//    }
//
//
//    @Test
//    void test2() {
//        final Board board = BoardFactory.generate();
//        final ChessGameRequest request = new ChessGameRequest(board.getBoard(), "임시 게임", Color.WHITE.name());
//
//        final ChessGame chessGame = dao.findById(4);
//
//        chessGame.move(Position.createByName("A", "two"), Position.createByName("A", "three"));
//    }
//
//    private Map<Position, Piece> createEmptyBoard() {
//        final Map<Position, Piece> board = new HashMap<>();
//        for (File file : File.values()) {
//            for (Rank rank : Rank.values()) {
//                board.put(Position.of(file, rank), Empty.create());
//            }
//        }
//
//        return board;
//    }
}
