package domain.pieces;

import domain.pieces.exceptions.IsNotMatchPieceException;
import domain.team.Team;
import java.util.Arrays;

public enum PiecesType {
    KING("k") {
        @Override
        public Piece createPiece(String initial) {
            return new King(Team.getInitialTeam(initial));
        }
    },
    QUEEN("q") {
        @Override
        public Piece createPiece(String initial) {
            return new Queen(Team.getInitialTeam(initial));
        }
    },
    BISHOP("b") {
        @Override
        public Piece createPiece(String initial) {
            return new Bishop(Team.getInitialTeam(initial));
        }
    },
    KNIGHT("n") {
        @Override
        public Piece createPiece(String initial) {
            return new Knight(Team.getInitialTeam(initial));
        }
    },
    ROOK("r") {
        @Override
        public Piece createPiece(String initial) {
            return new Rook(Team.getInitialTeam(initial));
        }
    },
    PAWN("p") {
        @Override
        public Piece createPiece(String initial) {
            return new Pawn(Team.getInitialTeam(initial));
        }
    },
    EMPTY(".") {
        @Override
        public Piece createPiece(String initial) {
            return new Empty(Team.getInitialTeam(initial));
        }
    };

    private String initial;

    PiecesType(String initial) {
        this.initial = initial;
    }

    public abstract Piece createPiece(String initial);

    public static PiecesType findPiece(String initial) {
        return Arrays.stream(PiecesType.values())
            .filter(initialType -> initialType.initial.equalsIgnoreCase(initial))
            .findFirst()
            .orElseThrow(() -> new IsNotMatchPieceException("맞는 Piece가 없습니다."));
    }
}
