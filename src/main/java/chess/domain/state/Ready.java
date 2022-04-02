package chess.domain.state;

import chess.domain.ChessBoard;
import chess.domain.Result;
import chess.domain.generator.InitBoardGenerator;

public class Ready extends Started {

    public static final String ERROR_MESSAGE_GAME_NOT_START = "게임이 시작되지 않았습니다.";

    public Ready() {
        super(new ChessBoard(new InitBoardGenerator()));
    }

    @Override
    public State start() {
        return new WhiteTurn(chessBoard);
    }

    @Override
    public State end() {
        throw new UnsupportedOperationException(ERROR_MESSAGE_GAME_NOT_START);
    }

    @Override
    public State move(String source, String target) {
        throw new UnsupportedOperationException(ERROR_MESSAGE_GAME_NOT_START);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public Result winner() {
        throw new UnsupportedOperationException(ERROR_MESSAGE_GAME_NOT_START);
    }
}
