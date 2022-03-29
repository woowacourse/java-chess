package chess.domain.board;

import static chess.domain.Camp.BLACK;
import static chess.domain.Camp.WHITE;

import chess.domain.Camp;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.None;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.BiConsumer;
import java.util.function.Function;

public class BoardInitializer {
    private static final Position INITIAL_POSITION_ROOK = Position.of(Column.A, Row.ONE);
    private static final Position INITIAL_POSITION_KNIGHT = Position.of(Column.B, Row.ONE);
    private static final Position INITIAL_POSITION_BISHOP = Position.of(Column.C, Row.ONE);
    private static final Position INITIAL_POSITION_QUEEN = Position.of(Column.D, Row.ONE);
    private static final Position INITIAL_POSITION_KING = Position.of(Column.E, Row.ONE);
    private static final Row INITIAL_ROW_PAWN = Row.TWO;
    private static final int INITIAL_START_ROW_INDEX_BLANK = 2;
    private static final int INITIAL_END_ROW_INDEX_BLANK = 5;

    public static Board get() {
        Map<Position, Piece> value = new TreeMap<>();
        putFourPiecesOn(value);
        putTwoPiecesOn(value);
        putPawnsOn(value);
        putBlanks(value);
        return new Board(value);
    }

    private static void putFourPiecesOn(Map<Position, Piece> boardValue) {
        putFourPiecesOn(INITIAL_POSITION_ROOK, putPieceOn(boardValue, Rook::new));
        putFourPiecesOn(INITIAL_POSITION_KNIGHT, putPieceOn(boardValue, Knight::new));
        putFourPiecesOn(INITIAL_POSITION_BISHOP, putPieceOn(boardValue, Bishop::new));
    }

    private static void putFourPiecesOn(Position initialPosition, BiConsumer<Position, Camp> pieceToBoardConsumer) {
        pieceToBoardConsumer.accept(initialPosition, WHITE);
        pieceToBoardConsumer.accept(initialPosition.flipHorizontally(), WHITE);
        pieceToBoardConsumer.accept(initialPosition.flipVertically(), BLACK);
        pieceToBoardConsumer.accept(initialPosition.flipDiagonally(), BLACK);
    }

    private static void putTwoPiecesOn(Map<Position, Piece> boardValue) {
        putTwoPiecesOn(INITIAL_POSITION_QUEEN, putPieceOn(boardValue, Queen::new));
        putTwoPiecesOn(INITIAL_POSITION_KING, putPieceOn(boardValue, King::new));
    }

    private static void putTwoPiecesOn(Position initialPosition, BiConsumer<Position, Camp> pieceToBoardConsumer) {
        pieceToBoardConsumer.accept(initialPosition, WHITE);
        pieceToBoardConsumer.accept(initialPosition.flipVertically(), BLACK);
    }

    private static void putPawnsOn(Map<Position, Piece> boardValue) {
        for (Column column : Column.values()) {
            putTwoPiecesOn(Position.of(column, INITIAL_ROW_PAWN), putPieceOn(boardValue, Pawn::new));
        }
    }

    private static BiConsumer<Position, Camp> putPieceOn(Map<Position, Piece> boardValue,
                                                         Function<Camp, Piece> pieceSupplier) {
        return (position, camp) -> boardValue.put(position, pieceSupplier.apply(camp));
    }

    private static void putBlanks(Map<Position, Piece> boardValue) {
        for (Column column : Column.values()) {
            putBlanksOn(column, boardValue);
        }
    }

    private static void putBlanksOn(Column column, Map<Position, Piece> boardValue) {
        for (int i = INITIAL_START_ROW_INDEX_BLANK; i <= INITIAL_END_ROW_INDEX_BLANK; i++) {
            boardValue.put(Position.of(column, Row.values()[i]), new None());
        }
    }
}
