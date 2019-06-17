package chess.domain;

import java.util.Objects;

public class Position {
    private ChessTeam team;
    private ChessPiece chessPiece;

    public Position(ChessTeam team, ChessPiece chessPiece) {
        this.team = team;
        this.chessPiece = chessPiece;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return team == position.team &&
                Objects.equals(chessPiece, position.chessPiece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, chessPiece);
    }

    @Override
    public String toString() {
        return chessPiece.toString();
    }
}
