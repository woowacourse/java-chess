package chess.domain.piece;

import chess.domain.position.Position;

import java.util.Arrays;

public enum PieceCreator {
    FIRST_WHITE_PAWN(PieceType.FIRST_WHITE_PAWN, Turn.WHITE){
        @Override
        public Piece createPiece(final Position position) {
            return new Pawn(PieceType.FIRST_WHITE_PAWN, 'p', Turn.WHITE, position);
        }
    },
    FIRST_BLACK_PAWN(PieceType.FIRST_BLACK_PAWN, Turn.BLACK) {
        @Override
        public Piece createPiece(final Position position) {
            return new Pawn(PieceType.FIRST_BLACK_PAWN, 'P', Turn.BLACK, position);
        }
    },
    WHITE_PAWN(PieceType.WHITE_PAWN, Turn.WHITE) {
        @Override
        public Piece createPiece(final Position position) {
            return new Pawn(PieceType.WHITE_PAWN, 'p', Turn.WHITE, position);
        }
    },
    BLACK_PAWN(PieceType.BLACK_PAWN, Turn.BLACK) {
        @Override
        public Piece createPiece(final Position position) {
            return new Pawn(PieceType.BLACK_PAWN, 'P', Turn.BLACK, position);
        }
    },
    WHITE_KNIGHT(PieceType.KNIGHT, Turn.WHITE) {
        @Override
        public Piece createPiece(final Position position) {
            return new Knight(PieceType.KNIGHT, 'n', Turn.WHITE, position);
        }
    },
    BLACK_KNIGHT(PieceType.KNIGHT, Turn.BLACK) {
        @Override
        public Piece createPiece(final Position position) {
            return new Knight(PieceType.KNIGHT, 'N', Turn.BLACK, position);
        }
    },
    WHITE_KING(PieceType.KING, Turn.WHITE) {
        @Override
        public Piece createPiece(final Position position) {
            return new King(PieceType.KING, 'k', Turn.WHITE, position);
        }
    },
    BLACK_KING(PieceType.KING, Turn.BLACK) {
        @Override
        public Piece createPiece(final Position position) {
            return new King(PieceType.KING, 'K', Turn.BLACK, position);
        }
    },
    WHITE_BISHOP(PieceType.BISHOP, Turn.WHITE) {
        @Override
        public Piece createPiece(final Position position) {
            return new Bishop(PieceType.BISHOP, 'b', Turn.WHITE, position);
        }
    },
    BLACK_BISHOP(PieceType.BISHOP, Turn.BLACK) {
        @Override
        public Piece createPiece(final Position position) {
            return new Bishop(PieceType.BISHOP, 'B', Turn.BLACK, position);
        }
    },
    WHITE_ROOK(PieceType.ROOK, Turn.WHITE) {
        @Override
        public Piece createPiece(final Position position) {
            return new Rook(PieceType.ROOK, 'r', Turn.WHITE, position);
        }
    },
    BLACK_ROOK(PieceType.ROOK, Turn.BLACK) {
        @Override
        public Piece createPiece(final Position position) {
            return new Rook(PieceType.ROOK, 'R', Turn.BLACK, position);
        }
    },
    WHITE_QUEEN(PieceType.QUEEN, Turn.WHITE) {
        @Override
        public Piece createPiece(final Position position) {
            return new Queen(PieceType.QUEEN, 'q', Turn.WHITE, position);
        }
    },
    BLACK_QUEEN(PieceType.QUEEN, Turn.BLACK) {
        @Override
        public Piece createPiece(final Position position) {
            return new Queen(PieceType.QUEEN, 'Q', Turn.BLACK, position);
        }
    },
    BLANK(PieceType.BLANK, Turn.BLANK) {
        @Override
        public Piece createPiece(final Position position) {
            return new Blank(PieceType.BLANK, '.', Turn.BLANK, position);
        }
    };

    private final PieceType pieceType;
    private final Turn turn;

    PieceCreator(PieceType pieceType, Turn turn) {
        this.pieceType = pieceType;
        this.turn = turn;
    }

    public static Piece of(String pieceInformation, Position position) {
        String[] parses = pieceInformation.split("-");
        PieceType pieceType;
        Turn turn;
        if (parses[0].equals("blank")) {
            pieceType = PieceType.of(parses[1]);
            turn = Turn.BLANK;
        } else {
            pieceType = PieceType.of(parses[2]);
            turn = Turn.of(parses[1]);
        }

        return Arrays.stream(values())
                .filter(value -> value.pieceType == pieceType && value.turn == turn)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("피스를 생성할 수 없습니다."))
                .createPiece(position);
    }

    public abstract Piece createPiece(Position position);
}
