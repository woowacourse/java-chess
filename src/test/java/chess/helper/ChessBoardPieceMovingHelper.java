package chess.helper;

import chess.model.board.ChessBoard;
import chess.model.piece.Piece;
import chess.model.position.Position;
import java.lang.reflect.Field;
import java.util.Map;

public final class ChessBoardPieceMovingHelper {

    private ChessBoardPieceMovingHelper() {
    }

    @SuppressWarnings("unchecked") // 테스트 용도 캐스팅 경고 억제
    public static void move(final ChessBoard chessBoard, final Position source, final Position target) {
        try {
            final Field board = ChessBoard.class.getDeclaredField("board");
            board.setAccessible(true);

            final Map<Position, Piece> squares = (Map<Position, Piece>) board.get(chessBoard);
            final Piece sourcePiece = squares.get(source);

            squares.put(target, sourcePiece.pick());
            squares.put(source, Piece.EMPTY);

            board.set(chessBoard, squares);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
