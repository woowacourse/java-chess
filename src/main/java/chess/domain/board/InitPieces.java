package chess.domain.board;

import chess.domain.board.position.Xpoint;
import chess.domain.board.position.Ypoint;
import chess.domain.piece.*;

import java.util.Arrays;
import java.util.List;

public enum InitPieces {
    BLACK_ROOK(Xpoint.getRookPoints(), Ypoint.getBlackPoint(), Rook.createBlack()),
    WHITE_ROOK(Xpoint.getRookPoints(), Ypoint.getWhitePoint(), Rook.createWhite()),
    BLACK_KNIGHT(Xpoint.getKnightPoints(), Ypoint.getBlackPoint(), Knight.createBlack()),
    WHITE_KNIGHT(Xpoint.getKnightPoints(), Ypoint.getWhitePoint(), Knight.createWhite()),
    BLACK_BISHOP(Xpoint.getBishopPoints(), Ypoint.getBlackPoint(), Bishop.createBlack()),
    WHITE_BISHOP(Xpoint.getBishopPoints(), Ypoint.getWhitePoint(), Bishop.createWhite()),
    BLACK_QUEEN(Xpoint.getQueenPoint(), Ypoint.getBlackPoint(), Queen.createBlack()),
    WHITE_QUEEN(Xpoint.getQueenPoint(), Ypoint.getWhitePoint(), Queen.createWhite()),
    BLACK_KING(Xpoint.getKingPoint(), Ypoint.getBlackPoint(), King.createBlack()),
    WHITE_KING(Xpoint.getKingPoint(), Ypoint.getWhitePoint(), King.createWhite()),
    BLACK_PAWN(Xpoint.getPawnOrEmptyPoints(), Ypoint.getBlackPawnPoint(), Pawn.createBlack()),
    WHITE_PAWN(Xpoint.getPawnOrEmptyPoints(), Ypoint.getWhitePawnPoint(), Pawn.createWhite()),
    EMPTY_PIECE(Xpoint.getPawnOrEmptyPoints(), Ypoint.getEmptyPoints(), Empty.create());

    private final List<Xpoint> xpoints;
    private final List<Ypoint> ypoints;
    private final Piece piece;

    InitPieces(List<Xpoint> xpoints, List<Ypoint> ypoints, Piece piece) {
        this.xpoints = xpoints;
        this.ypoints = ypoints;
        this.piece = piece;
    }

    public static Piece findPiece(Xpoint xpoint, Ypoint ypoint) {
        return Arrays.stream(values())
                .filter(p -> p.containsXY(xpoint, ypoint))
                .findFirst()
                .orElseThrow(IllegalAccessError::new).piece;
    }

    private boolean containsXY(Xpoint xpoint, Ypoint ypoint) {
        return this.xpoints.contains(xpoint) && this.ypoints.contains(ypoint);
    }
}
