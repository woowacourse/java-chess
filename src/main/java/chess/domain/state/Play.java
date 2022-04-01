package chess.domain.state;

import chess.domain.Turn;
import chess.domain.board.Chessboard;
import chess.domain.piece.Color;
import chess.domain.position.Position;

public class Play implements State {

    private final Chessboard chessboard;
    private final Turn turn;

    public Play(Turn turn) {
        this.turn = turn;
        this.chessboard = Chessboard.create();
    }

    public Play(Turn turn, Chessboard chessboard) {
        this.turn = turn;
        this.chessboard = chessboard;
    }

    @Override
    public State start() {
        throw new UnsupportedOperationException();
    }

    @Override
    public State move(Position source, Position target) {
        chessboard.movePiece(source, target, turn);
        if (chessboard.isKing(target)) {
            return new Finish(chessboard);
        }
        turn.nextTurn();
        return new Play(turn, chessboard);
    }

    @Override
    public State end() {
        return new Finish(chessboard);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double computeScore(Color color) {
        return chessboard.computeScore(color);
    }

    @Override
    public Chessboard getChessboard() {
        return chessboard;
    }
}
