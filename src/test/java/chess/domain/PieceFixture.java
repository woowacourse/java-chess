package chess.domain;

import chess.domain.piece.*;

public class PieceFixture {

    private static final Team whiteTeam = Team.WHITE;

    public static Rook whiteRook = new Rook(whiteTeam, "r");
    public static Knight whiteKnight = new Knight(whiteTeam, "n");
    public static Bishop whiteBishop = new Bishop(whiteTeam, "b");
    public static Queen whiteQueen = new Queen(whiteTeam, "q");
    public static King whiteKing = new King(whiteTeam, "k");

}
