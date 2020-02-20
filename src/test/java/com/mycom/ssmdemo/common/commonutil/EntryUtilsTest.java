package com.mycom.ssmdemo.common.commonutil;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ：damiaokuaipao
 * @date ：Created in 2020-02-19 下午 12:11
 * @description：
 * @modified By：
 * @version: $
 */
class EntryUtilsTest {

    @Test
    void encrypt() throws Exception {
        EntryUtils entryUtils = new EntryUtils();
        String enStr = entryUtils.encrypt("112233");
        System.out.println(enStr);
    }

    @Test
    void decrypt() throws Exception {
        EntryUtils entryUtils = new EntryUtils();
        String enStr = entryUtils.decrypt("mtA92tSZyvk=");
        System.out.println(enStr);
    }
}