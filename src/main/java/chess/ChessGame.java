package chess;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.piece.Piece;
import chess.domain.position.Column;
import chess.domain.position.Position;
import chess.domain.position.Row;
import chess.dto.PieceDto;
import chess.view.OutputView;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChessGame {

    private final OutputView outputView;

    public ChessGame(OutputView outputView) {
        this.outputView = outputView;
    }

    public void play() {
        Board board = BoardFactory.startGame();

        showBoard(board);
    }

    private void showBoard(Board board) {
        Map<Position, PieceDto> boardDto = new HashMap<>();
        for (Row row : Row.values()) {
            for (Column column : Column.values()) {
                Position position = new Position(row, column);
                Optional<Piece> piece = board.find(position);
                if (piece.isEmpty()) {
                    continue;
                }
                boardDto.put(position, PieceDto.from(piece.get()));
            }
        }
        outputView.printBoard(boardDto);
    }
}
