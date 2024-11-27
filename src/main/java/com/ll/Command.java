package com.ll;

public class Command {
    private final String actionName;

    public Command(String cmd) {
        String[] cmtBits = cmd.trim().split("\\?");
        this.actionName = cmtBits[0];
    }

    public String getActionName() {
        return actionName;
    }
}
