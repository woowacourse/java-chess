package chess.domain.board;

import chess.domain.board.position.Position;
import chess.domain.board.position.Xpoint;
import chess.domain.board.position.Ypoint;
import chess.domain.piece.Bishop;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

    private final List<Xpoint> xPoints;
    private final List<Ypoint> yPoints;
    private final Piece piece;

    InitPieces(final List<Xpoint> xPoints, final List<Ypoint> yPoints, final Piece piece) {
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.piece = piece;
    }

    public static List<Rank> initRanks() {
        List<Rank> ranks = new ArrayList<>();
        for (Ypoint ypoint : Ypoint.values()) {
            ranks.add(initRank(ypoint));
        }
        return ranks;
    }

    private static Rank initRank(final Ypoint ypoint) {
        Map<Position, Piece> line = new LinkedHashMap<>();
        for (Xpoint xpoint : Xpoint.values()) {
            line.put(Position.of(xpoint, ypoint), InitPieces.findPiece(xpoint, ypoint));
        }
        return new Rank(line);
    }

    private static Piece findPiece(final Xpoint xpoint, final Ypoint ypoint) {
        return Arrays.stream(values())
            .filter(p -> p.containsXY(xpoint, ypoint))
            .findFirst()
            .orElseThrow(IllegalAccessError::new).piece;
    }

    private boolean containsXY(final Xpoint xpoint, final Ypoint ypoint) {
        return this.xPoints.contains(xpoint) && this.yPoints.contains(ypoint);
    }
}
