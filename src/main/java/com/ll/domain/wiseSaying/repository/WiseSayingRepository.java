package com.ll.domain.wiseSaying.repository;

import com.ll.domain.wiseSaying.entity.WiseSaying;

import java.util.List;
import java.util.Optional;

public abstract class WiseSayingRepository {
    public abstract WiseSaying save(WiseSaying wiseSaying);

    public abstract List<WiseSaying> findAll();

    public abstract boolean deleteById(int id);

    public abstract Optional<WiseSaying> findById(int id);
}
