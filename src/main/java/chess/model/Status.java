package chess.model;

public enum Status {
    EMPTY {
        @Override
        public Status changeStatus(GameCommand gameCommand) {
            if (gameCommand.isMove() || gameCommand.isStatus()) {
                throw new IllegalStateException("잘못된 명령어 실행입니다.");
            }
            if (gameCommand.isStart()) {
                return PLAYING;
            }
            return this;
        }
    },

    PLAYING {
        @Override
        public Status changeStatus(GameCommand gameCommand) {
            if (gameCommand.isStatus()) {
                return RESULT;
            }
            if (gameCommand.isEnd()) {
                return EMPTY;
            }
            return this;
        }
    },

    RESULT {
        @Override
        public Status changeStatus(GameCommand gameCommand) {
            if (gameCommand.isStart()) {
                return PLAYING;
            }
            if (gameCommand.isEnd()) {
                return EMPTY;
            }
            if (gameCommand.isStatus()) {
                return this;
            }
            throw new IllegalStateException("잘못된 상태입력입니다.");
        }
    };

    public abstract Status changeStatus(GameCommand gameCommand);

    public boolean isPlaying() {
        return this.equals(PLAYING);
    }

    public boolean isEmpty() {
        return this.equals(EMPTY);
    }
}
