package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Result;
import chess.domain.generator.InitBoardGenerator;

public class Ready extends Started {

    public Ready() {
        super(new ChessBoard(new InitBoardGenerator()));
    }

    @Override
    public State start() {
        return new WhiteTurn(chessBoard);
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException();
    }

    @Override
    public State move(String source, String target) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Result winner() {
        throw new UnsupportedOperationException();
    }
}
