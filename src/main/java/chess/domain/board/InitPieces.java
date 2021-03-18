package chess.domain.board;

import chess.domain.piece.*;
import chess.domain.board.position.Xpoint;
import chess.domain.board.position.Ypoint;
import chess.domain.piece.team.Black;
import chess.domain.piece.team.Symbol;
import chess.domain.piece.team.Neutral;
import chess.domain.piece.team.White;

import java.util.Arrays;
import java.util.List;

public enum InitPieces {
    BLACK_ROOK(Xpoint.getRookPoints(), Ypoint.getBlackPoint(), new Rook(new Black(Symbol.ROOK))),
    WHITE_ROOK(Xpoint.getRookPoints(), Ypoint.getWhitePoint(), new Rook(new White(Symbol.ROOK))),
    BLACK_KNIGHT(Xpoint.getKnightPoints(), Ypoint.getBlackPoint(), new Knight(new Black(Symbol.KNIGHT))),
    WHITE_KNIGHT(Xpoint.getKnightPoints(), Ypoint.getWhitePoint(), new Knight(new White(Symbol.KNIGHT))),
    BLACK_BISHOP(Xpoint.getBishopPoints(), Ypoint.getBlackPoint(), new Bishop(new Black(Symbol.BISHOP))),
    WHITE_BISHOP(Xpoint.getBishopPoints(), Ypoint.getWhitePoint(), new Bishop(new White(Symbol.BISHOP))),
    BLACK_QUEEN(Xpoint.getQueenPoint(), Ypoint.getBlackPoint(), new Queen(new Black(Symbol.QUEEN))),
    WHITE_QUEEN(Xpoint.getQueenPoint(), Ypoint.getWhitePoint(), new Queen(new White(Symbol.QUEEN))),
    BLACK_KING(Xpoint.getKingPoint(), Ypoint.getBlackPoint(), new King(new Black(Symbol.KING))),
    WHITE_KING(Xpoint.getKingPoint(), Ypoint.getWhitePoint(), new King(new White(Symbol.KING))),
    BLACK_PAWN(Xpoint.getPawnOrEmptyPoints(), Ypoint.getBlackPawnPoint(), new Pawn(new Black(Symbol.PAWN))),
    WHITE_PAWN(Xpoint.getPawnOrEmptyPoints(), Ypoint.getWhitePawnPoint(), new Pawn(new White(Symbol.PAWN))),
    EMPTY_PIECE(Xpoint.getPawnOrEmptyPoints(), Ypoint.getEmptyPoints(), new Empty(new Neutral(Symbol.EMPTY)));

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
