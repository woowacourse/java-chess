package chess.dao.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import chess.domain.piece.*;
import chess.domain.position.File;
import chess.domain.position.Position;
import chess.domain.position.Rank;

public class PieceEntity {

    private static Map<String, BiFunction<Position, Side, Piece>> createPieces = new HashMap<>();

    static {
        createPieces.put("KING", King::new);
        createPieces.put("ROOK", Rook::new);
        createPieces.put("BISHOP", Bishop::new);
        createPieces.put("PAWN", Pawn::new);
        createPieces.put("KNIGHT", Knight::new);
        createPieces.put("QUEEN", Queen::new);
    }

    private Long id;
    private String rank;
    private String file;
    private String type;
    private String side;
    private Long gameId;

    public static class Builder {

        private Long id;
        private String rank;
        private String file;
        private String type;
        private String side;
        private Long gameId;


        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder rank(String rank) {
            this.rank = rank;
            return this;
        }

        public Builder file(String file) {
            this.file = file;
            return this;
        }

        public Builder type(String type) {
            this.type = type;
            return this;
        }

        public Builder side(String side) {
            this.side = side;
            return this;
        }

        public Builder gameId(Long gameId) {
            this.gameId = gameId;
            return this;
        }

        public PieceEntity build() {
            return new PieceEntity(this);
        }
    }

    private PieceEntity(Builder builder) {
        this.id = builder.id;
        this.rank = builder.rank;
        this.file = builder.file;
        this.type = builder.type;
        this.side = builder.side;
        this.gameId = builder.gameId;
    }

    public Piece generatePiece() {
        Position position = new Position(File.of(Integer.parseInt(file)), Rank.of(Integer.parseInt(rank)));
        Side side = Side.of(this.side);
        BiFunction<Position, Side, Piece> positionSidePieceBiFunction = createPieces.get(type);
        return positionSidePieceBiFunction.apply(position, side);
    }

    public Long getId() {
        return id;
    }

    public String getRank() {
        return rank;
    }

    public String getFile() {
        return file;
    }

    public String getType() {
        return type;
    }

    public String getSide() {
        return side;
    }

    public Long getGameId() {
        return gameId;
    }
}
