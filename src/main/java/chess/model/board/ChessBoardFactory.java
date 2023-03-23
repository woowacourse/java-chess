package chess.model.board;

import chess.model.piece.Camp;
import chess.model.piece.Piece;
import chess.model.piece.type.Bishop;
import chess.model.piece.type.Empty;
import chess.model.piece.type.King;
import chess.model.piece.type.Knight;
import chess.model.piece.type.Pawn;
import chess.model.piece.type.PieceFactory;
import chess.model.piece.type.Queen;
import chess.model.piece.type.Rook;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ChessBoardFactory {

    private static final List<Class<? extends Piece>> backPieces = List.of(
            Rook.class, Knight.class, Bishop.class, Queen.class, King.class, Bishop.class, Knight.class, Rook.class
    );
    private static final List<Class<? extends Piece>> frontPieces = List.of(
            Pawn.class, Pawn.class, Pawn.class, Pawn.class, Pawn.class, Pawn.class, Pawn.class, Pawn.class
    );
    private static final int CHESS_BOARD_SIZE = 8;

    private ChessBoardFactory() {
    }

    public static ChessBoard create() {
        Map<Position, Piece> initialChessBoard = new HashMap<>();

        initialPiece(initialChessBoard);
        return new ChessBoard(initialChessBoard);
    }

    private static void initialPiece(final Map<Position, Piece> initialChessBoard) {
        initialBackPiece(initialChessBoard, Rank.EIGHTH, Camp.BLACK);
        initialFrontPiece(initialChessBoard, Rank.SEVENTH, Camp.BLACK);
        initialEmptyPiece(initialChessBoard, Rank.SIXTH);
        initialEmptyPiece(initialChessBoard, Rank.FIFTH);
        initialEmptyPiece(initialChessBoard, Rank.FOURTH);
        initialEmptyPiece(initialChessBoard, Rank.THIRD);
        initialFrontPiece(initialChessBoard, Rank.SECOND, Camp.WHITE);
        initialBackPiece(initialChessBoard, Rank.FIRST, Camp.WHITE);
    }

    private static void initialBackPiece(
            final Map<Position, Piece> initialChessBoard,
            final Rank targetRank,
            final Camp camp) {
        final List<Position> positions = Arrays.stream(File.values())
                .map(file -> Position.of(file, targetRank))
                .collect(Collectors.toList());

        for (int index = 0; index < CHESS_BOARD_SIZE; index++) {
            final Position targetPosition = positions.get(index);
            final Piece targetPiece = initialBackPieceToChessBoard(index, camp);

            initialChessBoard.put(targetPosition, targetPiece);
        }
    }

    private static Piece initialBackPieceToChessBoard(final int index, final Camp camp) {
        final Class<? extends Piece> pieceType = backPieces.get(index);
        return PieceFactory.create(pieceType, camp);
    }

    private static void initialFrontPiece(
            final Map<Position, Piece> initialChessBoard,
            final Rank targetRank,
            final Camp camp) {
        final List<Position> positions = Arrays.stream(File.values())
                .map(file -> Position.of(file, targetRank))
                .collect(Collectors.toList());

        for (int index = 0; index < CHESS_BOARD_SIZE; index++) {
            final Position targetPosition = positions.get(index);
            final Piece targetPiece = initialFrontPieceToChessBoard(index, camp);

            initialChessBoard.put(targetPosition, targetPiece);
        }
    }

    private static Piece initialFrontPieceToChessBoard(final int index, final Camp camp) {
        final Class<? extends Piece> pieceType = frontPieces.get(index);

        return PieceFactory.create(pieceType, camp);
    }

    private static void initialEmptyPiece( final Map<Position, Piece> initialChessBoard, final Rank targetRank) {
        for (final File targetFile : File.values()) {
            final Position targetPosition = Position.of(targetFile, targetRank);

            initialChessBoard.put(targetPosition, Empty.EMPTY_PIECE);
        }
    }
}
