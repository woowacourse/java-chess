package chess;

import chess.domain.ChessBoard;
import chess.domain.Menu;
import chess.domain.Position;
import chess.domain.Team;
import chess.domain.chesspiece.Bishop;
import chess.domain.chesspiece.King;
import chess.domain.chesspiece.Knight;
import chess.factory.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        ChessBoard chessBoard = new ChessBoard(BoardFactory.createBoard());
//        while (new Menu(InputView.checkStart()).isStart()) {
//            OutputView.printBoard(chessBoard);
//        }
        Knight knight = new Knight(new Position(4, 4), Team.BLACK);
        List<Position> positions = knight.makeCanMovePositions();
        for(Position position : positions){
            System.out.println(position.getX() +", "+position.getY());
        }
        System.out.println();
    }
}
