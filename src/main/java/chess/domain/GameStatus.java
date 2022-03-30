package chess.domain;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import java.util.Objects;

public class GameStatus {

    private Progress progress;
    private Color currentTurnColor;

    public GameStatus() {
        this.progress = Progress.READY;
        this.currentTurnColor = Color.WHITE;
    }

    public void start() {
        progress.checkReady();
        progress = Progress.PLAYING;
    }

    public void end() {
        progress = Progress.END;
    }

    public void kingDie() {
        progress = Progress.KING_DIE;
    }

    public boolean canPlay() {
        return !progress.isEnd();
    }

    public void checkCanMove(ChessPiece chessPiece) {
        progress.checkPlaying();
        if (Objects.nonNull(chessPiece)) {
            checkTurn(chessPiece);
        }
    }

    private void checkTurn(final ChessPiece piece) {
        if (!piece.isSameColor(currentTurnColor)) {
            throw new IllegalArgumentException(currentTurnColor.name() + "의 차례입니다.");
        }
    }

    public void checkCanCalculateScore() {
        progress.checkPlaying();
    }

    public void changeTurn() {
        currentTurnColor = currentTurnColor.toOpposite();
    }

    public boolean isKingDie() {
        return progress.equals(Progress.KING_DIE);
    }

    private enum Progress {

        READY,
        PLAYING,
        END,
        KING_DIE,
        ;

        private void checkReady() {
            if (this.equals(PLAYING)) {
                throw new IllegalArgumentException("게임이 이미 시작되었습니다.");
            }
        }

        private void checkPlaying() {
            if (this.equals(READY)) {
                throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
            }
            if (isEnd()) {
                throw new IllegalArgumentException("게임이 이미 종료되었습니다.");
            }
        }

        private boolean isEnd() {
            return this.equals(END) || this.equals(KING_DIE);
        }
    }
}
