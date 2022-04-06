package chess.util;

import static chess.domain.board.piece.PieceType.BISHOP;
import static chess.domain.board.piece.PieceType.KING;
import static chess.domain.board.piece.PieceType.KNIGHT;
import static chess.domain.board.piece.PieceType.PAWN;
import static chess.domain.board.piece.PieceType.QUEEN;
import static chess.domain.board.piece.PieceType.ROOK;

import chess.domain.board.piece.Color;
import chess.domain.board.piece.Piece;
import chess.domain.board.position.File;
import chess.domain.board.position.Position;
import chess.domain.board.position.Rank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BoardMapGeneratorUtil {

    private static final Rank BLACK_NON_PAWN_INIT_RANK = Rank.EIGHT;
    private static final Rank BLACK_PAWN_INIT_RANK = Rank.SEVEN;
    private static final Rank WHITE_PAWN_INIT_RANK = Rank.TWO;
    private static final Rank WHITE_NON_PAWN_INIT_RANK = Rank.ONE;

    private static final List<File> ROOK_INIT_FILES = List.of(File.A, File.H);
    private static final List<File> KNIGHT_INIT_FILES = List.of(File.B, File.G);
    private static final List<File> BISHOP_INIT_FILES = List.of(File.C, File.F);
    private static final File QUEEN_INIT_FILE = File.D;
    private static final File KING_INIT_FILE = File.E;

    private BoardMapGeneratorUtil() {
    }

    public static Map<Position, Piece> initFullChessBoard() {
        final Map<Position, Piece> boardMap = new HashMap<>();
        updateBoard(boardMap, initNonPawns(Color.BLACK, BLACK_NON_PAWN_INIT_RANK));
        updateBoard(boardMap, initPawns(Color.BLACK, BLACK_PAWN_INIT_RANK));
        updateBoard(boardMap, initPawns(Color.WHITE, WHITE_PAWN_INIT_RANK));
        updateBoard(boardMap, initNonPawns(Color.WHITE, WHITE_NON_PAWN_INIT_RANK));
        return boardMap;
    }

    private static List<PositionPiecePair> initPawns(Color color, Rank initRank) {
        return File.allFilesAscending()
                .stream()
                .map(file -> Position.of(file, initRank))
                .map(position -> new PositionPiecePair(position, Piece.of(color, PAWN)))
                .collect(Collectors.toUnmodifiableList());
    }

    private static List<PositionPiecePair> initNonPawns(Color color, Rank initRank) {
        List<PositionPiecePair> nonPawns = new ArrayList<>();
        nonPawns.addAll(initRooks(color, initRank));
        nonPawns.addAll(initKnights(color, initRank));
        nonPawns.addAll(initBishops(color, initRank));
        nonPawns.addAll(initQueenAndKing(color, initRank));
        return nonPawns;
    }

    private static List<PositionPiecePair> initRooks(Color color, Rank initRank) {
        return ROOK_INIT_FILES.stream()
                .map(file -> Position.of(file, initRank))
                .map(position -> new PositionPiecePair(position, Piece.of(color, ROOK)))
                .collect(Collectors.toUnmodifiableList());
    }

    private static List<PositionPiecePair> initKnights(Color color, Rank initRank) {
        return KNIGHT_INIT_FILES.stream()
                .map(file -> Position.of(file, initRank))
                .map(position -> new PositionPiecePair(position, Piece.of(color, KNIGHT)))
                .collect(Collectors.toUnmodifiableList());
    }

    private static List<PositionPiecePair> initBishops(Color color, Rank initRank) {
        return BISHOP_INIT_FILES.stream()
                .map(file -> Position.of(file, initRank))
                .map(position -> new PositionPiecePair(position, Piece.of(color, BISHOP)))
                .collect(Collectors.toUnmodifiableList());
    }

    private static List<PositionPiecePair> initQueenAndKing(Color color, Rank initRank) {
        Position queenPosition = Position.of(QUEEN_INIT_FILE, initRank);
        Position kingPosition = Position.of(KING_INIT_FILE, initRank);
        return List.of(new PositionPiecePair(queenPosition, Piece.of(color, QUEEN)),
                new PositionPiecePair(kingPosition, Piece.of(color, KING)));
    }

    private static void updateBoard(Map<Position, Piece> boardMap, List<PositionPiecePair> pieceInfos) {
        for (PositionPiecePair pieceInfo : pieceInfos) {
            Position positionKey = pieceInfo.position;
            Piece pieceValue = pieceInfo.piece;
            boardMap.put(positionKey, pieceValue);
        }
    }

    private static class PositionPiecePair {

        final Position position;
        final Piece piece;

        PositionPiecePair(Position position, Piece piece) {
            this.position = position;
            this.piece = piece;
        }
    }
}
