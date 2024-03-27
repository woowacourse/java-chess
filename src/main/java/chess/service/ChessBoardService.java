package chess.service;

import chess.domain.chessGame.ChessBoard;
import chess.repository.ChessBoardRepository;

public class ChessBoardService {

    private final ChessBoardRepository chessBoardRepository;

    public ChessBoardService(ChessBoardRepository chessBoardRepository) {
        this.chessBoardRepository = chessBoardRepository;
    }

    public ChessBoard findChessBoard() {
        return chessBoardRepository.findChessBoard();
    }

    public ChessBoard saveChessBoard(ChessBoard chessBoard) {
        return chessBoardRepository.saveChessBoard(chessBoard);
    }

    public void deleteChessBoard() {
        chessBoardRepository.deleteChessBoard();
    }
}
