package chess.domain.piece;

import static chess.domain.piece.vo.TeamColor.BLACK;
import static chess.domain.piece.vo.TeamColor.WHITE;

import chess.domain.board.Position;
import chess.domain.piece.vo.TeamColor;
import java.util.Arrays;
import java.util.List;

public class Queen extends Piece {

    private static final Position WHITE_POSITION = Position.from("1d");
    private static final Position BLACK_POSITION = Position.from("8d");

    private static final String SYMBOL = "q";

    public Queen(final TeamColor teamColor) {
        super(teamColor, SYMBOL);
    }
}