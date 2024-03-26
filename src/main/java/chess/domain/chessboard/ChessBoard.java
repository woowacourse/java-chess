package chess.domain.chessboard;

import chess.domain.chesspiece.Empty;
import chess.domain.chesspiece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.Map;

public class ChessBoard {

    private final Map<Position, Piece> chessBoard;

    private ChessBoard(Map<Position, Piece> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public static ChessBoard initializeChessBoard() {
        return new ChessBoard(ChessBoardGenerator.initializeBoard());
    }

    public void move(Position source, Position target) {
        Piece piece = chessBoard.get(source);
        Piece targetPiece = chessBoard.get(target);

        piece.getRoute(source, target)
                .forEach(this::checkObstacle);

        checkTeam(piece, targetPiece);

        if (piece.isPawn()) {
            checkPawnMoving(source, target);
        }

        chessBoard.put(source, new Empty());
        chessBoard.put(target, piece);
    }

    private void checkPawnMoving(Position source, Position target) {
        if (Direction.isUpDown(source, target)) {
            checkObstacle(target);
            return;
        }
        checkIsEmpty(target);
    }

    private boolean isEmpty(Position position) {
        return chessBoard.get(position)
                .isEmpty();
    }

    private boolean isTeam(Piece piece, Position position) {
        return chessBoard.get(position)
                .isTeam(piece);
    }

    private void checkObstacle(Position position) {
        if (isEmpty(position)) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    private void checkIsEmpty(Position target) {
        Piece targetPiece = chessBoard.get(target);
        if (targetPiece.isEmpty()) {
            throw new IllegalArgumentException("폰은 공격할 때만 대각선으로 이동할 수 있습니다.");
        }
    }

    private void checkTeam(Piece piece, Piece targetPiece) {
        if (piece.isTeam(targetPiece)) {
            throw new IllegalArgumentException("같은 팀이 있는 곳으로는 이동할 수 없습니다.");
        }
    }

    public Map<Position, Piece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }
}
