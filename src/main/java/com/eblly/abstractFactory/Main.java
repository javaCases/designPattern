package com.eblly.abstractFactory;

/**
 * 抽象工厂模式
 * 场景:当启动软件时需要生成与操作系统风格对应的界面，而这界面暂且假设只有Label，Button两个组件。
 * @author eblly
 * @date Aug 8, 2015
 * @version 1.0
 **/

interface Label {
    void paint();
}

interface Button {
    void panint();
}

interface GUIFactory {
    Label createLabel();

    Button createButton();
}

/**
 * WIN operation system
 */
class WINLabel implements Label {
    public void paint() {
        System.out.println("I am WIN label!");
    }
}

class WINButton implements Button {
    public void panint() {
        System.out.println("I am WIN button!");
    }
}

class WINGUIFactory implements GUIFactory {
    public Label createLabel() {
        return new WINLabel();
    }

    public Button createButton() {
        return new WINButton();
    }
}

/**
 * Linux operation system
 */
class LINUXLabel implements Label {
    public void paint() {
        System.out.println("I am LINUX label!");
    }
}

class LINUXButton implements Button {
    public void panint() {
        System.out.println("I am LINUX button!");
    }
}

class LINUXGUIFactory implements GUIFactory {
    public Label createLabel() {
        return new LINUXLabel();
    }

    public Button createButton() {
        return new LINUXButton();
    }
}

/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
        String system = "WIN";
        GUIFactory factory = null;
        if ("LINUX".equals(system)) {
            factory = new LINUXGUIFactory();
        } else if ("WIN".equals(system)) {
            factory = new WINGUIFactory();
        }
        Label label = factory.createLabel();
        Button button = factory.createButton();
        label.paint();
        button.panint();
    }
}
