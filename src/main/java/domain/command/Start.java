package domain.command;

import domain.board.Board;
import dto.DtoMapper;
import java.util.StringTokenizer;
import java.util.function.Supplier;
import view.OutputView;

public final class Start extends Command {

    private static final Command instance = new Start();

    private Start() {
    }

    @Override
    public Command next(final Command command) {
        if (command == this) {
            throw new UnsupportedOperationException("시작 상태에서 시작 상태로 넘어갈 수 없습니다");
        }
        return command;
    }


    public static Command getInstance() {
        return instance;
    }
}
