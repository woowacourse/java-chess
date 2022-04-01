package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class InitialBoard {
    private static final Map<Position, Piece> INITIAL_BOARD = new HashMap<>();

    static {
        setupOthersPieces(8, Color.BLACK);
        setupPawns(7, Color.BLACK);
        setupPawns(2, Color.WHITE);
        setupOthersPieces(1, Color.WHITE);
    }

    private static void setupOthersPieces(int rank, Color color) {
        final List<Position> positionsByRank = getPositionsByRank(Rank.from(rank));
        final List<Piece> piecesByColorExceptPawn = getPiecesByColorExceptPawn(color);

        for (int i = 0; i < positionsByRank.size(); i++) {
            INITIAL_BOARD.put(positionsByRank.get(i), piecesByColorExceptPawn.get(i));
        }
    }

    private static List<Position> getPositionsByRank(Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> Position.of(file, rank))
                .collect(Collectors.toList());
    }

    private static List<Piece> getPiecesByColorExceptPawn(Color color) {
        return List.of(new Rook(color), new Knight(color), new Bishop(color), new Queen(color), new King(color),
                new Bishop(color), new Knight(color), new Rook(color));
    }

    private static void setupPawns(int rank, Color color) {
        for (Position position : getPositionsByRank(Rank.from(rank))) {
            INITIAL_BOARD.put(position, new Pawn(color));
        }
    }

    private InitialBoard() {
    }

    static Map<Position, Piece> newInstance() {
        return new HashMap<>(INITIAL_BOARD);
    }
}
