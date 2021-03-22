package domain;

import domain.piece.Piece;
import domain.piece.Position;

import java.util.HashMap;
import java.util.Map;

public class ChessGame {
    private boolean running;
    private Board board;

    public ChessGame(Map<Position, Piece> pieces) {
        this.running = true;
        board = new Board(pieces);
    }

    public void move(Position start, Position end, boolean turn) {
        if (movablePiece(start, end, turn)) {
            Piece endPiece = board.getPiece(end);
            board.move(start, end);
            isKingDead(endPiece);
        }
    }

    private boolean movablePiece(Position start, Position end, boolean turn) {
        checkSamePosition(start, end);
        return board.canMovable(start, turn);
    }

    private void isKingDead(Piece endPiece) {
        if (endPiece.isKingDead()) {
            running = false;
        }
    }

    private void checkSamePosition(Position start, Position end) {
        if (start.equals(end)) {
            throw new IllegalArgumentException("현재 위치와 이동 위치가 같습니다.");
        }
    }

    public boolean isRunning() {
        return running;
    }

    public Map<Boolean, Score> piecesScore() {
        Map<Boolean, Score> result = new HashMap<>();
        result.put(true, ScoreMachine.blackPiecesScore(board));
        result.put(false, ScoreMachine.whitePiecesScore(board));
        return result;
    }

    public Board getBoard() {
        return board;
    }
}
