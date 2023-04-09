package chess.controller.command;

import chess.domain.chessgame.ChessGame;

import java.util.List;

public abstract class AbstractCommand implements Command {

    protected static void validateLength(final List<String> commandWithOptions, final int expectedLength) {
        if (commandWithOptions.size() != expectedLength) {
            throw new IllegalArgumentException("명령어를 바르게 입력해주십시오");
        }
    }

    protected void validatePlaying(final ChessGame chessGame) {
        validateGameNotEmpty(chessGame);
        if (chessGame.isGameOver()) {
            throw new IllegalArgumentException("게임이 종료되었습니다");
        }
    }

    protected void validateGameNotEmpty(final ChessGame chessGame) {
        if (chessGame == null) {
            throw new IllegalArgumentException("게임을 먼저 시작해주세요");
        }
    }

    protected void validateGameEmpty(final ChessGame chessGame) {
        if (chessGame != null) {
            System.out.println("플레이 중인 게임이 있습니다");
        }
    }

    @Override
    public boolean isGameEnd() {
        return false;
    }
}
