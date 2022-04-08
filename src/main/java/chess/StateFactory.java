package chess;

import chess.domain.Board;
import chess.domain.Symbol;
import chess.domain.piece.Nothing;
import chess.domain.piece.Piece;
import chess.domain.postion.Position;
import chess.domain.state.Black;
import chess.domain.state.State;
import chess.domain.state.White;
import chess.dto.board.PieceDto;
import chess.dto.board.PositionDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StateFactory {

    public static State of(final String turn, final List<PieceDto> pieces) {
        final Board board = makePiecesToBoard(pieces);

        return decideState(turn, board);
    }

    private static Board makePiecesToBoard(List<PieceDto> pieces) {
        final Map<Position, Piece> cells = new HashMap<>();

        for (PieceDto pieceDto : pieces) {
            addValueInCells(pieceDto, cells);
        }

        return new Board(cells);
    }

    private static void addValueInCells(final PieceDto pieceDto, final Map<Position, Piece> cells) {
        final PositionDto positionDto = pieceDto.getPosition();
        final int file = positionDto.getFile();
        final int rank = positionDto.getRank();
        final String symbol = pieceDto.getSymbol();
        final Position position = Position.of(file, rank);
        final Piece piece = Symbol.webSymbolToPiece(symbol);

        if (!(piece instanceof Nothing)) {
            cells.put(position, piece);
        }
    }

    private static State decideState(final String turn, final Board board) {
        if (turn.equals("white")) {
            return new White(board);
        }

        return new Black(board);
    }
}
