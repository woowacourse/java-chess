package techcourse.fp.study;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

public class SomeTest {

    // 순서: test1 -> test2
    // junit5로 오면서 없어진 것으로 알고 있다...?

    @Before
    public void setup() {
        System.out.println("setup");
    }

    @Test
    public void test1() throws Exception {
        System.out.println("test1");
    }

    @Test
    public void test2() throws Exception {
        System.out.println("test2");
    }

    @After
    public void teardown() {
        System.out.println("teardown");
    }
}
