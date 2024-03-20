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

    public void start() {
        outputView.printStartGame();
        GameCommand command = inputView.readCommand();
        if (command == GameCommand.START) {
            Board board = BoardFactory.startGame();
            showBoard(board);
            play(board);
        }
        if (command == GameCommand.MOVE) {
            throw new IllegalArgumentException("아직 게임을 시작하지 않았습니다.");
        }
    }

    private void play(Board board) {
        GameCommand gameCommand;
        do {
            gameCommand = inputView.readCommand();
            play(gameCommand, board);
        } while (gameCommand.isContinue());
    }

    private void play(GameCommand gameCommand, Board board) {
        if (gameCommand == GameCommand.MOVE) {
            Position start = inputView.readPosition();
            Position end = inputView.readPosition();
            board.move(start, end);
            showBoard(board);
        }
        if (gameCommand == GameCommand.START) {
            throw new IllegalArgumentException("이미 게임을 시작했습니다.");
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
