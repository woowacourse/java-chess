package chess.dto;

import chess.domain.board.File;
import chess.domain.board.Rank;
import chess.domain.board.Square;
import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PiecesDto {
    private final List<PieceDto> piecesDto;

    public PiecesDto(List<PieceDto> piecesDto) {
        this.piecesDto = piecesDto;
    }

    public Map<Square, Piece> generateBoard() {
        Map<Square, Piece> initBoard = initializeBoard();

        for (PieceDto pieceDto : piecesDto) {
            Square square = new Square(File.valueOf(pieceDto.getFile()), Rank.valueOf(pieceDto.getRank()));
            initBoard.put(square, pieceDto.makePiece());
        }

        return initBoard;
    }

    private Map<Square, Piece> initializeBoard() {
        List<Square> squares = generateSquares();
        Map<Square, Piece> initBoard = new LinkedHashMap<>();

        for (Square square : squares) {
            initBoard.put(square, new Empty());
        }

        return initBoard;
    }

    private List<Square> generateSquares() {
        return Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(File.values())
                        .map(file -> new Square(file, rank)))
                .collect(Collectors.toList());
    }
}
