package chess.view;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.chesspiece.ChessPiece;

public class OutputView {
    public static void printBoard(ChessBoard chessBoard){
        for(int i=8;i>=1;i--){
            for(char j='a' ;j<='h'; j++){
                ChessPiece chessPiece = chessBoard.checkPosition(new Position(i, j));
                if( chessPiece != null){
                    if(chessPiece.isBlack()){
                        System.out.print(chessPiece.getName().toUpperCase());
                    }
                    if(!chessPiece.isBlack()){
                        System.out.print(chessPiece.getName());
                    }
                }
                if(chessPiece == null){
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
}
