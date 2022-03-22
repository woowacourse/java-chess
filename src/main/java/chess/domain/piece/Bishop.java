package chess.domain.piece;

import chess.domain.piece.vo.TeamColor;

public class Bishop extends Piece {

    private static final List<Position> WHITE_POSITIONS = Arrays.asList(Position.from("1c"), Position.from("1f"));
    private static final List<Position> BLACK_POSITIONS = Arrays.asList(Position.from("8c"), Position.from("8f"));

    private static final String SYMBOL = "b";

    public Bishop(final TeamColor teamColor) {
        super(teamColor, SYMBOL);
    }
}
