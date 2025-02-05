package com.dam.model;

import java.io.IOException;

public class BatchCommand implements Command{
    private String file;

    @Override
    public void execute() {
        ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "start", file, "exit");
        try {
            pb.start();
        } catch (IOException e) {
            System.err.println("Error: "+e.getMessage());
            e.printStackTrace();
        }
    }

    public BatchCommand(String file) {
        this.file = file;
    }
}
