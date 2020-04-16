package chess.model.domain.board;

import chess.model.domain.piece.Bishop;
import chess.model.domain.piece.Color;
import chess.model.domain.piece.King;
import chess.model.domain.piece.Knight;
import chess.model.domain.piece.Pawn;
import chess.model.domain.piece.Piece;
import chess.model.domain.piece.Queen;
import chess.model.domain.piece.Rook;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BoardInitialDefault implements BoardInitialization {

    private static final int RANK_BLACK_PAWN_INIT = 7;
    private static final int RANK_WHITE_PAWN_INIT = 2;
    private static final Map<BoardSquare, Piece> INITIAL_BOARD;

    static {
        Map<BoardSquare, Piece> initialBoard = new HashMap<>();
        initialBoard.put(BoardSquare.of("a1"), Rook.getPieceInstance(Color.WHITE));
        initialBoard.put(BoardSquare.of("h1"), Rook.getPieceInstance(Color.WHITE));
        initialBoard.put(BoardSquare.of("a8"), Rook.getPieceInstance(Color.BLACK));
        initialBoard.put(BoardSquare.of("h8"), Rook.getPieceInstance(Color.BLACK));
        initialBoard.put(BoardSquare.of("b1"), Knight.getPieceInstance(Color.WHITE));
        initialBoard.put(BoardSquare.of("g1"), Knight.getPieceInstance(Color.WHITE));
        initialBoard.put(BoardSquare.of("b8"), Knight.getPieceInstance(Color.BLACK));
        initialBoard.put(BoardSquare.of("g8"), Knight.getPieceInstance(Color.BLACK));
        initialBoard.put(BoardSquare.of("c1"), Bishop.getPieceInstance(Color.WHITE));
        initialBoard.put(BoardSquare.of("f1"), Bishop.getPieceInstance(Color.WHITE));
        initialBoard.put(BoardSquare.of("c8"), Bishop.getPieceInstance(Color.BLACK));
        initialBoard.put(BoardSquare.of("f8"), Bishop.getPieceInstance(Color.BLACK));
        initialBoard.put(BoardSquare.of("d1"), Queen.getPieceInstance(Color.WHITE));
        initialBoard.put(BoardSquare.of("d8"), Queen.getPieceInstance(Color.BLACK));
        initialBoard.put(BoardSquare.of("e8"), King.getPieceInstance(Color.BLACK));
        initialBoard.put(BoardSquare.of("e1"), King.getPieceInstance(Color.WHITE));
        for (int i = 0; i < 8; i++) {
            char file = (char) ('a' + i);
            initialBoard.put(BoardSquare.of(String.valueOf(file) + RANK_BLACK_PAWN_INIT)
                , Pawn.getPieceInstance(Color.BLACK));
            initialBoard.put(BoardSquare.of(String.valueOf(file) + RANK_WHITE_PAWN_INIT)
                , Pawn.getPieceInstance(Color.WHITE));
        }
        INITIAL_BOARD = Collections.unmodifiableMap(initialBoard);
    }

    @Override
    public Map<BoardSquare, Piece> getInitialize() {
        return new HashMap<>(INITIAL_BOARD);
    }
}
