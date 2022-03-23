package chess;

import chess.domain.ChessGame;
import java.util.List;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
        Scanner scanner = new Scanner(System.in);
        ChessGame chessGame = new ChessGame();
        do {
            String inputValue = scanner.nextLine();
            chessGame.execute(List.of(inputValue.split(" ")));
        } while (chessGame.isRunning());
    }
}
