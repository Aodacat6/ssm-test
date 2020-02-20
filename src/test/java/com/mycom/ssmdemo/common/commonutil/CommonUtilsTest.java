package com.mycom.ssmdemo.common.commonutil;

//import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-19 上午 10:55
 * @description：
 * @modified By：
 * @version: $
 */
class CommonUtilsTest {

    @Test
    void isMobile() {
        CommonUtils commonUtils = new CommonUtils();
        boolean b = commonUtils.isMobile("17660635488");
        assertEquals(true, b);
    }

    @Test
    void isNumberic() {
        CommonUtils commonUtils = new CommonUtils();
        boolean b = commonUtils.isMobile("17660635488");
        assertEquals(true, b);
    }
}