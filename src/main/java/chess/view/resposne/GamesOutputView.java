package chess.view.resposne;

import chess.controller.game.games.GamesOutput;
import java.util.List;

public class GamesOutputView implements GamesOutput {

    @Override
    public void printGames(List<Integer> games) {
        System.out.println("현재 유저의 게임들");
        games.forEach(System.out::println);
        System.out.println("게임을 시작하려면 join 게임번호 를 입력해주세요");
        System.out.println("게임을 생성하려면 create 를 입력해주세요");
    }
}
