package controller.chess;

public class SystemEndController extends ChessController {

    protected SystemEndController() {
    }

    @Override
    public ChessController run() {
        return this;
    }

    @Override
    protected ChessController readNextController() {
        return this;
    }

    @Override
    public boolean isEndController() {
        return true;
    }
}
