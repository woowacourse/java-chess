package chess.domain;

import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class ChessBoard {

    private static final int FIRST_INDEX = 1;
    private static final int RANK_SIZE = 8;
    private static final int FILE_SIZE = 8;

    private final Map<Position, Piece> piecesByPosition = new HashMap<>();

    public ChessBoard() {
        initializeBoard();
    }

    private void initializeBoard() {
        insertPiecesByColor(TeamColor.WHITE);
        insertPiecesByColor(TeamColor.BLACK);
    }

    private void insertPiecesByColor(TeamColor color) {
        piecesByPosition.put(new Position(color.startingRank(), 1), new Rook(color));
        piecesByPosition.put(new Position(color.startingRank(), 2), new Knight(color));
        piecesByPosition.put(new Position(color.startingRank(), 3), new Bishop(color));
        piecesByPosition.put(new Position(color.startingRank(), 4), new King(color));
        piecesByPosition.put(new Position(color.startingRank(), 5), new Queen(color));
        piecesByPosition.put(new Position(color.startingRank(), 6), new Bishop(color));
        piecesByPosition.put(new Position(color.startingRank(), 7), new Knight(color));
        piecesByPosition.put(new Position(color.startingRank(), 8), new Rook(color));
        IntStream.range(FIRST_INDEX, RANK_SIZE + 1)
                .forEach(file -> piecesByPosition.put(new Position(color.startingPawnRank(), file),
                        new Pawn(color)));
    }

    public Map<Position, Piece> piecesByPosition() {
        return new HashMap<>(piecesByPosition);
    }

}
