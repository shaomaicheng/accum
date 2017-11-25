package shaomai.app;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import shaomai.app.middle.network.ParamsUtilsKt;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void randomSaltTest() {
        String randomSlat = ParamsUtilsKt.randomSalt();
        System.out.println(randomSlat);
    }


    @Test
    public void md5Test() {
        String md5 = ParamsUtilsKt.getMd5("hello,world");
        System.out.println(md5);
    }

    @Test
    public void mergeParamsTest() {
        HashMap<String,String> map = new HashMap<>();
        map = ParamsUtilsKt.mergeParams(map, "hello");
        for (Map.Entry<String,String> entry: map.entrySet()) {
            System.out.println("key: " + entry.getKey() + "=> value: " + entry.getValue());
        }
    }
}