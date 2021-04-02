package chess.domain.game;

import chess.domain.location.Position;
import chess.domain.state.State;

import java.util.Arrays;
import java.util.List;

public enum Command {
    START(0) {
        @Override
        public void action(Game game) {
            game.start();
        }
    },
    END(0) {
        @Override
        public void action(Game game) {
            game.end();
        }
    },
    MOVE(2) {
        @Override
        public void action(Game game) {
            game.move(Position.from(options().get(0)), Position.from(options().get(1)));
        }

    },
    STATUS(0) {
        @Override
        public void action(Game game) {
            game.status();
        }
    };

    public static final String SPACE_REGEX = "\\s+";
    private int optionCount;
    private List<String> options;

    Command(int optionCount) {
        this.optionCount = optionCount;
        this.options = Arrays.asList(new String[optionCount]);
    }

    public static Command from(String input) {
        String[] argv = input.split(SPACE_REGEX);
        Command command;
        try {
            command = Command.valueOf(argv[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("명령어가 존재하지 않습니다.");
        }
        command.setOptions(argv);
        return command;
    }

    private void setOptions(String[] argv) {
        validateOptionSize(argv);
        for (int i = 0; i < optionCount; i++) {
            this.options.set(i, argv[i + 1]);
        }
    }

    private void validateOptionSize(String[] argv) {
        if (argv.length != optionCount + 1) {
            throw new IllegalArgumentException("잘못된 갯수의 옵션 입력입니다.");
        }
    }

    public List<String> options() {
        return options;
    }

    public abstract void action(Game game);
}
