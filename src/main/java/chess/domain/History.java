package chess.domain;

import chess.domain.manager.ChessManager;

import java.util.Objects;

public class History {

    private final String moveCommand;
    private final String turnOwner;
    private final int turnNumber;
    private final boolean isPlaying;

    private History(String moveCommand, String turnOwner, int turnNumber, boolean isPlaying) {
        validateHistoryCapture(moveCommand, turnOwner);
        this.moveCommand = moveCommand;
        this.turnOwner = turnOwner;
        this.turnNumber = turnNumber;
        this.isPlaying = isPlaying;
    }

    public static History of(MoveCommand moveCommand, ChessManager chessManager) {
        return new History(moveCommand.moveCommand(), chessManager.turnOwner().name()
                , chessManager.turnNumber(), chessManager.isPlaying());
    }

    private void validateHistoryCapture(String moveCommand, String turnOwner) {
        validateNull(moveCommand, turnOwner);
        validateEmpty(moveCommand, turnOwner);
    }

    private void validateNull(String moveCommand, String turnOwner) {
        Objects.requireNonNull(moveCommand, "moveCommand 는 null일 수 없습니다.");
        Objects.requireNonNull(turnOwner, "turnOwner 는 null일 수 없습니다.");
    }

    private void validateEmpty(String moveCommand, String turnOwner) {
        if (moveCommand.isEmpty()) {
            throw new IllegalArgumentException("moveCommand 는 빈값일 수 없습니다.");
        }
        if (turnOwner.isEmpty()) {
            throw new IllegalArgumentException("turnOwner 는 빈값일 수 없습니다.");
        }
    }


    public String moveCommand() {
        return moveCommand;
    }

    public String turnOwner() {
        return turnOwner;
    }

    public int turnNumber() {
        return turnNumber;
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
