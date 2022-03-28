package chess.command;

import chess.domain.ChessGame;
import chess.domain.board.Position;
import chess.view.OutputView;

public interface Command {

    static Command createStart() {
        return new Start();
    }

    static Command createMove(Position sourcePosition, Position targetPosition) {
        return new Move(sourcePosition, targetPosition);
    }

    static Command createEnd() {
        return new End();
    }

    static Command createStatus() {
        return new Status();
    }

    void execute(ChessGame chessGame, OutputView outputView);

    boolean isStart();

    boolean isMove();

    boolean isEnd();

    boolean isStatus();
}
