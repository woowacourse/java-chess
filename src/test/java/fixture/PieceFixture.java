package fixture;

import domain.*;

public class PieceFixture {

    public static Rook blackRook() {
        return new Rook(Side.BLACK);
    }

    public static Rook whiteRook() {
        return new Rook(Side.WHITE);
    }

    public static Bishop blackBishop() {
        return new Bishop(Side.BLACK);
    }

    public static Bishop whiteBishop() {
        return new Bishop(Side.WHITE);
    }

    public static Knight blackKnight() {
        return new Knight(Side.BLACK);
    }

    public static Knight whiteKnight() {
        return new Knight(Side.WHITE);
    }

    public static Queen blackQueen() {
        return new Queen(Side.BLACK);
    }

    public static Queen whiteQueen() {
        return new Queen(Side.WHITE);
    }

    public static King blackKing() {
        return new King(Side.BLACK);
    }

    public static King whiteKing() {
        return new King(Side.WHITE);
    }

    public static Pawn blackPawn() {
        return new Pawn(Side.BLACK);
    }

    public static Pawn whitePawn() {
        return new Pawn(Side.WHITE);
    }
}
