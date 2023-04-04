package chessgame.controller.commandstatus;

import java.util.List;

public interface CommandStatus {

    boolean canContinue();

    CommandStatus start(final List<String> commands);

    CommandStatus move(final List<String> commands);

    CommandStatus status();

    CommandStatus end();
}
