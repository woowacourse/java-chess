package chess;

public enum Team {
    BLACK(-1),
    WHITE(1),
    NONE(0),
    ;

    private final int forwardDirection;

    Team(int forwardDirection) {
        this.forwardDirection = forwardDirection;
    }

    public static Team from(String name){
        if("black".equals(name)){
            return Team.BLACK;
        }
        if("white".equals(name)){
            return Team.WHITE;
        }
        return Team.NONE;
    }
    public int getForwardDirection() {
        return this.forwardDirection;
    }
}
