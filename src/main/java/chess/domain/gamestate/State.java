package chess.domain.gamestate;

public interface State {

    boolean isFinished();

    State processCommand(CommandType command);

}
