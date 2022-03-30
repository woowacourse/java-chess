package domain;

public enum Player {
    BLACK,
    WHITE,
    NULL;

    public static Player otherPlayer(Player player){
        if(player.equals(NULL)){
            return NULL;
        }
        if(player.equals(BLACK)){
            return WHITE;
        }
        return BLACK;
    }
}
