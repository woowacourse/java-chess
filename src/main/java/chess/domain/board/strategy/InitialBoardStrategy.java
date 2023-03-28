package chess.domain.board.strategy;

import chess.domain.Column;
import chess.domain.Position;
import chess.domain.Rank;
import chess.domain.piece.Bishop;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import static chess.domain.piece.PieceInfo.BLACK_BISHOP_INFO;
import static chess.domain.piece.PieceInfo.BLACK_KING_INFO;
import static chess.domain.piece.PieceInfo.BLACK_KNIGHT_INFO;
import static chess.domain.piece.PieceInfo.BLACK_PAWN_INFO;
import static chess.domain.piece.PieceInfo.BLACK_QUEEN_INFO;
import static chess.domain.piece.PieceInfo.BLACK_ROOK_INFO;
import static chess.domain.piece.PieceInfo.EMPTY_INFO;
import static chess.domain.piece.PieceInfo.WHITE_BISHOP_INFO;
import static chess.domain.piece.PieceInfo.WHITE_KING_INFO;
import static chess.domain.piece.PieceInfo.WHITE_KNIGHT_INFO;
import static chess.domain.piece.PieceInfo.WHITE_PAWN_INFO;
import static chess.domain.piece.PieceInfo.WHITE_QUEEN_INFO;
import static chess.domain.piece.PieceInfo.WHITE_ROOK_INFO;

public class InitialBoardStrategy implements BoardStrategy {
    private final Map<Position, Piece> board = new LinkedHashMap<>();

    @Override
    public Map<Position, Piece> generate() {
        initEmptyPieces();
        initFirstRow();
        initSecondRow();

        return new LinkedHashMap<>(board);
    }

    private void initEmptyPieces() {
        for (Rank rank : Rank.getReversedOrderedRanks()) {
            for (Column column : Column.getOrderedColumns()) {
                board.put(new Position(column, rank), new EmptyPiece(EMPTY_INFO));
            }
        }
    }

    private void initFirstRow() {
        initBlackFirstRow();
        initWhiteFirstRow();
    }

    private void initWhiteFirstRow() {
        Rank rank = Rank.ONE;

        List<Piece> firstRowPieces = List.of(new Rook(WHITE_ROOK_INFO), new Knight(WHITE_KNIGHT_INFO), new Bishop(WHITE_BISHOP_INFO),
                new Queen(WHITE_QUEEN_INFO), new King(WHITE_KING_INFO), new Bishop(WHITE_BISHOP_INFO),
                new Knight(WHITE_KNIGHT_INFO), new Rook(WHITE_ROOK_INFO));

        IntStream.range(0, firstRowPieces.size())
                .forEach(i -> board.replace(new Position(Column.findColumnByIndex(i + 1), rank),
                        firstRowPieces.get(i))
                );
    }

    private void initBlackFirstRow() {
        Rank rank = Rank.EIGHT;

        List<Piece> firstRowPieces = List.of(new Rook(BLACK_ROOK_INFO), new Knight(BLACK_KNIGHT_INFO), new Bishop(BLACK_BISHOP_INFO),
                new Queen(BLACK_QUEEN_INFO), new King(BLACK_KING_INFO), new Bishop(BLACK_BISHOP_INFO),
                new Knight(BLACK_KNIGHT_INFO), new Rook(BLACK_ROOK_INFO));

        IntStream.range(0, firstRowPieces.size())
                .forEach(i -> board.replace(new Position(Column.findColumnByIndex(i + 1), rank),
                        firstRowPieces.get(i))
                );
    }

    private void initSecondRow() {
        initBlackSecondRow();
        initWhiteSecondRow();
    }

    private void initBlackSecondRow() {
        Rank rank = Rank.SEVEN;

        Arrays.stream(Column.values())
                .map(column -> new Position(column, rank))
                .forEach(position -> board.replace(position,
                        new Pawn(BLACK_PAWN_INFO)));
    }

    private void initWhiteSecondRow() {
        Rank rank = Rank.TWO;

        Arrays.stream(Column.values())
                .map(column -> new Position(column, rank))
                .forEach(position -> board.replace(position,
                        new Pawn(WHITE_PAWN_INFO)));
    }
}
