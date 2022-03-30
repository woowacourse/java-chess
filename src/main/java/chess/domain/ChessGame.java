package chess.domain;

import chess.domain.piece.ChessPiece;
import java.util.HashMap;
import java.util.Map;

public class ChessGame {
    private final ChessBoard chessBoard;

    private ChessGame() {
        this.chessBoard = ChessBoard.initialize();
    }

    public static ChessGame create() {
        return new ChessGame();
    }

    public Map<ChessBoardPosition, ChessPiece> getChessBoardInformation() {
        return new HashMap<>(chessBoard.getMapInformation());
    }
}
