package com.project_rabbit.Epic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EpicServiceImpl implements EpicService {

    private final EpicRepository epicRepository;

    @Override
    public Integer createEpic(Epic epic) {
        return epicRepository.save(epic).getEpicId();
    }

    @Override
    public Optional<Epic> findEpic(Integer epicId) {
        return epicRepository.findById(epicId);
    }

    @Override
    public List<Epic> getAllEpics() {
        return epicRepository.findAll();
    }

    @Override
    public Epic updateEpic(Integer epicId, Epic epic) {
        Epic theEpic = epicRepository.findById(epicId).stream().findFirst().orElseThrow(
                () -> new RuntimeException(String.format("epic with id %s does not exist",
                        epic.getEpicId())));

        theEpic.setTitle(epic.getTitle());
        theEpic.setDescription(epic.getDescription());
        theEpic.setDueDate(epic.getDueDate());
        theEpic.setProjectId(epic.getProjectId());
        theEpic.setStatus(epic.getStatus());
        theEpic.setAssignedTo(epic.getAssignedTo());

        return epicRepository.save(theEpic);
    }

    @Override
    public void deleteEpic(Integer epicId) {
        Epic theEpic = epicRepository.findById(epicId).get();
        if (theEpic == null) {
            throw new RuntimeException(String.format("The epic with id %s does not exist",
                    epicId));
        }
        epicRepository.delete(theEpic);
    }
}
