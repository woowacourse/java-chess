package chess.domain.dto;

import java.util.Map;
import java.util.Objects;

public class ChessRoomDto {
    private final int roomNo;
    private final Map<PositionDto, PieceDto> chessBoard;
    private final String turn;
    private final double blackScore;
    private final double whiteScore;

    public ChessRoomDto(int roomNo, Map<PositionDto, PieceDto> chessBoard, String turn, double blackScore, double whiteScore) {
        this.roomNo = roomNo;
        this.chessBoard = chessBoard;
        this.turn = turn;
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChessRoomDto that = (ChessRoomDto) o;
        return roomNo == that.roomNo && Double.compare(that.blackScore, blackScore) == 0 && Double.compare(that.whiteScore, whiteScore) == 0 && Objects.equals(chessBoard, that.chessBoard) && Objects.equals(turn, that.turn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomNo, chessBoard, turn, blackScore, whiteScore);
    }

    public Map<PositionDto, PieceDto> getChessBoard() {
        return chessBoard;
    }

    public String getTurn() {
        return turn;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }
}
