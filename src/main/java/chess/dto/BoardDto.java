package chess.dto;

import chess.Member;
import chess.model.piece.Piece;
import chess.model.square.File;
import chess.model.square.Rank;
import chess.model.square.Square;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BoardDto {

    private final List<List<PieceDto>> dto;
    private final String roomTitle;
    private final String whiteMemberName;
    private final String blackMemberName;

    private BoardDto(List<List<PieceDto>> dto, String roomTitle, String whiteMemberName,
                     String blackMemberName) {
        this.dto = dto;
        this.roomTitle = roomTitle;
        this.whiteMemberName = whiteMemberName;
        this.blackMemberName = blackMemberName;
    }

    public static BoardDto of(Map<Square, Piece> pieces, String roomName, Member whiteMember, Member blackMember) {
        List<List<PieceDto>> boardDto = new ArrayList<>();
        List<Rank> ranks = Arrays.asList(Rank.values());
        Collections.reverse(ranks);
        for (Rank rank : ranks) {
            boardDto.add(makeLineByFile(pieces, rank));
        }
        return new BoardDto(boardDto, roomName, whiteMember.getName(), blackMember.getName());
    }

    private static List<PieceDto> makeLineByFile(Map<Square, Piece> pieces, Rank rank) {
        List<PieceDto> tempLine = new ArrayList<>();
        for (File file : File.values()) {
            Piece piece = pieces.get(Square.of(file, rank));
            tempLine.add(PieceDto.of(piece, file, rank));
        }
        return tempLine;
    }

    public List<List<PieceDto>> getDto() {
        return dto;
    }

    public String getRoomTitle() {
        return roomTitle;
    }

    public String getWhiteMemberName() {
        return whiteMemberName;
    }

    public String getBlackMemberName() {
        return blackMemberName;
    }
}
