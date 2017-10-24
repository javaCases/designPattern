package com.eblly.builderPattern;

/**
 * builder模式:遇到多个构造器参数时，
 * 一：采用重叠构造器的方法虽然可行，但是当有很多参数时客户端代码很难编写，并且代码难以阅读，
 * 还会容易颠倒其中两个参数的顺序(有可能不会出错)。
 * 二：采用javaBeans，缺點是構造過程中被分到幾個調用中，在構造過程中javaBean
 * 可能處於不一致的狀態，......將會導致失敗。---例如：被多线程调用
 * 三：采用Builder模式。——Effective java 第二版 第9页
 * <p>
 * 情景：车的轮子，颜色，长度等是一辆车必须的属性。而车的某些属性是可选的，例如：车内的挂件和音响（这些是由车主决定的）。
 *
 * @author eblly
 * @version 1.0
 * @date Aug 11, 2015
 **/
public class Car {
    // 必须属性
    private String color;
    private long height;
    private long width;

    // 可选属性
    private String widget;
    private String audio;

    public Car(CarBuilder carBuilder) {
        color = carBuilder.color;
        height = carBuilder.height;
        width = carBuilder.width;

        widget = carBuilder.widget;
        audio = carBuilder.audio;
    }


    public static class CarBuilder {
        // 必须属性
        private String color;
        private long height;
        private long width;

        // 可选属性
        private String widget;
        private String audio;

        public CarBuilder(String color, long height, long width) {
            this.color = color;
            this.height = height;
            this.width = width;
        }

        public CarBuilder setWidget(String widget) {
            this.widget = widget;
            return this;
        }

        public CarBuilder setAudio(String audio) {
            this.audio = audio;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }

    public static void main(String[] args) {
        Car car = new Car.CarBuilder("red", 11l, 12l).setAudio("漫步者1号").setWidget("平安符").build();

        System.out.println(car);
    }
}
