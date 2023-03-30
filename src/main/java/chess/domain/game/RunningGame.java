package chess.domain.game;

import chess.domain.Position;
import chess.domain.board.Board;
import chess.domain.board.Score;

public class RunningGame extends Game {

    public RunningGame(Board board) {
        super(board);
    }

    @Override
    public Game move(Position from, Position to) {
        Board movedBoard = board.movePiece(from, to);
        if (isZeroValue(movedBoard.whiteScore()) || isZeroValue(movedBoard.blackScore())) {
            return new EndGame(movedBoard);
        }
        return new RunningGame(movedBoard);
    }

    private boolean isZeroValue(Score score) {
        return score.getScore() == 0;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

}
