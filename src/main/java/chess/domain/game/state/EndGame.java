package chess.domain.game.state;

import chess.domain.piece.Camp;
import chess.domain.position.ChessBoard;
import chess.domain.position.Position;

public class EndGame extends StartedGame {

    private static final String GAME_ALREADY_END = "이미 게임이 종료 되었습니다.";

    public EndGame(ChessBoard chessBoard, Camp turnCamp) {
        super(chessBoard, turnCamp);
    }

    @Override
    public ChessGame startGame() {
        throw new IllegalStateException(GAME_ALREADY_END);
    }

    @Override
    public ChessGame move(Position fromPosition, Position toPosition) {
        throw new IllegalStateException(GAME_ALREADY_END);
    }

    @Override
    public boolean isRunnableGame() {
        return false;
    }

    @Override
    public ChessGame endGame() {
        throw new IllegalStateException(GAME_ALREADY_END);
    }
}
