package chess.domain.dto;

import java.util.Map;

public class ChessRoomDto {
    private int room_no;
    private Map<PositionDto, PieceDto> chessBoard;
    private String turn;
    private double blackScore;
    private double whiteScore;

    public ChessRoomDto() {
    }

    public ChessRoomDto(Map<PositionDto, PieceDto> chessBoard, String turn, double blackScore, double whiteScore) {
        this.chessBoard = chessBoard;
        this.turn = turn;
        this.blackScore = blackScore;
        this.whiteScore = whiteScore;
    }

    public int getRoom_no() {
        return room_no;
    }

    public Map<PositionDto, PieceDto> getChessBoard() {
        return chessBoard;
    }

    public void setChessBoard(Map<PositionDto, PieceDto> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }

    public void setBlackScore(double blackScore) {
        this.blackScore = blackScore;
    }

    public void setWhiteScore(double whiteScore) {
        this.whiteScore = whiteScore;
    }

    public void setRoom_no(int room_no) {
        this.room_no = room_no;
    }

    public String getTurn() {
        return turn;
    }

    public double getBlackScore() {
        return blackScore;
    }

    public double getWhiteScore() {
        return whiteScore;
    }
}
