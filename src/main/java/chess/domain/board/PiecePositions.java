package chess.domain.board;

import chess.domain.piece.Bishop;
import chess.domain.piece.Color;
import chess.domain.piece.Empty;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public enum PiecePositions {
    BLACK_BISHOP(Bishop.from(Color.BLACK),
            Set.of(new Position(File.C, Rank.EIGHTH), new Position(File.F, Rank.EIGHTH))),
    WHITE_BISHOP(Bishop.from(Color.WHITE),
            Set.of(new Position(File.C, Rank.FIRST), new Position(File.F, Rank.FIRST))),
    BLACK_KING(King.from(Color.BLACK),
            Set.of(new Position(File.E, Rank.EIGHTH))),
    WHITE_KING(King.from(Color.WHITE),
            Set.of(new Position(File.E, Rank.FIRST))),
    BLACK_KNIGHT(Knight.from(Color.BLACK),
            Set.of(new Position(File.B, Rank.EIGHTH), new Position(File.G, Rank.EIGHTH))),
    WHITE_KNIGHT(Knight.from(Color.WHITE),
            Set.of(new Position(File.B, Rank.FIRST), new Position(File.G, Rank.FIRST))),
    BLACK_PAWN(Pawn.from(Color.BLACK),
            createPositionsByRank(Rank.SEVENTH)),
    WHITE_PAWN(Pawn.from(Color.WHITE),
            createPositionsByRank(Rank.SECOND)),
    BLACK_QUEEN(Queen.from(Color.BLACK),
            Set.of(new Position(File.D, Rank.EIGHTH))),
    WHITE_QUEEN(Queen.from(Color.WHITE),
            Set.of(new Position(File.D, Rank.FIRST))),
    BLACK_ROOK(Rook.from(Color.BLACK),
            Set.of(new Position(File.A, Rank.EIGHTH), new Position(File.H, Rank.EIGHTH))),
    WHITE_ROOK(Rook.from(Color.WHITE),
            Set.of(new Position(File.A, Rank.FIRST), new Position(File.H, Rank.FIRST))),
    EMPTY_POSITION(Empty.getInstance(),
            createEmptyPositions());

    private final Piece piece;
    private final Set<Position> positions;

    PiecePositions(Piece piece, Set<Position> positions) {
        this.piece = piece;
        this.positions = positions;
    }

    private static Set<Position> createPositionsByRank(Rank rank) {
        return Arrays.stream(File.values())
                .map(file -> new Position(file, rank))
                .collect(Collectors.toSet());
    }

    private static Set<Position> createEmptyPositions() {
        Set<Position> emptyPositions = new HashSet<>();
        emptyPositions.addAll(createPositionsByRank(Rank.THIRD));
        emptyPositions.addAll(createPositionsByRank(Rank.FOURTH));
        emptyPositions.addAll(createPositionsByRank(Rank.FIFTH));
        emptyPositions.addAll(createPositionsByRank(Rank.SIXTH));

        return emptyPositions;
    }

    public static ChessBoard createBoard() {
        Map<Position, Piece> board = new HashMap<>();
        for (PiecePositions piecePositions : values()) {
            updateBoard(piecePositions, board);
        }

        return new ChessBoard(board);
    }

    private static void updateBoard(PiecePositions piecePositions, Map<Position, Piece> board) {
        for (Position position : piecePositions.positions) {
            board.put(position, piecePositions.piece);
        }
    }
}
