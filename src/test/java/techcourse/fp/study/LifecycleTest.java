package techcourse.fp.study;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LifecycleTest {

    // initAll -> init -> anyTest -> teatDown -> init -> someTest -> teatDown -> tearDownAll

    @BeforeAll // JUnit 4의 @BeforeClass
    static void initAll() {
        System.out.println("initAll");
    }

    @BeforeEach
        // JUnit 4의 @Before
    void init() {
        System.out.println("init");
    }

    @Test
    void someTest() {
        System.out.println("someTest");
    }

    @Test
    void anyTest() {
        System.out.println("anyTest");
    }

    @AfterEach
        // JUnit 4의 @After
    void tearDown() {
        System.out.println("tearDown");
    }

    @AfterAll // JUnit 4의 @AfterClass
    static void tearDownAll() {
        System.out.println("tearDownAll");
    }
}
