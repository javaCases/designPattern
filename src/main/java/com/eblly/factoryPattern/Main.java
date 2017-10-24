package com.eblly.factoryPattern;

/**
 * FactoryPattern:根據輸入對應的圖形名稱生成對應的圖形.
 * 类似Abstract Facroty Pattern
 *
 * @author eblly
 * @version 1.0
 * @date Aug 12, 2015
 **/
interface Shape {
    void paint();
}

/**
 * 矩形
 */
class Rectangle implements Shape {
    public void paint() {
        System.out.println("我是矩形!");
    }
}

/**
 * 圓形
 */
class Circle implements Shape {
    public void paint() {
        System.out.println("我是圓形!");
    }
}

/**
 * ShapeFactory
 */
class ShapeFactory {
    public Shape createShape(String name) {
        if (name.equalsIgnoreCase("circle")) {
            return new Circle();
        } else if (name.equalsIgnoreCase("rectangle")) {
            return new Rectangle();
        }
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        String shapeName = "rectangle";
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape = shapeFactory.createShape(shapeName);
        shape.paint();
    }
}
