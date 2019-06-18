package chess.domain;

public enum ChessTeam {
    BLACK(-1),
    EMPTY(0),
    WHITE(1);

    private int color;

    private ChessTeam(int color) {
        this.color = color;
    }

    public boolean match(ChessTeam chessTeam) {
        return this.color == chessTeam.color;
    }
}
