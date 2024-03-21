package chess.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {
    private final Map<ChessPosition, Piece> board;

    public ChessBoard(Map<ChessPosition, Piece> board) {
        this.board = new HashMap<>(board);
    }

    public void move(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Piece sourcePiece = board.get(sourcePosition);
        if (sourcePiece == null) {
            throw new IllegalArgumentException("Source에 기물이 존재하지 않습니다.");
        }

        Piece targetPiece = board.get(targetPosition);
        List<ChessPosition> path = sourcePiece.findPath(sourcePosition, targetPosition, targetPiece);
        if (path.isEmpty()) {
            throw new IllegalArgumentException("경로가 존재하지 않습니다.");
        }
        for (int i = 0; i < path.size() - 1; i++) {
            ChessPosition chessPosition = path.get(i);
            Piece found = board.get(chessPosition);
            if (found != null) {
                throw new IllegalArgumentException("이동 경로에 기물이 존재하여 움직일 수 없습니다.");
            }
        }
        board.remove(sourcePosition);
        board.put(targetPosition, sourcePiece);
    }

    public Map<ChessPosition, Piece> getBoard() {
        return board;
    }
}
