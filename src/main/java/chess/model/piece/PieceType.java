package chess.model.piece;

import chess.model.Color;
import chess.model.piece.pawn.Pawn;
import chess.service.dto.PieceWithSquareDto;
import java.util.Arrays;
import java.util.function.Function;

public enum PieceType {
    KING(King.class, "k") {
        @Override
        public Piece getPiece(Color color) {
            return new King(color);
        }
    },

    QUEEN(Queen.class, "q") {
        @Override
        public Piece getPiece(Color color) {
            return new Queen(color);
        }
    },

    KNIGHT(Knight.class, "n") {
        @Override
        public Piece getPiece(Color color) {
            return new Knight(color);
        }
    },

    ROOK(Rook.class, "r") {
        @Override
        public Piece getPiece(Color color) {
            return new Rook(color);
        }
    },

    BISHOP(Bishop.class, "b") {
        @Override
        public Piece getPiece(Color color) {
            return new Bishop(color);
        }
    },

    PAWN(Pawn.class, "p") {
        @Override
        public Piece getPiece(Color color) {
            return Pawn.of(color);
        }
    },

    EMPTY(Empty.class, ".") {
        @Override
        public Piece getPiece(Color color) {
            return new Empty();
        }
    };

    private final Class<? extends Piece> pieceClass;
    private final String letter;

    PieceType(Class<? extends Piece> pieceClass, String letter) {
        this.pieceClass = pieceClass;
        this.letter = letter;
    }

    public static String getLetterByColor(Piece piece) {
        return convertByColor(piece, getLetter(piece));
    }

    private static String convertByColor(Piece piece, String letter) {
        if (piece.isBlack()) {
            return letter.toUpperCase();
        }
        return letter;
    }

    private static String getLetter(Piece piece) {
        return getStringByPieceLetter(piece, pieceType -> pieceType.letter);
    }

    private static String getStringByPieceLetter(Piece piece, Function<PieceType, String> letterMapping) {
        return Arrays.stream(values())
                .filter(pieceType -> pieceType.pieceClass.isAssignableFrom(piece.getClass()))
                .map(letterMapping)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("표현할 수 없는 기물이 파악됐습니다. " + piece.getClass()));
    }

    public static String getName(Piece piece) {
        return getStringByPieceLetter(piece, Enum::name);
    }

    public static Piece createPiece(PieceWithSquareDto pieceDto) {
        PieceType pieceType = valueOf(pieceDto.getType().toUpperCase());
        Color color = Color.valueOf(pieceDto.getColor().toUpperCase());
        return pieceType.getPiece(color);
    }

    public abstract Piece getPiece(Color color);
}
