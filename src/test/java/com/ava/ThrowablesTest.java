package com.ava;

import com.google.common.base.Throwables;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class ThrowablesTest {

    @Test
    public void testThrowables() {
        try {
            throw new Exception();
        } catch (Throwable t) {
            String stackTrace = Throwables.getStackTraceAsString(t);
            System.out.println(stackTrace);
            Throwables.propagate(t);//传播繁衍异常
            Throwables.getRootCause(t);
            Throwables.getCausalChain(t);//异常链
        }
    }

    @Test
    public void call() throws IOException {
        try {
            throw new IOException();
        } catch (Throwable t) {
            //void propagateIfInstanceOf(Throwable, Class<X extends Exception>) throws X
            // 仅当是所属异常的实例时，传递Throwable
            Throwables.propagateIfInstanceOf(t, IOException.class);
            throw Throwables.propagate(t);
        }
    }

    @Test
    public void testCheckException() {
        try {
            URL url = new URL("http://www.baidu.com");
            final InputStream inputStream = url.openStream();
            byte buf[] = new byte[1024];
            int len = 0;
            StringBuilder stringBuilder = new StringBuilder();
            while((len = inputStream.read(buf))!=-1) {
                stringBuilder.append(new String(buf));
            }
            System.out.println(stringBuilder);
            inputStream.close();
        } catch (Throwable t) {
            //RuntimeException propagate(Throwable)
            // 把Throwable封装成RuntimeException,用于传递异常
            throw Throwables.propagate(t);
        }
    }

    @Test
    public void testPropagateIfPossible() throws Exception {
        try {
            throw new Exception();
        } catch (Throwable t) {
            //void propagateIfPossible(Throwable): 当且仅当T是一个RuntimeException和error时，传递Throwable
            //void propagateIfPossible(Throwable, Class<X extends Throwable>) throws X：当且仅当它是一个RuntimeException和Error时，或者是一个X的实例时，传递throwable
            Throwables.propagateIfPossible(t,Exception.class);
            Throwables.propagate(t);
        }
    }
}
