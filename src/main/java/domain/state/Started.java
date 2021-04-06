package domain.state;

import domain.Board;
import domain.piece.objects.Piece;
import domain.piece.position.Position;
import domain.score.Score;
import domain.score.ScoreMachine;

import java.util.Map;

public abstract class Started implements State {
    protected Board board;
    protected boolean turn;

    public Started(Map<Position, Piece> pieces) {
        this.board = new Board(pieces);
    }

    public Started(Map<Position, Piece> pieces, boolean turn) {
        this(pieces);
        this.turn = turn;
    }

    @Override
    public Score blackScore() {
        return ScoreMachine.blackPiecesScore(board);
    }

    @Override
    public Score whiteScore() {
        return ScoreMachine.whitePiecesScore(board);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public boolean getTurn() {
        return turn;
    }
}
