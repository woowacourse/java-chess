package chess.dto;

public class GameTableDTO {
    private final Long id;
    private final String state;
    private final Long whiteMemberId;
    private final Long blackMemberId;

    public GameTableDTO(Long id, String state, Long whiteMemberId, Long blackMemberId) {
        this.id = id;
        this.state = state;
        this.whiteMemberId = whiteMemberId;
        this.blackMemberId = blackMemberId;
    }

    public Long getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public Long getWhiteMemberId() {
        return whiteMemberId;
    }

    public Long getBlackMemberId() {
        return blackMemberId;
    }
}
