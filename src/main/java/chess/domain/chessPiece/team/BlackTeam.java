package chess.domain.chessPiece.team;

public class BlackTeam implements TeamStrategy {
    @Override
    public String pawnName() {
        return "p";
    }

    @Override
    public String kingName() {
        return "k";
    }

    @Override
    public String queenName() {
        return "q";
    }

    @Override
    public String bishopName() {
        return "b";
    }

    @Override
    public String knightName() {
        return "n";
    }

    @Override
    public String rookName() {
        return "r";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BlackTeam;
    }
}
