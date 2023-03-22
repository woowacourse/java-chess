package chess.domain.state;

public class RunningState implements GameState{
    public static final GameState STATE = new RunningState();

    public void startGame(Runnable runnable){
        throw new IllegalArgumentException("입력된 명령어가 올바르지 않습니다.");
    }

    public void movePiece(Runnable runnable){
        runnable.run();
    }

    public void finishGame(Runnable runnable){
        runnable.run();
    }

    public boolean isFinished(){
        return false;
    }
}
