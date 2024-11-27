package com.ll;

import java.util.HashMap;
import java.util.Map;

public class Command {
    private final String actionName;
    private final Map<String, String> params;

    public Command(String cmd) {
        this.params = new HashMap<>();
        String[] cmtBits = cmd.trim().split("\\?");
        this.actionName = cmtBits[0];

        String queryString = cmtBits[1];

        String[] params = queryString.split("&");

        for (String param : params) {
            String[] paramBits = param.split("=", 2);
            this.params.put(paramBits[0], paramBits[1]);
        }
    }

    public String getActionName() {
        return actionName;
    }

    public String getParam(String key) {
        return params.get(key);
    }
}
