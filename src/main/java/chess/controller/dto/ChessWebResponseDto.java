package chess.controller.dto;

import chess.domain.player.Team;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChessWebResponseDto {

    private Long id;
    private List<WebDto> board;
    private List<WebDto> score;
    private WebDto turn;
    private String message;

    private ChessWebResponseDto(Long id, List<WebDto> board, List<WebDto> score, WebDto turn) {
        this.id = id;
        this.board = board;
        this.score = score;
        this.turn = turn;
    }

    public ChessWebResponseDto(Long id, List<WebDto> board, List<WebDto> score, WebDto turn, String message) {
        this.id = id;
        this.board = board;
        this.score = score;
        this.turn = turn;
        this.message = message;
    }

    public static ChessWebResponseDto of(Long id, ResponseDto responseDto) {
        List<WebDto> board = getBoardDto(responseDto.getBoard());
        List<WebDto> score = getScoreDto(responseDto.getScores());
        WebDto turn = getTurnDto(responseDto.getTurn());
        return new ChessWebResponseDto(id, board, score, turn);
    }

    public static ChessWebResponseDto of(Long id, ResponseDto responseDto, String message) {
        List<WebDto> board = getBoardDto(responseDto.getBoard());
        List<WebDto> score = getScoreDto(responseDto.getScores());
        WebDto turn = getTurnDto(responseDto.getTurn());
        return new ChessWebResponseDto(id, board, score, turn, message);
    }

    private static List<WebDto> getBoardDto(Map<Position, PieceDto> board) {
        return board.entrySet()
                .stream()
                .map(entry -> {
                    Position position = entry.getKey();
                    PieceDto pieceDto = entry.getValue();
                    String key = position.getFile().toString() + position.getRank().getRank();
                    String value = pieceDto.getTeam() + pieceDto.getPieceType();
                    return new WebDto(key, value);
                })
                .collect(Collectors.toList());
    }

    private static List<WebDto> getScoreDto(final Map<Team, Double> scores) {
        return scores.entrySet()
                .stream()
                .map(entry -> {
                    String key = entry.getKey().toString();
                    String value = String.valueOf(entry.getValue());
                    return new WebDto(key, value);
                })
                .collect(Collectors.toList());
    }

    private static WebDto getTurnDto(final Team turn) {
        return new WebDto(turn.toString(), turn.toString());
    }

    public Long getId() {
        return id;
    }

    public List<WebDto> getBoard() {
        return board;
    }

    public List<WebDto> getScore() {
        return score;
    }

    public WebDto getTurn() {
        return turn;
    }

    public String getMessage() {
        return message;
    }
}
