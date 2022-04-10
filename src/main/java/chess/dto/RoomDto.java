package chess.dto;

import chess.domain.member.Member;

public class RoomDto {

    private final int roomId;
    private final String roomTitle;
    private final String whiteMember;
    private final String blackMember;

    public RoomDto(int roomId, String roomTitle, Member whiteMember, Member blackMember) {
        this.roomId = roomId;
        this.roomTitle = roomTitle;
        this.whiteMember = whiteMember.getName();
        this.blackMember = blackMember.getName();
    }

    public int getRoomId() {
        return roomId;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public String getWhiteMember() {
        return whiteMember;
    }

    public String getBlackMember() {
        return blackMember;
    }
}
