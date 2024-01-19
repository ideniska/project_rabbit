package com.project_rabbit.Epic;

import java.util.Optional;
import java.util.List;

public interface EpicService {
    Integer createEpic(Epic epic);

    Optional<Epic> findEpic(Integer epicId);

    Epic updateEpic(Integer epicId,
            Epic epic);

    List<Epic> getAllEpics();

    void deleteEpic(Integer epic);
}
