package chess.domain.game;

public enum Team {

    BLACK {
        @Override
        public Team alter() {
            return WHITE;
        }
    },
    WHITE {
        @Override
        public Team alter() {
            return BLACK;
        }
    },
    NEUTRAL {
        @Override
        public Team alter() {
            return this;
        }
    };

    public abstract Team alter();
}
