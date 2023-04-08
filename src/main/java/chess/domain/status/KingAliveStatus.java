package chess.domain.status;

public class KingAliveStatus {

//    private final Color turn;
//
//    public KingAliveStatus(final Color turn) {
//        this.turn = turn;
//    }
//
//    @Override
//    public void validateMove(final ChessBoard chessBoard, final Position from, final Position to) {
//        final Piece movingPiece = chessBoard.getPiece(from);
//        validatePlayerTurn(movingPiece);
//        chessBoard.validateMove(from, to);
//    }
//
//    private void validatePlayerTurn(final Piece movingPiece) {
//        if (!turn.isTurnOf(movingPiece)) {
//            throw new IllegalArgumentException("해당 기물의 턴이 아닙니다");
//        }
//    }
//
//    @Override
//    public GameStatus nextStatus(final ChessBoard chessBoard) {
//        if (chessBoard.isKingDead()) {
//            return new KingDeadStatus(turn);
//        }
//        return new KingAliveStatus(turn.nextTurn());
//    }
//
//    @Override
//    public boolean isGameOver() {
//        return false;
//    }
//
//    @Override
//    public Color getWinner() {
//        throw new UnsupportedOperationException("게임이 아직 진행 중입니다");
//    }
//
//    @Override
//    public Color getTurn() {
//        return turn;
//    }
}
