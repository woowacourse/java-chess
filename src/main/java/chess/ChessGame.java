package chess;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.PieceDto;
import chess.view.OutputView;
import java.util.HashMap;
import java.util.List;
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
        List<Position> positions = Position.ALL_POSITIONS;
        Map<Position, PieceDto> boardDto = new HashMap<>();
        positions.forEach(position -> addPiece(board, position, boardDto));
        outputView.printBoard(boardDto);
    }

    private void addPiece(Board board, Position position, Map<Position, PieceDto> boardDto) {
        Optional<Piece> optionalPiece = board.find(position);
        if (optionalPiece.isEmpty()) {
            return;
        }
        Piece piece = optionalPiece.get();
        boardDto.put(position, PieceDto.from(piece));
    }
}
