package chess.domain.piece.piecefigure;

import chess.domain.board.Position;
import chess.domain.piece.pieceinfo.PieceType;
import chess.domain.piece.pieceinfo.TeamType;

import java.util.Arrays;
import java.util.List;

public class BlackPawn extends Pawn {
    private static final List<Position> DEFAULT_POSITIONS = Arrays.asList(
            Position.of("10"), Position.of("11"), Position.of("12"), Position.of("13"),
            Position.of("14"), Position.of("15"), Position.of("16"), Position.of("17")
    );
    private static final Piece BLACK_PAWN = new BlackPawn(PieceType.PAWN);

    private BlackPawn(PieceType pieceType) {
        super(TeamType.BLACK, pieceType, DEFAULT_POSITIONS);
    }

    public static Piece of() {
        return BLACK_PAWN;
    }
}
