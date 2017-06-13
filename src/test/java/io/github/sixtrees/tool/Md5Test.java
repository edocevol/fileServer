package io.github.sixtrees.tool;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2017/6/13 0013.
 */
public class Md5Test {
    @Test
    public void md5() throws Exception {
        Assert.assertEquals(Md5.md5("1"),Md5.md5("1"));
    }

}