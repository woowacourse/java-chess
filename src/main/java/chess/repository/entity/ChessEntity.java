package chess.repository.entity;

import chess.piece.Team;

import java.time.LocalDateTime;

public class ChessEntity extends BaseEntity {
    private Team winner;
    private boolean active;

    public ChessEntity(LocalDateTime createdTime, boolean active) {
        super(createdTime);
        this.winner = Team.NOTHING;
        this.active = active;
    }

    public ChessEntity(boolean active) {
        super();
        this.active = active;
    }

    public ChessEntity(Long id, ChessEntity chessEntity) {
        super(id, chessEntity.getCreatedTime());
        this.winner = chessEntity.winner;
        this.active = chessEntity.active;
    }

    public ChessEntity(Long id, LocalDateTime now, ChessEntity entity) {
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
        if (winner == Team.NOTHING) {
            throw new IllegalArgumentException("무승부 상태로 게임을 끝낼 수 없습니다.");
        }
        this.winner = winner;
        this.active = false;
    }

}
