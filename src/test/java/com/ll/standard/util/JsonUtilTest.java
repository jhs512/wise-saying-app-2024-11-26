package com.ll.standard.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
        Map<String, Object> map = new LinkedHashMap<>();
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


    @Test
    @DisplayName("맵을 JSON으로 바꿀 수 있다.(숫자(정수) 필드)")
    public void t3() {
        // given
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", 1);
        map.put("name", "이름");
        map.put("gender", "남자");

        // when
        String jsonStr = Util.json.toString(map);

        // then
        assertThat(jsonStr).isEqualTo("""
                {
                    "id": 1,
                    "name": "이름",
                    "gender": "남자"
                }
                """.stripIndent().trim());
    }

    @Test
    @DisplayName("맵을 JSON으로 바꿀 수 있다.(숫자(실수) 필드)")
    public void t4() {
        // given
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", 1);
        map.put("name", "이름");
        map.put("gender", "남자");
        map.put("height", 178.1543221);

        // when
        String jsonStr = Util.json.toString(map);

        // then
        assertThat(jsonStr).isEqualTo("""
                {
                    "id": 1,
                    "name": "이름",
                    "gender": "남자",
                    "height": 178.1543221
                }
                """.stripIndent().trim());
    }

    @Test
    @DisplayName("맵을 JSON으로 바꿀 수 있다.(논리 필드)")
    public void t5() {
        // given
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", 1);
        map.put("name", "이름");
        map.put("gender", "남자");
        map.put("height", 178.1543221);
        map.put("married", true);

        // when
        String jsonStr = Util.json.toString(map);

        // then
        assertThat(jsonStr).isEqualTo("""
                {
                    "id": 1,
                    "name": "이름",
                    "gender": "남자",
                    "height": 178.1543221,
                    "married": true
                }
                """.stripIndent().trim());
    }

    @Test
    @DisplayName("JSON to Map(필드 1개)")
    public void t6() {
        // given
        String jsonStr = """
                {
                    "name": "이름",
                }
                """.stripIndent().trim();

        // when
        Map<String, Object> map = Util.json.toMap(jsonStr);

        // then
        assertThat(map).containsEntry("name", "이름");
    }

    @Test
    @DisplayName("JSON to Map(필드 2개)")
    public void t7() {
        // given
        String jsonStr = """
                {
                    "name": "이름",
                    "gender": "남자"
                }
                """.stripIndent().trim();

        // when
        Map<String, Object> map = Util.json.toMap(jsonStr);

        // then
        assertThat(map)
                .containsEntry("name", "이름")
                .containsEntry("gender", "남자");
    }

    @Test
    @DisplayName("JSON to Map(숫자필드(정수))")
    public void t8() {
        // given
        String jsonStr = """
                {
                    "id": 1,
                    "name": "이름",
                    "gender": "남자"
                }
                """.stripIndent().trim();

        // when
        Map<String, Object> map = Util.json.toMap(jsonStr);

        // then
        assertThat(map)
                .containsEntry("id", 1)
                .containsEntry("name", "이름")
                .containsEntry("gender", "남자");
    }

    @Test
    @DisplayName("JSON to Map(숫자필드(실수))")
    public void t9() {
        // given
        String jsonStr = """
                {
                    "id": 1,
                    "name": "이름",
                    "gender": "남자",
                    "height": 178.1543221
                }
                """.stripIndent().trim();

        // when
        Map<String, Object> map = Util.json.toMap(jsonStr);

        // then
        assertThat(map)
                .containsEntry("id", 1)
                .containsEntry("name", "이름")
                .containsEntry("gender", "남자")
                .containsEntry("height", 178.1543221);
    }

    @Test
    @DisplayName("JSON to Map(논리필드)")
    public void t10() {
        // given
        String jsonStr = """
                {
                    "id": 1,
                    "name": "이름",
                    "gender": "남자",
                    "height": 178.1543221,
                    "married": false
                }
                """.stripIndent().trim();

        // when
        Map<String, Object> map = Util.json.toMap(jsonStr);

        // then
        assertThat(map)
                .containsEntry("id", 1)
                .containsEntry("name", "이름")
                .containsEntry("gender", "남자")
                .containsEntry("height", 178.1543221)
                .containsEntry("married", false);
    }
}
