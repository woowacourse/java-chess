package chess.dto;

import chess.domain.board.ChessBoard;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.Map;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toMap;

public class ChessBoardDto {
    private final Map<String, PieceDto> chessBoard;

    private ChessBoardDto(Map<String, PieceDto> chessBoard) {
        this.chessBoard = chessBoard;
    }

    public Map<String, PieceDto> board() {
        return Collections.unmodifiableMap(chessBoard);
    }

    public static ChessBoardDto from(ChessBoard chessBoard) {
        return chessBoard.board().entrySet()
                .stream()
                .filter(entrySet -> entrySet.getValue().isNotBlank())
                .collect(collectingAndThen(
                        toMap(entrySet -> entrySet.getKey().getNotation(),
                                entrySet -> PieceDto.from(entrySet.getValue())),
                        ChessBoardDto::new));
    }

    public ChessBoard toChessBoard() {
        return this.chessBoard.entrySet()
                .stream()
                .collect(collectingAndThen(
                        toMap(
                                entry -> Position.of(entry.getKey()),
                                entry -> entry.getValue().toPiece()
                        )
                , ChessBoard::from));
    }
}
