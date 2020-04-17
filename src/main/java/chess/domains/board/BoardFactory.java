package chess.domains.board;

import chess.domains.piece.Piece;
import chess.domains.piece.PieceFactory;
import chess.domains.position.Position;
import chess.domains.position.Row;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static chess.domains.board.Board.COLUMN_SIZE;


public class BoardFactory {
    private static Map<Position, Piece> board;

    static {
        List<Piece> whitePieces = PieceFactory.getWhitePieces();
        List<Piece> whitePawnsPieces = PieceFactory.getWhitePawnsPieces();
        List<Piece> blackPieces = PieceFactory.getBlackPieces();
        List<Piece> blackPawnsPieces = PieceFactory.getBlackPawnsPieces();
        List<Piece> blankPieces = PieceFactory.getBlankPieces();

        Map<Position, Piece> boardPieces = new HashMap<>();

        boardPieces.putAll(createOneRow(Row.ONE, whitePieces));
        boardPieces.putAll(createOneRow(Row.TWO, whitePawnsPieces));
        boardPieces.putAll(createOneRow(Row.THREE, blankPieces));
        boardPieces.putAll(createOneRow(Row.FOUR, blankPieces));
        boardPieces.putAll(createOneRow(Row.FIVE, blankPieces));
        boardPieces.putAll(createOneRow(Row.SIX, blankPieces));
        boardPieces.putAll(createOneRow(Row.SEVEN, blackPawnsPieces));
        boardPieces.putAll(createOneRow(Row.EIGHT, blackPieces));

        board = boardPieces;
    }

    private static Map<Position, Piece> createOneRow(Row row, List<Piece> pieces) {
        List<Position> aRow = Position.fromRow(row);
        Map<Position, Piece> boardPieces = new HashMap<>();

        for (int i = 0; i < COLUMN_SIZE; i++) {
            boardPieces.put(aRow.get(i), pieces.get(i));
        }

        return boardPieces;
    }

    public static Map<Position, Piece> getBoard() {
        return new HashMap<>(board);
    }

    public static Piece findPieceByPieceName(String name) {
        return board.values().stream()
                .filter(piece -> name.equals(piece.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 이름이 입력되었습니다."));
    }
}
