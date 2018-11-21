package com.ava;

import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.Test;

import java.net.ServerSocket;
import java.net.Socket;

public class EventBusTest {

    @Test
    public void testEventBus() {
        EventBus eventBus = new EventBus("TestEvent");
        EventListener eventListener = new EventListener();
        eventBus.register(eventListener);
        eventBus.post(new TestEvent("the first message info coming..."));
        eventBus.post(new TestEvent("the second message info coming..."));
        System.out.println(eventListener.getLastMessage());
    }
    @Test
    public void testMultipleEvents() {
        EventBus eventBus = new EventBus("MultipleEvents");
        MultipleListener multipleListener = new MultipleListener();
        eventBus.register(multipleListener);
        eventBus.post(new Integer(100));
        eventBus.post(new Integer(200));
        eventBus.post(new Long(1000));
        eventBus.post(new Long(2000));
        System.out.println(multipleListener.getLastInteger());
        System.out.println(multipleListener.getLastLong());
    }

    @Test
    public void testDeadEventListener() {
        EventBus eventBus = new EventBus("DeadEvent");
        DeadEventListener deadEventListener = new DeadEventListener();
        eventBus.register(deadEventListener);

        eventBus.post(new TestEvent("never arrive"));
        eventBus.post(new TestEvent("no arrival"));
        System.out.println(deadEventListener.isNotDelivered());
    }
    @Test
    public void testEventFromSubClass() {
        EventBus eventBus = new EventBus("EventFromSubClass");
        NumberListener numberListener = new NumberListener();
        IntegerListener integerListener = new IntegerListener();
        eventBus.register(numberListener);
        eventBus.register(integerListener);

        eventBus.post(new Integer(100));
        System.out.println(numberListener.getLastMessage());
        System.out.println(integerListener.getLastMessage());
        eventBus.post(new Long(1000L));
        System.out.println(numberListener.getLastMessage());
        System.out.println(integerListener.getLastMessage());
    }

    public static void main(String[] args) {
        EventBus channel = new EventBus();
        ServerSocket socket;
        try {
            socket = new ServerSocket(10080);
            while (true) {
                Socket connection = socket.accept();
                EventBusThread eventBusThread = new EventBusThread(connection, channel);
                channel.register(eventBusThread);
                eventBusThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * 消息封装类
 */
class TestEvent {
    private final String message;
    public TestEvent(String message) {
        this.message = message;
        System.out.println(message);
    }

    public String getMessage() {
        return message;
    }
}

/**
 * 消息接受类
 */
class EventListener {
    private String lastMessage = "";

    @Subscribe
    public void listen(TestEvent testEvent) {
        lastMessage = testEvent.getMessage();
        System.out.println("receive: "+lastMessage);
    }

    public String getLastMessage() {
        return lastMessage;
    }
}

/**
 * 多通道消息订阅
 */
class MultipleListener {
    private Integer lastInteger;
    private Long lastLong;

    @Subscribe
    public void listenInteger(Integer event) {
        lastInteger = event;
        System.out.println("listen: "+lastInteger);
    }
    @Subscribe
    public void listenLong(Long event) {
        lastLong = event;
        System.out.println("listen: "+lastLong);
    }

    public Integer getLastInteger() {
        return lastInteger;
    }

    public Long getLastLong() {
        return lastLong;
    }
}

/**
 * 不推送消息
 */
class DeadEventListener {  //EventBus发送的消息都不是订阅者关心的则是Dead Event
    private boolean notDelivered = false;
    @Subscribe
    public void listen(DeadEvent event) {
        notDelivered = true;
    }

    public boolean isNotDelivered() {
        return notDelivered;
    }
}

/**
 * Event继承：Listener A 监听Event A,Event B是Event A的子类，则Listener将同时接受Event A和B的消息
 */
class NumberListener {
    private Number lastMessage;
    @Subscribe
    public void listen(Number number) {
        this.lastMessage = number;
        System.out.println("listen: "+lastMessage);
    }

    public Number getLastMessage() {
        return lastMessage;
    }
}

class IntegerListener {
    private Integer lastMessage;
    @Subscribe
    public void listen(Integer integer) {
        lastMessage = integer;
        System.out.println("listen: "+lastMessage);
    }

    public Integer getLastMessage() {
        return lastMessage;
    }
}