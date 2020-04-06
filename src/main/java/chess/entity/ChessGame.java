package chess.entity;

import chess.piece.Team;

import java.time.LocalDateTime;

public class ChessGame extends BaseEntity {
    private Team winner;
    private boolean active;

    public ChessGame(LocalDateTime createdTime, boolean active) {
        super(createdTime);
        this.winner = Team.NOTHING;
        this.active = active;
    }

    public ChessGame(boolean active) {
        super();
        this.active = active;
    }

    public ChessGame(Long id, ChessGame chessGame) {
        super(id, chessGame.getCreatedTime());
        this.winner = chessGame.winner;
        this.active = chessGame.active;
    }

    public ChessGame(Long id, LocalDateTime now, ChessGame entity) {
        super(id, now);
        this.winner = entity.winner;
        this.active = entity.active;
    }

    public Team getWinner() {
        return winner;
    }

    public boolean isActive() {
        return active;
    }

    public void endGame(Team winner) {
        if (!this.active) {
            throw new IllegalArgumentException("이미 종료된 게임입니다.");
        }
        if (winner == Team.NOTHING) {
            throw new IllegalArgumentException("무승부 상태로 게임을 끝낼 수 없습니다.");
        }
        this.winner = winner;
        this.active = false;
    }

}
