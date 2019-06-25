package chess.persistence.dto;

public class ChessMoveDTO {
    String sourceX;
    String sourceY;
    String targetX;
    String targetY;

    public String getSourceX() {
        return sourceX;
    }

    public void setSourceX(String sourceX) {
        this.sourceX = sourceX;
    }

    public String getSourceY() {
        return sourceY;
    }

    public void setSourceY(String sourceY) {
        this.sourceY = sourceY;
    }

    public String getTargetX() {
        return targetX;
    }

    public void setTargetX(String targetX) {
        this.targetX = targetX;
    }

    public String getTargetY() {
        return targetY;
    }

    public void setTargetY(String targetY) {
        this.targetY = targetY;
    }
}
