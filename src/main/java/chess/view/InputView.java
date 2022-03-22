package chess.view;

import static camp.nextstep.edu.missionutils.Console.*;

import chess.Command;

public class InputView {

    private InputView() {
    }


    public static Command inputCommand() {
        System.out.println("게임 시작은 start, 종료는 end 명령을 입력하세요.");

        return Command.of(readLine());
    }

}
