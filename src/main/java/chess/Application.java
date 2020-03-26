package chess;

import chess.domain.ChessBoard;
import chess.domain.ChessGame;
import chess.domain.Menu;
import chess.domain.Position;
import chess.domain.Team;
import chess.factory.BoardFactory;
import chess.view.InputView;
import chess.view.OutputView;

public class Application {
    private static Menu menu;
    public static void main(String[] args) {
        OutputView.printRule();
        ChessGame chessGame = new ChessGame();
        initMenu();
       while (menu.isNotEnd()){
           ChessBoard chessBoard = chessGame.play(menu, OutputView::printScore);
           OutputView.printBoard(chessBoard);
           initMenu();
       }

    }
    private static void initMenu() {
        menu = new Menu(InputView.input());
    }
}


////해당 말이 갈 수 있는 방향에 대해서 Route
////비숍의 경우 한 점에서 우측상단 우측하단 좌측상단 좌측하단
////Destination이 (Posiotion)이 이 루트 리스트에서 저 Position을 찾아 - 없으면 이동불가
////Route -> Position의 리스트 => 해당 루트를 따라서 점검해보는데 그 떄 다른 말이 있으면 이동불가
//d4 ->   f5 g6 (h7)
//        ...
//        ...
//        ...
//// (h7)목적지에 아군말이 있으면 이동불가
////모든 조건 만족시 이동
//---------------------------------
//미구현
//- 리팩토링 아예 안됨
//- 점수 규칙 중 (pawn의 기본 점수는 1점이다. 하지만 같은 세로줄에 같은 색의 폰이 있는 경우 1점이 아닌 0.5점을 준다.) 안됨
//- Input Outputview 이용해서 게임진행

