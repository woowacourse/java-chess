package chess.command;

import chess.position.Position;

public interface Command {

    static Command from(String command) {
        if (command.equals("start")) {
            return new StartCommand();
        }
        if (command.equals("end")) {
            return new EndCommand();
        }
        if (command.startsWith("move")) {
            return new MoveCommand(command);
        }
        if (command.equals("status")) {
            return new StatusCommand();
        }
        throw new IllegalArgumentException("잘못된 명령어를 입력하셨습니다.");
    }

    boolean isFinished();

    boolean isStart();

    boolean isEnd();

    boolean isMove();

    boolean isStatus();

    Position from();

    Position to();
}
