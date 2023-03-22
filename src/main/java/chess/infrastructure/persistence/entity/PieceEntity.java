package chess.infrastructure.persistence.entity;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.movestrategy.PieceMovementStrategy;
import chess.domain.piece.position.File;
import chess.domain.piece.position.PiecePosition;
import chess.domain.piece.position.Rank;

import java.util.concurrent.atomic.AtomicLong;

public class PieceEntity {

    private static final AtomicLong idGen = new AtomicLong(1L);

    private final Long id;
    private final int rank;
    private final char file;
    private final String color;
    private final String movementType;
    private final Long chessGameId;

    public PieceEntity(final Long id, final int rank, final char file, final String color, final String movementType, final Long chessGameId) {
        this.id = id;
        this.rank = rank;
        this.file = file;
        this.color = color;
        this.movementType = movementType;
        this.chessGameId = chessGameId;
    }

    public static PieceEntity fromDomain(final Piece piece, final Long chessGameId) {
        return new PieceEntity(
                genId(piece),
                piece.piecePosition().rank().value(),
                piece.piecePosition().file().value(),
                piece.color().name(),
                piece.pieceMovementStrategy().getClass().getName(),
                chessGameId
        );
    }

    private static Long genId(final Piece piece) {
        final Long id = piece.id();
        if (id != null) {
            return id;
        }
        return idGen.getAndIncrement();
    }

    public Piece toDomain() {
        final Color color = Color.valueOf(this.color);
        return new Piece(id,
                PiecePosition.of(Rank.from(rank), File.from(file)),
                makeStrategy(color)
        );
    }

    private PieceMovementStrategy makeStrategy(final Color color) {
        try {
            return PieceMovementStrategy.class.cast(
                    Class.forName(movementType).getDeclaredConstructor(Color.class).newInstance(color)
            );
        } catch (Exception e) {
            throw new RuntimeException("피스 생성 중 문제 발생", e);
        }
    }

    public Long id() {
        return id;
    }

    public int rank() {
        return rank;
    }

    public char file() {
        return file;
    }

    public String color() {
        return color;
    }

    public String movementType() {
        return movementType;
    }

    public Long chessGameId() {
        return chessGameId;
    }
}
