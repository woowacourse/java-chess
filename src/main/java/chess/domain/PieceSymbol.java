package chess.domain;

import chess.domain.pieces.*;
import chess.domain.position.Position;

public enum PieceSymbol {
    p("p") {
        @Override
        public Piece getPiece(Position position) {
            return new Pawn(position, Team.WHITE);
        }
    },
    r("r") {
        @Override
        public Piece getPiece(Position position) {
            return new Rook(position, Team.WHITE);
        }
    },
    n("n") {
        @Override
        public Piece getPiece(Position position) {
            return new Knight(position, Team.WHITE);
        }
    },
    k("k") {
        @Override
        public Piece getPiece(Position position) {
            return new King(position, Team.WHITE);
        }
    },
    q("q") {
        @Override
        public Piece getPiece(Position position) {
            return new Queen(position, Team.WHITE);
        }
    },
    b("b") {
        @Override
        public Piece getPiece(Position position) {
            return new Bishop(position, Team.WHITE);
        }
    },
    P("P") {
        @Override
        public Piece getPiece(Position position) {
            return new Pawn(position, Team.BLACK);
        }
    },
    R("R") {
        @Override
        public Piece getPiece(Position position) {
            return new Rook(position, Team.BLACK);
        }
    },
    N("N") {
        @Override
        public Piece getPiece(Position position) {
            return new Knight(position, Team.BLACK);
        }
    },
    K("K") {
        @Override
        public Piece getPiece(Position position) {
            return new King(position, Team.BLACK);
        }
    },
    Q("Q") {
        @Override
        public Piece getPiece(Position position) {
            return new Queen(position, Team.BLACK);
        }
    },
    B("B") {
        @Override
        public Piece getPiece(Position position) {
            return new Bishop(position, Team.BLACK);
        }
    },
    blank(".") {
        @Override
        public Piece getPiece(Position position) {
            return new Blank(position, Team.BLANK);
        }
    };

    private final String symbol;

    PieceSymbol(String symbol) {
        this.symbol = symbol;
    }

    private String getSymbol() {
        return symbol;
    }

    public static PieceSymbol getPieceSymbol(String symbol) {
        for (PieceSymbol pieceSymbol : PieceSymbol.values()) {
            if (pieceSymbol.getSymbol().equals(symbol)) {
                return pieceSymbol;
            }
        }
        throw new IllegalArgumentException("잘못된 심볼입니다.");
    }

    public abstract Piece getPiece(Position position);
}
