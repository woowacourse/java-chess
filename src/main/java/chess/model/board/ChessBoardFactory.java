package chess.model.board;

import chess.model.piece.Camp;
import chess.model.piece.Piece;
import chess.model.piece.PieceFactory;
import chess.model.piece.PieceType;
import chess.model.position.File;
import chess.model.position.Position;
import chess.model.position.Rank;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ChessBoardFactory {

    private static final List<PieceType> backPieces = List.of(
            PieceType.ROOK, PieceType.KNIGHT, PieceType.BISHOP, PieceType.QUEEN, PieceType.KING,
            PieceType.BISHOP, PieceType.KNIGHT, PieceType.ROOK
    );
    private static final List<PieceType> frontPieces = List.of(
            PieceType.PAWN, PieceType.PAWN, PieceType.PAWN, PieceType.PAWN, PieceType.PAWN, PieceType.PAWN,
            PieceType.PAWN, PieceType.PAWN
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
        final PieceType pieceType = backPieces.get(index);

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
        final PieceType pieceType = frontPieces.get(index);

        return PieceFactory.create(pieceType, camp);
    }

    private static void initialEmptyPiece(final Map<Position, Piece> initialChessBoard, final Rank targetRank) {
        for (final File targetFile : File.values()) {
            final Position targetPosition = Position.of(targetFile, targetRank);

            initialChessBoard.put(targetPosition, Piece.EMPTY);
        }
    }
}
