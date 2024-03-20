package chess.chessboard;

import chess.chesspiece.ChessPiece;
import java.util.Map;
import java.util.Optional;

public class ChessBoard {

    private static final BoardGenerator BOARD_GENERATOR = BoardGenerator.getInstance();

    private final Map<Square, Optional<ChessPiece>> board;

    private ChessBoard(Map<Square, Optional<ChessPiece>> board) {
        this.board = board;
    }

    public ChessBoard() {
        this(BOARD_GENERATOR.generate());
    }
}
