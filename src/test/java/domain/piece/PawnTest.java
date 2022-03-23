package domain.piece;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PawnTest {

    @Test
    @DisplayName("Pawn은 앞으로 한칸 이동할 수 있다.")
    void movePawnOneSpace(){
//        Piece piece = new Pawn(Player.WHITE);

        // target == null
//        assertThat(((Pawn)piece).avaliableMove(new Position(Row.TWO, Column.B), new Position(Row.THREE, Column.B)))
//                .isEqualTo(true);

        //chessBoard.mov//
        // 앞으로 한칸, 처음 2칸
//        piece.calculatePositivePosition(currentPisition);
//        @Test
//        @DisplayName("Ready 상태에서 한장만 입력했을 경우")
//        void ready() {
//            final State state = new Ready().draw(SPADE_JACK);
//
//            assertThat(state).isInstanceOf(Ready.class);
//        }
//        (board) >> pawn.ddd(position) -> available position return
        // board{
        //   list can, can`t
        //}

        // Player, Unit getter getPlayer getUnit
        // pawn  List<> == 1, 2
        // king  => currentPosition c3  =>  b4 c4 d4 b3 d3 b2 c2 d2

        // 각 Piece <- move A현재위치 B도착
        // 1. board <- List<Position> positions = calcul(A)  // pawn

        // board
        // 2. List<Position> positions can/can't  // Piece 별로 -> List<Position> newPositions (정말 갈 수 있는 곳 체크 공격/이동)
        // 3. newPositions.contains(B) -> 검증하겠다. -> true/false

        //
//        source.validateMove(source, target, isExist(target))
//        if (player == Player.BLACK)
//            blackList
//        if (player == Player.WHITE)
//            whileList
/*
2. board는 source/target을 통해 검증
    2-1	- 자신의 턴인지
    2-2	- source != null
    2-3	- source / target 서로다른 팀

    2-4	- 지나가는길 검증
            - knight일때 -> 검증이 필요없다.
            - 나머지 -> 검증

    2-5	- positions에 target이 있는지 -> source.validateMove(target)  -> true/false
            // target, boolean(target == null)
            // Pawn일때, 직진 -> target == null
                    대각선 -> target != null
            - return positions.contains(target)
 */
    }
}
