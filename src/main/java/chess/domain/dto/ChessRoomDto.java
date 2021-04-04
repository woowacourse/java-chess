package chess.domain.dto;

import java.util.Map;
import java.util.Objects;

public class ChessRoomDto {
    private int room_no;
    private Map<PositionDto, PieceDto> chessBoard;
    private String turn;
    private double blackScore;
    private double whiteScore;

    public ChessRoomDto() {
    }

    public ChessRoomDto(String turn, double blackScore, double whiteScore) {
        this.turn = turn;
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    public ChessRoomDto(Map<PositionDto, PieceDto> chessBoard, String turn, double blackScore, double whiteScore) {
        this.chessBoard = chessBoard;
        this.turn = turn;
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    public ChessRoomDto(int room_no, Map<PositionDto, PieceDto> chessBoard, String turn, double blackScore, double whiteScore) {
        this.room_no = room_no;
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
        return room_no == that.room_no && Double.compare(that.blackScore, blackScore) == 0 && Double.compare(that.whiteScore, whiteScore) == 0 && Objects.equals(chessBoard, that.chessBoard) && Objects.equals(turn, that.turn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room_no, chessBoard, turn, blackScore, whiteScore);
    }

    public int getRoom_no() {
        return room_no;
    }

    public void setRoom_no(int room_no) {
        this.room_no = room_no;
    }

    public Map<PositionDto, PieceDto> getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(Map<PositionDto, PieceDto> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public void setBlackScore(double blackScore) {
        this.blackScore = blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }

    public void setWhiteScore(double whiteScore) {
        this.whiteScore = whiteScore;
    }
}
