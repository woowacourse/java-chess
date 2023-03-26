package controller.command;

import dao.ChessBoardDao;
import domain.chessGame.ChessBoard;
import domain.position.Position;
import view.OutputView;

import java.util.List;

public class Move extends GameCommand {

    private final List<String> commandInput;

    protected Move(ChessBoardDao chessBoardDao, List<String> commandInput) {
        super(chessBoardDao);
        this.commandInput = commandInput;
    }

    @Override
    public Command execute() {
        ChessBoard chessBoard = chessBoardDao.select();
        executeMove(chessBoard);
        chessBoardDao.update(chessBoard);

        if (chessBoard.isGameEnded()) {
            return new GameEnd(chessBoardDao);
        }
        return readNextCommand();
    }

    private void executeMove(ChessBoard chessBoard) {
        try {
            validateCommandInputSize();
            movePiece(chessBoard);
        } catch (IllegalArgumentException e) {
            OutputView.printExceptionMessage(e.getMessage());
        }
    }

    private void validateCommandInputSize() {
        if (commandInput.size() != 3) {
            throw new IllegalArgumentException("[ERROR] Move 명령어 입력이 적합한 형식이 아닙니다.");
        }
    }

    private void movePiece(ChessBoard chessBoard) {
        Position start = Position.of(commandInput.get(1));
        Position end = Position.of(commandInput.get(2));

        chessBoard.movePiece(start, end);
        OutputView.printChessBoard(Position.getAllPosition(), chessBoard.getChessBoard());
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
