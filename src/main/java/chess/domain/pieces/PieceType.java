package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.position.Position;

import java.util.Arrays;

public enum PieceType {
    whitePawn("p") {
        @Override
        public Piece createPiece(Position position) {
            return new Pawn(position, Team.WHITE);
        }
    },
    whiteRook("r") {
        @Override
        public Piece createPiece(Position position) {
            return new Rook(position, Team.WHITE);
        }
    },
    whiteKnight("n") {
        @Override
        public Piece createPiece(Position position) {
            return new Knight(position, Team.WHITE);
        }
    },
    whiteBishop("b") {
        @Override
        public Piece createPiece(Position position) {
            return new Bishop(position, Team.WHITE);
        }
    },
    whiteKing("k") {
        @Override
        public Piece createPiece(Position position) {
            return new King(position, Team.WHITE);
        }
    },
    whiteQueen("q") {
        @Override
        public Piece createPiece(Position position) {
            return new Queen(position, Team.WHITE);
        }
    },
    blackPawn("P") {
        @Override
        public Piece createPiece(Position position) {
            return new Pawn(position, Team.BLACK);
        }
    },
    blackRook("R") {
        @Override
        public Piece createPiece(Position position) {
            return new Rook(position, Team.BLACK);
        }
    },
    blackKnight("N") {
        @Override
        public Piece createPiece(Position position) {
            return new Knight(position, Team.BLACK);
        }
    },
    blackBishop("B") {
        @Override
        public Piece createPiece(Position position) {
            return new Bishop(position, Team.BLACK);
        }
    },
    blackKing("K") {
        @Override
        public Piece createPiece(Position position) {
            return new King(position, Team.BLACK);
        }
    },
    blackQueen("Q") {
        @Override
        public Piece createPiece(Position position) {
            return new Queen(position, Team.BLACK);
        }
    },
    blank(".") {
        @Override
        public Piece createPiece(Position position) {
            return new Blank(position, Team.BLANK);
        }
    };

    private String symbol;

    PieceType(String symbol) {
        this.symbol = symbol;
    }

    public static PieceType getPieceType(String symbol) {
        return Arrays.stream(values())
                .filter(value -> value.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(IllegalAccessError::new);
    }

    public abstract Piece createPiece(Position position);
}
