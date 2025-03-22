package chess;

import chess.piece.Piece;
import java.util.List;
import view.InputView;
import view.OutputView;

public class ChessRunner {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessRunner(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run(){
        ChessBoard chessBoard = initializeBoard();
        showBoard(chessBoard);
        startGame(chessBoard);
    }

    private void showBoard(ChessBoard chessBoard){
        List<Piece> alivePiece = chessBoard.findAlivePiece();
        outputView.printBoard(alivePiece);
    }


    private void startGame(ChessBoard chessBoard){
        Color turn  = Color.WHITE;
        while (!chessBoard.isKingDead()) {
            move(chessBoard,turn);
            showBoard(chessBoard);
            turn = turn.opposite();
        }
        Color winColor = chessBoard.findWinColor();
        outputView.printWinner(winColor);
    }

    public void move(ChessBoard chessBoard,Color turn){
        while(true){
            try{
                Position startPosition = inputView.getStartPosition(turn);
                Position endPosition = inputView.getEndPosition();
                chessBoard.movePiece(startPosition,endPosition,turn);
                return;
            }catch(IllegalArgumentException e){
                outputView.printExceptionMessage(e.getMessage());
            }
        }
    }

    private ChessBoard initializeBoard(){
        return new ChessBoard(PiecesFactory.getInitializedPieces());
    }
}
