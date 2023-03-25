package dao;

import chessgame.domain.Team;
import chessgame.domain.piece.*;

import java.util.Arrays;


public enum PieceConveter {
    WHITE_KING("k", "WHITE", King.from(Team.WHITE)),
    WHITE_BISHOP("b", "WHITE", Bishop.from(Team.WHITE)),
    WHITE_KNIGHT("n", "WHITE", Knight.from(Team.WHITE)),
    WHITE_PAWN("p", "WHITE", Pawn.from(Team.WHITE)),
    WHITE_QUEEN("q", "WHITE", Queen.from(Team.WHITE)),
    WHITE_ROOK("r", "WHITE", Rook.from(Team.WHITE)),
    BLACK_KING("k", "BLACK", King.from(Team.BLACK)),
    BLACK_BISHOP("b", "BLACK", Bishop.from(Team.BLACK)),
    BLACK_KNIGHT("n", "BLACK", Knight.from(Team.BLACK)),
    BLACK_PAWN("p", "BLACK", Pawn.from(Team.BLACK)),
    BLACK_QUEEN("q", "BLACK", Queen.from(Team.BLACK)),
    BLACK_ROOK("r", "BLACK", Rook.from(Team.BLACK));

    private final String name;
    private final String team;
    private final Piece piece;

    PieceConveter(String name, String team, Piece piece) {
        this.name = name;
        this.team = team;
        this.piece = piece;
    }

    public static Piece getPiece(String name, String team) {
        return Arrays.stream(values())
                .filter(piece -> piece.name.equals(name) && piece.team.equals(team))
                .findAny()
                .get().piece;
    }

}
