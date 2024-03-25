package domain.piece;

import domain.Team;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.toMap;

public class PieceMaker {

    private static final Map<String, Piece> PIECE_CACHE;

    static {
        final List<Piece> pieces = List.of(
                new Pawn(Team.BLACK), new Pawn(Team.WHITE),
                new Rook(Team.BLACK), new Rook(Team.WHITE),
                new Knight(Team.BLACK), new Knight(Team.WHITE),
                new Bishop(Team.BLACK), new Bishop(Team.WHITE),
                new Queen(Team.BLACK), new Queen(Team.WHITE),
                new King(Team.BLACK), new King(Team.WHITE));

        PIECE_CACHE = pieces.stream()
                .collect(toMap(piece -> toKey(piece.pieceType(), piece.team()), Function.identity()));
    }

    private PieceMaker() {
    }

    public static Piece of(final String pieceType, final String team) {
        return PIECE_CACHE.get(pieceType + team);
    }

    private static String toKey(final PieceType pieceType, final Team team) {
        return pieceType.name() + team.name();
    }
}
