package chess.domain.piece;

import chess.domain.board.Position;

import java.util.Arrays;

public enum PieceType {
    EMPTY_PIECE(".") {
        @Override
        public Piece createPiece(Position position) {
            return new EmptyPiece(PieceColor.NONE, position);
        }
    },
    BLACK_BISHOP("B") {
        @Override
        public Piece createPiece(Position position) {
            return new Bishop(PieceColor.BLACK, position);
        }
    },
    BLACK_PAWN("P") {
        @Override
        public Piece createPiece(Position position) {
            return new BlackPawn(position);
        }
    },
    BLACK_KING("K") {
        @Override
        public Piece createPiece(Position position) {
            return new King(PieceColor.BLACK, position);
        }
    },
    BLACK_KNIGHT("N") {
        @Override
        public Piece createPiece(Position position) {
            return new Knight(PieceColor.BLACK, position);
        }
    },
    BLACK_QUEEN("Q") {
        @Override
        public Piece createPiece(Position position) {
            return new Queen(PieceColor.BLACK, position);
        }
    },
    BLACK_ROOK("R") {
        @Override
        public Piece createPiece(Position position) {
            return new Rook(PieceColor.BLACK, position);
        }
    },
    WHITE_BISHOP("b") {
        @Override
        public Piece createPiece(Position position) {
            return new Bishop(PieceColor.WHITE, position);
        }
    },
    WHITE_PAWN("p") {
        @Override
        public Piece createPiece(Position position) {
            return new WhitePawn(position);
        }
    },
    WHITE_KING("k") {
        @Override
        public Piece createPiece(Position position) {
            return new King(PieceColor.WHITE, position);
        }
    },
    WHITE_KNIGHT("n") {
        @Override
        public Piece createPiece(Position position) {
            return new Knight(PieceColor.WHITE, position);
        }
    },
    WHITE_QUEEN("q") {
        @Override
        public Piece createPiece(Position position) {
            return new Queen(PieceColor.WHITE, position);
        }
    },
    WHITE_ROOK("r") {
        @Override
        public Piece createPiece(Position position) {
            return new Rook(PieceColor.WHITE, position);
        }
    };

    private String name;

    PieceType(String name) {
        this.name = name;
    }

    public static PieceType of(String name) {
        return Arrays.stream(values())
                .filter(value -> value.name.equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public abstract Piece createPiece(Position position);
}
