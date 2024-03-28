package controller;

import controller.command.*;
import view.format.command.PlayCommandFormat;
import view.format.command.PlayCommandFormatType;
import view.format.command.StartCommandsFormat;

import java.sql.SQLException;
import java.util.Map;

public class CommandRouter {

    private final Map<StartCommandsFormat, Command> startRouter;
    private final Map<PlayCommandFormatType, Command> playRouter;

    public CommandRouter(final ChessController controller) {
        this.startRouter = Map.of(
                StartCommandsFormat.START, new StartCommand(controller),
                StartCommandsFormat.CONTINUE, new ContinueCommand(controller),
                StartCommandsFormat.RECORD, new RecordCommand(controller),
                StartCommandsFormat.QUIT, new QuitCommand(controller));

        this.playRouter = Map.of(
                PlayCommandFormatType.MOVE, new MoveCommand(controller),
                PlayCommandFormatType.STATUS, new StartCommand(controller),
                PlayCommandFormatType.END, new EndCommand(controller),
                PlayCommandFormatType.QUIT, new QuitCommand(controller));
    }

    public void execute(final StartCommandsFormat command) throws SQLException {
        startRouter.get(command).executeStart();
    }

    public void execute(final PlayCommandFormat playCommandFormat, final int gameId) throws SQLException {
        final PlayCommandFormatType playCommandType = playCommandFormat.getPlayCommandType();
        playRouter.get(playCommandType).executePlay(playCommandFormat, gameId);
    }
}
