package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.board.position.Xpoint;
import chess.domain.board.position.Ypoint;

import java.util.Arrays;
import java.util.List;

public enum InitPieces {
    BLACK_ROOK(Xpoint.getRookPoints(), Ypoint.getBlackPoint(), new Rook(Team.BLACK)),
    WHITE_ROOK(Xpoint.getRookPoints(), Ypoint.getWhitePoint(), new Rook(Team.WHITE)),
    BLACK_KNIGHT(Xpoint.getKnightPoints(), Ypoint.getBlackPoint(), new Knight(Team.BLACK)),
    WHITE_KNIGHT(Xpoint.getKnightPoints(), Ypoint.getWhitePoint(), new Knight(Team.WHITE)),
    BLACK_BISHOP(Xpoint.getBishopPoints(), Ypoint.getBlackPoint(), new Bishop(Team.BLACK)),
    WHITE_BISHOP(Xpoint.getBishopPoints(), Ypoint.getWhitePoint(), new Bishop(Team.WHITE)),
    BLACK_QUEEN(Xpoint.getQueenPoint(), Ypoint.getBlackPoint(), new Queen(Team.BLACK)),
    WHITE_QUEEN(Xpoint.getQueenPoint(), Ypoint.getWhitePoint(), new Queen(Team.WHITE)),
    BLACK_KING(Xpoint.getKingPoint(), Ypoint.getBlackPoint(), new King(Team.BLACK)),
    WHITE_KING(Xpoint.getKingPoint(), Ypoint.getWhitePoint(), new King(Team.WHITE)),
    BLACK_PAWN(Xpoint.getPawnOrEmptyPoints(), Ypoint.getBlackPawnPoint(), new Pawn(Team.BLACK)),
    WHITE_PAWN(Xpoint.getPawnOrEmptyPoints(), Ypoint.getWhitePawnPoint(), new Pawn(Team.WHITE)),
    EMPTY_PIECE(Xpoint.getPawnOrEmptyPoints(), Ypoint.getEmptyPoints(), new Empty(Team.EMPTY));

    private final List<Xpoint> xpoints;
    private final List<Ypoint> ypoints;
    private final Piece piece;

    InitPieces(List<Xpoint> xpoints, List<Ypoint> ypoints, Piece piece) {
        this.xpoints = xpoints;
        this.ypoints = ypoints;
        this.piece = piece;
    }

    public static Piece findPiece(Xpoint xpoint, Ypoint ypoint){
        return Arrays.stream(values())
                .filter(p -> p.containsXY(xpoint, ypoint))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .piece;
    }

    private boolean containsXY(Xpoint xpoint, Ypoint ypoint) {
        return this.xpoints.contains(xpoint) && this.ypoints.contains(ypoint);
    }
}
