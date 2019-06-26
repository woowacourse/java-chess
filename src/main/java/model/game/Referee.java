package model.game;

public class Referee {
    public static boolean isKingAlive(final Game game) {
        return game.board().getPieces()
                            .anyMatch(p -> p.isKing() && p.team() == game.turn().team());
    }
}