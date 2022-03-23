package chess;

import chess.domain.Board;
import chess.domain.ChessGame;
import chess.domain.File;
import chess.domain.Location;
import chess.domain.Rank;
import chess.domain.piece.Piece;
import java.util.Map;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        System.out.println("체스 게임을 시작합니다.");
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");
        Scanner scanner = new Scanner(System.in);
        ChessGame chessGame = new ChessGame();
        do {
            String inputValue = scanner.nextLine();
            chessGame.execute(GameCommand.of(inputValue));
        } while (chessGame.isStarted());
    }
}
