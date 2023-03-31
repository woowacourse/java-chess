package controller.chess;

public abstract class ChessController {

    public abstract ChessController run();

    protected abstract ChessController readNextController();

    public abstract boolean isEndController();
}
