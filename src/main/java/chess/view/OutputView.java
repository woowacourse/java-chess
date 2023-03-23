package chess.view;

import chess.domain.pieces.component.Name;

import java.util.List;

public class OutputView {

    public static void printGameStart() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(List<List<Name>> pieceNames) {
        for(List<Name> names : pieceNames){
            for(Name name : names){
                System.out.print(name.getName());
            }
            System.out.println();
        }
    }
}
