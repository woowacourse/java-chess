package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceFactory;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public final class BoardFactory {
    private static final int RANK_EIGHT = 8;
    private static final int RANK_SEVEN = 7;
    private static final int RANK_TWO = 2;
    private static final int RANK_ONE = 1;
    private static final Map<Position, Piece> INITIAL_BOARD = new HashMap<>();

    static {
        setupOthersPieces(RANK_EIGHT, Color.BLACK);
        setupPawns(RANK_SEVEN, Color.BLACK);
        setupPawns(RANK_TWO, Color.WHITE);
        setupOthersPieces(RANK_ONE, Color.WHITE);
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

    private BoardFactory() {
    }

    public static Board newInstance() {
        return new Board(new HashMap<>(INITIAL_BOARD));
    }

    public static Board newInstance(Map<Position, Piece> board) {
        return new Board(board);
    }

    public static Board newInstance(Map<String, String> boardByGameId, Color color) {
        final Map<Position, Piece> board = boardByGameId.entrySet()
                .stream()
                .map(entry -> Map.entry(
                        Position.from(entry.getKey()),
                        PieceFactory.getInstance(entry.getValue())))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
        return new Board(board, color);
    }
}
