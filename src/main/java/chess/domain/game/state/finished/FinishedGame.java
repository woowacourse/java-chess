package chess.domain.game.state.finished;

import chess.domain.game.state.ChessGame;
import chess.domain.game.state.started.StartedGame;
import chess.domain.piece.Camp;
import chess.domain.position.ChessBoard;
import chess.domain.position.Position;

public abstract class FinishedGame extends StartedGame {

    private static final String GAME_ALREADY_END = "이미 게임이 종료 되었습니다.";

    protected FinishedGame(ChessBoard chessBoard, Camp turnCamp) {
        super(chessBoard, turnCamp);
    }

    @Override
    public ChessGame move(Position fromPosition, Position toPosition) {
        throw new IllegalStateException(GAME_ALREADY_END);
    }

    @Override
    public ChessGame endGame() {
        throw new IllegalStateException(GAME_ALREADY_END);
    }

    @Override
    public boolean isRunnableGame() {
        return false;
    }
}
