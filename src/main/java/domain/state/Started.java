package domain.state;

import domain.Board;
import domain.piece.objects.Piece;
import domain.piece.position.Position;
import domain.score.Score;
import domain.score.ScoreMachine;

import java.util.HashMap;
import java.util.Map;

public abstract class Started implements State {
    protected Board board;
    protected boolean turn = false;

    public Started(Map<Position, Piece> pieces) {
        this.board = new Board(pieces);
    }

    public boolean isRunning() {
        return true;
    }

    public Map<Boolean, Score> pieceScore() {
        Map<Boolean, Score> result = new HashMap<>();
        result.put(true, ScoreMachine.blackPiecesScore(board));
        result.put(false, ScoreMachine.whitePiecesScore(board));
        return result;
    }

    @Override
    public Board getBoard() {
        return board;
    }
}
