package chess.domain.piece;

import java.util.Arrays;

public enum PieceFactory {

    WHITE_BISHOP("bishop", Team.WHITE, new Bishop(Team.WHITE)),
    WHITE_KING("king", Team.WHITE, new King(Team.WHITE)),
    WHITE_KNIGHT("knight", Team.WHITE, new Knight(Team.WHITE)),
    WHITE_PAWN("pawn", Team.WHITE, new Pawn(Team.WHITE)),
    WHITE_QUEEN("queen", Team.WHITE, new Queen(Team.WHITE)),
    WHITE_ROOK("rook", Team.WHITE, new Rook(Team.WHITE)),
    BLACK_BISHOP("bishop", Team.BLACK, new Bishop(Team.BLACK)),
    BLACK_KING("king", Team.BLACK, new King(Team.BLACK)),
    BLACK_KNIGHT("knight", Team.BLACK, new Knight(Team.BLACK)),
    BLACK_PAWN("pawn", Team.BLACK, new Pawn(Team.BLACK)),
    BLACK_QUEEN("queen", Team.BLACK, new Queen(Team.BLACK)),
    BLACK_ROOK("rook", Team.BLACK, new Rook(Team.BLACK));

    private final String type;
    private final Team team;
    private final Piece piece;

    PieceFactory(String type, Team team, Piece piece) {
        this.type = type;
        this.team = team;
        this.piece = piece;
    }

    public static Piece of(String type, Team team) {
        return Arrays.stream(PieceFactory.values())
                .filter(piece -> piece.type.equals(type) && piece.team == team)
                .findAny()
                .map(pieceInfo -> pieceInfo.piece)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 타입의 Piece 입니다."));
    }
}
