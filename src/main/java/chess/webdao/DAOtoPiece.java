package chess.webdao;

import chess.domain.piece.*;

import java.sql.SQLException;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Stream;

public enum DAOtoPiece {
    WHITE_PAWN("white", "Pawn", (isFirstMove) -> new Pawn(isFirstMove, 1)),
    BLACK_PAWN("black", "Pawn", (isFirstMove) -> new Pawn(isFirstMove, -1)),
    WHITE_KNIGHT("white", "Knight", Knight::new),
    BLACK_KNIGHT("black", "Knight", Knight::new),
    WHITE_BISHOP("white", "Bishop", Bishop::new),
    BLACK_BISHOP("black", "Bishop", Bishop::new),
    WHITE_ROOK("white", "Rook", Rook::new),
    BLACK_ROOK("black", "Rook", Rook::new),
    WHITE_QUEEN("white", "Queen", Queen::new),
    BLACK_QUEEN("black", "Queen", Queen::new),
    WHITE_KING("white", "King", King::new),
    BLACK_KING("black", "King", King::new);

    private final String team;
    private final String pieceAsString;
    private final Function<Boolean, Piece> pieceFunction;

    DAOtoPiece(final String team, final String pieceAsString, final Function<Boolean, Piece> pieceFunction) {
        this.team = team;
        this.pieceAsString = pieceAsString;
        this.pieceFunction = pieceFunction;
    }

    public static Piece generatePiece(final String team, final String pieceAsString, final boolean isFirstMove)
            throws SQLException {
        final DAOtoPiece request = Stream.of(values())
                .filter(piece -> piece.team.equals(team))
                .filter(piece -> piece.pieceAsString.equals(pieceAsString))
                .findAny()
                .orElse(null);
        if (Objects.isNull(request)) {
            throw new SQLException("기물 정보가 DB에 잘못 저장되어 있습니다.");
        }
        return request.pieceFunction.apply(isFirstMove);
    }
}
