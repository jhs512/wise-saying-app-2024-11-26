package com.ll;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CommandTest {
    @Test
    public void t1() {
        Command cmd = new Command("삭제?id=10");
        assertThat(cmd.getActionName()).isEqualTo("삭제");
    }

    @Test
    public void t2() {
        Command cmd = new Command("삭제?id=10");
        assertThat(cmd.getParam("id")).isEqualTo("10");
    }

    @Test
    public void t3() {
        Command cmd = new Command("삭제?id=10");
        assertThat(cmd.getParam("number")).isNull();
    }
}
