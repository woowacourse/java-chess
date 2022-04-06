package chess.domain.piece;

public class PieceFactory {

    public static Piece of(String teamInput, String nameInput) {
        Team team = Team.of(teamInput);
        Name name = Name.of(nameInput);

        if (name == Name.PAWN) {
            return new Pawn(team);
        }

        if (name == Name.QUEEN) {
            return new Queen(team);
        }

        if (name == Name.KING) {
            return new King(team);
        }

        if (name == Name.BISHOP) {
            return new Bishop(team);
        }

        if (name == Name.ROOK) {
            return new Rook(team);
        }

        return new EmptyPiece();
    }
}
