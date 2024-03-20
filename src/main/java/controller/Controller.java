package controller;

import static util.InputRetryHelper.inputRetryHelper;
import static view.OutputView.*;

import java.util.List;
import model.ChessBoard;
import view.GameCommand;
import view.InputView;
import view.dto.InfoMapper;
import view.dto.PieceInfo;

public class Controller {

    public void execute(){
        printInitialGamePrompt();
        while(inputRetryHelper(InputView::inputGameCommand) != GameCommand.END) {
            ChessBoard chessBoard = new ChessBoard();
            List<PieceInfo> pieceInfos = InfoMapper.toPieceInfoMapper(chessBoard);
            printChessBoard(pieceInfos);
        }
    }
}
