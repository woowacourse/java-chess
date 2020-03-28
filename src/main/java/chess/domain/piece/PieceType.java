package chess.domain.piece;

import chess.domain.player.Team;

public enum PieceType {

    PAWN("\u2659", "\u265F", 1d),
    KNIGHT("\u2658", "\u265E", 2.5d),
    BISHOP("\u2657", "\u265D", 3d),
    ROOK("\u2656", "\u265C", 5d),
    QUEEN("\u2655", "\u265B", 9d),
    KING("\u2654", "\u265A", 0d);

    private String whiteFigure;
    private String blackFigure;
    private double point;

    PieceType(String whiteFigure, String blackFigure, double point) {
        this.whiteFigure = whiteFigure;
        this.blackFigure = blackFigure;
        this.point = point;
    }

    public String getFigure(Team team) {
        return team.isWhite() ? whiteFigure : blackFigure;
    }

    public double getPoint() {
        return point;
    }
}
