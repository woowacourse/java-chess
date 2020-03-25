package chess;

import chess.domain.ChessBoard;
import chess.domain.Menu;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.chesspiece.Bishop;
import chess.factory.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    public static void main(String []args) {
        ChessBoard chessBoard = new ChessBoard(BoardFactory.createBoard());
        while (new Menu(InputView.checkStart()).isStart()){
            OutputView.printBoard(chessBoard);
        }
        Bishop bishop = new Bishop(new Position(3, 3), Team.BLACK);
        System.out.println(bishop.makeCanMovePositions());
    }
}
