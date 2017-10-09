package com.jagregory.shiro.freemarker;

import java.io.IOException;
import java.io.Writer;

public class ShiroInfoFilterWriter extends Writer {
    private final Writer out;
    private boolean isShow;
    private String p;

    ShiroInfoFilterWriter(Writer out, String p, boolean isShow) {
        this.out = out;
        this.isShow = isShow;
        this.p = p;
    }

    public void write(char[] cbuf, int off, int len) throws IOException {
        String prefix = "<!-- shiro(" + this.isShow + "):" + this.p + " -->";
        char[] prefixChars = prefix.toCharArray();
        char[] transformedCbuf = prefixChars;
        if (isShow) {
            transformedCbuf = new char[prefixChars.length + len];
            System.arraycopy(prefixChars, 0, transformedCbuf, 0, prefixChars.length);
            System.arraycopy(cbuf, off, transformedCbuf, prefixChars.length, len);
        }

        out.write(transformedCbuf);
    }

    public void flush() throws IOException {
        out.flush();
    }

    public void close() throws IOException {
        out.close();
    }
}
