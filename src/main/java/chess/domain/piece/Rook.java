package chess.domain.piece;

import chess.domain.piece.vo.TeamColor;

public class Rook extends Piece {

    private static final List<Position> WHITE_POSITIONS = Arrays.asList(Position.from("1a"), Position.from("1h"));
    private static final List<Position> BLACK_POSITIONS = Arrays.asList(Position.from("8a"), Position.from("8h"));

    private static final String SYMBOL = "r";

    public Rook(final TeamColor teamColor, final Position position) {
        super(teamColor, position);
    }

    public Rook(final TeamColor teamColor) {
        super(teamColor, SYMBOL);
    }
}
