package chess.domain.game;

import chess.domain.Position;
import chess.domain.board.Board;
import chess.domain.piece.Color;

public class RunningGame extends Game {

    public RunningGame(Board board) {
        super(board);
    }

    @Override
    public Game move(Position from, Position to) {
        Board movedBoard = board.movePiece(from, to);
        if (movedBoard.getScoreOf(Color.WHITE).getScore() == 0 || movedBoard.getScoreOf(Color.BLACK).getScore() == 0) {
            return new EndGame(movedBoard);
        }
        return new RunningGame(movedBoard);
    }

    @Override
    public boolean isEnd() {
        return false;
    }

}
