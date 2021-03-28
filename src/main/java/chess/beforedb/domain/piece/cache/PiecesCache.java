package chess.beforedb.domain.piece.cache;

import chess.beforedb.domain.piece.Bishop;
import chess.beforedb.domain.piece.King;
import chess.beforedb.domain.piece.Knight;
import chess.beforedb.domain.piece.Pawn;
import chess.beforedb.domain.piece.Piece;
import chess.beforedb.domain.piece.Queen;
import chess.beforedb.domain.piece.Rook;
import chess.beforedb.domain.piece.type.PieceType;
import chess.beforedb.domain.player.type.TeamColor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PiecesCache {
    private static final List<Piece> pieces = new ArrayList<>();

    private PiecesCache() {
    }

    static {
        cachePieces();
    }

    private static void cachePieces() {
        for (TeamColor teamColor : TeamColor.values()) {
            cachePiecesWithColor(teamColor);
        }
    }

    private static void cachePiecesWithColor(TeamColor teamColor) {
        List<Piece> piecesWithColor = createPiecesWithColor(teamColor);
        pieces.addAll(piecesWithColor);
    }

    private static List<Piece> createPiecesWithColor(TeamColor teamColor) {
        return Arrays.asList(
            new Pawn(teamColor),
            new Rook(teamColor),
            new Knight(teamColor),
            new Bishop(teamColor),
            new Queen(teamColor),
            new King(teamColor)
        );
    }

    public static Piece find(PieceType pieceType, TeamColor teamColor) {
        return pieces.stream()
            .filter(piece -> piece.getPieceType() == pieceType && piece.getTeamColor() == teamColor)
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 기물입니다."));
    }
}
