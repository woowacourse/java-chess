package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.generator.InitBoardGenerator;

public class Ready implements State {

    private final ChessBoard chessBoard;

    public Ready() {
        this.chessBoard = new ChessBoard(new InitBoardGenerator());
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
    public ChessBoard chessBoard() {
        return chessBoard;
    }
}
