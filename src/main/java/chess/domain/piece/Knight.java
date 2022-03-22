package chess.domain.piece;

import chess.domain.piece.vo.TeamColor;

public class Knight extends Piece {

    private static final List<Position> WHITE_POSITIONS = Arrays.asList(Position.from("1b"), Position.from("1g"));
    private static final List<Position> BLACK_POSITIONS = Arrays.asList(Position.from("8b"), Position.from("8g"));

    private static final String SYMBOL = "n";

    public Knight(final TeamColor teamColor) {
        super(teamColor, SYMBOL);
    }
}
