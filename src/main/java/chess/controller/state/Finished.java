package chess.controller.state;

public class Finished implements ChessGameState {

    @Override
    public ChessGameState start() {
        return alertFinished();
    }

    @Override
    public ChessGameState move(String from, String to) {
        return alertFinished();
    }

    @Override
    public ChessGameState status() {
        return alertFinished();
    }

    @Override
    public ChessGameState end() {
        return alertFinished();
    }

    @Override
    public boolean isEnded() {
        return true;
    }

    private Finished alertFinished() {
        throw new IllegalStateException("이미 게임이 종료되었습니다.");
    }

}
