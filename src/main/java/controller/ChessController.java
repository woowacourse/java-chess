package controller;

import controller.command.Command;
import controller.command.EndOnCommand;
import db.PieceDao;
import db.TurnDao;
import domain.board.ChessBoard;
import domain.board.ChessBoardFactory;
import domain.dto.PieceDto;
import domain.piece.Color;
import domain.piece.Piece;
import domain.position.Position;
import view.InputView;
import view.OutputView;

public class ChessController {
    private final InputView inputView;
    private final OutputView outputView;

    public ChessController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        PieceDao pieceDao = new PieceDao();
        TurnDao turnDao = new TurnDao();

        outputView.printGameGuideMessage();
        final ChessBoard board = createChessBoard(pieceDao, turnDao);

        Command command = readStartCommandUntilValid();
        while (command.isNotEnded()) {
            command.execute(board, outputView);
            command = readCommandIfGameNotEnded(board);
        }

        pieceDao.deleteAll();
        for (var positionAndPiece : board.getBoard().entrySet()) {
            Position position = positionAndPiece.getKey();
            Piece piece = positionAndPiece.getValue();
            pieceDao.add(PieceDto.of(position, piece));
        }
        turnDao.update(board.getTurn().name());
    }

    private ChessBoard createChessBoard(PieceDao pieceDao, TurnDao turnDao) {
        if (pieceDao.count() != 0) {
            String color = turnDao.find();
            return ChessBoardFactory.loadPreviousChessBoard(pieceDao.findAll(), Color.valueOf(color));
        }
        return ChessBoardFactory.createInitialChessBoard();
    }

    private Command readStartCommandUntilValid() {
        try {
            return inputView.readStartCommand();
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
            return readStartCommandUntilValid();
        }
    }

    private Command readCommandIfGameNotEnded(final ChessBoard board) {
        if (board.isKingNotExist()) {
            return new EndOnCommand();
        }
        return readCommandUntilValid();
    }

    private Command readCommandUntilValid() {
        try {
            return inputView.readCommand();
        } catch (Exception e) {
            outputView.printErrorMessage(e.getMessage());
            return readCommandUntilValid();
        }
    }
}
