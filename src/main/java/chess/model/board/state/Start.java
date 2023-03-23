package chess.model.board.state;

import chess.controller.GameCommand;
import chess.model.ChessGame;
import chess.model.Scores;
import chess.model.position.Position;

public class Start extends ProgressState {

    public Start(final ChessGame chessGame) {
        super(chessGame);
    }

    public static GameState from(final GameCommand gameCommand) {
        if (gameCommand.isMove()) {
            throw new IllegalArgumentException("시작하기 전에 move를 호출 할 수 없습니다.");
        }

        if (gameCommand.isEnd()) {
            return new End();
        }

        if (gameCommand.isStatus()) {
            return new Status(new ChessGame());
        }

        return new Playing(new ChessGame());
    }

    @Override
    public void execute(final Position source, final Position target) {
        // 게임이 시작하기 전에 실행 할 수 없음.
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public Scores calculateScores() {
        throw new UnsupportedOperationException("지원하지 않는 기능 입니다.");
    }
}
