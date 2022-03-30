package chess.domain;

import chess.domain.chesspiece.ChessPiece;
import chess.domain.chesspiece.Color;
import java.util.Optional;

public class GameStatus {

    private Progress progress;
    private Color currentTurnColor;

    public GameStatus() {
        this.progress = Progress.READY;
        this.currentTurnColor = Color.WHITE;
    }

    public void start() {
        if (progress.equals(Progress.PLAYING)) {
            throw new IllegalArgumentException("게임이 이미 시작되었습니다.");
        }
        progress = Progress.PLAYING;
    }

    public boolean canPlay() {
        return !progress.equals(Progress.END) && !progress.equals(Progress.KING_DIE);
    }

    public void checkCanMove(Optional<ChessPiece> possibleChessPiece) {
        if (progress.equals(Progress.READY)) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
        if (progress.equals(Progress.END) || progress.equals(Progress.KING_DIE)) {
            throw new IllegalArgumentException("게임이 이미 종료되었습니다.");
        }
        possibleChessPiece.ifPresent(piece -> {
            if (!piece.isSameColor(currentTurnColor)) {
                throw new IllegalArgumentException(currentTurnColor.name() + "의 차례입니다.");
            }
        });
    }

    public void end() {
        progress = Progress.END;
    }

    public void kingDie() {
        progress = Progress.KING_DIE;
    }

    public void changeTurn() {
        currentTurnColor = currentTurnColor.toOpposite();
    }

    public boolean isKingDie() {
        return progress.equals(Progress.KING_DIE);
    }

    public void checkCanCalculateScore() {
        if (progress.equals(Progress.READY)) {
            throw new IllegalArgumentException("게임이 시작되지 않았습니다.");
        }
    }

    private enum Progress {

        READY,
        PLAYING,
        END,
        KING_DIE,
        ;
    }
}
