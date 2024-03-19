package chess.view;

import java.util.List;

public class OutputView {

    public void printStartMessage(){
        System.out.println("체스 게임을 시작합니다.");
    }

    public void printBoard(List<List<String>> board){
        board.forEach(this::printEach);
    }

    private void printEach(List<String> strings) {
        StringBuilder stringBuilder = new StringBuilder();
        strings.forEach(stringBuilder::append);
        System.out.println(stringBuilder);
    }
}
