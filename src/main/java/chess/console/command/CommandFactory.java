package chess.console.command;

public final class CommandFactory {

    public static Command getPreGameInstanceFrom(String input) {
        if (input.equals("start")) {
            return new Start();
        }

        if (input.equals("end")) {
            return new End();
        }

        throw new IllegalArgumentException("start 혹은 end를 입력해주세요");
    }

    public static Command getInGameInstanceFrom(String input) {

        if (input.startsWith("move")) {
            return new Move(input);
        }

        if (input.equals("end")) {
            return new End();
        }

        if (input.equals("status")) {
            return new Status();
        }

        throw new IllegalArgumentException("move, status 혹은 end를 입력해주세요");
    }
}
