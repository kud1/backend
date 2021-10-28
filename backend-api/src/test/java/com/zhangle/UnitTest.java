package com.zhangle;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

public class UnitTest {

    @Test
    void testMd5() {
        String password = "1";
        System.out.println(new String(DigestUtils.md5Hex(password)));
    }
}
