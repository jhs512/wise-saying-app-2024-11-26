package com.ll.domain.wiseSaying.repository;

import com.ll.domain.wiseSaying.entity.WiseSaying;
import com.ll.standard.util.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WiseSayingFileRepository implements WiseSayingRepository {
    private int lastId;

    public static String getTableDirPath() {
        return "db/test/wiseSaying";
    }

    public static String getRowFilePath(int id) {
        return getTableDirPath() + "/" + id + ".json";
    }

    public WiseSaying save(WiseSaying wiseSaying) {
        if (!wiseSaying.isNew()) {
            return wiseSaying;
        }

        wiseSaying.setId(
                findAll().size() + 1
        );

        Map<String, Object> wiseSayingMap = wiseSaying.toMap();
        String jsonStr = Util.json.toString(wiseSayingMap);

        Util.file.set(getRowFilePath(wiseSaying.getId()), jsonStr);

        return wiseSaying;
    }

    public List<WiseSaying> findAll() {
        try {
            return Files.walk(Path.of(getTableDirPath()))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().matches("\\d+\\.json"))
                    .map(path -> Util.file.get(path.toString(), ""))
                    .map(jsonString -> Util.json.toMap(jsonString))
                    .map(map -> new WiseSaying(map))
                    .toList();
        } catch (NoSuchFileException e) {
            return List.of();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteById(int id) {
        return Util.file.delete(getRowFilePath(id));
    }

    public Optional<WiseSaying> findById(int id) {
        String filePath = getRowFilePath(id);

        if (Util.file.notExists(filePath)) {
            return Optional.empty();
        }

        String jsonStr = Util.file.get(filePath, "");

        if (jsonStr.isEmpty()) {
            return Optional.empty();
        }

        Map<String, Object> wiseSayingMap = Util.json.toMap(jsonStr);

        return Optional.of(new WiseSaying(wiseSayingMap));
    }
}
