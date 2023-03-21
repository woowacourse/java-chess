import controller.ChessController;

public class Application {
    // TODO: 2023/03/17 기물 타겟까지만 리스트 뽑아야됨 
    // TODO: 2023/03/17 move 중간에 continue 빼는거
    // TODO: 2023/03/21 rook 안움직임
    public static void main(String[] args) {
        new ChessController().run();
    }
}
