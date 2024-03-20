package controller;

import java.util.List;
import model.ChessBoard;
import view.OutputView;
import view.dto.InfoMapper;
import view.dto.PieceInfo;

public class Controller {

    public void execute(){
        ChessBoard chessBoard = new ChessBoard();
        List<PieceInfo> pieceInfos = InfoMapper.toPieceInfoMapper(chessBoard);
        OutputView outputView = new OutputView();
        outputView.printChessBoard(pieceInfos);
    }
}
