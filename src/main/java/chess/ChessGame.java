package chess;

import chess.domain.Board;
import chess.domain.BoardFactory;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import chess.dto.PieceDto;
import chess.view.GameCommand;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ChessGame {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessGame(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void play() {
        outputView.printStartGame();
        GameCommand gameCommand;
        do {
            gameCommand = inputView.readCommand();
            play(gameCommand);
        } while (gameCommand.isContinue());
    }

    private void play(GameCommand gameCommand) {
        if (gameCommand == GameCommand.START) {
            Board board = BoardFactory.startGame();
            showBoard(board);
        }
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
