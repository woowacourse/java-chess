package controller.status;

public interface ChessProgramStatus {

    String readCommand();

    int gameId();

    boolean isNotEnd();

    boolean isStarting();

    boolean isRunning();
}
