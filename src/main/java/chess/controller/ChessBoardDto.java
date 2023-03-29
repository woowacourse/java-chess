package chess.controller;

import chess.domain.ChessBoard;
import java.util.List;

public class ChessBoardDto {

    private final List<String> chessBoardView;

    public ChessBoardDto(ChessBoard chessBoard) {
        chessBoardView = ChessViewMapper.generateChessView(chessBoard.getSquares());
    }

    public List<String> getBoard() {
        return chessBoardView;
    }
}
