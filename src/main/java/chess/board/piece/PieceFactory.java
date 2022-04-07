package chess.board.piece;

import chess.board.Team;
import chess.board.piece.position.Position;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum PieceFactory {

    ROOK(Rook::new),
    KNIGHT(Knight::new),
    BISHOP(Bishop::new),
    QUEEN(Queen::new),
    KING(King::new),
    PAWN(Pawn::new),
    EMPTY(Empty::new),
    ;
    private final BiFunction<Position, Team, Piece> function;

    PieceFactory(BiFunction<Position, Team, Piece> function) {
        this.function = function;
    }

    public static Piece create(final String position, final String team, final String type) {
        return Arrays.stream(values())
                .filter(pieceFactory -> pieceFactory.name().toLowerCase().equals(type))
                .findFirst()
                .map(pieceFactory -> {
                    Position loadPosition = Position.of(position.charAt(0), position.charAt(1));
                    Team from = Team.from(team);
                    return pieceFactory.function.apply(loadPosition, from);
                })
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 잘못입력되었습니다."));
    }
}
