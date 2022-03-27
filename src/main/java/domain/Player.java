package domain;

public enum Player {
    BLACK,
    WHITE;

    public static Player otherPlayer(Player player){
        if(player.equals(BLACK)){
            return WHITE;
        }
        return BLACK;
    }
}
