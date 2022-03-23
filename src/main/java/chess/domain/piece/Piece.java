package chess.domain.piece;

import chess.domain.piece.vo.TeamColor;

public abstract class Piece {

    private static final Map<File, BiFunction<TeamColor, Position, Piece>> initialPieceCreationStrategy =
            Map.of(A, Rook::new, B, Knight::new, C, Bishop::new, D, Queen::new,
                    E, King::new, F, Bishop::new, G, Knight::new, H, Rook::new);

    private final TeamColor teamColor;
    private final String symbol;

    Piece(final TeamColor teamColor, final String symbol) {
        this.teamColor = teamColor;
        this.symbol = symbol;
    }

    public final String getSymbol() {
        return teamColor.convertSymbolByTeamColor(symbol);
    }
}
