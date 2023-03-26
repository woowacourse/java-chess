package chessgame.domain.piece;

import chessgame.domain.chessgame.Camp;

public enum PieceType {

    KING(0) {
        @Override
        public Piece createPiece(Camp camp) {
            return new King(camp);
        }
    },
    QUEEN(9) {
        @Override
        public Piece createPiece(Camp camp) {
            return new Queen(camp);
        }
    },
    ROOK(5) {
        @Override
        public Piece createPiece(Camp camp) {
            return new Rook(camp);
        }
    },
    BISHOP(3) {
        @Override
        public Piece createPiece(Camp camp) {
            return new Bishop(camp);
        }
    },
    KNIGHT(2.5) {
        @Override
        public Piece createPiece(Camp camp) {
            return new Knight(camp);
        }
    },
    PAWN(1) {
        @Override
        public Piece createPiece(Camp camp) {
            if (camp.equals(Camp.WHITE)) {
                return new WhitePawn();
            }
            return new BlackPawn();
        }
    },
    EMPTY(0) {
        @Override
        public Piece createPiece(Camp camp) {
            return new Empty();
        }
    };

    private final double score;

    PieceType(double score) {
        this.score = score;
    }

    public boolean isEmpty() {
        return this.equals(EMPTY);
    }

    public double score() {
        return score;
    }

    abstract public Piece createPiece(Camp camp);
}
