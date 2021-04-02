package chess.utils;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.board.position.Ypoint;
import chess.domain.game.ChessGame;
import chess.domain.game.Score;
import chess.domain.piece.Piece;
import chess.dto.ChessGameDto;
import chess.dto.SquareDto;
import chess.dto2.MovableResponseDto;
import chess.dto2.PositionDto;
import chess.dto2.RankDto;
import chess.dto2.ScoreDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class DtoAssembler {

    public static ChessGameDto chessGameDto(final ChessGame chessGame) {
        List<SquareDto> squareDtos = new ArrayList<>();
        Map<Position, Piece> squares = chessGame.board().squares();

        for (Entry<Position, Piece> entry : squares.entrySet()) {
            Position position = entry.getKey();
            Piece piece = entry.getValue();
            squareDtos.add(new SquareDto(position.toString(), piece.getSymbol()));
        }

        return new ChessGameDto(squareDtos, chessGame.state());
    }

    public static List<RankDto> ranks(final Board board) {
        List<RankDto> rankDtos = new ArrayList<>();

        for (Ypoint ypoint : Ypoint.values()) {
            List<String> symbols = ypointSymbols(board, ypoint);
            rankDtos.add(new RankDto(symbols));
        }

        return rankDtos;
    }
    private static List<String> ypointSymbols(Board board, Ypoint ypoint) {
        return board.piecesByYpoint(ypoint)
            .stream()
            .map(Piece::getSymbol)
            .collect(Collectors.toList());
    }

    public static MovableResponseDto movableResponse(List<Position> positions) {
        return new MovableResponseDto(
            positions.stream()
                .map(Position::toString)
                .map(PositionDto::new)
                .collect(Collectors.toList())
        );
    }
}
