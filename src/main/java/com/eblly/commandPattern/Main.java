package com.eblly.commandPattern;


/** The Command interface */
interface Command {
    void execute();
}

/** The Invoker class */
class Switch {
    public void storeAndExecute(Command cmd) {
        cmd.execute();
    }
}

/** The Receiver class */
class Light {
    public void turnOn() {
        System.out.println("The light is on");
    }

    public void turnOff() {
        System.out.println("The light is off");
    }
}

/** The Command for turning on the light - ConcreteCommand #1 */
class FlipUpCommand implements Command {
    private Light theLight;

    public FlipUpCommand(Light light) {
        this.theLight = light;
    }

    @Override
    public void execute() {
        theLight.turnOn();
    }
}

/** The Command for turning off the light - ConcreteCommand #2 */
class FlipDownCommand implements Command {
    private Light theLight;

    public FlipDownCommand(Light light) {
        this.theLight = light;
    }

    @Override
    public void execute() {
        theLight.turnOff();
    }
}

/* The test class or client */
public class Main {
    public static void main(String[] args1) {
        Light lamp = new Light();
        Command switchUp = new FlipUpCommand(lamp);
        Command switchDown = new FlipDownCommand(lamp);
        Switch mySwitch = new Switch();
        String[] args = new String[] { "ON", "222" };
//		String[] args = new String[] { "OFF", "222" };
        switch (args[0]) {
            case "ON":
                mySwitch.storeAndExecute(switchUp);
                break;
            case "OFF":
                mySwitch.storeAndExecute(switchDown);
                break;
            default:
                System.err.println("Argument \"ON\" or \"OFF\" is required.");
                System.exit(-1);
        }
    }
}