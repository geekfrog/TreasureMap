package gg.frog.mc.treasuremap.utils;

import java.text.MessageFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

import gg.frog.mc.treasuremap.config.LangCfg;

public class StrUtil {

    private static final String dfs = "yyyy/MM/dd HH:mm:ss";
    private static final long dt = 24 * 60 * 60 * 1000L;
    private static final long ht = 60 * 60 * 1000L;
    private static final long mt = 60 * 1000L;

    public static String messageFormat(String src, Object... args) {
        return MessageFormat.format(src, args).replace("&", "§").replace("\\n", "\n");
    }

    public static String timestampToString(long time) {
        return DateFormatUtils.format(new Date(time), dfs);
    }

    public static String dateToString(Date d) {
        return DateFormatUtils.format(d, dfs);
    }

    public static String nowTimeString() {
        return DateFormatUtils.format(new Date(), dfs);
    }

    public static String getLeftTime(long time) {
        long leftTime = time - new Date().getTime();
        long d = leftTime / dt;
        long h = (leftTime % dt) / ht;
        long m = (leftTime % ht) / mt;
        return messageFormat(LangCfg.LEFT_TIME, d, LangCfg.TIME_UNIT_D, h, LangCfg.TIME_UNIT_H, m, LangCfg.TIME_UNIT_M);
    }

}
