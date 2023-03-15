package view;

public enum Command {
    START, END;

    public static Command find(String command) {
        if (command.equalsIgnoreCase(InputView.START)) {
            return START;
        }
        if(command.equalsIgnoreCase(InputView.END)){
            return END;
        }
        throw new IllegalArgumentException("start 또는 end 를 입력해주세요.");
    }
}
