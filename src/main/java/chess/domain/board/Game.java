//package chess.domain.board;
//
//import chess.domain.piece.Piece;
//import chess.domain.piece.Color;
//import chess.domain.position.Column;
//import chess.domain.position.Position;
//
//public final class Game {
//
//    private static final int VALID_KING_COUNT = 2;
//
//    private final Board board;
//
//    public Game(Board board) {
//        this.board = board;
//    }
//
//    public Game move(final Position sourcePosition, final Position targetPosition) {
//        if (!board.hasAvailablePath(sourcePosition, targetPosition)) {
//        }
//        return new Game(board.movePiece(sourcePosition, targetPosition));
//    }
//
//    public boolean containsEnemyPiece(final Position sourcePosition, final Color enemyColor) {
//        final Piece otherPiece = board.pieceAt(sourcePosition);
//        return otherPiece.isColor(enemyColor);
//    }
//
//    public boolean isGameOver() {
//        return board.kingCount() != VALID_KING_COUNT;
//    }
//
//    public int pawnCount(final Column column, final Color color) {
//        return board.pawnCount(column, color);
//    }
//}
