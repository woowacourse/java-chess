package chess.controller;

import chess.domain.Board;
import chess.domain.Position;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Pawn;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class ChessController {

    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        Board board = initBoard();
        String command = "";
        do {
            command = runCommand(board, command);
        } while (!command.equals("Q"));
    }

    private String runCommand(final Board board, String command) {
        try {
            outputView.printBoard(board.getPositionToPiece());
            command = inputView.readCommand();
            if (command.equals("MOVE")) {
                movePiece(board);
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
        return command;
    }

    private void movePiece(final Board board) {
        Position departure = inputView.readDeparturePosition();
        Position destination = inputView.readDestinationPosition();
        board.move(departure, destination);
    }

    private static Board initBoard() {
        List<Piece> initPiece = new ArrayList<>();
        initPiece.addAll(Pawn.initPawn());
        initPiece.addAll(King.initKing());
        initPiece.addAll(Rook.initRook());
        initPiece.addAll(Bishop.initBishop());
        initPiece.addAll(Queen.initQueen());
        initPiece.addAll(Knight.initKnight());
        return new Board(initPiece);
    }
}
