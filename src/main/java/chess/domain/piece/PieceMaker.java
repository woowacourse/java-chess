package chess.domain.piece;

import chess.domain.team.Team;
import chess.view.TeamName;

import java.util.Arrays;
import java.util.function.Function;

public enum PieceMaker {

    KING(King::new),
    QUEEN(Queen::new),
    BISHOP(Bishop::new),
    KNIGHT(Knight::new),
    ROOK(Rook::new),
    PAWN(Pawn::new),
    EMPTY(Empty::new);

    private final Function<Team, Piece> function;

    PieceMaker(final Function<Team, Piece> function) {
        this.function = function;
    }

    public static Piece bind(final String pieceName, final String teamName) {
        return Arrays.stream(PieceMaker.values())
                .filter(pieceMaker -> pieceMaker.name().equals(pieceName))
                .map(pieceMaker -> pieceMaker.function.apply(TeamName.findByName(teamName)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("입력에 해당하는 체스말이 존재하지 않습니다"));
    }
}
