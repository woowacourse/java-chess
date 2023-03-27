package chess.dao.entity;

public class ChessGameEntity {

    private Long id;
    private String turn;

    public static class Builder {
        private Long id;
        private String turn;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder turn(String turn) {
            this.turn = turn;
            return this;
        }

        public ChessGameEntity build() {
            return new ChessGameEntity(this);
        }
    }

    private ChessGameEntity(Builder builder) {
        id = builder.id;
        turn = builder.turn;
    }

    public Long getId() {
        return id;
    }

    public String getTurn() {
        return turn;
    }
}
