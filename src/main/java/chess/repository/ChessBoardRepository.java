package chess.repository;

import chess.domain.chessGame.ChessBoard;

public interface ChessBoardRepository {

    ChessBoard findChessBoard();

    ChessBoard saveChessBoard(ChessBoard chessBoard);

    void deleteChessBoard();
}
