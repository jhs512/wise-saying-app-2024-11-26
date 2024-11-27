package com.ll.standard.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonUtilTest {
    @Test
    @DisplayName("맵을 JSON으로 바꿀 수 있다.(필드 1개)")
    public void t1() {
        // given
        Map<String, Object> map = new HashMap<>();
        map.put("name", "이름");

        // when
        String jsonStr = Util.json.toString(map);

        // then
        assertThat(jsonStr).isEqualTo("""
                {
                    "name": "이름"
                }
                """.stripIndent().trim());
    }

    @Test
    @DisplayName("맵을 JSON으로 바꿀 수 있다.(필드 2개)")
    public void t2() {
        // given
        Map<String, Object> map = new HashMap<>();
        map.put("name", "이름");
        map.put("gender", "남자");

        // when
        String jsonStr = Util.json.toString(map);

        // then
        assertThat(jsonStr).isEqualTo("""
                {
                    "name": "이름",
                    "gender": "남자"
                }
                """.stripIndent().trim());
    }
}
