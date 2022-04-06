package chess.model.status;

import chess.model.GameCommand;

public enum Status {
    READY {
        @Override
        public Status changeStatus(GameCommand gameCommand) {
            if (gameCommand.isMove() || gameCommand.isStatus()) {
                throw new IllegalStateException("잘못된 명령어 실행입니다.");
            }
            if (gameCommand.isStart()) {
                return PLAYING;
            }
            return END;
        }
    },

    PLAYING {
        @Override
        public Status changeStatus(GameCommand gameCommand) {
            if (gameCommand.isStatus() || gameCommand.isEnd()) {
                return END;
            }
            return this;
        }
    },

    END {
        @Override
        public Status changeStatus(GameCommand gameCommand) {
            if (gameCommand.isStatus()) {
                return this;
            }
            throw new IllegalStateException("잘못된 상태입력입니다.");
        }
    };

    public abstract Status changeStatus(GameCommand gameCommand);

    public boolean isRunning() {
        return this.equals(PLAYING) || this.equals(READY);
    }
}
