package chess.domain.chessPiece.team;

public class WhiteTeam implements TeamStrategy {

    @Override
    public String pawnName() {
        return "P";
    }

    @Override
    public String kingName() {
        return "K";
    }

    @Override
    public String queenName() {
        return "Q";
    }

    @Override
    public String bishopName() {
        return "B";
    }

    @Override
    public String knightName() {
        return "N";
    }

    @Override
    public String rookName() {
        return "R";
    }
}
