package chess.dao.entity;

import java.util.Objects;

public class PlayerEntity {
    private Long id;
    private final ChessRoomEntity chessRoomEntity;

    public PlayerEntity(ChessRoomEntity chessRoomEntity) {
        this.chessRoomEntity = chessRoomEntity;
    }

    public PlayerEntity(Long id, ChessRoomEntity chessRoomEntity) {
        this.id = id;
        this.chessRoomEntity = chessRoomEntity;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public ChessRoomEntity getChessRoomEntity() {
        return chessRoomEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PlayerEntity)) {
            return false;
        }
        PlayerEntity that = (PlayerEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
