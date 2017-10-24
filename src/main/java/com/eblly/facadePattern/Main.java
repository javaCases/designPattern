package com.eblly.facadePattern;


/* Complex parts */

class CPU {
    public void freeze() {
    }

    public void jump(long position) {
    }

    public void execute() {
    }
}

class Memory {
    public void load(long position, byte[] data) {
    }
}

class HardDrive {
    public byte[] read(long lba, int size) {
        return null;
    }
}

/* Facade */

class ComputerFacade {
    private CPU processor;
    private Memory ram;
    private HardDrive hd;

    public ComputerFacade() {
        this.processor = new CPU();
        this.ram = new Memory();
        this.hd = new HardDrive();
    }

    public void start() {
        processor.freeze();
        ram.load(0l, hd.read(0l, 0));
        processor.jump(0l);
        processor.execute();
    }
}

/* Client */

class You {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.start();
    }
}