package chess.domain.piece.piecefigure;

import chess.domain.board.Position;
import chess.domain.piece.pieceinfo.PieceType;
import chess.domain.piece.pieceinfo.TeamType;

import java.util.Arrays;
import java.util.List;

public class WhitePawn extends Pawn {
    private static final List<Position> DEFAULT_POSITIONS = Arrays.asList(
            Position.of("60"), Position.of("61"), Position.of("62"), Position.of("63"),
            Position.of("64"), Position.of("65"), Position.of("66"), Position.of("67")
    );
    private static final Piece WHITE_PAWN = new WhitePawn(PieceType.PAWN);

    private WhitePawn(PieceType pieceType) {
        super(TeamType.WHITE, pieceType, DEFAULT_POSITIONS);
    }

    public static Piece of() {
        return WHITE_PAWN;
    }
}
