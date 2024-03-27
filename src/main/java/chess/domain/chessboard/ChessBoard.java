package chess.domain.chessboard;

import chess.domain.chesspiece.Empty;
import chess.domain.chesspiece.Piece;
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

        checkTargetIsTeam(piece, targetPiece);
        piece.findRoute(source, target, targetPiece)
                .forEach(this::checkObstacle);

        chessBoard.put(source, new Empty());
        chessBoard.put(target, piece);
    }

    private void checkObstacle(Position position) {
        if (!chessBoard.get(position)
                .isEmpty()) {
            throw new IllegalArgumentException("이동할 수 없습니다.");
        }
    }

    private void checkTargetIsTeam(Piece source, Piece target) {
        if(source.isTeam(target)) {
            throw new IllegalStateException("같은 팀이 있는 곳으로는 이동할 수 없습니다.");
        }
    }

    public Map<Position, Piece> getChessBoard() {
        return Collections.unmodifiableMap(chessBoard);
    }
}
