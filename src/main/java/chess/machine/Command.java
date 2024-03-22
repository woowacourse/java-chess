package chess.machine;

import chess.domain.chessBoard.ChessBoard;

public interface Command {
    void conductCommand(ChessBoard chessBoard);
}
