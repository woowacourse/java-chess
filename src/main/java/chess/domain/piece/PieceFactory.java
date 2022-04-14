package chess.domain.piece;

import chess.domain.piece.property.Color;
import java.util.Arrays;
import java.util.NoSuchElementException;

public enum PieceFactory {
    BISHOP_BLACK("B_BLACK", new Bishop(Color.BLACK)),
    BISHOP_WHITE("b_WHITE", new Bishop(Color.WHITE)),
    KING_BLACK("K_BLACK", new King(Color.BLACK)),
    KING_WHITE("k_WHITE", new King(Color.WHITE)),
    KNIGHT_BLACK("N_BLACK", new Knight(Color.BLACK)),
    KNIGHT_WHITE("n_WHITE", new Knight(Color.WHITE)),
    PAWN_BLACK("P_BLACK", new StartedPawn(Color.BLACK)),
    PAWN_WHITE("p_WHITE", new StartedPawn(Color.WHITE)),
    QUEEN_BLACK("Q_BLACK", new Queen(Color.BLACK)),
    QUEEN_WHITE("q_WHITE", new Queen(Color.WHITE)),
    ROOK_BLACK("R_BLACK", new Rook(Color.BLACK)),
    ROOK_WHITE("r_WHITE", new Rook(Color.WHITE))
    ;

    public static final String BLACK_PAWN_START_RANK = "7";
    public static final String WHITE_PAWN_START_RANK = "2";

    private String name;
    private Piece piece;

    PieceFactory(String name, Piece piece) {
        this.name = name;
        this.piece = piece;
    }

    public static Piece of(String position, String name) {
        Piece piece = Arrays.stream(PieceFactory.values())
                .filter(pieceFactory -> pieceFactory.getName().equals(name))
                .map(pieceFactory -> pieceFactory.getPiece())
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("해당하는 기물이 없습니다."));

        if (piece instanceof StartedPawn) {
            String file = position.substring(1, 2);
            if(piece.color() == Color.BLACK && !file.equals(BLACK_PAWN_START_RANK)) {
                return new MovedPawn(piece.color());
            }
            if(piece.color() == Color.WHITE && !file.equals(WHITE_PAWN_START_RANK)) {
                return new MovedPawn(piece.color());
            }
        }

        return piece;
    }

    public String getName() {
        return name;
    }

    public Piece getPiece() {
        return piece;
    }
}
