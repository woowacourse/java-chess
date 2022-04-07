package chess.domain;

import chess.domain.board.Board;
import chess.domain.piece.Position;
import chess.domain.state.GameState;
import java.util.Optional;

public class ChessGame {
    private Long id;
    private GameState state;
    private Participant participant;

    public ChessGame(GameState state) {
        this.state = state;
    }

    public ChessGame(GameState state, Participant participant) {
        this.state = state;
        this.participant = participant;
    }

    public ChessGame(Long id, GameState state, Participant participant) {
        this.id = id;
        this.state = state;
        this.participant = participant;
    }

    public GameState getState() {
        return state;
    }

    public Board getBoard() {
        return state.getBoard();
    }

    public String getTurn() {
        if (state.isBlackTurn()) {
            return Team.BLACK.name();
        }
        return Team.WHITE.name();
    }

    public void terminate() {
        state = state.terminate();
    }

    public void move(String position1, String position2) {
        state = state.move(new Position(position1), new Position(position2));
    }

    public boolean isEnd() {
        return state.isEnd();
    }

    public Long getId() {
        return id;
    }

    public String getWinnerName() {
        return state.findWinner().getName();
    }

    public double getWhiteScore() {
        return state.calculateWhiteScore();
    }

    public double getBlackScore() {
        return state.calculateBlackScore();
    }

    public Long getWinnerId() {
        if (isTerminated()) {
            throw new IllegalStateException("강제종료시엔 승자의 ID를 얻을 수 없습니다.");
        }
        if (state.isBlackWin()) {
            return participant.getBlackId();
        }
        return participant.getWhiteId();
    }

    public boolean isTerminated() {
        return state.isTerminated();
    }

    public boolean isBlackTurn() {
        return state.isBlackTurn();
    }

    public Participant getParticipant() {
        return participant;
    }

    public String getStateString() {
        return state.getType()
                .getName();
    }

    public Long getWhiteId() {
        return participant.getWhiteId();
    }

    public Long getBlackId() {
        return participant.getBlackId();
    }
}
