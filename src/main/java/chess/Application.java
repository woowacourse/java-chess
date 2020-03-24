package chess;

import chess.domain.ChessBoard;
import chess.domain.Menu;
import chess.factory.ChessPieceFactory;
import chess.view.InputView;
import chess.view.OutputView;
import jdk.internal.util.xml.impl.Input;

public class Application {
    public static void main(String []args) {
        ChessBoard chessBoard = new ChessBoard(ChessPieceFactory.blackTeamCreate(), ChessPieceFactory.whiteTeamCreate());
        while (new Menu(InputView.checkStart()).isStart()){

        }
    }
}
