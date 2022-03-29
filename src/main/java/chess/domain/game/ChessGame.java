package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.boardstrategy.BoardStrategy;
import chess.domain.piece.attribute.Team;

public final class ChessGame {
    private static final String NO_TURN_MESSAGE = "현재 진영에 속해있지 않는 위치입니다.";

    private final Board board;
    private GameState gameState;
    private Team turn = Team.WHITE;

    public ChessGame(Board board) {
        this.board = board;
        this.gameState = GameState.READY;
    }

    public ChessGame(BoardStrategy boardStrategy) {
        this.board = new Board(boardStrategy.create());
        this.gameState = GameState.READY;
    }

    public void play(Position from, Position to) {
        validateTurn(from);
        boolean isCheckmate = isCheckmate(to);
        board.move(from, to);
        if (isCheckmate) {
            gameState = GameState.END;
            return;
        }
        turn = turn.changeTeam();
    }

    private void validateTurn(Position from) {
        if (!isTurn(from)) {
            throw new IllegalArgumentException(NO_TURN_MESSAGE);
        }
    }

    private boolean isTurn(Position position) {
        return board.isSameColor(position, turn);
    }

    public Status getStatus() {
        return new Status(board.getTotalStatus(), turn);
    }

    public boolean isCheckmate(Position to) {
        return board.isCheckmate(to);
    }

    public void start() {
        gameState = GameState.PLAYING;
    }

    public boolean isPlaying() {
        return gameState == GameState.PLAYING;
    }

    public Board getBoard() {
        return board;
    }
}
