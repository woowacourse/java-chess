package chess.domain.status;

import chess.domain.chessboard.ChessBoard;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

public interface GameStatus {

    static GameStatus getInitialStatus() {
        return new KingAliveStatus(Color.initialTurn());
    }

    void validatePlayerTurn(ChessBoard chessBoard, Position from);

    boolean isGameOver();

    Color getWinner();

    Color getTurn();

    GameStatus nextStatus(Piece capturedPiece);
}
