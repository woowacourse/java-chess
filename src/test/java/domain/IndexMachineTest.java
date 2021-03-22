//package domain;
//
//import domain.position.Position;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//class IndexMachineTest {
//    @DisplayName("a3를 (0, 5)로 변환 가능하다.")
//    @Test
//    void index_machine_test() {
//        Assertions.assertThat(IndexMachine.convertPosition("a3")).isEqualTo(Position.Of(0, 5));
//        Assertions.assertThat(IndexMachine.convertPosition("c5")).isEqualTo(Position.Of(2, 3));
//    }
//}