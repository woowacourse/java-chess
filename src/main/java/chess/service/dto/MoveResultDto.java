package chess.service.dto;

public class MoveResultDto {
    public boolean isKingDead;
    public boolean success;

    public MoveResultDto(boolean isKingDead, boolean success) {
        this.isKingDead = isKingDead;
        this.success = success;
    }

    public boolean isKingDead() {
        return isKingDead;
    }

    public void setKingDead(boolean kingDead) {
        isKingDead = kingDead;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
