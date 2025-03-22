package chess;

import chess.board.ChessBoard;
import chess.board.ChessBoardFactory;
import chess.board.Color;
import chess.board.GameState;
import chess.board.Position;
import chess.view.View;

public class Chess {

    private final View view;

    public Chess(View view) {
        this.view = view;
    }

    public void play() {
        ChessBoard chessBoard = ChessBoardFactory.createChessBoard();
        view.printStartGuide(chessBoard);
        Color currentTurnColor = Color.WHITE;
        while(true) {
            view.printChessBoard(chessBoard);
            view.printTurnGuide(currentTurnColor);
            try {
                Position start = view.readPlayerStartPosition();
                Position end = view.readPlayerEndPosition();
                GameState gameState = chessBoard.movePiece(currentTurnColor, start, end);
                if(gameState == GameState.END) {
                    view.printChessBoard(chessBoard);
                    System.out.println(currentTurnColor + "가 우승하였습니다.");
                    return;
                }
            } catch (RuntimeException ex) {
                view.printExceptionGuide(ex.getMessage());
                continue;
            }
            currentTurnColor = changeTurnColor(currentTurnColor);
        }
    }

    private Color changeTurnColor(Color currentTurnColor) {
        return currentTurnColor.opposite();
    }
}
